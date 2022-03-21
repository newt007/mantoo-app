package com.elapp.mantuapp.presentation.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elapp.mantuapp.data.entity.Category
import com.elapp.mantuapp.databinding.CategoryItemRowBinding

class CategoryAdapter(private val categoryList: List<Category>): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CategoryViewHolder {
        val binding = CategoryItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
        categoryList[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = categoryList.size

    inner class CategoryViewHolder(private val binding: CategoryItemRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.apply {
                txCategoryName.text = category.categoryName
            }
        }
    }

}