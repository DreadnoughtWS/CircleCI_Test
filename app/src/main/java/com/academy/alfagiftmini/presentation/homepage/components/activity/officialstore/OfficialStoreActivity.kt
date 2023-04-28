package com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.databinding.ActivityOfficialStoreBinding
import com.academy.alfagiftmini.presentation.PresentationUtils.HIDE_LIHAT_SEMUA
import com.academy.alfagiftmini.presentation.PresentationUtils.SHOW_LIHAT_SEMUA
import com.academy.alfagiftmini.presentation.PresentationUtils.isNetworkAvailable
import com.academy.alfagiftmini.presentation.PresentationUtils.loadingAlertDialog
import com.academy.alfagiftmini.presentation.PresentationUtils.setLoading
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.adapter.officialstore.OfficialStore14Adapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.OfficialStoreViewModel
import javax.inject.Inject

class OfficialStoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOfficialStoreBinding
    private lateinit var adapter: OfficialStore14Adapter
    private lateinit var dialog: Dialog

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
        setProgressBar()
        setAdapter()
        setObserver()
        getDataFromApi()
        setButtonLihatSemua()

    }

    private fun setProgressBar() {
        dialog = loadingAlertDialog(this)
    }

    private fun setButtonLihatSemua() {
        binding.tvLihatSemuaOfficial.setOnClickListener {
            val intent = Intent(this, AllOfficialStoreActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getDataFromApi() {
        viewModel.get14OfficialStre()
    }

    private fun setAdapter() {
        adapter = OfficialStore14Adapter().apply {
            setOnItemClickListener { position, data ->
                val intent = Intent(this@OfficialStoreActivity, DetailOfficialStoreActivity::class.java)
                intent.putExtra("data", data)
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
        setLoading(true, dialog)
        viewModel.officialStore14.observe(this) {
            if (it.isNullOrEmpty()) {
                setLoading(false, dialog)
                setLihatSemua(HIDE_LIHAT_SEMUA)
                if (isNetworkAvailable(this)) {
                    Toast.makeText(this, "Tidak ada internet", Toast.LENGTH_SHORT).show()
                }
                return@observe
            }
            if (it.size < 14) {
                setLoading(false, dialog)
                setLihatSemua(HIDE_LIHAT_SEMUA)
                adapter.updateData(it)
                return@observe
            }
            setLoading(false, dialog)
            setLihatSemua(SHOW_LIHAT_SEMUA)
            adapter.updateData(it.dropLast(1))

        }
    }

    private fun setLihatSemua(banyakDataOfficialStore: Boolean) {
        if (banyakDataOfficialStore == HIDE_LIHAT_SEMUA) {
            binding.tvLihatSemuaOfficial.visibility = View.INVISIBLE
        } else {
            binding.tvLihatSemuaOfficial.visibility = View.VISIBLE
        }
    }
}