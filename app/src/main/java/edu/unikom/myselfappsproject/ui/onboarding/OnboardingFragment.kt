/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.unikom.myselfappsproject.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {
    
    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    
    private var title: String? = null
    private var description: String? = null
    private var imageRes: String? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            description = it.getString(ARG_DESCRIPTION)
            imageRes = it.getString(ARG_IMAGE_RES)
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }
    
    private fun setupUI() {
        binding.titleText.text = title
        binding.descriptionText.text = description
        
        // Set image based on type
        val imageResource = when (imageRes) {
            "welcome" -> getWelcomeDrawable()
            "activity" -> getActivityDrawable()
            "profile" -> getProfileDrawable()
            else -> getWelcomeDrawable()
        }
        

    }

    private fun getWelcomeDrawable(): Int {
        return android.R.drawable.ic_dialog_info // Temporary, will be replaced with custom drawable
    }

    private fun getActivityDrawable(): Int {
        return android.R.drawable.ic_menu_gallery
    }

    private fun getProfileDrawable(): Int {
        return android.R.drawable.ic_menu_myplaces
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    companion object {
        private const val ARG_TITLE = "arg_title"
        private const val ARG_DESCRIPTION = "arg_description"
        private const val ARG_IMAGE_RES = "arg_image_res"
        
        fun newInstance(title: String, description: String, imageRes: String): OnboardingFragment {
            return OnboardingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_DESCRIPTION, description)
                    putString(ARG_IMAGE_RES, imageRes)
                }
            }
        }
    }
} 