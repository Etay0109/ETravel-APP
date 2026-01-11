package com.example.etravelapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.etravelapp.databinding.ItemPopularDestinationBinding
import com.example.etravelapp.model.DestinationItem
import com.example.etravelapp.utilities.ImageLoader
import com.example.etravelapp.interfaces.DestinationCallback
import com.example.etravelapp.utilities.DestinationType


class PopularDestinationsAdapter(
    private val destinations: MutableList<DestinationItem>
) : RecyclerView.Adapter<PopularDestinationsAdapter.DestinationViewHolder>() {

    var destinationCallback: DestinationCallback? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DestinationViewHolder {
        val binding = ItemPopularDestinationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DestinationViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DestinationViewHolder,
        position: Int
    ) {
        with(holder) {
            with(getItem(position)) {

                binding.popularLBLCity.text = city
                binding.popularLBLCountry.text = country

                ImageLoader.getInstance().loadImage(
                    poster,
                    binding.popularIMGDestination
                )

                binding.popularIMGFavorite.setImageResource(
                    if (isFavorite)
                        com.example.etravelapp.R.drawable.ic_favorite
                    else
                        com.example.etravelapp.R.drawable.ic_heart
                )

                binding.popularIMGFavorite.setOnClickListener {
                    destinationCallback?.favoriteClicked(
                        this,
                        position,
                        DestinationType.POPULAR
                    )
                }



                binding.root.setOnClickListener {
                    destinationCallback?.destinationClicked(this)
                }
            }
        }
    }

    override fun getItemCount(): Int = destinations.size

    fun updateData(newList: List<DestinationItem>) {    // Replace the current list with new data and update the UI
        destinations.clear()
        destinations.addAll(newList)
        notifyDataSetChanged()
    }


    fun getItem(position: Int): DestinationItem = destinations[position]

    inner class DestinationViewHolder(
        val binding: ItemPopularDestinationBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
