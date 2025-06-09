/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.unikom.myselfappsproject.R
import edu.unikom.myselfappsproject.data.models.Friend
import edu.unikom.myselfappsproject.data.repository.FriendRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendListViewModel @Inject constructor(
    private val friendRepository: FriendRepository
) : ViewModel() {

    // Map untuk menghubungkan nama dengan resource foto
    private val photoResourceMap = mapOf(
        "Alice" to R.drawable.friend1,
        "Bob" to R.drawable.friend2,
        "Charlie" to R.drawable.friend3,
        "Diana" to R.drawable.friend4,
        "Eve" to R.drawable.friend5,
        "Frank" to R.drawable.friend6
    )

    // LiveData yang akan diobserve oleh Activity
    val friendsLiveData: LiveData<List<Friend>> = friendRepository.getAllFriends().map { friendEntities ->
        friendEntities.mapIndexed { index, friendEntity ->
            Friend(
                id = friendEntity.id,
                name = friendEntity.name,
                // Ambil photo resource berdasarkan nama, atau gunakan default
                photoResourceId = photoResourceMap[friendEntity.name] 
                    ?: getDefaultPhotoResource(index)
            )
        }
    }

    // Initialize sample friends when ViewModel is created
    init {
        initializeFriends()
    }

    private fun initializeFriends() {
        viewModelScope.launch {
            friendRepository.initializeSampleFriends()
        }
    }

    private fun getDefaultPhotoResource(index: Int): Int {
        val defaultPhotos = listOf(
            R.drawable.friend1,
            R.drawable.friend2,
            R.drawable.friend3,
            R.drawable.friend4,
            R.drawable.friend5,
            R.drawable.friend6
        )
        return defaultPhotos[index % defaultPhotos.size]
    }
} 