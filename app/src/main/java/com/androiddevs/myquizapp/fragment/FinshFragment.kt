package com.androiddevs.myquizapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.androiddevs.myquizapp.R
import com.androiddevs.myquizapp.databinding.FragmentFinshBinding


class FinshFragment : Fragment() {

    private lateinit var binding : FragmentFinshBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        binding = FragmentFinshBinding.inflate(inflater)
        binding.button1.setOnClickListener {
            findNavController().navigate(R.id.action_finshFragment_to_mainFragment)

        }
        return binding.root
    }


}