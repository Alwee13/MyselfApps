/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.unikom.myselfappsproject.utils.PreferenceManager
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val preferenceManager: PreferenceManager
) : ViewModel() {
    
    private val _navigateToOnboarding = MutableLiveData<Boolean>()
    val navigateToOnboarding: LiveData<Boolean> = _navigateToOnboarding
    
    private val _navigateToMain = MutableLiveData<Boolean>()
    val navigateToMain: LiveData<Boolean> = _navigateToMain
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    init {
        _isLoading.value = true
    }
    
    fun completeSplash() {
        _isLoading.value = false
        
        // Check if onboarding has been completed
        if (preferenceManager.isOnboardingCompleted()) {
            // Skip onboarding, go directly to main
            _navigateToMain.value = true
        } else {
            // Show onboarding first
            _navigateToOnboarding.value = true
        }
    }
} 