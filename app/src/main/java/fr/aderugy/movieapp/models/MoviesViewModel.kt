package fr.aderugy.movieapp.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Movie(
    val name: String,
    val image: String,
    val certification: String,
    val description: String,
    val starring: Array<String>,
    val running_time_mins: Int,
    initialSeatsRemaining: Int = (0..15).random()
) {
    var seats_remaining by mutableStateOf(initialSeatsRemaining)
    var seats_selected by mutableStateOf(0)
}
