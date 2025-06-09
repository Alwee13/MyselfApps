/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.unikom.myselfappsproject.data.database.MyselfDatabase
import edu.unikom.myselfappsproject.data.dao.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MyselfDatabase {
        return Room.databaseBuilder(
            context,
            MyselfDatabase::class.java,
            MyselfDatabase.DATABASE_NAME
        )
        .fallbackToDestructiveMigration() // For development - remove in production
        .build()
    }
    
    @Provides
    fun provideProfileDao(database: MyselfDatabase): ProfileDao {
        return database.profileDao()
    }
    
    @Provides
    fun provideInterestDao(database: MyselfDatabase): InterestDao {
        return database.interestDao()
    }
    
    @Provides
    fun provideDailyActivityDao(database: MyselfDatabase): DailyActivityDao {
        return database.dailyActivityDao()
    }
    
    @Provides
    fun provideFriendDao(database: MyselfDatabase): FriendDao {
        return database.friendDao()
    }
} 