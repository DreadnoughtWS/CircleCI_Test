package com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.data.repository.network.officialstore.OfficialStoreListPagingSource
import com.academy.alfagiftmini.databinding.ActivityOfficialStoreSearchBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.adapter.officialstore.AllOfficialStorePagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.OfficialStoreViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class OfficialStoreSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOfficialStoreSearchBinding
    private lateinit var adapter: AllOfficialStorePagingAdapter

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val viewModel: OfficialStoreViewModel by viewModels {
        presentationFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.officialStoreSearchActivityInject(this)
        binding = ActivityOfficialStoreSearchBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setHideToolbar()
        setToolbar()
        setAdapter()
        setDataSearch()
    }

    private fun setDataSearch() {
        binding.tietSearchView.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(binding.tietSearchView.text.toString())
            }
            true
        }
    }

    private fun performSearch(name: String) {
        lifecycleScope.launch {
            viewModel.getAllOfficialStore(name, PresentationUtils.TYPE_SEARCH_OFFICIAL)
                .collectLatest {
                    adapter.submitData(it)
                }
        }
    }

    private fun setAdapter() {
        adapter = AllOfficialStorePagingAdapter().apply {
            setOnItemClickListener { position, data ->
                val intent = Intent(this@OfficialStoreSearchActivity, DetailOfficialStoreActivity::class.java)
                intent.putExtra(PresentationUtils.INTENT_DATA, data)
                startActivity(intent)
            }
        }
        binding.rvOfficialStoreSearch.layoutManager = GridLayoutManager(this, 3)
        binding.rvOfficialStoreSearch.adapter = adapter
    }

    private fun setToolbar() {
        binding.officialStoreSeachToolbar.btnBannerBack.setOnClickListener {
            finish()
        }
        binding.officialStoreSeachToolbar.tvPromoToolbarTitle.text = "Cari Official Store"
    }

    private fun setHideToolbar() {
        supportActionBar?.hide()
    }
}