package com.elapp.mantuapp.presentation.ui.priority

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView
import com.elapp.mantuapp.R
import com.elapp.mantuapp.data.entity.Priority

class SpinnerPriorityAdapter(
    private val priorityList: List<Priority>
): BaseAdapter(), SpinnerAdapter {

    private lateinit var mInflater: LayoutInflater

    fun setInflate(inflater: LayoutInflater) {
        this.mInflater = inflater
    }

    override fun getCount(): Int = priorityList.size

    override fun getItem(position: Int) = priorityList[position]

    override fun getItemId(position: Int) = priorityList[position].priorityId

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View? {
        var mView = view
        if (mView == null) {
            mView = mInflater.inflate(R.layout.spinner_item, parent, false)
        }
        val txCategory = mView?.findViewById<TextView>(R.id.tx_category_name)
        txCategory?.text = priorityList[position].priorityName
        txCategory?.setTextColor(priorityList[position].priorityColor)
        return mView
    }

}