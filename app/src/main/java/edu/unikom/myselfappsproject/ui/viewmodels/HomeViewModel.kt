/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.unikom.myselfappsproject.data.entities.ProfileEntity
import edu.unikom.myselfappsproject.data.entities.InterestEntity
import edu.unikom.myselfappsproject.data.repository.ProfileRepository
import edu.unikom.myselfappsproject.data.repository.InterestRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val interestRepository: InterestRepository
) : ViewModel() {
    
    val profileData: LiveData<ProfileEntity?> = profileRepository.getProfile()
    val interestData: LiveData<InterestEntity?> = interestRepository.getInterests()
    
    init {
        initializeData()
    }
    
    private fun initializeData() {
        viewModelScope.launch {
            // Initialize default data if not exists
            profileRepository.initializeDefaultProfile()
            interestRepository.initializeDefaultInterests()
        }
    }
    
    fun updateProfileName(name: String) {
        viewModelScope.launch {
            profileRepository.updateName(name)
        }
    }
    
    fun updateProfileDescription(description: String) {
        viewModelScope.launch {
            profileRepository.updateDescription(description)
        }
    }
    
    fun updateHobbies(hobbies: String) {
        viewModelScope.launch {
            interestRepository.updateHobbies(hobbies)
        }
    }
    
    fun updateMakes(makes: String) {
        viewModelScope.launch {
            interestRepository.updateMakes(makes)
        }
    }
    
    fun updateMikes(mikes: String) {
        viewModelScope.launch {
            interestRepository.updateMikes(mikes)
        }
    }
    
    fun updateGoals(goals: String) {
        viewModelScope.launch {
            interestRepository.updateGoals(goals)
        }
    }
} 