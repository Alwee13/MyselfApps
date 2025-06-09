/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.data.entities

data class GalleryEntity(
    val id: Int,
    val imagePath: String, // Path to image file
    val title: String,
    val description: String = "",
    val dateAdded: String,
    val isFavorite: Boolean = false,
    val height: Int = 300,
    // Legacy support
    val imageResource: Int = 0,
    val heightRatio: Float = 1.0f
) 