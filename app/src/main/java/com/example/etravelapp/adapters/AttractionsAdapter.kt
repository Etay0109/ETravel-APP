package com.example.etravelapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.etravelapp.databinding.ItemAttractionCardPlaceholderBinding
import com.example.etravelapp.model.Attraction
import com.example.etravelapp.utilities.ImageLoader

class AttractionsAdapter(
    private val attractions: MutableList<Attraction>
) : RecyclerView.Adapter<AttractionsAdapter.AttractionViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AttractionViewHolder {

        val binding = ItemAttractionCardPlaceholderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AttractionViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: AttractionViewHolder,
        position: Int
    ) {
        val attraction = attractions[position]

        with(holder.binding) {
            itemLBLAttractionName.text = attraction.name

            ImageLoader.getInstance().loadImage(
                attraction.poster,
                itemIMGAttraction
            )
        }
    }

    override fun getItemCount(): Int = attractions.size

    fun updateData(newList: List<Attraction>) {
        attractions.clear()
        attractions.addAll(newList)
        notifyDataSetChanged()
    }

    inner class AttractionViewHolder(
        val binding: ItemAttractionCardPlaceholderBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
