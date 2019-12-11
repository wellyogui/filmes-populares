package br.well.moviedbservice.api.movie.remote

import br.well.moviedbservice.api.model.MovieDetail
import br.well.moviedbservice.api.model.Movies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Single


interface MovieService {

    @GET("movie/popular")
    fun movie(@Query("api_key") apiKey: String, @Query("language") language: String, @Query("page") page: Int): Single<Movies>

    @GET("movie/{movie_id}")
    fun movieDetail(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String, @Query("language") language: String): Single<MovieDetail>
}