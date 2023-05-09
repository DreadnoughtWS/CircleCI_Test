package com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.databinding.ActivityOfficialStoreBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.PresentationUtils.HIDE_LIHAT_SEMUA
import com.academy.alfagiftmini.presentation.PresentationUtils.SHOW_LIHAT_SEMUA
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.adapter.officialstore.OfficialStore14Adapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.OfficialStoreViewModel
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
            startActivity(Intent(this, AllOfficialStoreActivity::class.java))
        }
    }

    private fun getDataFromApi() {
        viewModel.get14OfficialStre()
    }

    private fun setAdapter() {
        adapter = OfficialStore14Adapter().apply {
            setOnItemClickListener { _, data ->
                val intent =
                    Intent(this@OfficialStoreActivity, DetailOfficialStoreActivity::class.java)
                intent.putExtra(PresentationUtils.INTENT_DATA, data)
                startActivity(intent)
            }
        }

        binding.apply {
            rvOfficalStore.setHasFixedSize(true)
            rvOfficalStore.layoutManager = GridLayoutManager(
                this@OfficialStoreActivity, 2, GridLayoutManager.HORIZONTAL, false
            )
            rvOfficalStore.adapter = adapter
        }

    }

    private fun setObserver() {
        viewModel.officialStore14.observe(this) {
            if (it.isNullOrEmpty()) {
                setLihatSemua(HIDE_LIHAT_SEMUA)
                return@observe
            }
            if (it.size < 14) {
                setLihatSemua(HIDE_LIHAT_SEMUA)
                adapter.updateData(it)
                return@observe
            }
            setLihatSemua(SHOW_LIHAT_SEMUA)
            adapter.updateData(it.dropLast(1))

        }
    }

    private fun setLihatSemua(banyakDataOfficialStore: Boolean) {
        binding.apply {
            if (banyakDataOfficialStore == HIDE_LIHAT_SEMUA) {
                tvLihatSemuaOfficial.visibility = View.INVISIBLE
            } else {
                tvLihatSemuaOfficial.visibility = View.VISIBLE
            }
        }
    }
}