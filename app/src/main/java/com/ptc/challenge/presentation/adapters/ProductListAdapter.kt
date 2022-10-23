package com.mahmoud.zaher.geideatask.presentation.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ptc.challenge.R
import com.ptc.challenge.data.model.pojo.ProductData
import com.ptc.challenge.databinding.ProductItemBinding
import kotlinx.android.synthetic.main.product_item.view.*


class ProductListAdapter :
    ListAdapter<ProductData, ProductListAdapter.UserViewHolder>(DiffUtilCallBack()),
    ItemClickListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ProductItemBinding>(
            layoutInflater,
            R.layout.product_item,
            parent,
            false
        )
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.view.product = getItem(position)
        holder.view.listener = this
    }

    class UserViewHolder(var view: ProductItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onItemClickListener(v: View) {
        val uuid = v.uuidTextView.text.toString().toInt()
        val bundle = Bundle()
        bundle.putInt("uuid", uuid)
        Navigation.findNavController(v).navigate(R.id.detailsFragment, bundle)
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<ProductData>() {
        override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
            return oldItem.sku == newItem.sku
        }

        override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
            return oldItem == newItem
        }

    }
}