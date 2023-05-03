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
    val context: Context
): RecyclerView.Adapter<BannerListAdapter.BannerListViewHolder>() {
    class BannerListViewHolder(val binding: ItemBannerListBinding): RecyclerView.ViewHolder(binding.root){
        fun bindData(data: BannerDomainModel, context: Context) {
            val loadingDrawable1 = CircularProgressDrawable(context)
            loadingDrawable1.strokeWidth = 5f
            loadingDrawable1.centerRadius = 30f
            loadingDrawable1.setColorSchemeColors(Color.RED)
            loadingDrawable1.start()

            Glide.with(itemView.context).load(data.bannerImageFileName).placeholder(loadingDrawable1)
                .into(binding.ivBannerImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerListViewHolder {
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