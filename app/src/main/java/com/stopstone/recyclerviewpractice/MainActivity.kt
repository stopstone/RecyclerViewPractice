package com.stopstone.recyclerviewpractice

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.stopstone.recyclerviewpractice.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var addAdapter: ItemsAdapter
    private lateinit var deleteAdapter: ItemsAdapter
    private var addItems = mutableListOf<Items>()
    private var deleteItems = mutableListOf<Items>()
    private var count = 0

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState != null) {
            addItems = ((savedInstanceState.getParcelableArrayList(ADD_ITEMS_KEY, Items::class.java) ?: mutableListOf()))
            deleteItems = ((savedInstanceState.getParcelableArrayList(DELETE_ITEMS_KEY, Items::class.java) ?: mutableListOf()))
        }

        addAdapter = ItemsAdapter(addItems)
        deleteAdapter = ItemsAdapter(deleteItems)

        // onCreate에서 adapter와 연결해야 화면 이탈 후 다시 돌아와도 스크롤이 유지된다.
        with(binding) {
            rvItemAddList.adapter = addAdapter
            btnItemAdd.setOnClickListener {
                when (Random.nextInt(FIRST_NUMBER, LAST_NUMBER)) {
                    0 -> addItems.add(TextItem("$ITEM_TITLE ${++count}"))
                    1 -> addItems.add(ImageItem(ITEM_IMAGE_URL))
                    2 -> addItems.add(Item(ITEM_IMAGE_VIEW_HOLDER_LABEL, ITEM_IMAGE_URL))
                }
                addAdapter.notifyItemInserted(addItems.size - 1)
            }

            rvItemDeleteList.adapter = deleteAdapter
            btnItemDelete.setOnClickListener {
                if (addItems.isEmpty())
                    Toast.makeText(this@MainActivity, EMPTY_MESSAGE, Toast.LENGTH_SHORT).show()
                if (addItems.isNotEmpty()) {
                    deleteItems.add(addItems.first())
                    deleteAdapter.notifyItemInserted(deleteItems.size - 1)

                    addItems.removeAt(FIRST_INDEX)
                    addAdapter.notifyItemRemoved(FIRST_INDEX)
                }
            }
        }
        Log.d(TAG, "$TAG: onCreate()")
    }
    override fun onSaveInstanceState(outState: Bundle) {
        //항상 슈퍼클래스의 메소드 호출
        super.onSaveInstanceState(outState)

        //상태 저장
        outState.putParcelableArrayList(ADD_ITEMS_KEY, ArrayList(addItems))
        outState.putParcelableArrayList(DELETE_ITEMS_KEY, ArrayList(deleteItems))
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "$TAG: onStart()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "$TAG: onRestart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "$TAG: onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "$TAG: onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "$TAG: onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "$TAG: onDestroy()")
    }

    companion object {
        const val TAG = "MainActivity"
        const val FIRST_INDEX = 0

        const val FIRST_NUMBER = 0
        const val LAST_NUMBER = 3
        const val EMPTY_MESSAGE = "제거할 아이템이 없습니다."

        const val ADD_ITEMS_KEY = "ADD_ITEMS"
        const val DELETE_ITEMS_KEY = "DELETE_ITEMS"

        const val ITEM_TITLE = "Items"
        const val ITEM_IMAGE_VIEW_HOLDER_LABEL = "Image View Holder Sample"
        const val ITEM_IMAGE_URL =
            "https://github.com/stopstone/CapstoneProject/assets/77120604/02330cab-bbfd-471e-884c-046eb169f710"
    }
}