/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.data.repository

import androidx.lifecycle.LiveData
import edu.unikom.myselfappsproject.data.dao.DailyActivityDao
import edu.unikom.myselfappsproject.data.entities.DailyActivityEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DailyActivityRepository @Inject constructor(
    private val dailyActivityDao: DailyActivityDao
) {
    
    fun getAllActivities(): LiveData<List<DailyActivityEntity>> = dailyActivityDao.getAllActivities()
    
    suspend fun getActivityById(id: Int): DailyActivityEntity? = dailyActivityDao.getActivityById(id)
    
    suspend fun insertActivity(activity: DailyActivityEntity): Long = 
        dailyActivityDao.insertActivity(activity)
    
    suspend fun insertActivities(activities: List<DailyActivityEntity>) = 
        dailyActivityDao.insertActivities(activities)
    
    suspend fun updateActivity(activity: DailyActivityEntity) = dailyActivityDao.updateActivity(activity)
    
    suspend fun deleteActivity(activity: DailyActivityEntity) = dailyActivityDao.deleteActivity(activity)
    
    suspend fun deleteActivityById(id: Int) = dailyActivityDao.deleteActivityById(id)
    
    suspend fun deleteAllActivities() = dailyActivityDao.deleteAllActivities()
    
    suspend fun getActivityCount(): Int = dailyActivityDao.getActivityCount()
    
    // Initialize sample data
    suspend fun initializeSampleData() {
        if (getActivityCount() == 0) {
            val sampleActivities = listOf(
                DailyActivityEntity(title = "Wake up", emoji = "☀️"),
                DailyActivityEntity(title = "Breakfast", emoji =  "🍞" ),
                DailyActivityEntity(title = "Lecture", emoji =  "🎓" ),
                DailyActivityEntity(title = "lunch", emoji =  "🍽️" ),
                DailyActivityEntity(title = "Go Home", emoji =  "🏠" ),
                DailyActivityEntity(title = "Rest", emoji = "😌"),
                DailyActivityEntity(title = "doing homework", emoji = "📚"),
                DailyActivityEntity(title = "Sleep", emoji =  "🛌" )
            )
            insertActivities(sampleActivities)
        }
    }
} 