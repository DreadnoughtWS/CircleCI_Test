package com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityOfficialStoreSearchBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.PresentationUtils.loadingAlertDialog
import com.academy.alfagiftmini.presentation.PresentationUtils.setLoading
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.adapter.officialstore.AllOfficialStorePagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.OfficialStoreViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class OfficialStoreSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOfficialStoreSearchBinding
    private lateinit var adapter: AllOfficialStorePagingAdapter
    private lateinit var dialog: Dialog
    private var nameOfficialStore:String = ""
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
        setDialog()
        setHideToolbar()
        setToolbar()
        setAdapter()
        setDataSearch()
    }

    private fun setDialog() {
        dialog = loadingAlertDialog(this)
    }

    private fun setDataSearch() {
        binding.tietSearchView.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                nameOfficialStore = binding.tietSearchView.text.toString()
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
            setOnItemClickListener { _, data ->
                val intent = Intent(
                    this@OfficialStoreSearchActivity, DetailOfficialStoreActivity::class.java
                )
                intent.putExtra(PresentationUtils.INTENT_DATA, data)
                startActivity(intent)
            }
        }
        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                setLoading(true, dialog)
            } else {
                setLoading(false, dialog)
            }
            if (loadState.refresh is LoadState.Error) {
                setLoading(false, dialog)
                if (!PresentationUtils.isNetworkAvailable(this)) {
                    val dialogg = PresentationUtils.noInternetDialog(this)
                    dialogg.setPositiveButton("RETRY") { _, _ ->
                        performSearch(nameOfficialStore)
                    }
                    dialogg.setNegativeButton("CLOSE") { dialog, _ ->
                        dialog.cancel()
                    }
                    PresentationUtils.shownoInternetDialog(dialogg)
                } else {
                    PresentationUtils.showError("Product tidak ditemukan", this)
                }
            }

            if (loadState.append is LoadState.Error) {
                setLoading(false, dialog)
                if (!PresentationUtils.isNetworkAvailable(this)) {
                    val dialogg = PresentationUtils.noInternetDialog(this)
                    dialogg.setPositiveButton("RETRY") { _, _ ->
                        performSearch(nameOfficialStore)
                    }
                    dialogg.setNegativeButton("CLOSE") { dialog, _ ->
                        dialog.cancel()
                    }
                    PresentationUtils.shownoInternetDialog(dialogg)
                }
            }
        }
        binding.apply {
            rvOfficialStoreSearch.layoutManager =
                GridLayoutManager(this@OfficialStoreSearchActivity, 3)
            rvOfficialStoreSearch.adapter = adapter
        }

    }

    private fun setToolbar() {
        binding.apply {
            officialStoreSeachToolbar.btnBannerBack.setOnClickListener {
                finish()
            }
            officialStoreSeachToolbar.tvPromoToolbarTitle.text =
                getString(R.string.cari_official_store)
        }

    }

    private fun setHideToolbar() {
        supportActionBar?.hide()
    }
}