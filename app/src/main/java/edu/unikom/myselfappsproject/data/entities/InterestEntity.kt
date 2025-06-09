/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "interests")
data class InterestEntity(
    @PrimaryKey val id: Int = 1,
    val hobbies: String,
    val makes: String,
    val mikes: String,
    val goals: String,
    val updatedAt: Long = System.currentTimeMillis()
) 