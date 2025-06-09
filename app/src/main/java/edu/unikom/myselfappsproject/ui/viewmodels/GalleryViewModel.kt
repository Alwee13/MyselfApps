/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.unikom.myselfappsproject.data.entities.GalleryEntity
import edu.unikom.myselfappsproject.R
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor() : ViewModel() {
    
    companion object {
        private const val TAG = "GalleryViewModel"
        
        // Pinterest-style gallery data dengan foto asli dari drawable
        private val GALLERY_DATA = listOf(
            GalleryEntity(
                id = 1,
                imagePath = "drawable://gallery1",
                imageResource = R.drawable.gallery1,
                title = "padang rumput",
                description = "Pemandangan padang rumput",
                dateAdded = "2025-05-25",
                isFavorite = true,
                height = 320 //
            ),
            GalleryEntity(
                id = 2,
                imagePath = "drawable://gallery2",
                imageResource = R.drawable.gallery2,
                title = "Mountain",
                description = "Mountains in the distance",
                dateAdded = "2022-06-26",
                isFavorite = false,
                height = 280
            ),
            GalleryEntity(
                id = 3,
                imagePath = "drawable://gallery3",
                imageResource = R.drawable.gallery3,
                title = "car",
                description = "car driving",
                dateAdded = "2023-08-21",
                isFavorite = true,
                height = 350
            ),
            GalleryEntity(
                id = 4,
                imagePath = "drawable://gallery4",
                imageResource = R.drawable.gallery4,
                title = "car 2",
                description = "car driving 2",
                dateAdded = "2025-02-18",
                isFavorite = false,
                height = 380
            ),
            GalleryEntity(
                id = 5,
                imagePath = "drawable://gallery5",
                imageResource = R.drawable.gallery5,
                title = "cosmic",
                description = "space travel",
                dateAdded = "2024-08-15",
                isFavorite = true,
                height = 300
            ),
            GalleryEntity(
                id = 6,
                imagePath = "drawable://pics2",
                imageResource = R.drawable.gallery6,
                title = "car",
                description = "Car in the rain",
                dateAdded = "2025-01-8",
                isFavorite = false,
                height = 340
            ),
            GalleryEntity(
                id = 7,
                imagePath = "drawable://gallery7",
                imageResource = R.drawable.gallery7,
                title = "space 3",
                description = "space travel",
                dateAdded = "2024-03-01",
                isFavorite = true,
                height = 290
            ),
            GalleryEntity(
                id = 8,
                imagePath = "drawable://gallery8",
                imageResource = R.drawable.gallery8,
                title = "moon",
                description = "moon in the sky",
                dateAdded = "2021-03-30",
                isFavorite = false,
                height = 360
            ),
            GalleryEntity(
                id = 9,
                imagePath = "drawable://gallery9",
                imageResource = R.drawable.gallery9,
                title = "Planets",
                description = "planets in the sky",
                dateAdded = "2021-11-27",
                isFavorite = true,
                height = 320
            )
        )
    }
    
    private val _galleryImages = MutableLiveData<List<GalleryEntity>>()
    val galleryImages: LiveData<List<GalleryEntity>> = _galleryImages
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty
    
    init {
        Log.d(TAG, "GalleryViewModel initialized - Loading real photos from drawable")
        loadGalleryData()
    }
    
    private fun loadGalleryData() {
        Log.d(TAG, "Loading real photos from drawable resources...")
        _isLoading.value = true
        
        // Simulate brief loading for smooth UX
        _galleryImages.value = GALLERY_DATA
        _isEmpty.value = GALLERY_DATA.isEmpty()
        _isLoading.value = false
        
        Log.d(TAG, "Gallery loaded: ${GALLERY_DATA.size} real photos")
    }
    
    // Toggle favorite status
    fun toggleFavorite(imageId: Int) {
        val currentList = _galleryImages.value?.toMutableList() ?: return
        val index = currentList.indexOfFirst { it.id == imageId }
        if (index != -1) {
            currentList[index] = currentList[index].copy(isFavorite = !currentList[index].isFavorite)
            _galleryImages.value = currentList
            Log.d(TAG, "Toggled favorite for image $imageId")
        }
    }
    
    // Filter favorites
    fun getFavorites(): List<GalleryEntity> {
        return _galleryImages.value?.filter { it.isFavorite } ?: emptyList()
    }
    
    // Search functionality
    fun searchImages(query: String): List<GalleryEntity> {
        return if (query.isBlank()) {
            _galleryImages.value ?: emptyList()
        } else {
            _galleryImages.value?.filter { 
                it.title.contains(query, ignoreCase = true) || 
                it.description.contains(query, ignoreCase = true) 
            } ?: emptyList()
        }
    }
    
    // Get total count
    fun getGalleryCount(): Int = _galleryImages.value?.size ?: 0
    
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "GalleryViewModel cleared")
    }
} 