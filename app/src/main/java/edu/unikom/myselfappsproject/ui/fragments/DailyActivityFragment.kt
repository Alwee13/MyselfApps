/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */
package edu.unikom.myselfappsproject.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import edu.unikom.myselfappsproject.databinding.FragmentDailyActivityBinding
import edu.unikom.myselfappsproject.ui.activities.FriendListActivity
import edu.unikom.myselfappsproject.ui.adapters.DailyActivityAdapter
import edu.unikom.myselfappsproject.ui.viewmodels.DailyActivityViewModel

@AndroidEntryPoint
class DailyActivityFragment : Fragment() {
    
    private var _binding: FragmentDailyActivityBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: DailyActivityViewModel by viewModels()
    private lateinit var dailyActivityAdapter: DailyActivityAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyActivityBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupUI()
        observeViewModel()
    }
    
    private fun setupRecyclerView() {
        dailyActivityAdapter = DailyActivityAdapter()
        
        binding.rvDailyActivities.apply {
            adapter = dailyActivityAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
    
    private fun setupUI() {
        setupButtonListeners()
        
        // Add entrance animations
        binding.root.alpha = 0f
        binding.root.animate()
            .alpha(1f)
            .setDuration(500)
            .start()
    }
    
    private fun setupButtonListeners() {
        // Friend List Button Click
        binding.btnFriendList.setOnClickListener {
            // Add animation
            it.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(100)
                .withEndAction {
                    it.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(100)
                        .start()
                }
                .start()
            
            // Navigate to Friend List
            navigateToFriendList()
        }
    }
    
    private fun navigateToFriendList() {
        val intent = Intent(requireContext(), FriendListActivity::class.java)
        startActivity(intent)
    }
    
    private fun observeViewModel() {
        viewModel.dailyActivities.observe(viewLifecycleOwner) { activities ->
            if (activities.isNotEmpty()) {
                dailyActivityAdapter.updateActivities(activities)
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 