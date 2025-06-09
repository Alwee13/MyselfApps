/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import edu.unikom.myselfappsproject.data.dao.*
import edu.unikom.myselfappsproject.data.entities.*

@Database(
    entities = [
        ProfileEntity::class,
        InterestEntity::class,
        DailyActivityEntity::class,
        FriendEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class MyselfDatabase : RoomDatabase() {
    
    // DAO getters
    abstract fun profileDao(): ProfileDao
    abstract fun interestDao(): InterestDao
    abstract fun dailyActivityDao(): DailyActivityDao
    abstract fun friendDao(): FriendDao
    
    companion object {
        const val DATABASE_NAME = "myself_database"
    }
} 