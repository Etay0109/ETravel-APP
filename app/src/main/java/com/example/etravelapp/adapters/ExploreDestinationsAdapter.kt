package com.example.etravelapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.etravelapp.databinding.ItemExploreDestinationBinding
import com.example.etravelapp.interfaces.DestinationCallback
import com.example.etravelapp.model.DestinationItem
import com.example.etravelapp.utilities.DestinationType
import com.example.etravelapp.utilities.ImageLoader

class ExploreDestinationsAdapter(
    private val destinations: MutableList<DestinationItem>
) : RecyclerView.Adapter<ExploreDestinationsAdapter.ExploreViewHolder>() {

    var destinationCallback: DestinationCallback? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExploreViewHolder {
        val binding = ItemExploreDestinationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExploreViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ExploreViewHolder,
        position: Int
    ) {
        val destination = destinations[position]

        with(holder.binding) {
            itemExploreLBLName.text = destination.city
            itemExploreLBLCountry.text = destination.country
            itemExploreLBLFlightTime.text =
                destination.flightTime ?: ""

            ImageLoader.getInstance().loadImage(
                destination.poster,
                itemExploreIMGPhoto
            )

            itemExploreIMGFavorite.setImageResource(
                if (destination.isFavorite)
                    com.example.etravelapp.R.drawable.ic_favorite
                else
                    com.example.etravelapp.R.drawable.ic_heart
            )

            itemExploreIMGFavorite.setOnClickListener {
                destinationCallback?.favoriteClicked(
                    destination,
                    position,
                    DestinationType.EXPLORE
                )
            }


            root.setOnClickListener {
                destinationCallback?.destinationClicked(destination)
            }
        }
    }

    override fun getItemCount(): Int = destinations.size

    fun updateData(newList: List<DestinationItem>) {  // Replace the current list with new data and update the UI
        destinations.clear()
        destinations.addAll(newList)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        destinations.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ExploreViewHolder(
        val binding: ItemExploreDestinationBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
