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
class OnboardingViewModel @Inject constructor() : ViewModel() {
    
    private val _shouldNavigateToMain = MutableLiveData<Boolean>()
    val shouldNavigateToMain: LiveData<Boolean> = _shouldNavigateToMain
    
    private val _currentPage = MutableLiveData<Int>()
    val currentPage: LiveData<Int> = _currentPage
    
    init {
        _shouldNavigateToMain.value = false
        _currentPage.value = 0
    }
    
    fun setCurrentPage(page: Int) {
        _currentPage.value = page
    }
    
    fun navigateToMain() {
        _shouldNavigateToMain.value = true
    }
    
    fun resetNavigation() {
        _shouldNavigateToMain.value = false
    }
} 