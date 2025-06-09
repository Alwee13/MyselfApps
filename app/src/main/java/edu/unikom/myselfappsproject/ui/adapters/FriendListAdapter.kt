/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.unikom.myselfappsproject.data.models.Friend
import edu.unikom.myselfappsproject.databinding.ItemFriendCardBinding

class FriendListAdapter(
    private var friends: List<Friend> = emptyList(),
    private val onFriendClick: (Friend) -> Unit = {}
) : RecyclerView.Adapter<FriendListAdapter.FriendViewHolder>() {

    class FriendViewHolder(private val binding: ItemFriendCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(friend: Friend, onFriendClick: (Friend) -> Unit) {
            binding.apply {
                ivFriendPhoto.setImageResource(friend.photoResourceId)
                tvFriendName.text = friend.name
                
                // Add click animation and listener
                root.setOnClickListener {
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
                    onFriendClick(friend)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val binding = ItemFriendCardBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return FriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.bind(friends[position], onFriendClick)
    }

    override fun getItemCount(): Int = friends.size

    fun updateFriends(newFriends: List<Friend>) {
        friends = newFriends
        notifyDataSetChanged()
    }
} 