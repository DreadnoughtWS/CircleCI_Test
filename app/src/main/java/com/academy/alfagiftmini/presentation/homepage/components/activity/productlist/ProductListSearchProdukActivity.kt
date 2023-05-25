package com.academy.alfagiftmini.presentation.homepage.components.activity.productlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityProductListSearchProdukBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListPreviewProductNameAdapter
import com.academy.alfagiftmini.presentation.homepage.components.adapter.riwayatpencarian.RiwayatPencarianAdapter
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productcategories.FragmentProductCategorySearchView
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.searchview.FragmentProductListSearchProductNamaProduk
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.searchview.FragmentProductListSearchProductPromosi
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.searchview.FragmentProductListSearchProductTerlaris
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductCategoriesViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.RiwayatPencarianViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductListSearchProdukActivity : AppCompatActivity(),
    RiwayatPencarianAdapter.SetOnRiwayatClickListener {
    private lateinit var binding: ActivityProductListSearchProdukBinding
    private lateinit var adapter: ProductListPreviewProductNameAdapter
    private lateinit var riwayatAdapter: RiwayatPencarianAdapter
    private var dataName: String = ""
    private var type: String = ""

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val viewModel: ProductListViewModel by viewModels {
        presentationFactory
    }
    private val kategoriPilihanViewModel: ProductCategoriesViewModel by viewModels {
        presentationFactory
    }
    private val riwayatPencarianViewModel: RiwayatPencarianViewModel by viewModels {
        presentationFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.productListSearchProductActivityInject(this)
        binding = ActivityProductListSearchProdukBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setInitialView()
        setHideToolbar()
        setAdapter()
        setPreviewProductName()
    }

    private fun setInitialView() {
        setRvRiwayatPencarian()
        setRiwayatObserver()
        setFragment(
            binding.containerFragmentRvListKategori.id,
            FragmentProductCategorySearchView(),
            FragmentProductCategorySearchView::class.java.simpleName
        )
    }

    private fun setRiwayatObserver() {
        riwayatPencarianViewModel.getRiwayatLiveData.observe(this) {
            riwayatAdapter.updateList(it)
        }
    }

    private fun setRvRiwayatPencarian() {
        binding.apply {
            rvRiwayatPencarian.layoutManager = LinearLayoutManager(
                this@ProductListSearchProdukActivity, LinearLayoutManager.HORIZONTAL, false
            )
            riwayatAdapter = RiwayatPencarianAdapter(mutableListOf())
            rvRiwayatPencarian.adapter = riwayatAdapter
            riwayatAdapter.listener = this@ProductListSearchProdukActivity
            tvHapusRiwayat.setOnClickListener {
                riwayatPencarianViewModel.deleteRiwayatPencarian()
            }
        }
    }

    private fun setFragment(containerId: Int, fragment: Fragment, tag: String) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(containerId, fragment, tag)
            commit()
        }
    }

    private fun setupFragment(position: Int) {
        val fragment = when (position) {
            0 -> {
                FragmentProductListSearchProductPromosi()
            }
            1 -> {
                FragmentProductListSearchProductNamaProduk()
            }
            else -> {
                FragmentProductListSearchProductTerlaris()
            }
        }

        val tag = when (position) {
            0 -> FragmentProductListSearchProductPromosi::class.java.simpleName
            1 -> FragmentProductListSearchProductNamaProduk::class.java.simpleName
            else -> FragmentProductListSearchProductTerlaris::class.java.simpleName
        }
        setFragment(binding.container.id, fragment, tag)
    }

    private fun initTabs() {

        binding.apply {
            tlSearchView.addTab(tlSearchView.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text =
                    getString(R.string.promosi)
            })

            tlSearchView.addTab(tlSearchView.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                with(customView) {
                    this?.findViewById<TextView>(R.id.tv_tab_item)?.text =
                        getString(R.string.nama_product)
                    this?.findViewById<ImageView>(R.id.iv_tab_item_up)
                        ?.setImageResource(R.drawable.arrow_up_tab_item)
                    this?.findViewById<ImageView>(R.id.iv_tab_item_down)
                        ?.setImageResource(R.drawable.arrow_down_tab_item)
                }
            })

            tlSearchView.addTab(tlSearchView.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text =
                    getString(R.string.terlaris)
            })

            tlSearchView.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab) {
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    if (tab.position == 1) {
                        with(tab.customView) {
                            this?.findViewById<ImageView>(R.id.iv_tab_item_up)
                                ?.setImageResource(R.drawable.arrow_up_tab_item)
                            this?.findViewById<ImageView>(R.id.iv_tab_item_down)
                                ?.setImageResource(R.drawable.arrow_down_tab_item)
                        }
                    }
                }

                override fun onTabSelected(tab: TabLayout.Tab) {
                    setupFragment(tab.position)

                    if (tab.position == 1) {
                        tab.customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
                            ?.setImageResource(R.drawable.arrow_up_tab_item_blue)

                    }
                }
            })
        }
    }

    private fun setAdapter() {
        adapter = ProductListPreviewProductNameAdapter().apply {
            setOnItemClickListener { _, data ->
                performSearch(data, PresentationUtils.TYPE_PRODUCT_NAME)
            }
        }
        binding.apply {
            rvPreviewNameProduct.adapter = adapter
            rvPreviewNameProduct.layoutManager =
                LinearLayoutManager(this@ProductListSearchProdukActivity)
        }

    }

    private fun setPreviewProductName() {
        binding.apply {
            tilSearchView.editText?.doOnTextChanged { text, start, _, _ ->
                if (start == 0) {
                    setLayoutVisibility(kategori = true, previewName = false, tab = false)
                }
                if (start != 0) {
                    setLayoutVisibility(kategori = false, previewName = true, tab = false)
                    getData(text)
                }
            }
            tietSearchView.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch(
                        binding.tietSearchView.text.toString(),
                        PresentationUtils.TYPE_PRODUCT_NAME_LIKE
                    )
                }
                true
            }
        }

    }

    private fun performSearch(name: String, type: String) {
        if (name.isNotBlank()) riwayatPencarianViewModel.insertRiwayatPencarian(name)
        dataName = name
        this.type = type
        clearTabs()
        setupFragment(0)
        initTabs()
        setLayoutVisibility(kategori = false, previewName = false, tab = true)
    }

    private fun clearTabs() {
        binding.tlSearchView.removeAllTabs()
    }

    private fun getData(text: CharSequence?) {
        lifecycleScope.launch {
            viewModel.getPreviewProductName(text.toString()).collect {
                adapter.updateData(it)
            }
        }
    }

    private fun setLayoutVisibility(kategori: Boolean, previewName: Boolean, tab: Boolean) {
        binding.apply {
            clKategoriPilihan.visibility = if (kategori) View.VISIBLE else View.GONE
            clPreviewNameProduct.visibility = if (previewName) View.VISIBLE else View.GONE
            llTab.visibility = if (tab) View.VISIBLE else View.GONE
        }

    }

    private fun setHideToolbar() {
        supportActionBar?.hide()
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    fun getProductViewModel(): ProductListViewModel {
        return viewModel
    }

    fun getTab(): TabLayout {
        return binding.tlSearchView
    }

    fun getNameSearch(): String {
        return dataName
    }

    fun getCategoryProductViewModel(): ProductCategoriesViewModel {
        return kategoriPilihanViewModel
    }

    fun getDataType(): String {
        return type
    }

    override fun onItemClicked(text: String) {
        binding.tilSearchView.editText?.apply {
            setText(text)
            setSelection(text.length)
            onEditorAction(EditorInfo.IME_ACTION_SEARCH)
        }
    }
}