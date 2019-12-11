package br.well.movies.view.controller

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import br.well.movies.common.provider.ImmediateSchedulerProvider
import br.well.moviedbservice.api.movie.MovieDataSource
import br.well.movies.ui.usecase.MovieUseCase
import br.well.movies.ui.view.controller.MoviesController
import br.well.movies.ui.view.controller.MoviesViewContract
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

    private val movieDataSourceMock = mock<MovieDataSource>()

    lateinit var SUT: MoviesController

    @Before
    fun setup() {
        val lifecycleMock = LifecycleRegistry(Mockito.mock(LifecycleOwner::class.java))
        lifecycleMock.handleLifecycleEvent(Lifecycle.Event.ON_START)
        val useCase = MovieUseCase(movieDataSourceMock, ImmediateSchedulerProvider())
        SUT = MoviesController(
            useCase,
            lifecycleMock
        )
    }

    @Test
    fun onStart_fetchMovies_success() {
        //Arrange
        `when`(movieDataSourceMock.movies(1)).thenReturn(Single.just(mock()))
        //Act
        SUT.onStart()
        //Assert
        verify(viewContractMock).showLoading()
        verify(viewContractMock).bindMovies()
        verify(viewContractMock).hideLoading()
    }

    @Test
    fun onStart_fetchMovies_failed() {
        //Arrange
        `when`(movieDataSourceMock.movies(1)).thenReturn(Single.error(RuntimeException("")))
        //Act
        SUT.onStart()
        //Assert
        verify(viewContractMock).showLoading()
        verify(viewContractMock).showMessageError()
        verify(viewContractMock).hideLoading()
    }
}