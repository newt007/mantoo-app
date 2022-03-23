package com.elapp.mantuapp.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.elapp.mantuapp.R
import com.elapp.mantuapp.databinding.FragmentHomeBinding
import com.elapp.mantuapp.presentation.ui.task.TaskAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _fragmentHomeBinding: FragmentHomeBinding? = null
    private val binding get() = _fragmentHomeBinding!!

    private lateinit var fabOpen: Animation
    private lateinit var fabClose: Animation
    private lateinit var fabClock: Animation
    private lateinit var fabAntiClock: Animation

    private var fabIsOpen = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return _fragmentHomeBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAnimation()
        setupFabButton()

        binding.fabAddCategory.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_categoryFragment)
        }

        binding.fabAddTask.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_addNewTaskFragment)
        }

        getUserData()
        getAllTaskData()
    }

    private fun initAnimation() {
        fabOpen = AnimationUtils.loadAnimation(context?.applicationContext, R.anim.fab_open)
        fabClock =
            AnimationUtils.loadAnimation(context?.applicationContext, R.anim.fab_rotate_clock)
        fabClose = AnimationUtils.loadAnimation(context?.applicationContext, R.anim.fab_close)
        fabAntiClock =
            AnimationUtils.loadAnimation(context?.applicationContext, R.anim.fab_rotate_anticlock)
    }

    private fun setupFabButton() {
        binding.mainFab.setOnClickListener {
            if (fabIsOpen) {
                binding.apply {
                    fabUnknown.startAnimation(fabClose)
                    fabAddCategory.startAnimation(fabClose)
                    fabAddTask.startAnimation(fabClose)
                    mainFab.startAnimation(fabAntiClock)
                    fabUnknown.isClickable = false
                    fabAddCategory.isClickable = false
                    fabAddTask.isClickable = false
                }
                fabIsOpen = false;

            } else {
                binding.apply {
                    fabUnknown.startAnimation(fabOpen)
                    fabAddCategory.startAnimation(fabOpen)
                    fabAddTask.startAnimation(fabOpen)
                    mainFab.startAnimation(fabClock)
                    fabUnknown.isClickable = true
                    fabAddCategory.isClickable = true
                    fabAddTask.isClickable = true
                }
                fabIsOpen = true
            }
        }
    }

    private fun getUserData() {
        homeViewModel.getUserData(0).observe(viewLifecycleOwner) { user ->
            binding.txName.text = resources.getString(R.string.welcome_messages, user.name)
        }
    }

    private fun getAllTaskData() {
        homeViewModel.getAllTask().observe(viewLifecycleOwner) { task ->
            val adapter = TaskAdapter(task)
            binding.rvTask.adapter = adapter
            binding.rvTask.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onResume() {
        super.onResume()
        fabIsOpen = false
        getAllTaskData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentHomeBinding = null
    }

}