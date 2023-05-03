package com.academy.alfagiftmini.presentation.homepage.components.activity.productlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityProductListPaketBinding
import com.academy.alfagiftmini.databinding.ActivityProductListSearchProdukBinding
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListPreviewProductNameAdapter
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.paket.FragmentProductListPaketNamaProduk
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.paket.FragmentProductListPaketPromosi
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.paket.FragmentProductListPaketTerlaris
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.searchview.FragmentProductListSearchProductNamaProduk
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.searchview.FragmentProductListSearchProductPromosi
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.searchview.FragmentProductListSearchProductTerlaris
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductListSearchProduk : AppCompatActivity() {
    private lateinit var binding: ActivityProductListSearchProdukBinding
    private lateinit var adapter: ProductListPreviewProductNameAdapter
    var dataName: String = ""

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val viewModel: ProductListViewModel by viewModels {
        presentationFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.productListSearchProductActivityInject(this)
        binding = ActivityProductListSearchProdukBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setHideToolbar()
        setAdapter()
        setPreviewProductName()
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
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment, tag)
            commit()
        }
    }

    private fun initTabs() {
        var isClicked: Boolean? = null


        binding.tlSearchView.addTab(binding.tlSearchView.newTab().setCustomView(
            R.layout.tab_item
        ).apply {
            customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = "Promosi"
        })

        binding.tlSearchView.addTab(binding.tlSearchView.newTab().setCustomView(
            R.layout.tab_item
        ).apply {
            customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = "Nama Product"
            customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
                ?.setImageResource(R.drawable.arrow_up_tab_item)
            customView?.findViewById<ImageView>(R.id.iv_tab_item_down)
                ?.setImageResource(R.drawable.arrow_down_tab_item)
        })



        binding.tlSearchView.addTab(binding.tlSearchView.newTab().setCustomView(
            R.layout.tab_item
        ).apply {
            customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = "Terlaris"
        })

        binding.tlSearchView.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                if (tab.position == 1) {
                    isClicked = null
                    tab.customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
                        ?.setImageResource(R.drawable.arrow_up_tab_item)
                    tab.customView?.findViewById<ImageView>(R.id.iv_tab_item_down)
                        ?.setImageResource(R.drawable.arrow_down_tab_item)

                }
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                setupFragment(tab.position)

                if (tab.position == 1) {
                    isClicked = true
                    tab.customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
                        ?.setImageResource(R.drawable.arrow_up_tab_item_blue)

                }
            }
        })
    }

    private fun setAdapter() {
        adapter = ProductListPreviewProductNameAdapter().apply {
            setOnItemClickListener { _, data ->
                setLayoutVisibility(kategori = false, previewName = false, tab = true)
                performSearch(data)
            }
        }
        binding.rvPreviewNameProduct.adapter = adapter
        binding.rvPreviewNameProduct.layoutManager = LinearLayoutManager(this)
    }

    private fun setPreviewProductName() {
        binding.tilSearchView.editText?.doOnTextChanged { text, start, _, _ ->
            if (start == 0) {
                setLayoutVisibility(kategori = true, previewName = false, tab = false)
            }
            if (start != 0) {
                setLayoutVisibility(kategori = false, previewName = true, tab = false)
                getData(text)
            }
        }
        binding.tietSearchView.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                setLayoutVisibility(kategori = false, previewName = false, tab = true)
                performSearch(binding.tietSearchView.text.toString())
            }
            true
        }
    }

    private fun performSearch(name: String) {
        dataName = name
        clearTabs()
        initTabs()
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
        binding.clKategoriPilihan.visibility = if (kategori) View.VISIBLE else View.GONE
        binding.clPreviewNameProduct.visibility = if (previewName) View.VISIBLE else View.GONE
        binding.llTab.visibility = if (tab) View.VISIBLE else View.GONE
    }

    private fun setHideToolbar() {
        supportActionBar?.hide()
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
}