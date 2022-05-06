package br.damous.tracker_presentation.tracker_overview

import androidx.annotation.DrawableRes
import br.damous.core.util.UiText
import br.damous.tracker_domain.model.MealType

data class Meal(
    val name: UiText,
    @DrawableRes val drawableRes: Int,
    val mealType: MealType,
    val carbs: Int = 0,
    val protein: Int = 0,
    val fat: Int = 0,
    val calories: Int = 0,
    val isExpanded: Boolean = false,
)

val defaultMeals = listOf(
    Meal(
        name = UiText.StringResource(br.damous.core.R.string.breakfast),
        drawableRes = br.damous.core.R.drawable.ic_breakfast,
        mealType = MealType.Breakfast
    ),
    Meal(
        name = UiText.StringResource(br.damous.core.R.string.lunch),
        drawableRes = br.damous.core.R.drawable.ic_lunch,
        mealType = MealType.Lunch
    ),
    Meal(
        name = UiText.StringResource(br.damous.core.R.string.dinner),
        drawableRes = br.damous.core.R.drawable.ic_dinner,
        mealType = MealType.Dinner
    ),
    Meal(
        name = UiText.StringResource(br.damous.core.R.string.snacks),
        drawableRes = br.damous.core.R.drawable.ic_snack,
        mealType = MealType.Snack
    )

)
