package com.stopstone.recyclerviewpractice

sealed class Items

data class Item(val text: String, val image: String) : Items()

data class TextItem(val text: String) : Items()

data class ImageItem(val image: String): Items()
