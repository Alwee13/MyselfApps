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
class MusicFavoriteViewModel @Inject constructor() : ViewModel() {
    
    private val _favoriteMusic = MutableLiveData<List<String>>()
    val favoriteMusic: LiveData<List<String>> = _favoriteMusic
    
    private val _favoriteVideos = MutableLiveData<List<String>>()
    val favoriteVideos: LiveData<List<String>> = _favoriteVideos
    
    init {
        loadData()
    }
    
    private fun loadData() {
        _favoriteMusic.value = listOf("Song 1", "Song 2", "Song 3")
        _favoriteVideos.value = listOf("Video 1", "Video 2", "Video 3")
    }
} 