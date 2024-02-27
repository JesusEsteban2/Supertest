package com.example.supertest.data

import com.google.gson.annotations.SerializedName

data class SuperHeroResponse (
    @SerializedName("response") val response: String,
    @SerializedName("results") val listSuperHero: List<SuperHero>,
    @SerializedName("results-for") val query: String
)

data class SuperHero (
    @SerializedName("id") val id:String,
    @SerializedName("name") val name:String,
    @SerializedName("biography") val biography: Biography,
    @SerializedName("image") val httpImage:Image
)

data class Biography(
    /*@SerializedName ("") aliases: List<String>,
    val alignment: String,
    val alter-egos: String,
    val first-appearance: String,
    val full-name: String,
    val place-of-birth: String,*/
    @SerializedName("publisher") val publisher: String
)

data class Image(
    @SerializedName("url") val url: String
)

/*
data class Result(
    val appearance: Appearance,
    val biography: Biography,
    val connections: Connections,
    val id: String,
    val image: Image,
    val name: String,
    val powerstats: Powerstats,
    val work: Work
)

data class Appearance(
    val eye-color: String,
    val gender: String,
    val hair-color: String,
    val height: List<String>,
    val race: String,
    val weight: List<String>
)

data class Connections(
    val group-affiliation: String,
    val relatives: String
)

data class Powerstats(
    val combat: String,
    val durability: String,
    val intelligence: String,
    val power: String,
    val speed: String,
    val strength: String
)

data class Work(
    val base: String,
    val occupation: String
)
*/


