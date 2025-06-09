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
import edu.unikom.myselfappsproject.data.entities.DailyActivityEntity
import edu.unikom.myselfappsproject.data.repository.DailyActivityRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyActivityViewModel @Inject constructor(
    private val dailyActivityRepository: DailyActivityRepository
) : ViewModel() {
    
    // LiveData yang akan diobserve oleh Fragment
    val dailyActivities: LiveData<List<DailyActivityEntity>> = 
        dailyActivityRepository.getAllActivities()
    
    // Initialize sample data when ViewModel is created
    init {
        initializeActivities()
    }
    
    private fun initializeActivities() {
        viewModelScope.launch {
            dailyActivityRepository.initializeSampleData()
        }
    }

    fun addActivity(title: String, emoji: String) {
        viewModelScope.launch {
            val activity = DailyActivityEntity(title = title, emoji = emoji)
            dailyActivityRepository.insertActivity(activity)
        }
    }

    fun deleteActivity(activity: DailyActivityEntity) {
        viewModelScope.launch {
            dailyActivityRepository.deleteActivity(activity)
        }
    }
} 