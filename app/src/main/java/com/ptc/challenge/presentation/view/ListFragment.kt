package com.ptc.challenge.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.mahmoud.zaher.geideatask.presentation.adapter.ProductListAdapter
import com.ptc.challenge.R
import com.ptc.challenge.core.pagination.ScrollingPagination
import com.ptc.challenge.data.model.pojo.ProductData
import com.ptc.challenge.databinding.FragmentListBinding
import com.ptc.challenge.presentation.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {
    private lateinit var dataBinding: FragmentListBinding
    private lateinit var viewmodel: ListViewModel
    private val productListAdapter = ProductListAdapter()
    private val newProducts = mutableListOf<ProductData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this).get(ListViewModel::class.java)
        recyclerview.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = productListAdapter
        }

        refreshLayout.setOnRefreshListener {
            recyclerview.visibility = View.GONE
            errorTextView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            refreshLayout.isRefreshing = false
            viewmodel.fetchProductsFromRemote(1)
        }

        dataBinding.etSearch.setOnClickListener {
            val query = dataBinding.etSearch.text.toString()
            val action = ListFragmentDirections.actionListFragmentToSearchFragment()
            action.query = query
            Navigation.findNavController(it).navigate(action)
        }
        observeViewModel()
        initView()
    }

    private fun initView() {
        recyclerview.addOnScrollListener(object :
            ScrollingPagination(recyclerview.layoutManager as GridLayoutManager) {
            override fun loadMoreDate() {
                viewmodel.loadMore()
            }
        })
    }

    private fun observeViewModel() {
        viewmodel.products.observe(viewLifecycleOwner) { products ->
            products?.let {
                recyclerview.visibility = View.VISIBLE
                newProducts.addAll(products)
                //productListAdapter.submitList(products)
                productListAdapter.submitList(newProducts.distinct())
            }
        }
        viewmodel.currency.observe(viewLifecycleOwner) {
         
        }
        viewmodel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(context, it.message.toString(), Toast.LENGTH_LONG).show()
            }
        }

        viewmodel.loading.observe(viewLifecycleOwner) { loading ->
            loading?.let { it ->
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        }

    }
}