/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.data.repository

import androidx.lifecycle.LiveData
import edu.unikom.myselfappsproject.data.dao.ProfileDao
import edu.unikom.myselfappsproject.data.entities.ProfileEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    private val profileDao: ProfileDao
) {
    
    fun getProfile(): LiveData<ProfileEntity?> = profileDao.getProfile()
    
    suspend fun getProfileSync(): ProfileEntity? = profileDao.getProfileSync()
    
    suspend fun insertProfile(profile: ProfileEntity) = profileDao.insertProfile(profile)
    
    suspend fun updateProfile(profile: ProfileEntity) = profileDao.updateProfile(profile)
    
    suspend fun updateName(name: String) = profileDao.updateName(name)
    
    suspend fun updateDescription(description: String) = profileDao.updateDescription(description)
    
    suspend fun updatePhoto(photoPath: String) = profileDao.updatePhoto(photoPath)
    
    suspend fun deleteAllProfiles() = profileDao.deleteAllProfiles()
    
    // Initialize default profile if not exists
    suspend fun initializeDefaultProfile() {
        if (getProfileSync() == null) {
            val defaultProfile = ProfileEntity(
                id = 1,
                name = "Rafi Alwi Saputra",
                photoPath = "pics1",
                aboutDescription = "Seorang pecinta sepak bola yang suka bermain dan menonton pertandingan, penggemar musik dari berbagai genre, dan gamer yang menikmati tantangan baik di dunia virtual maupun nyata. Saya juga suka menikmati waktu sendiri dengan menonton film, terutama sci-fi dan teknologi futuristik, karena saya lebih nyaman di lingkungan tenang daripada keramaian. Impian saya adalah menjadi AI Engineer karena terinspirasi oleh kecanggihan kecerdasan buatan dan bagaimana teknologi bisa membentuk masa depan."
            )
            insertProfile(defaultProfile)
        }
    }
} 