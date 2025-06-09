/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import edu.unikom.myselfappsproject.databinding.ActivityMainBinding
import edu.unikom.myselfappsproject.ui.fragments.HomeFragment
import edu.unikom.myselfappsproject.ui.fragments.DailyActivityFragment
import edu.unikom.myselfappsproject.ui.fragments.GalleryFragment
import edu.unikom.myselfappsproject.ui.fragments.MusicFavoriteFragment
import edu.unikom.myselfappsproject.ui.fragments.ProfileFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        setupBottomNavigation()
        
        // Load default fragment
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
            binding.bottomNavigation.selectedItemId = R.id.navigation_home
        }
    }
    
    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.navigation_home -> HomeFragment()
                R.id.navigation_daily -> DailyActivityFragment()
                R.id.navigation_gallery -> GalleryFragment()
                R.id.navigation_music -> MusicFavoriteFragment()
                R.id.navigation_profile -> ProfileFragment()
                else -> HomeFragment()
            }
            
            loadFragment(fragment)
            updateToolbarTitle(item.itemId)
            true
        }
    }
    
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
    
    private fun updateToolbarTitle(itemId: Int) {
        val title = when (itemId) {
            R.id.navigation_home -> "Home"
            R.id.navigation_daily -> "Daily Activity"
            R.id.navigation_gallery -> "Gallery"
            R.id.navigation_music -> "Music Favorite"
            R.id.navigation_profile -> "Profile"
            else -> "MyselfApps"
        }
        binding.toolbar.title = title
    }
}