/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {
    
    private val _profileData = MutableLiveData<String>()
    val profileData: LiveData<String> = _profileData
    
    private val _contactInfo = MutableLiveData<String>()
    val contactInfo: LiveData<String> = _contactInfo
    
    private val _locationInfo = MutableLiveData<String>()
    val locationInfo: LiveData<String> = _locationInfo
    
    init {
        loadData()
    }
    
    private fun loadData() {
        _profileData.value = "Profile Information"
        _contactInfo.value = "Contact Information"
        _locationInfo.value = "Location Information"
    }
} 