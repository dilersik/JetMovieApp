package com.example.jetmovieapp.nav

enum class MovieViewEnum {
    HOME,
    DETAIL;

    companion object {
        fun fromRoute(route: String?): MovieViewEnum =
            when (route?.substringBefore("/")) {
                HOME.name -> HOME
                DETAIL.name -> DETAIL
                else -> HOME
            }
    }
}