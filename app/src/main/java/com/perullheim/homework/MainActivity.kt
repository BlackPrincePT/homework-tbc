package com.perullheim.homework

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.perullheim.homework.databinding.ActivityMainBinding
import com.perullheim.homework.model.Item

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setup()
    }

    private fun setup() {
        val itemsAdapter = ItemsRecyclerViewAdapter()
        val categoriesAdapter = CategoriesRecyclerViewAdapter()

        with(binding) {
            // ====== Items Recycler View ====== \\
            rvItems.layoutManager = GridLayoutManager(this@MainActivity, 2)
            rvItems.addItemDecoration(RecyclerViewItemDecoration(60))

            rvItems.adapter = itemsAdapter
            itemsAdapter.onFavoriteAdded = {
                categoriesAdapter.checkFavoriteCategory()
            }

            // ====== Categories Recycler View ====== \\
            rvCategories.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            rvCategories.addItemDecoration(RecyclerViewItemDecoration(20, 60))

            rvCategories.adapter = categoriesAdapter
            categoriesAdapter.onSelectionChanged = { selectedCategory ->
                itemsAdapter.currentFilter = selectedCategory
            }
        }
    }
}