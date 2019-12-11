package br.well.movies.view.controller

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import br.well.moviedbservice.api.model.Movies
import br.well.moviedbservice.api.movie.MovieDataSource
import br.well.movies.common.provider.ImmediateSchedulerProvider
import br.well.movies.ui.usecase.MovieUseCase
import br.well.movies.ui.view.controller.MoviesController
import br.well.movies.ui.view.controller.MoviesViewContract
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import rx.Single

class MoviesControllerTest {

    private val viewContractMock = mock<MoviesViewContract>()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    companion object {
        const val ERROR_MESSAGE = "Erro ao carregar a lista"

    }

    private val movieDataSourceMock = mock<MovieDataSource>()
    private val moviesMock = Movies(arrayListOf())

    lateinit var SUT: MoviesController

    @Before
    fun setup() {
        val lifecycleMock = LifecycleRegistry(Mockito.mock(LifecycleOwner::class.java))
        lifecycleMock.handleLifecycleEvent(Lifecycle.Event.ON_START)
        val useCase = MovieUseCase(movieDataSourceMock, ImmediateSchedulerProvider())
        SUT = MoviesController(useCase, lifecycleMock)
        SUT.onCreate(viewContractMock)
    }

    @Test
    fun onStart_fetchMovies_success() {
        //Arrange
        `when`(movieDataSourceMock.movies(1)).thenReturn(Single.just(moviesMock))
        //Act
        SUT.onStart()
        //Assert
        verify(viewContractMock).showLoading()
        verify(viewContractMock).bindMovies(moviesMock)
        verify(viewContractMock).hideLoading()
    }

    @Test
    fun onStart_fetchMovies_failed() {
        //Arrange
        val captor = argumentCaptor<() -> Unit>()
        `when`(movieDataSourceMock.movies(1)).thenReturn(Single.error(RuntimeException(ERROR_MESSAGE)))
        //Act
        SUT.onStart()
        //Assert
        verify(viewContractMock).showLoading()
        verify(viewContractMock).showMessageError(ERROR_MESSAGE, captor.capture())
        verify(viewContractMock).hideLoading()
    }

    @Test
    fun onScrolled_fetchMoreMovies_success() {
        //Arrange
        `when`(movieDataSourceMock.movies(2)).thenReturn(Single.just(moviesMock))
        //Act
        SUT.loadNextPage()
        //Assert
        verify(viewContractMock).showListLoad()
        verify(viewContractMock).bindMovies(moviesMock)
        verify(viewContractMock).hideListLoad()
    }

    @Test
    fun onScrolled_fetchMoreMovies_failure() {
        //Arrange
        val captor = argumentCaptor<() -> Unit>()
        `when`(movieDataSourceMock.movies(2)).thenReturn(
            Single.error(RuntimeException(ERROR_MESSAGE))
        )
        //Act
        SUT.loadNextPage()
        //Assert
        verify(viewContractMock).showListLoad()
        verify(viewContractMock).showMessageError(ERROR_MESSAGE, captor.capture())
        verify(viewContractMock).hideListLoad()
    }
}