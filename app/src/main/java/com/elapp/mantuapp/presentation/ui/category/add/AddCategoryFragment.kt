package com.elapp.mantuapp.presentation.ui.category.add

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.elapp.mantuapp.R
import com.elapp.mantuapp.data.entity.Category
import com.elapp.mantuapp.databinding.FragmentAddCategoryBinding
import com.elapp.mantuapp.presentation.ui.category.CategoryViewModel
import com.elapp.mantuapp.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCategoryFragment: Fragment() {

    private val categoryViewModel: CategoryViewModel by viewModels()

    private var _fragmentAddCategoryBinding: FragmentAddCategoryBinding? = null
    private val binding get() = _fragmentAddCategoryBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentAddCategoryBinding = FragmentAddCategoryBinding.inflate(inflater, container, false)
        return _fragmentAddCategoryBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddCategory.setOnClickListener {
            val categoryName = binding.edtCategoryName.text.toString()
            when {
                categoryName.isEmpty() -> {
                    binding.edtCategoryName.error = "Nama kategori tidak boleh kosong"
                }
                categoryName.length < 4 -> {
                    binding.edtCategoryName.error = "Nama kategori tidak boleh kurang dari 4 karakter"
                }
                else -> {
                    val category = Category(
                        categoryName = categoryName
                    )
                    addNewCategory(view, category)
                }
            }
        }
    }

    private fun addNewCategory(mView: View, category: Category) {
        categoryViewModel.addNewCategory(category)
        view?.showSnackbar(mView, getString(R.string.add_category_success_message))
    }
    
}