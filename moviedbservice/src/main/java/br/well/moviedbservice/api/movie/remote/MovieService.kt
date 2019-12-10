package br.well.moviedbservice.api.movie.remote

import br.well.moviedbservice.api.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Single


interface MovieService {

    @GET("movie/popular?api_key={api_key}{language}{page}")
    fun movie(@Path("api_key") apiKey: String, @Query("language") language: String, @Query("page") page: Int): Single<List<Movie>>
}