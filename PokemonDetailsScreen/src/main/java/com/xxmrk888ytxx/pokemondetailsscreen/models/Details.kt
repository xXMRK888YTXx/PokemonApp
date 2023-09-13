package com.xxmrk888ytxx.pokemondetailsscreen.models

data class Details(
    val id:Int,
    val name:String,
    val pokemonImageUrl:String,
    val weight:Int,
    val height:Int,
    val typesName:List<String>
)