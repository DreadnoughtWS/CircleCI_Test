package com.academy.alfagiftmini.presentation.homepage.components.adapter.banner

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.academy.alfagiftmini.databinding.ItemBannerSliderBinding
import com.academy.alfagiftmini.domain.banner.model.BannerDomainModel
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.activity.banner.BannerPromoItemListActivity
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter

class BannerBerandaSliderAdapter(
    private var listBanner: List<BannerDomainModel>, var context: Context
): SliderViewAdapter<BannerBerandaSliderAdapter.BannerAdapterViewHolder>() {

    class BannerAdapterViewHolder(val binding: ItemBannerSliderBinding) : ViewHolder(binding.root) {
        fun bindData(data: BannerDomainModel, context: Context) {
            val loadingDrawable1 = CircularProgressDrawable(context)
            loadingDrawable1.strokeWidth = 5f
            loadingDrawable1.centerRadius = 30f
            loadingDrawable1.setColorSchemeColors(Color.RED)
            loadingDrawable1.start()

            Glide.with(itemView.context).load(data.bannerImageFileName).placeholder(loadingDrawable1)
                .into(binding.ivImageSlider)
        }
    }

    override fun getCount(): Int {
        return listBanner.size
    }

    override fun onCreateViewHolder(parent: ViewGroup): BannerAdapterViewHolder {
        val binding =
            ItemBannerSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return BannerAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: BannerAdapterViewHolder, position: Int) {
            viewHolder.bindData(listBanner[position],context)
            viewHolder.binding.ivImageSlider.setOnClickListener {
                val intent = Intent(context, BannerPromoItemListActivity::class.java)
                intent.putExtra(PresentationUtils.BANNER_DATA, listBanner[position])
                context.startActivity(intent)
            }
    }


}