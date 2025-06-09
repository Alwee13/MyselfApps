/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */

package edu.unikom.myselfappsproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.unikom.myselfappsproject.data.entities.DailyActivityEntity
import edu.unikom.myselfappsproject.databinding.ItemDailyActivityBinding

class DailyActivityAdapter : ListAdapter<DailyActivityEntity, DailyActivityAdapter.ActivityViewHolder>(ActivityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val binding = ItemDailyActivityBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ActivityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ActivityViewHolder(
        private val binding: ItemDailyActivityBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(activity: DailyActivityEntity) {
            binding.apply {
                tvActivityEmoji.text = activity.emoji
                tvActivityTitle.text = activity.title
            }
        }
    }

    fun updateActivities(activities: List<DailyActivityEntity>) {
        submitList(activities)
    }
}

class ActivityDiffCallback : DiffUtil.ItemCallback<DailyActivityEntity>() {
    override fun areItemsTheSame(oldItem: DailyActivityEntity, newItem: DailyActivityEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DailyActivityEntity, newItem: DailyActivityEntity): Boolean {
        return oldItem == newItem
    }
} 