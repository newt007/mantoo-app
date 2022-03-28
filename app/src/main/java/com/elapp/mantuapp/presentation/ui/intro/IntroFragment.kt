package com.elapp.mantuapp.presentation.ui.intro

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.elapp.mantuapp.R
import com.elapp.mantuapp.databinding.FragmentIntroBinding
import com.elapp.mantuapp.presentation.ui.profile.ProfileViewModel
import com.elapp.mantuapp.utils.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class IntroFragment : Fragment() {

    private val userViewModel: ProfileViewModel by viewModels()

    private var _fragmentIntroBinding: FragmentIntroBinding? = null
    private val binding get() = _fragmentIntroBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentIntroBinding = FragmentIntroBinding.inflate(inflater, container, false)
        return _fragmentIntroBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAction()
    }

    private fun setupAction() {
        binding.btnSave.setOnClickListener {
            val name = binding.edtName.text.toString()
            saveDataForFirstTime(name)
        }
    }

    private fun saveDataForFirstTime(name: String) {
        when {
            name.isEmpty() -> {
                binding.edtName.error = "Nama tidak boleh kosong"
            }
            name.length < 4 -> {
                binding.edtName.error = "Nama harus lebih dari 4 karakter"
            }
            name.isNotEmpty() && name.length > 4 -> {
                updateLogin()
                userViewModel.saveUserDataForFirstTime(name)
                findNavController().navigate(R.id.action_introFragment_to_homeFragment)
            }
            else -> {
                Timber.e("Unknown error occured")
            }
        }
    }

    private fun updateLogin() {
        SessionManager(requireContext()).updateIsLogin(true)
    }

}