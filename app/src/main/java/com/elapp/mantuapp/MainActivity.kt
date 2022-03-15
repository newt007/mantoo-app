package com.elapp.mantuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elapp.mantuapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _activityMain: ActivityMainBinding? = null
    private val binding get() = _activityMain!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_activityMain?.root)
    }
}