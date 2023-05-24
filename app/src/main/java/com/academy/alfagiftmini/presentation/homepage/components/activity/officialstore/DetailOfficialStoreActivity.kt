package com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore

import android.app.Dialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityDetailOfficialStoreBinding
import com.academy.alfagiftmini.domain.banner.model.BannerDomainModel
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStorebrandsDomainItemModel
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.PresentationUtils.HIDE_LIHAT_SEMUA
import com.academy.alfagiftmini.presentation.PresentationUtils.SHOW_LIHAT_SEMUA
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.adapter.banner.BannerBerandaSliderAdapter
import com.academy.alfagiftmini.presentation.homepage.components.adapter.officialstore.BrandsAdapter
import com.academy.alfagiftmini.presentation.homepage.components.fragment.officialstore.detail.FragmentDetailofficialStoreNamaProduct
import com.academy.alfagiftmini.presentation.homepage.components.fragment.officialstore.detail.FragmentDetailofficialStorePromosi
import com.academy.alfagiftmini.presentation.homepage.components.fragment.officialstore.detail.FragmentDetailofficialStoreTerlaris
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.BannerListViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.OfficialStoreViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailOfficialStoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailOfficialStoreBinding
    private lateinit var data: OfficialStoreDomainItemModel
    private lateinit var brandsAdapter: BrandsAdapter
    private lateinit var bannerAdapter: BannerBerandaSliderAdapter
    private lateinit var dialog: Dialog

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val productListViewModel: ProductListViewModel by viewModels {
        presentationFactory
    }
    private val officialStoreViewModel: OfficialStoreViewModel by viewModels {
        presentationFactory
    }
    private val bannerListViewModel: BannerListViewModel by viewModels {
        presentationFactory
    }

    private var brandId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.detailOfficialStoreActivityInject(this)
        binding = ActivityDetailOfficialStoreBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setProgress()
        setAdapter()
        setObserver()
        getDataFromIntent()
        getDataFromApi()
        initTabs()
        setupFragment(0)
    }

    private fun setProgress() {
        dialog = PresentationUtils.loadingAlertDialog(this)
    }

    private fun getDataFromApi() {
        officialStoreViewModel.getBrands(brandId)
        bannerListViewModel.getAllBannerList()
    }

    private fun setAdapter() {
        brandsAdapter = BrandsAdapter()
        binding.apply {
            rvBrandsDetailOfficialStore.layoutManager =
                GridLayoutManager(this@DetailOfficialStoreActivity, 4)
            rvBrandsDetailOfficialStore.setHasFixedSize(true)
            rvBrandsDetailOfficialStore.adapter = brandsAdapter
        }

    }

    private fun setObserver() {
        PresentationUtils.setLoading(true, dialog)
        officialStoreViewModel.brand.observe(this) {
            if (it.size > 8) {
                setLihatSemua(SHOW_LIHAT_SEMUA, it)
                brandsAdapter.updateData(it.subList(0, 8))
                PresentationUtils.setLoading(false, dialog)
            } else {
                setLihatSemua(HIDE_LIHAT_SEMUA, it)
                brandsAdapter.updateData(it)
                PresentationUtils.setLoading(false, dialog)
            }

        }

        lifecycleScope.launch {
            bannerListViewModel.bannerListData.collectLatest {
                setupSlider(it)
                PresentationUtils.setLoading(false, dialog)
            }
        }
    }

    private fun setupSlider(it: List<BannerDomainModel>) {
        bannerAdapter = BannerBerandaSliderAdapter(it, this)
        binding.svSliderBanner.setSliderAdapter(bannerAdapter)
        binding.svSliderBanner.startAutoCycle()
    }

    private fun setLihatSemua(
        isLihatSemua: Boolean, data: List<OfficialStorebrandsDomainItemModel>
    ) {
        var isOpen: Boolean? = false
        if (isLihatSemua) {
            binding.apply {
                tvLihatSemuaBrandsDetailOfficialStore.visibility = View.VISIBLE
                tvLihatSemuaBrandsDetailOfficialStore.setOnClickListener {
                    if (isOpen == false) {

                        isOpen = true
                        brandsAdapter.updateData(data)
                        tvLihatSemuaBrandsDetailOfficialStore.text =
                            getString(R.string.lihat_sebagian)
                        tvLihatSemuaBrandsDetailOfficialStore.setCompoundDrawablesWithIntrinsicBounds(
                            0, 0, 0, R.drawable.baseline_keyboard_arrow_up_24_blue
                        )

                    } else {

                        isOpen = false
                        brandsAdapter.updateData(data.subList(0, 8))
                        tvLihatSemuaBrandsDetailOfficialStore.text = getString(R.string.lihat_semua)
                        tvLihatSemuaBrandsDetailOfficialStore.setCompoundDrawablesWithIntrinsicBounds(
                            0, 0, 0, R.drawable.baseline_keyboard_arrow_down_24_blue
                        )

                    }
                }
            }


        } else {
            binding.tvLihatSemuaBrandsDetailOfficialStore.visibility = View.GONE
        }
    }

    private fun setToolbar(data: OfficialStoreDomainItemModel) {
        supportActionBar?.hide()
        binding.apply {
            detailOfficialStoreToolbar.tvToolbar.text = data.name
            detailOfficialStoreToolbar.ivBackToolbar.setOnClickListener {
                finish()
            }
            detailOfficialStoreToolbar.ivSearchToolbar.setOnClickListener {
                startActivity(
                    Intent(
                        this@DetailOfficialStoreActivity, OfficialStoreSearchActivity::class.java
                    )
                )
            }
        }

    }

    private fun getDataFromIntent() {
        data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(
                PresentationUtils.INTENT_DATA, OfficialStoreDomainItemModel::class.java
            ) ?: return
        } else {
            intent.getParcelableExtra(PresentationUtils.INTENT_DATA) ?: return
        }
        setToolbar(data)
        setData(data)
    }

    private fun setData(data: OfficialStoreDomainItemModel) {
        with(binding) {
            tvTitleOfficialStore.text = data.name
            tvPengikutOfficialStore.text = getString(R.string.total_pengikut, data.totalFollowers)
        }
        Glide.with(this).load(data.image).placeholder(R.drawable.uniliver_logo)
            .into(binding.ivStoreLogo)

        brandId = data.brands.map {
            it.brandId
        }.joinToString(separator = getString(R.string.operator_brand_id), prefix = "")
    }

    private fun initTabs() {

        binding.apply {
            tlOfficialStore.addTab(tlOfficialStore.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text =
                    getString(R.string.promosi)
            })

            tlOfficialStore.addTab(tlOfficialStore.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text =
                    getString(R.string.nama_product)
                customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
                    ?.setImageResource(R.drawable.arrow_up_tab_item)
                customView?.findViewById<ImageView>(R.id.iv_tab_item_down)
                    ?.setImageResource(R.drawable.arrow_down_tab_item)
            })



            tlOfficialStore.addTab(tlOfficialStore.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text =
                    getString(R.string.terlaris)
            })

            tlOfficialStore.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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

    private fun setupFragment(position: Int) {
        val fragment = when (position) {
            0 -> {
                FragmentDetailofficialStorePromosi()
            }
            1 -> {
                FragmentDetailofficialStoreNamaProduct()
            }
            else -> {
                FragmentDetailofficialStoreTerlaris()
            }
        }

        val tag = when (position) {
            0 -> FragmentDetailofficialStorePromosi::class.java.simpleName
            1 -> FragmentDetailofficialStoreNamaProduct::class.java.simpleName
            else -> FragmentDetailofficialStoreTerlaris::class.java.simpleName
        }
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment, tag)
            commit()
        }
    }

    fun getProductViewModel(): ProductListViewModel {
        return productListViewModel
    }

    fun getTab(): TabLayout {
        return binding.tlOfficialStore
    }

    fun getDataModel(): OfficialStoreDomainItemModel {
        return data
    }
}