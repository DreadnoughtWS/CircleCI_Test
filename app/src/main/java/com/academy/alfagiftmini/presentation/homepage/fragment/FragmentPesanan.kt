package com.academy.alfagiftmini.presentation.homepage.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.academy.alfagiftmini.databinding.FragmentPesananBinding
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity


class FragmentPesanan : Fragment() {
    private lateinit var binding: FragmentPesananBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBtnBelanja()
    }

    private fun setBtnBelanja() {
        binding.btnYukBelanja.setOnClickListener {
            (requireActivity() as MainActivity).setCurrentFragment(FragmentBelanja())
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPesananBinding.inflate(inflater)
        return binding.root
    }


}