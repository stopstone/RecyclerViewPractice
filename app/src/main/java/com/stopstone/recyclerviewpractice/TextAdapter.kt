package com.stopstone.recyclerviewpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.stopstone.recyclerviewpractice.databinding.ItemTextBinding


class TextAdapter(private val items: MutableList<Items>) :
    RecyclerView.Adapter<TextAdapter.TextItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        return TextItemViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size


    class TextItemViewHolder(private val binding: ItemTextBinding) : ViewHolder(binding.root) {
        fun bind(item: Items) {
            binding.tvItemText.text = item.toString()
        }

        companion object {
            fun from(parent: ViewGroup): TextItemViewHolder {
                val binding = ItemTextBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return TextItemViewHolder(binding)
            }
        }
    }
}

