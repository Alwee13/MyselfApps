/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import edu.unikom.myselfappsproject.MainActivity
import edu.unikom.myselfappsproject.databinding.ActivityOnboardingBinding
import edu.unikom.myselfappsproject.ui.viewmodels.OnboardingViewModel
import edu.unikom.myselfappsproject.utils.PreferenceManager
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityOnboardingBinding
    private val viewModel: OnboardingViewModel by viewModels()
    private lateinit var onboardingAdapter: OnboardingAdapter
    
    @Inject
    lateinit var preferenceManager: PreferenceManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupViewPager()
        setupClickListeners()
        observeViewModel()
    }
    
    private fun setupViewPager() {
        onboardingAdapter = OnboardingAdapter(this)
        binding.viewPager.adapter = onboardingAdapter
        
        // Setup dots indicator
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // Empty implementation for dots indicator
        }.attach()
        
        // ViewPager page change listener
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateButtons(position)
            }
        })
    }
    
    private fun setupClickListeners() {
        binding.btnSkip.setOnClickListener {
            navigateToMain()
        }
        
        binding.btnNext.setOnClickListener {
            val currentItem = binding.viewPager.currentItem
            if (currentItem < onboardingAdapter.itemCount - 1) {
                binding.viewPager.currentItem = currentItem + 1
            } else {
                navigateToMain()
            }
        }
        
        binding.btnPrevious.setOnClickListener {
            val currentItem = binding.viewPager.currentItem
            if (currentItem > 0) {
                binding.viewPager.currentItem = currentItem - 1
            }
        }
    }
    
    private fun updateButtons(position: Int) {
        binding.btnPrevious.visibility = if (position == 0) {
            android.view.View.INVISIBLE
        } else {
            android.view.View.VISIBLE
        }
        
        binding.btnNext.text = if (position == onboardingAdapter.itemCount - 1) {
            "Get Started"
        } else {
            "Next"
        }
    }
    
    private fun observeViewModel() {
        viewModel.shouldNavigateToMain.observe(this) { shouldNavigate ->
            if (shouldNavigate) {
                navigateToMain()
            }
        }
    }
    
    private fun navigateToMain() {
        // Mark onboarding as completed
        preferenceManager.setOnboardingCompleted(true)
        
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        overridePendingTransition(
            android.R.anim.slide_in_left,
            android.R.anim.slide_out_right
        )
    }
}

class OnboardingAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3
    
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingFragment.newInstance(
                title = "Welcome to My Persona App",
                description = "Discover all about me through this interactive profile application",
                imageRes = "welcome"
            )
            1 -> OnboardingFragment.newInstance(
                title = "Explore My Activities",
                description = "See my daily routines, hobbies, and interests in one place",
                imageRes = "activity"
            )
            2 -> OnboardingFragment.newInstance(
                title = "Connect With Me",
                description = "Find all my contact information and social media links",
                imageRes = "profile"
            )
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
} 