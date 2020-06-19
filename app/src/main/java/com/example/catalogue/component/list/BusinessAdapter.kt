package com.example.catalogue.component.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catalogue.data.beans.Businesses
import com.example.catalogue.databinding.ItemBusinessBinding

class BusinessAdapter(
    private val listViewModel: ListViewModel,
    var list: List<Businesses>?
) :
    RecyclerView.Adapter<CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder =
        CustomViewHolder(
            ItemBusinessBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listViewModel
        )

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        list?.get(position)?.let { holder.bind(it) }
    }
}

class CustomViewHolder(
    private val binding: ItemBusinessBinding,
    private val listViewModel: ListViewModel
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(businesses: Businesses) {
        binding.business = businesses
        binding.root.setOnClickListener {
            listViewModel.itemSelected(businesses.id)
        }
    }
}