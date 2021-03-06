package br.damous.tracker_data.local.dao

import androidx.room.*
import br.damous.tracker_data.local.entity.TrackedFoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    @Delete
    suspend fun deleteTrackFood(trackedFoodEntity: TrackedFoodEntity)

    @Query(
        """
            SELECT *
            FROM trackedfoodentity
            WHERE dayOfMonth = :day
            AND month = :month
            AND year = :year
        """
    )
    fun getFoodForDate(day: Int, month: Int, year: Int): Flow<List<TrackedFoodEntity>>
}