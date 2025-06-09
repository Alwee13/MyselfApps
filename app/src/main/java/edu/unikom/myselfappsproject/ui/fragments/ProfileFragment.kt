/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.ui.fragments

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import edu.unikom.myselfappsproject.R
import edu.unikom.myselfappsproject.databinding.DialogAboutBinding
import edu.unikom.myselfappsproject.databinding.FragmentProfileBinding
import edu.unikom.myselfappsproject.ui.viewmodels.HomeViewModel

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    
    // Using HomeViewModel to get the same data as Home
    private val homeViewModel: HomeViewModel by viewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupProfile()
        setupContactListeners()
        setupAboutDialog()
    }
    
    private fun setupProfile() {
        // Profile picture - using correct way to access include layout views
        Glide.with(this)
            .load(R.drawable.profile_photo)
            .circleCrop()
            .into(binding.profileSection.profileImage)

        // Profile name - using direct access to include layout view
        binding.profileSection.profileName.text = "Rafi Alwi Saputra"

        // About description - using direct access to include layout view
        binding.aboutSection.aboutDescription.text = "seorang pecinta sepak bola yang suka bermain dan menonton pertandingan, penggemar musik dari berbagai genre, dan gamer yang menikmati tantangan baik di dunia virtual maupun nyata. Saya juga suka menikmati waktu sendiri dengan menonton film, terutama sci-fi dan teknologi futuristik, karena saya lebih nyaman di lingkungan tenang daripada keramaian. Impian saya adalah menjadi AI Engineer karena terinspirasi oleh kecanggihan kecerdasan buatan dan bagaimana teknologi bisa membentuk masa depan."
    }

    private fun setupAboutDialog() {
        binding.cardAbout.setOnClickListener {
            showAboutDialog()
        }
    }

    private fun showAboutDialog() {
        val dialog = Dialog(requireContext())
        val dialogBinding = DialogAboutBinding.inflate(layoutInflater)
        
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        
        // Set dialog size
        dialog.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.9).toInt(),
            android.view.ViewGroup.LayoutParams.WRAP_CONTENT
        )
        
        // Close button action
        dialogBinding.btnCloseDialog.setOnClickListener {
            dialog.dismiss()
        }
        
        dialog.show()
    }
    
    private fun setupContactListeners() {
        // Phone call button
        binding.btnCallPhone.setOnClickListener {
            val phoneNumber = binding.tvPhoneNumber.text.toString()
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Tidak dapat membuka aplikasi telepon", Toast.LENGTH_SHORT).show()
            }
        }

        // Email button
        binding.btnSendEmail.setOnClickListener {
            val email = binding.tvEmail.text.toString()
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$email")
                putExtra(Intent.EXTRA_SUBJECT, "Hello from MyPersona")
            }
            try {
                startActivity(Intent.createChooser(intent, "Kirim Email"))
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Tidak dapat membuka aplikasi email", Toast.LENGTH_SHORT).show()
            }
        }

        // Instagram button
        binding.btnInstagram.setOnClickListener {
            openSocialMedia("instagram://user?username=rafiii13_", "https://www.instagram.com/rafiii13_/")
        }

        // GitHub button
        binding.btnGithub.setOnClickListener {
            openSocialMedia("", "https://github.com/Alwee13")
        }

        // TikTok button
        binding.btnTiktok.setOnClickListener {
            openSocialMedia("", "https://www.tiktok.com/@clyke22")
        }

        // Open Map button
        binding.btnOpenMap.setOnClickListener {
            openMapsLocation()
        }
    }
    
    private fun openSocialMedia(appIntent: String, webUrl: String) {
        try {
            // Try to open the app first
            if (appIntent.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(appIntent))
                intent.setPackage(getPackageNameForSocialMedia(appIntent))
                startActivity(intent)
            } else {
                throw Exception("App intent not available")
            }
        } catch (e: Exception) {
            // If app is not installed, open in browser
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Tidak dapat membuka link", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun getPackageNameForSocialMedia(intent: String): String? {
        return when {
            intent.contains("instagram") -> "com.instagram.android"
            intent.contains("tiktok") -> "com.ss.android.ugc.trill"
            else -> null
        }
    }

    private fun openMapsLocation() {
        try {
            val latitude = -6.837889
            val longitude = 107.348115
            val label = "Rafi Alwi Saputra"
            val address = "Kp. Pabrik, RT.02 RW.08 Desa. Mandalawangi, Kec. Cipatat, Kab. Bandung Barat"
            
            // Coba buka Google Maps dulu
            val gmmIntentUri = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude($label)")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            
            if (mapIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(mapIntent)
            } else {
                // Fallback ke browser jika Google Maps tidak ada
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://maps.google.com/?q=$latitude,$longitude")
                )
                startActivity(browserIntent)
            }
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Tidak dapat membuka peta", Toast.LENGTH_SHORT).show()
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 