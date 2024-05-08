package com.stopstone.recyclerviewpractice

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
sealed class Items : Parcelable

data class Item(val text: String, val image: String) : Items()

data class TextItem(val text: String) : Items()

data class ImageItem(val image: String): Items()
