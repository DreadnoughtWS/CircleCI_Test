package com.academy.alfagiftmini.presentation.homepage.components.activity.banner

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityBannerPromoItemListBinding
import com.academy.alfagiftmini.domain.banner.model.BannerDomainModel
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.bannerproduct.FragmentBannerProductNamaProduk
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.bannerproduct.FragmentBannerProductPromosi
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.bannerproduct.FragmentBannerProductTerlaris
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.BannerListViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject

class BannerPromoItemListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBannerPromoItemListBinding

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val viewModel: BannerListViewModel by viewModels {
        presentationFactory
    }

    private val productListViewModel: ProductListViewModel by viewModels {
        presentationFactory
    }

    private var bannerData: BannerDomainModel? = null
    private var bannerId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.bannerListPromoItemListActivityInject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityBannerPromoItemListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent != null){
            if (Build.VERSION.SDK_INT >= 33) { // TIRAMISU
                bannerData = intent.getParcelableExtra (PresentationUtils.BANNER_DATA, BannerDomainModel::class.java)
            }else{
                bannerData = intent.getParcelableExtra(PresentationUtils.BANNER_DATA)
            }
        }

        bannerId = bannerData?.id

        if (bannerId != null){
            setToolbarBanner()
            setButton()
            initTabs()
            setupFragment(0)
        }

    }

    private fun setButton() {
        if (!(bannerData!!.syaratKetentuan.isNullOrEmpty())){
            binding.apply {
                ivArrowBlue.visibility = View.VISIBLE
                ivLogoSyarat.visibility = View.VISIBLE
                tvSyaratDkt.visibility = View.VISIBLE
                ivArrowBlue.setOnClickListener {
                    val intentSyarat = Intent(this@BannerPromoItemListActivity,SyaratKetentuanActivity::class.java)
                    intentSyarat.putExtra(PresentationUtils.BANNER_RULE,bannerData!!.syaratKetentuan)
                    startActivity(intentSyarat)
                }
            }
        }else{
            binding.apply {
                ivArrowBlue.visibility = View.INVISIBLE
                ivLogoSyarat.visibility = View.INVISIBLE
                tvSyaratDkt.visibility = View.INVISIBLE
            }
        }

    }

    private fun initTabs() {
        var isClicked: Boolean? = null

        binding.tlBannerProduct.addTab(binding.tlBannerProduct.newTab().setCustomView(
            R.layout.tab_item
        ).apply {
            customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = "Promosi"
        })

        binding.tlBannerProduct.addTab(binding.tlBannerProduct.newTab().setCustomView(
            R.layout.tab_item
        ).apply {
            customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = "Nama Product"
            customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
                ?.setImageResource(R.drawable.arrow_up_tab_item)
            customView?.findViewById<ImageView>(R.id.iv_tab_item_down)
                ?.setImageResource(R.drawable.arrow_down_tab_item)
        })



        binding.tlBannerProduct.addTab(binding.tlBannerProduct.newTab().setCustomView(
            R.layout.tab_item
        ).apply {
            customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = getString(R.string.terlaris)
        })

        binding.tlBannerProduct.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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

    private fun setupFragment(position: Int) {
        val fragment = when (position) {
            0 -> {
                FragmentBannerProductPromosi()
            }
            1 -> {
                FragmentBannerProductNamaProduk()
            }
            else -> {
                FragmentBannerProductTerlaris()
            }
        }

        val tag = when (position) {
            0 -> FragmentBannerProductPromosi::class.java.simpleName
            1 -> FragmentBannerProductNamaProduk::class.java.simpleName
            else -> FragmentBannerProductTerlaris::class.java.simpleName
        }
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment, tag)
            commit()
        }
    }

    fun getTab(): TabLayout {
        return binding.tlBannerProduct
    }

    fun getProductViewModel(): ProductListViewModel {
        return productListViewModel
    }

    fun getBannerIdValue(): Int {
        return bannerId ?: 0
    }

    private fun setToolbarBanner() {
        binding.bannerItemListToolbar.tvPromoToolbarTitle.text = bannerData?.bannerName
        binding.bannerItemListToolbar.btnBannerBack.setOnClickListener {
            finish()
        }
        Glide.with(this).load(bannerData?.bannerImageFileName).into(binding.ivBannerImage)
    }
}