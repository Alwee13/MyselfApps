/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.unikom.myselfappsproject.data.entities.ProfileEntity

@Dao
interface ProfileDao {
    
    @Query("SELECT * FROM profile WHERE id = 1 LIMIT 1")
    fun getProfile(): LiveData<ProfileEntity?>
    
    @Query("SELECT * FROM profile WHERE id = 1 LIMIT 1")
    suspend fun getProfileSync(): ProfileEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: ProfileEntity)
    
    @Update
    suspend fun updateProfile(profile: ProfileEntity)
    
    @Query("DELETE FROM profile")
    suspend fun deleteAllProfiles()
    
    @Query("UPDATE profile SET name = :name, updatedAt = :updatedAt WHERE id = 1")
    suspend fun updateName(name: String, updatedAt: Long = System.currentTimeMillis())
    
    @Query("UPDATE profile SET aboutDescription = :description, updatedAt = :updatedAt WHERE id = 1")
    suspend fun updateDescription(description: String, updatedAt: Long = System.currentTimeMillis())
    
    @Query("UPDATE profile SET photoPath = :photoPath, updatedAt = :updatedAt WHERE id = 1")
    suspend fun updatePhoto(photoPath: String, updatedAt: Long = System.currentTimeMillis())
} 