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
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import edu.unikom.myselfappsproject.databinding.FragmentHomeBinding
import edu.unikom.myselfappsproject.ui.viewmodels.HomeViewModel

@AndroidEntryPoint
class HomeFragment : Fragment() {
    
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: HomeViewModel by viewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeViewModel()
    }
    
    private fun setupUI() {
        // Add entrance animations
        binding.root.alpha = 0f
        binding.root.animate()
            .alpha(1f)
            .setDuration(500)
            .start()
    }
    
    private fun observeViewModel() {
        viewModel.profileData.observe(viewLifecycleOwner) { profile ->
            profile?.let {
                binding.profileSection.profileName.text = it.name
                // TODO: Load profile photo using it.photoPath
                // TODO: Update about description using it.aboutDescription
            }
        }
        
        viewModel.interestData.observe(viewLifecycleOwner) { interests ->
            interests?.let {
                binding.interestSection.hobbyText.text = it.hobbies
                binding.interestSection.makesText.text = it.makes
                binding.interestSection.mikesText.text = it.mikes
                binding.interestSection.citaCitaText.text = it.goals
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 