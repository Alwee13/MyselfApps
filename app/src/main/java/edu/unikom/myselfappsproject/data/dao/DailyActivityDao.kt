/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.unikom.myselfappsproject.data.entities.DailyActivityEntity

@Dao
interface DailyActivityDao {
    
    @Query("SELECT * FROM daily_activities ORDER BY id ASC")
    fun getAllActivities(): LiveData<List<DailyActivityEntity>>
    
    @Query("SELECT * FROM daily_activities WHERE id = :id LIMIT 1")
    suspend fun getActivityById(id: Int): DailyActivityEntity?
    
    @Insert
    suspend fun insertActivity(activity: DailyActivityEntity): Long
    
    @Insert
    suspend fun insertActivities(activities: List<DailyActivityEntity>)
    
    @Update
    suspend fun updateActivity(activity: DailyActivityEntity)
    
    @Delete
    suspend fun deleteActivity(activity: DailyActivityEntity)
    
    @Query("DELETE FROM daily_activities WHERE id = :id")
    suspend fun deleteActivityById(id: Int)
    
    @Query("DELETE FROM daily_activities")
    suspend fun deleteAllActivities()
    
    @Query("SELECT COUNT(*) FROM daily_activities")
    suspend fun getActivityCount(): Int
} 