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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import edu.unikom.myselfappsproject.databinding.FragmentGalleryBinding
import edu.unikom.myselfappsproject.ui.adapters.GalleryAdapter
import edu.unikom.myselfappsproject.ui.viewmodels.GalleryViewModel

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    companion object {
        private const val TAG = "GalleryFragment"
        private const val SPAN_COUNT = 2 // Pinterest-style grid columns
    }

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GalleryViewModel by viewModels()
    private lateinit var galleryAdapter: GalleryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupAdapter() {
        galleryAdapter = GalleryAdapter(
            context = requireContext(),
            onItemClick = { gallery ->
                // Handle item click if needed
            }
        )
    }

    private fun setupRecyclerView() {
        binding.rvGallery.apply {
            adapter = galleryAdapter
            
            // Pinterest-style staggered grid layout
            layoutManager = StaggeredGridLayoutManager(
                SPAN_COUNT,
                StaggeredGridLayoutManager.VERTICAL
            ).apply {
                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            }
            
            // Add item decoration for consistent spacing
            addItemDecoration(GalleryItemDecoration(16))
            
            // Smooth scrolling
            isNestedScrollingEnabled = true
            
            // Improve performance
            setHasFixedSize(false) // Pinterest cards have different heights
            setItemViewCacheSize(20)
        }
    }

    private fun observeViewModel() {
        // Observe gallery images
        viewModel.galleryImages.observe(viewLifecycleOwner) { images ->
            galleryAdapter.updateGallery(images)
            updateEmptyState(images.isEmpty())
        }

        // Observe loading state
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Observe empty state
        viewModel.isEmpty.observe(viewLifecycleOwner) { isEmpty ->
            updateEmptyState(isEmpty)
        }
    }
    
    private fun updateEmptyState(isEmpty: Boolean) {
        if (isEmpty) {
            binding.layoutEmptyState.visibility = View.VISIBLE
            binding.rvGallery.visibility = View.GONE
        } else {
            binding.layoutEmptyState.visibility = View.GONE
            binding.rvGallery.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Custom ItemDecoration for Pinterest-style spacing
    private inner class GalleryItemDecoration(private val spacing: Int) :
        androidx.recyclerview.widget.RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: android.graphics.Rect,
            view: View,
            parent: androidx.recyclerview.widget.RecyclerView,
            state: androidx.recyclerview.widget.RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view)
            val spanIndex = (view.layoutParams as StaggeredGridLayoutManager.LayoutParams).spanIndex

            // Apply spacing
            outRect.left = if (spanIndex == 0) spacing else spacing / 2
            outRect.right = if (spanIndex == SPAN_COUNT - 1) spacing else spacing / 2
            outRect.top = if (position < SPAN_COUNT) spacing else spacing / 2
            outRect.bottom = spacing / 2
        }
    }
} 