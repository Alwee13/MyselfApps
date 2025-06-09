/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.ui.splash

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import edu.unikom.myselfappsproject.databinding.ActivitySplashBinding
import edu.unikom.myselfappsproject.MainActivity
import edu.unikom.myselfappsproject.ui.onboarding.OnboardingActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        // Install splash screen before super.onCreate()
        val splashScreen = installSplashScreen()
        
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Setup splash screen exit animation
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val slideUp = ObjectAnimator.ofFloat(
                splashScreenView.view,
                View.TRANSLATION_Y,
                0f,
                -splashScreenView.view.height.toFloat()
            )
            slideUp.interpolator = AnticipateInterpolator()
            slideUp.duration = 500L
            slideUp.doOnEnd { splashScreenView.remove() }
            slideUp.start()
        }
        
        setupUI()
        observeViewModel()
        startSplashSequence()
    }
    
    private fun setupUI() {
        // Animate logo entrance
        binding.logoImage.apply {
            alpha = 0f
            scaleX = 0.8f
            scaleY = 0.8f
            animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(800)
                .start()
        }

        
        // Animate progress indicator
        binding.loadingProgress.apply {
            alpha = 0f
            animate()
                .alpha(1f)
                .setDuration(400)
                .setStartDelay(800)
                .start()
        }
    }
    
    private fun observeViewModel() {
        // Navigate to onboarding for first-time users
        viewModel.navigateToOnboarding.observe(this) { shouldNavigate ->
            if (shouldNavigate) {
                navigateToOnboarding()
            }
        }
        
        // Navigate directly to main for returning users
        viewModel.navigateToMain.observe(this) { shouldNavigate ->
            if (shouldNavigate) {
                navigateToMain()
            }
        }
    }
    
    private fun startSplashSequence() {
        lifecycleScope.launch {
            // Simulate app initialization
            delay(2500)
            viewModel.completeSplash()
        }
    }
    
    private fun navigateToOnboarding() {
        val intent = Intent(this, OnboardingActivity::class.java)
        startActivity(intent)
        finish()
        
        // Custom transition animation
        overridePendingTransition(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
    }
    
    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        
        // Custom transition animation
        overridePendingTransition(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
    }
} 