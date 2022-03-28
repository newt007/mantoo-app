package com.elapp.mantuapp.presentation.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.elapp.mantuapp.R
import com.elapp.mantuapp.databinding.FragmentCategoryBinding
import com.elapp.mantuapp.presentation.ui.category.add.AddCategoryFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private val categoryViewModel: CategoryViewModel by viewModels()

    private var _fragmentCategoryBinding: FragmentCategoryBinding? = null
    private val binding get() = _fragmentCategoryBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentCategoryBinding = FragmentCategoryBinding.inflate(inflater, container, false)
        return _fragmentCategoryBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllCategory()

        initUI()
        initAction()
    }

    private fun initUI() {
        binding.toolbar.apply {
            setBackgroundColor(ContextCompat.getColor(context, R.color.dark_blue_200))
            title = getString(R.string.tambah_kategori_baru)
            setTitleTextColor(ContextCompat.getColor(context, R.color.white))
            navigationIcon = AppCompatResources.getDrawable(context, R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                it.findNavController().popBackStack()
            }
        }
    }

    private fun initAction() {
        binding.btnAddCategory.setOnClickListener {
            it.findNavController().navigate(R.id.action_categoryFragment_to_addCategoryFragment)
        }
    }

    private fun getAllCategory() {
        categoryViewModel.getAllCategory().observe(viewLifecycleOwner) { categoryList ->
            val categoryAdapter = CategoryAdapter(categoryList)
            binding.apply {
                rvCategory.adapter = categoryAdapter
                rvCategory.layoutManager = LinearLayoutManager(context)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getAllCategory()
    }

}