package com.academy.alfagiftmini.presentation.homepage.activity.officialstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityDetailOfficialStoreBinding
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.adapter.DetailOfficialStorePagingAdapter
import com.academy.alfagiftmini.presentation.homepage.viewmodel.OfficialStoreViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailOfficialStoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailOfficialStoreBinding
    private lateinit var adapter: DetailOfficialStorePagingAdapter

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val viewModel: OfficialStoreViewModel by viewModels {
        presentationFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.detailOfficialStoreActivityInject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailOfficialStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setHideToolbar()
        setDetailToolbar()
        setAdapter()
        getDataFromApi()
    }

    private fun getDataFromApi() {
        lifecycleScope.launch {
            viewModel.getAllOfficialStore().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun setAdapter() {
        adapter = DetailOfficialStorePagingAdapter()
        binding.rvDetailOfficialStore.layoutManager = GridLayoutManager(this, 3)
        binding.rvDetailOfficialStore.adapter = adapter
    }

    private fun setDetailToolbar() {
        binding.detailOfficialStoreToolbar.tvToolbar.text = getString(R.string.official_store)
        binding.detailOfficialStoreToolbar.ivBackToolbar.setOnClickListener {
            finish()
        }
    }

    private fun setHideToolbar() {
        supportActionBar?.hide()
    }
}