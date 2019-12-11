package br.well.moviedbservice.api.model

data class MovieDetail(
    val backdrop_path: String,
    val id: Int,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val release_date: String,
    val title: String,
    val vote_average: Double
)


data class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)