/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.data.repository

import androidx.lifecycle.LiveData
import edu.unikom.myselfappsproject.data.dao.InterestDao
import edu.unikom.myselfappsproject.data.entities.InterestEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InterestRepository @Inject constructor(
    private val interestDao: InterestDao
) {
    
    fun getInterests(): LiveData<InterestEntity?> = interestDao.getInterests()
    
    suspend fun getInterestsSync(): InterestEntity? = interestDao.getInterestsSync()
    
    suspend fun insertInterests(interests: InterestEntity) = interestDao.insertInterests(interests)
    
    suspend fun updateInterests(interests: InterestEntity) = interestDao.updateInterests(interests)
    
    suspend fun updateHobbies(hobbies: String) = interestDao.updateHobbies(hobbies)
    
    suspend fun updateMakes(makes: String) = interestDao.updateMakes(makes)
    
    suspend fun updateMikes(mikes: String) = interestDao.updateMikes(mikes)
    
    suspend fun updateGoals(goals: String) = interestDao.updateGoals(goals)
    
    suspend fun deleteAllInterests() = interestDao.deleteAllInterests()
    
    // Initialize default interests if not exists
    suspend fun initializeDefaultInterests() {
        if (getInterestsSync() == null) {
            val defaultInterests = InterestEntity(
                id = 1,
                hobbies = "Gaming, Sleeping, Watch Movie, Watch sports",
                makes = "Playing Football, Basketball, Games",
                mikes = "Sweet Food, crowd",
                goals = "Become Ai Engineer"
            )
            insertInterests(defaultInterests)
        }
    }
} 