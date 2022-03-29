package com.elapp.mantuapp.presentation.ui.category.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.elapp.mantuapp.R
import com.elapp.mantuapp.data.entity.Category
import com.elapp.mantuapp.databinding.FragmentAddCategoryBinding
import com.elapp.mantuapp.presentation.ui.category.CategoryViewModel
import com.elapp.mantuapp.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCategoryFragment : Fragment() {

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
            val categoryName = binding.edtCategoryName.text.toString()
            when {
                categoryName.isEmpty() -> {
                    binding.edtCategoryName.error = "Nama kategori tidak boleh kosong"
                }
                categoryName.length < 4 -> {
                    binding.edtCategoryName.error =
                        "Nama kategori tidak boleh kurang dari 4 karakter"
                }
                else -> {
                    val category = Category(
                        categoryName = categoryName
                    )
                    addNewCategory(requireView(), category)
                }
            }
        }
    }

    private fun addNewCategory(mView: View, category: Category) {
        categoryViewModel.addNewCategory(category)
        view?.showSnackbar(mView, getString(R.string.add_category_success_message))
        findNavController().popBackStack()
    }

}