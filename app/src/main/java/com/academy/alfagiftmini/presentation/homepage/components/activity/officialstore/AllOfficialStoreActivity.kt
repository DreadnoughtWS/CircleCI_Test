package com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityAllOfficialStoreBinding
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.adapter.AllOfficialStorePagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.OfficialStoreViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllOfficialStoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllOfficialStoreBinding
    private lateinit var adapter: AllOfficialStorePagingAdapter

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val viewModel: OfficialStoreViewModel by viewModels {
        presentationFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.detailOfficialStoreActivityInject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityAllOfficialStoreBinding.inflate(layoutInflater)
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
        adapter = AllOfficialStorePagingAdapter()
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