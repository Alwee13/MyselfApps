/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.unikom.myselfappsproject.data.entities.InterestEntity

@Dao
interface InterestDao {
    
    @Query("SELECT * FROM interests WHERE id = 1 LIMIT 1")
    fun getInterests(): LiveData<InterestEntity?>
    
    @Query("SELECT * FROM interests WHERE id = 1 LIMIT 1")
    suspend fun getInterestsSync(): InterestEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInterests(interests: InterestEntity)
    
    @Update
    suspend fun updateInterests(interests: InterestEntity)
    
    @Query("DELETE FROM interests")
    suspend fun deleteAllInterests()
    
    @Query("UPDATE interests SET hobbies = :hobbies, updatedAt = :updatedAt WHERE id = 1")
    suspend fun updateHobbies(hobbies: String, updatedAt: Long = System.currentTimeMillis())
    
    @Query("UPDATE interests SET makes = :makes, updatedAt = :updatedAt WHERE id = 1")
    suspend fun updateMakes(makes: String, updatedAt: Long = System.currentTimeMillis())
    
    @Query("UPDATE interests SET mikes = :mikes, updatedAt = :updatedAt WHERE id = 1")
    suspend fun updateMikes(mikes: String, updatedAt: Long = System.currentTimeMillis())
    
    @Query("UPDATE interests SET goals = :goals, updatedAt = :updatedAt WHERE id = 1")
    suspend fun updateGoals(goals: String, updatedAt: Long = System.currentTimeMillis())
} 