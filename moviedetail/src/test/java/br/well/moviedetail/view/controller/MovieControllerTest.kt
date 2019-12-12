package br.well.moviedetail.view.controller

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import br.well.coreapp.ImmediateSchedulerProvider
import br.well.moviedbservice.api.model.MovieDetail
import br.well.moviedbservice.api.movie.MovieDataSource
import br.well.moviedetail.ui.view.controller.MovieDetailController
import br.well.moviedetail.ui.view.controller.MovieDetailViewContract
import br.well.moviedetail.ui.view.usecase.MovieDetailUseCase
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import rx.Single

class MovieControllerTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    companion object {
        const val MOVIE_ID = 512200
    }

    private val movieDataSourceMock = mock<MovieDataSource>()
    private val viewContractMock = mock<MovieDetailViewContract>()
    private val movieDetailMock = MovieDetail("", 0, "", arrayListOf(), "", "", 0.0)
    lateinit var SUT: MovieDetailController

    @Before
    fun setup() {
        val lifecycleMock = LifecycleRegistry(Mockito.mock(LifecycleOwner::class.java))
        lifecycleMock.handleLifecycleEvent(Lifecycle.Event.ON_START)
        val useCase = MovieDetailUseCase(
            movieDataSourceMock,
            ImmediateSchedulerProvider()
        )
        SUT = MovieDetailController(useCase, lifecycleMock, MOVIE_ID)
        SUT.onCreate(viewContractMock)
    }

    @Test
    fun onStart_fetchMovieDetail_success() {
        //Arrange
        Mockito.`when`(movieDataSourceMock.movieDetail(MOVIE_ID))
            .thenReturn(Single.just(movieDetailMock))
        //Act
        SUT.onStart()
        //Assert
        verify(viewContractMock).showLoading()
        verify(viewContractMock).bindMovieDetail(movieDetailMock)
        verify(viewContractMock).hideLoading()
    }

    @Test
    fun onStart_fetchMovieDetail_failure() {
        //Arrange
        val captor = argumentCaptor<() -> Unit>()
        Mockito.`when`(movieDataSourceMock.movieDetail(MOVIE_ID))
            .thenReturn(Single.error(RuntimeException("Houve um erro")))
        //Act
        SUT.onStart()
        //Assert
        verify(viewContractMock).showLoading()
        verify(viewContractMock).showErrorMessage("Houve um erro", captor.capture())
        verify(viewContractMock).hideLoading()
    }

    @Test
    fun onNavigateUpClicked_onBackPressed() {
        //Arrange
        //Act
        SUT.onBackPressed()
        //Assert
        verify(viewContractMock).onBackPressed()
    }

    @Test
    fun onStop_unregisterListener() {
        //Arrange
        //Act
        SUT.onStop()
        //Assert
        verify(viewContractMock).unregisterListener(SUT)
    }
}