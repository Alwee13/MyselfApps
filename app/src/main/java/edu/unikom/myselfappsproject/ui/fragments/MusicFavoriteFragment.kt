/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import edu.unikom.myselfappsproject.R
import edu.unikom.myselfappsproject.databinding.FragmentMusicFavoriteBinding
import edu.unikom.myselfappsproject.ui.viewmodels.MusicFavoriteViewModel

@AndroidEntryPoint
class MusicFavoriteFragment : Fragment() {
    
    private var _binding: FragmentMusicFavoriteBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: MusicFavoriteViewModel by viewModels()
    
    private var isMusicExpanded = false
    private var isMovieExpanded = false
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMusicFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupDropdowns()
        observeViewModel()
    }
    
    private fun setupUI() {
        // Setup entrance animation
        binding.root.alpha = 0f
        binding.root.animate()
            .alpha(1f)
            .setDuration(500)
            .start()
    }
    
    private fun setupDropdowns() {
        // Music dropdown click listener
        binding.musicDropdownButton.setOnClickListener {
            toggleMusicDropdown()
        }
        
        // Movie dropdown click listener
        binding.movieDropdownButton.setOnClickListener {
            toggleMovieDropdown()
        }
    }
    
    private fun toggleMusicDropdown() {
        isMusicExpanded = !isMusicExpanded
        
        if (isMusicExpanded) {
            // Expand music dropdown
            binding.musicDropdownContent.visibility = View.VISIBLE
            binding.musicDropdownIcon.animate()
                .rotation(90f) // Point down
                .setDuration(200)
                .start()
                
            // Slide down animation
            val slideDown = AnimationUtils.loadAnimation(requireContext(), android.R.anim.slide_in_left)
            binding.musicDropdownContent.startAnimation(slideDown)
        } else {
            // Collapse music dropdown
            binding.musicDropdownContent.visibility = View.GONE
            binding.musicDropdownIcon.animate()
                .rotation(0f) // Point right
                .setDuration(200)
                .start()
        }
    }
    
    private fun toggleMovieDropdown() {
        isMovieExpanded = !isMovieExpanded
        
        if (isMovieExpanded) {
            // Expand movie dropdown
            binding.movieDropdownContent.visibility = View.VISIBLE
            binding.movieDropdownIcon.animate()
                .rotation(90f) // Point down
                .setDuration(200)
                .start()
                
            // Slide down animation
            val slideDown = AnimationUtils.loadAnimation(requireContext(), android.R.anim.slide_in_left)
            binding.movieDropdownContent.startAnimation(slideDown)
        } else {
            // Collapse movie dropdown
            binding.movieDropdownContent.visibility = View.GONE
            binding.movieDropdownIcon.animate()
                .rotation(0f) // Point right
                .setDuration(200)
                .start()
        }
    }
    
    private fun observeViewModel() {
        viewModel.favoriteMusic.observe(viewLifecycleOwner) { music ->
            // Update music list (already handled in layout)
        }
        
        viewModel.favoriteVideos.observe(viewLifecycleOwner) { videos ->
            // Update video list (already handled in layout)
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 