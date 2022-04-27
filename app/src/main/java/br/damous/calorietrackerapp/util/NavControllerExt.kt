package br.damous.calorietrackerapp.util

import androidx.navigation.NavController
import br.damous.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}