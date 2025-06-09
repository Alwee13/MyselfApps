/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.data.models

data class Friend(
    val id: Int,
    val name: String,
    val photoResourceId: Int,
    val nickname: String = "",
    val relationship: String = ""
) 