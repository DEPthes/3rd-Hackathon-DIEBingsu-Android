package com.depth.diebingsu.presentation.view.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.depth.diebingsu.data.dto.Information
import com.depth.diebingsu.databinding.RvBingsuWorldItemBinding

class RvBingsuWorldAdapter : RecyclerView.Adapter<RvBingsuWorldAdapter.ViewHolder>() {
    var list = mutableListOf<Information>()
    inner class ViewHolder(private val binding: RvBingsuWorldItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Information) {
            Glide.with(binding.rvBingsuWorldImg)
                .load(item.image)
                .into(binding.rvBingsuWorldImg)
            binding.tvDes.text = item.shavedIceName
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvBingsuWorldAdapter.ViewHolder {
        val binding = RvBingsuWorldItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RvBingsuWorldAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(item: List<Information>) {
        list.addAll(item)
        notifyItemRangeInserted(itemCount, item.size)
    }
}