package com.academy.alfagiftmini.presentation.homepage.components.fragment.officialstore

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentOfficialStoreBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore.AllOfficialStoreActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore.DetailOfficialStoreActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.officialstore.OfficialStore14Adapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.OfficialStoreViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductCategoriesViewModel
import com.academy.alfagiftmini.presentation.homepage.fragment.FragmentBeranda

class FragmentOfficialStore() : Fragment() {
    private lateinit var binding: FragmentOfficialStoreBinding
    private lateinit var adapter: OfficialStore14Adapter
    private lateinit var viewModel: OfficialStoreViewModel
    private val context = requireContext()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOfficialStoreBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (requireActivity() as MainActivity).getViewModelOfficialStore()
        setAdapter()
        setObserver()
        getDataFromApi()
        setButtonLihatSemua()
    }

    private fun setButtonLihatSemua() {
        binding.tvLihatSemuaOfficial.setOnClickListener {
            val intent = Intent(requireContext(), AllOfficialStoreActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getDataFromApi() {
        viewModel.get14OfficialStre()
    }

    private fun setObserver() {
        viewModel.officialStore14.observe(requireActivity()) {
            if (it.isNullOrEmpty()) {
                setLihatSemua(PresentationUtils.HIDE_LIHAT_SEMUA)
                if (PresentationUtils.isNetworkAvailable(context)) {
                    Toast.makeText(context, "Tidak ada internet", Toast.LENGTH_SHORT)
                        .show()
                }
                return@observe
            }
            if (it.size < 14) {
                setLihatSemua(PresentationUtils.HIDE_LIHAT_SEMUA)
                adapter.updateData(it)
                return@observe
            }
            setLihatSemua(PresentationUtils.SHOW_LIHAT_SEMUA)
            adapter.updateData(it.dropLast(1))

        }
    }

    private fun setLihatSemua(banyakDataOfficialStore: Boolean) {
        if (banyakDataOfficialStore == PresentationUtils.HIDE_LIHAT_SEMUA) {
            binding.tvLihatSemuaOfficial.visibility = View.INVISIBLE
        } else {
            binding.tvLihatSemuaOfficial.visibility = View.VISIBLE
        }
    }

    private fun setAdapter() {
        adapter = OfficialStore14Adapter().apply {
            setOnItemClickListener { _, data ->
                val intent = Intent(requireContext(), DetailOfficialStoreActivity::class.java)
                intent.putExtra(PresentationUtils.INTENT_DATA, data)
                startActivity(intent)
            }
        }

        binding.apply {
            rvOfficalStore.setHasFixedSize(true)
            rvOfficalStore.layoutManager = GridLayoutManager(
                requireContext(), 2, GridLayoutManager.HORIZONTAL, false
            )
            rvOfficalStore.adapter = adapter
        }
    }

}