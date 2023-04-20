package com.academy.alfagiftmini.presentation.homepage.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.databinding.ActivityOfficialStoreBinding
import com.academy.alfagiftmini.presentation.PresentationUtils.DATA_OFFICIAL_STORE_KURANG_DARI_14
import com.academy.alfagiftmini.presentation.PresentationUtils.DATA_OFFICIAL_STORE_LEBIH_DARI_14
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.adapter.OfficialStore14Adapter
import com.academy.alfagiftmini.presentation.homepage.viewmodel.OfficialStoreViewModel
import javax.inject.Inject

class OfficialStoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOfficialStoreBinding
    private lateinit var adapter: OfficialStore14Adapter

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val viewModel: OfficialStoreViewModel by viewModels {
        presentationFactory
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.officialStoreActivityInject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityOfficialStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
        setObserver()
        getDataFromApi()
        setButtonLihatSemua()

    }

    private fun setButtonLihatSemua() {
        binding.tvLihatSemuaOfficial.setOnClickListener {
            val intent = Intent(this, DetailOfficialStoreActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getDataFromApi() {
        viewModel.get14OfficialStre()
    }

    private fun setAdapter() {
        adapter = OfficialStore14Adapter()
        val gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)
        binding.rvOfficalStore.setHasFixedSize(true)
        binding.rvOfficalStore.adapter = adapter
        binding.rvOfficalStore.layoutManager = gridLayoutManager
    }

    private fun setObserver() {
        viewModel.officialStore14.observe(this) {
            if (it == null) return@observe
            if (it.size < 14) {

                println(it)
                setLihatSemua(DATA_OFFICIAL_STORE_LEBIH_DARI_14)
                adapter.updateData(it)
                return@observe
            }
            setLihatSemua(DATA_OFFICIAL_STORE_KURANG_DARI_14)
            adapter.updateData(it.dropLast(1))

        }
    }

    private fun setLihatSemua(banyakDataOfficialStore: Boolean) {
        if (banyakDataOfficialStore == DATA_OFFICIAL_STORE_KURANG_DARI_14) {
            binding.tvLihatSemuaOfficial.visibility = View.INVISIBLE
        } else {
            binding.tvLihatSemuaOfficial.visibility = View.VISIBLE
        }
    }
}