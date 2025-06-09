/**
 * NIM: 10122414
 * Nama: Rafi Alwi Saputra
 * kelas: IF-11
 * Mata Kuliah: Andro 3
 */
package edu.unikom.myselfappsproject.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import edu.unikom.myselfappsproject.R
import edu.unikom.myselfappsproject.data.entities.GalleryEntity

class GalleryAdapter(
    private val context: Context,
    private var galleryList: List<GalleryEntity> = emptyList(),
    private val onItemClick: (GalleryEntity) -> Unit = {}
) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    companion object {
        private const val TAG = "GalleryAdapter"
    }

    inner class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardGalleryItem)
        val imageView: ImageView = itemView.findViewById(R.id.ivGalleryImage)
        
        fun bind(gallery: GalleryEntity) {
            // Set dynamic height for Pinterest effect
            val layoutParams = cardView.layoutParams
            layoutParams.height = (gallery.height * context.resources.displayMetrics.density).toInt()
            cardView.layoutParams = layoutParams
            
            // Load image with Glide - simple and clean
            Glide.with(context)
                .load(gallery.imageResource)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_image_placeholder)
                        .error(R.drawable.ic_image_error)
                        .centerCrop()
                )
                .into(imageView)
            
            // Simple click listener
            cardView.setOnClickListener {
                onItemClick(gallery)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gallery_pinterest, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(galleryList[position])
    }

    override fun getItemCount(): Int = galleryList.size

    fun updateGallery(newGalleryList: List<GalleryEntity>) {
        galleryList = newGalleryList
        notifyDataSetChanged()
    }
} 