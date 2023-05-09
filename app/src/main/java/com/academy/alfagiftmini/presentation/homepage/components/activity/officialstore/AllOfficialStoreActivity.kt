package com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityAllOfficialStoreBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.adapter.officialstore.AllOfficialStorePagingAdapter
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
        (application as MyApplication).appComponent.allOfficialStoreActivityInject(this)
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
            viewModel.getAllOfficialStore("", PresentationUtils.TYPE_GET_ALL_OFFICIAL)
                .collectLatest {
                    adapter.submitData(it)
                }
        }
    }

    private fun setAdapter() {
        adapter = AllOfficialStorePagingAdapter().apply {
            setOnItemClickListener { _, data ->
                val intent =
                    Intent(this@AllOfficialStoreActivity, DetailOfficialStoreActivity::class.java)
                intent.putExtra(PresentationUtils.INTENT_DATA, data)
                startActivity(intent)
            }
        }
        binding.apply {
            rvDetailOfficialStore.layoutManager =
                GridLayoutManager(this@AllOfficialStoreActivity, 3)
            rvDetailOfficialStore.adapter = adapter
        }

    }

    private fun setDetailToolbar() {
        binding.apply {
            allOfficialStoreToolbar.tvToolbar.text = getString(R.string.official_store)
            allOfficialStoreToolbar.ivBackToolbar.setOnClickListener {
                finish()
            }
            allOfficialStoreToolbar.ivSearchToolbar.setOnClickListener {
                val intent =
                    Intent(this@AllOfficialStoreActivity, OfficialStoreSearchActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun setHideToolbar() {
        supportActionBar?.hide()
    }
}