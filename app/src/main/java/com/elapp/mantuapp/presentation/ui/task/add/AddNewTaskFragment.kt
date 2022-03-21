package com.elapp.mantuapp.presentation.ui.task.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.elapp.mantuapp.R
import com.elapp.mantuapp.databinding.FragmentAddNewTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewTaskFragment: Fragment() {

    private var _fragmentAddNewTaskBinding: FragmentAddNewTaskBinding? = null
    private val binding get() = _fragmentAddNewTaskBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentAddNewTaskBinding = FragmentAddNewTaskBinding.inflate(inflater, container, false)
        return _fragmentAddNewTaskBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.apply {
            setBackgroundColor(ContextCompat.getColor(context, R.color.dark_blue_200))
            title = getString(R.string.add_new_task)
            setTitleTextColor(ContextCompat.getColor(context, R.color.white))
            navigationIcon = AppCompatResources.getDrawable(context, R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                it.findNavController().popBackStack()
            }
        }
    }

}