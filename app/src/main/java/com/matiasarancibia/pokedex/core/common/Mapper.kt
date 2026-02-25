package com.matiasarancibia.pokedex.core.common

/*
    This interface will be used in the mapper classes, which will be in charge of convert
    the data class parsed from the server response to a view data class, which will be used
    in the View layer and will contain the data that the user will see, and in some cases
    will also contain minor logic to format some String values with a specific format
 */
interface Mapper<S, R> {
    fun executeMapping(data: S): R
}