package com.example.catalogue.component.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catalogue.databinding.GalleryItemBinding


class GalleryAdapter(
    private val vModel: DetailsViewModel,
    var galleryItems: List<String>?
) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            GalleryItemBinding.inflate(LayoutInflater.from(parent.context))
                .apply { viewModel = vModel })
    }

    override fun getItemCount(): Int = galleryItems?.size ?: 0

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        galleryItems?.get(position)?.let { holder.bind(it) }
    }
}

class CustomViewHolder(private val binding: GalleryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.imageItem = item
    }
}