package br.damous.tracker_domain.use_case

import br.damous.core.domain.model.ActivityLevel
import br.damous.core.domain.model.Gender
import br.damous.core.domain.model.GoalType
import br.damous.core.domain.model.UserInfo
import br.damous.core.domain.preferences.Preferences
import br.damous.tracker_domain.model.MealType
import br.damous.tracker_domain.model.TrackedFood
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk

import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random

class CalculateMealNutrientsTest {

    private lateinit var calculateMealNutrients: CalculateMealNutrients

    @Before
    fun setUp() {
        val preferences = mockk<Preferences>(relaxed = true)
        every { preferences.loadUserInfo() } returns UserInfo(
            gender = Gender.Male,
            age = 24,
            weight = 80f,
            height = 180,
            ActivityLevel.High,
            GoalType.GainWeight,
            carbRatio = 0.4f,
            proteinRatio = 0.4f,
            fatRatio = 0.4f
        )
        calculateMealNutrients = CalculateMealNutrients(preferences)
    }

    @Test
    fun `calories for breakfast properly calculated`() {
        val trackedFoods = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                imageUrl = null,
                mealType = MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random()
                ),
                amount = 100,
                date = LocalDate.now(),
                calories = 2000
            )
        }
        val result = calculateMealNutrients(trackedFoods)
        val breakfastCalories = result.mealNutrients.values
            .filter { it.mealType is MealType.Breakfast }
            .sumOf { it.calories }
        val expectedCalories = trackedFoods
            .filter { it.mealType is MealType.Breakfast }
            .sumOf { it.calories }

        assertThat(breakfastCalories).isEqualTo(expectedCalories)
    }

    @Test
    fun `carbs for dinner properly calculated`() {
        val trackedFoods = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                imageUrl = null,
                mealType = MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random()
                ),
                amount = 100,
                date = LocalDate.now(),
                calories = 2000
            )
        }
        val result = calculateMealNutrients(trackedFoods)
        val dinnerCarbs = result.mealNutrients.values
            .filter { it.mealType is MealType.Dinner }
            .sumOf { it.carbs }
        val expectedCarbs = trackedFoods
            .filter { it.mealType is MealType.Dinner }
            .sumOf { it.carbs }

        assertThat(dinnerCarbs).isEqualTo(expectedCarbs)
    }

}