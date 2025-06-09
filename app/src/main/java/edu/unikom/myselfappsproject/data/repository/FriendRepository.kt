/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.data.repository

import androidx.lifecycle.LiveData
import edu.unikom.myselfappsproject.data.dao.FriendDao
import edu.unikom.myselfappsproject.data.entities.FriendEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class  FriendRepository @Inject constructor(
    private val friendDao: FriendDao
) {
    
    fun getAllFriends(): LiveData<List<FriendEntity>> = friendDao.getAllFriends()
    
    fun searchFriends(searchQuery: String): LiveData<List<FriendEntity>> = 
        friendDao.searchFriends(searchQuery)
    
    suspend fun getFriendById(id: Int): FriendEntity? = friendDao.getFriendById(id)
    
    suspend fun insertFriend(friend: FriendEntity): Long = friendDao.insertFriend(friend)
    
    suspend fun insertFriends(friends: List<FriendEntity>) = friendDao.insertFriends(friends)
    
    suspend fun updateFriend(friend: FriendEntity) = friendDao.updateFriend(friend)
    
    suspend fun deleteFriend(friend: FriendEntity) = friendDao.deleteFriend(friend)
    
    suspend fun deleteFriendById(id: Int) = friendDao.deleteFriendById(id)
    
    suspend fun deleteAllFriends() = friendDao.deleteAllFriends()
    
    suspend fun getFriendCount(): Int = friendDao.getFriendCount()
    
    // Initialize sample friends data
    suspend fun initializeSampleFriends() {
        // Always clear and refresh with latest data
        deleteAllFriends()
        
        val sampleFriends = listOf(
            FriendEntity(name = "Alice"),
            FriendEntity(name = "Bob"),
            FriendEntity(name = "Charlie"),
            FriendEntity(name = "Diana"),
            FriendEntity(name = "Eve"),
            FriendEntity(name = "Frank"),
        )
        insertFriends(sampleFriends)
    }
} 