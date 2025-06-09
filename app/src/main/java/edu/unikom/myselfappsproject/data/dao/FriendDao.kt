/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.unikom.myselfappsproject.data.entities.FriendEntity

@Dao
interface FriendDao {
    
    @Query("SELECT * FROM friends ORDER BY id ASC")
    fun getAllFriends(): LiveData<List<FriendEntity>>
    
    @Query("SELECT * FROM friends WHERE id = :id LIMIT 1")
    suspend fun getFriendById(id: Int): FriendEntity?
    
    @Query("SELECT * FROM friends WHERE name LIKE '%' || :searchQuery || '%' ORDER BY name ASC")
    fun searchFriends(searchQuery: String): LiveData<List<FriendEntity>>
    
    @Insert
    suspend fun insertFriend(friend: FriendEntity): Long
    
    @Insert
    suspend fun insertFriends(friends: List<FriendEntity>)
    
    @Update
    suspend fun updateFriend(friend: FriendEntity)
    
    @Delete
    suspend fun deleteFriend(friend: FriendEntity)
    
    @Query("DELETE FROM friends WHERE id = :id")
    suspend fun deleteFriendById(id: Int)
    
    @Query("DELETE FROM friends")
    suspend fun deleteAllFriends()
    
    @Query("SELECT COUNT(*) FROM friends")
    suspend fun getFriendCount(): Int
} 