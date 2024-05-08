package com.stopstone.recyclerviewpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.stopstone.recyclerviewpractice.databinding.ItemImageBinding
import com.stopstone.recyclerviewpractice.databinding.ItemImageTextBinding
import com.stopstone.recyclerviewpractice.databinding.ItemTextBinding
import java.lang.IllegalArgumentException


class ItemsAdapter(private val items: MutableList<Items>) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is TextItem -> VIEW_TYPE_TITLE
            is ImageItem -> VIEW_TYPE_IMAGE
            is Item -> VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            // ViewHolder 클래스 내부에서  ViewHolder를 생성한다.
            // ViewHolder를 생성하는 로직을 ViewHolder 내부로 캡슐화하여 세부사항을 숨긴다.
            VIEW_TYPE_TITLE -> TextItemViewHolder.from(parent)
            VIEW_TYPE_IMAGE -> ImageItemViewHolder.from(parent)
            VIEW_TYPE_ITEM -> ItemViewHolder.from(parent)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = items[position]
//        when (holder) {
//            is TextItemViewHolder -> holder.bind(item as TextItem)
//            is ImageItemViewHolder -> holder.bind(item as ImageItem)
//            is ItemViewHolder -> holder.bind(item as Item)
//        }
//    }
// with 함수를 사용하여 코드 간소화
    // 구현하고 나니 from함수가 반복적으로 사용된다. 재사용할 방법

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder) {
            when (this) {
                is TextItemViewHolder -> bind(item as TextItem)
                is ImageItemViewHolder -> bind(item as ImageItem)
                is ItemViewHolder -> bind(item as Item)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    companion object {
        private const val VIEW_TYPE_TITLE = 0
        private const val VIEW_TYPE_IMAGE = 1
        private const val VIEW_TYPE_ITEM = 2
    }

    class TextItemViewHolder private constructor(private val binding: ItemTextBinding) :
        ViewHolder(binding.root) {
        fun bind(item: TextItem) {
            binding.tvItemText.text = item.text
        }

        //        companion object {
//            fun from(parent: ViewGroup): TextItemViewHolder {
//                val binding = ItemTextBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//                return TextItemViewHolder(binding)
//            }
//        }
        companion object {
            fun from(parent: ViewGroup): TextItemViewHolder {
                val binding = ViewBindingUtil.inflateBinding(parent, ItemTextBinding::inflate)
                return TextItemViewHolder(binding)
            }
        }
    }

    class ImageItemViewHolder private constructor(private val binding: ItemImageBinding) :
        ViewHolder(binding.root) {
        fun bind(item: ImageItem) {
            Glide.with(binding.root)
                .load(item.image)
                .placeholder(R.color.black)
                .into(binding.ivItemImage)
        }

//        companion object {
//            fun from(parent: ViewGroup): ImageItemViewHolder {
//                val binding = ItemImageBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//                return ImageItemViewHolder(binding)
//            }
//        }

        companion object {
            fun from(parent: ViewGroup): ImageItemViewHolder {
                val binding = ViewBindingUtil.inflateBinding(parent, ItemImageBinding::inflate)
                return ImageItemViewHolder(binding)
            }
        }
    }

    class ItemViewHolder private constructor(private val binding: ItemImageTextBinding) :
        ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.tvItemTitle.text = item.text
            Glide.with(binding.root)
                .load(item.image)
                .placeholder(R.color.black)
                .into(binding.ivItemImage)
        }

        //        companion object {
//            fun from(parent: ViewGroup): ItemViewHolder {
//                val binding = ItemImageTextBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//                return ItemViewHolder(binding)
//            }
//        }
        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val binding = ViewBindingUtil.inflateBinding(parent, ItemImageTextBinding::inflate)
                return ItemViewHolder(binding)
            }
        }
    }

    object ViewBindingUtil {
        inline fun <T : ViewBinding> inflateBinding(
            parent: ViewGroup,
            bindingInflater: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> T,
        ): T = bindingInflater(LayoutInflater.from(parent.context), parent, false)
    }
}
