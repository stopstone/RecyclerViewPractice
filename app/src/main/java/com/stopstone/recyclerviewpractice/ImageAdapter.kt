package com.stopstone.recyclerviewpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.stopstone.recyclerviewpractice.databinding.ItemImageBinding


class ImageAdapter(private val items: MutableList<Items>) :
    RecyclerView.Adapter<ImageAdapter.ImageItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageItemViewHolder {
        return ImageItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ImageItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ImageItemViewHolder(private val binding: ItemImageBinding) :
        ViewHolder(binding.root) {
        fun bind(item: Items) {
            Glide.with(binding.root)
                .load(item)
                .placeholder(R.color.black)
                .into(binding.ivItemImage)
        }

        companion object {
            fun from(parent: ViewGroup): ImageItemViewHolder {
                val binding = ItemImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ImageItemViewHolder(binding)
            }
        }
    }
}
