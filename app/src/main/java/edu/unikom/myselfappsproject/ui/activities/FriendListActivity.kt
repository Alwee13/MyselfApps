/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import edu.unikom.myselfappsproject.R
import edu.unikom.myselfappsproject.databinding.ActivityFriendListBinding
import edu.unikom.myselfappsproject.ui.adapters.FriendListAdapter
import edu.unikom.myselfappsproject.ui.viewmodels.FriendListViewModel

@AndroidEntryPoint
class FriendListActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityFriendListBinding
    private lateinit var friendListAdapter: FriendListAdapter
    private val viewModel: FriendListViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        binding = ActivityFriendListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.toolbar)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, systemBars.top, v.paddingRight, v.paddingBottom)
            insets
        }
        
        setupToolbar()
        setupRecyclerView()
        observeFriends()
    }
    
    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupRecyclerView() {
        friendListAdapter = FriendListAdapter(
            onFriendClick = { friend ->
                Toast.makeText(this, "Clicked on ${friend.name}", Toast.LENGTH_SHORT).show()
            }
        )
        
        binding.rvFriendsList.apply {
            adapter = friendListAdapter
            layoutManager = LinearLayoutManager(this@FriendListActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }
    
    private fun observeFriends() {
        viewModel.friendsLiveData.observe(this) { friends ->
            if (friends.isNotEmpty()) {
                friendListAdapter.updateFriends(friends)
            }
        }
    }
} 