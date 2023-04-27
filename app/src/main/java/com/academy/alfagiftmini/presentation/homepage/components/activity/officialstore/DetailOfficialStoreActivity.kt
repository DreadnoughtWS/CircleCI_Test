package com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityDetailOfficialStoreBinding
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.fragment.officialstore.detail.FragmentDetailofficialStoreNamaProduct
import com.academy.alfagiftmini.presentation.homepage.components.fragment.officialstore.detail.FragmentDetailofficialStorePromosi
import com.academy.alfagiftmini.presentation.homepage.components.fragment.officialstore.detail.FragmentDetailofficialStoreTerlaris
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject

class DetailOfficialStoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailOfficialStoreBinding
    private lateinit var data: OfficialStoreDomainItemModel

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val productListViewModel: ProductListViewModel by viewModels {
        presentationFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.detailOfficialStoreActivityInject(this)
        binding = ActivityDetailOfficialStoreBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getDataFromIntent()
        initTabs()
        setupFragment(0)
    }

    private fun setToolbar(data: OfficialStoreDomainItemModel) {
        binding.detailOfficialStoreToolbar.tvToolbar.text = data.name
        binding.detailOfficialStoreToolbar.ivBackToolbar.setOnClickListener {
            finish()
        }
    }

    private fun getDataFromIntent() {
        data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(
                "data", OfficialStoreDomainItemModel::class.java
            ) ?: return
        } else {
            intent.getParcelableExtra("data") ?: return
        }
        setToolbar(data)
        setData(data)
    }

    private fun setData(data: OfficialStoreDomainItemModel) {
        with(binding) {
            tvTitleOfficialStore.text = data.name
            tvPengikutOfficialStore.text = "${data.totalFollowers} Pengikut"
        }
        Glide.with(this).load(data.image).placeholder(R.drawable.uniliver_logo)
            .into(binding.ivStoreLogo)
    }

    private fun initTabs() {
        var isClicked: Boolean? = null


        binding.tlOfficialStore.addTab(binding.tlOfficialStore.newTab().setCustomView(
            R.layout.tab_item
        ).apply {
            customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = "Promosi"
        })

        binding.tlOfficialStore.addTab(binding.tlOfficialStore.newTab().setCustomView(
            R.layout.tab_item
        ).apply {
            customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = "Nama Product"
            customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
                ?.setImageResource(R.drawable.arrow_up_tab_item)
            customView?.findViewById<ImageView>(R.id.iv_tab_item_down)
                ?.setImageResource(R.drawable.arrow_down_tab_item)
        })



        binding.tlOfficialStore.addTab(binding.tlOfficialStore.newTab().setCustomView(
            R.layout.tab_item
        ).apply {
            customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = "Terlaris"
        })

        binding.tlOfficialStore.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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