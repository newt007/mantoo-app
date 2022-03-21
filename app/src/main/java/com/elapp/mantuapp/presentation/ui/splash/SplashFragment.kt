package com.elapp.mantuapp.presentation.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.se.omapi.Session
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.elapp.mantuapp.R
import com.elapp.mantuapp.databinding.FragmentSplashBinding
import com.elapp.mantuapp.utils.SessionManager

class SplashFragment : Fragment() {

    private var _fragmentSplashBinding: FragmentSplashBinding? = null
    private val binding get() = _fragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentSplashBinding = FragmentSplashBinding.inflate(inflater, container, false)
        return _fragmentSplashBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isLogin = SessionManager(requireContext()).getIsLogin
        Handler(Looper.getMainLooper()).postDelayed({
            if (isLogin) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_introFragment)
            }
        }, 3000L)
    }

}