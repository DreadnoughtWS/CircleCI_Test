package com.academy.alfagiftmini.presentation.homepage.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentPesananBinding
import com.academy.alfagiftmini.presentation.homepage.components.adapter.pesanan.AdapterSubMenuPesanan


class FragmentPesanan : Fragment() {
    private lateinit var binding: FragmentPesananBinding
    private lateinit var adapterPesanan: AdapterSubMenuPesanan

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPesananRv()
    }

    private fun setPesananRv() {
        binding.apply {
            rvListPesanan.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapterPesanan = AdapterSubMenuPesanan(mutableListOf("menu1", "menu2", "menu3"))
            rvListPesanan.adapter = adapterPesanan
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPesananBinding.inflate(inflater)
        return binding.root
    }


}