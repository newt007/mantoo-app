package com.elapp.mantuapp.presentation.ui.category

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView
import com.elapp.mantuapp.R
import com.elapp.mantuapp.data.entity.Category

class SpinnerCategoryAdapter(
    private val context: Context,
    private val categoryList: List<Category>
) : BaseAdapter(), SpinnerAdapter {

    private lateinit var mInflater: LayoutInflater

    fun setInflate(inflater: LayoutInflater) {
        this.mInflater = inflater
    }

    override fun getCount(): Int = categoryList.size

    override fun getItem(position: Int): Any {
        return categoryList[position]
    }

    override fun getItemId(position: Int): Long {
        return categoryList[position].id.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View? {
        var mView = view
        if (mView == null) {
            mView = mInflater.inflate(R.layout.spinner_item, parent, false)
        }
        val txCategory = mView?.findViewById<TextView>(R.id.tx_category_name)
        txCategory?.text = categoryList[position].categoryName
        return mView
    }
}