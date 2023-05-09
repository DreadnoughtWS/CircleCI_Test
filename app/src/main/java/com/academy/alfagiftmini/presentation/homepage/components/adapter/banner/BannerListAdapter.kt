package com.academy.alfagiftmini.presentation.homepage.components.adapter.banner

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.academy.alfagiftmini.databinding.ItemBannerListBinding
import com.academy.alfagiftmini.databinding.ItemBannerSliderBinding
import com.academy.alfagiftmini.domain.banner.model.BannerDomainModel
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.activity.banner.BannerPromoItemListActivity
import com.bumptech.glide.Glide

class BannerListAdapter(
    private val listBanner: List<BannerDomainModel>,
): RecyclerView.Adapter<BannerListAdapter.BannerListViewHolder>() {
    lateinit var context: Context
    class BannerListViewHolder(val binding: ItemBannerListBinding): RecyclerView.ViewHolder(binding.root){
        fun bindData(data: BannerDomainModel, context: Context) {
            val loadingDrawable2 = CircularProgressDrawable(context)
            loadingDrawable2.strokeWidth = 5f
            loadingDrawable2.centerRadius = 30f
            loadingDrawable2.setColorSchemeColors(Color.RED)
            loadingDrawable2.start()

            Glide.with(binding.root).load(data.bannerImageFileName).placeholder(loadingDrawable2)
                .into(binding.ivBannerImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerListViewHolder {
        context = parent.context
        val binding =
            ItemBannerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listBanner.size
    }

    override fun onBindViewHolder(holder: BannerListViewHolder, position: Int) {
        holder.bindData(listBanner[position],context)
        holder.binding.ivBannerImage.setOnClickListener {
            val intent = Intent(context, BannerPromoItemListActivity::class.java)
            intent.putExtra(PresentationUtils.BANNER_DATA, listBanner[position])
            context.startActivity(intent)
        }
    }
}