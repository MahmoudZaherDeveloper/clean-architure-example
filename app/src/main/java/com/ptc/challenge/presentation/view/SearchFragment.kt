package com.ptc.challenge.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.mahmoud.zaher.geideatask.presentation.adapter.ProductListAdapter
import com.ptc.challenge.R
import com.ptc.challenge.databinding.FragmentSearchBinding
import com.ptc.challenge.presentation.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {
    private lateinit var dataBinding: FragmentSearchBinding
    private lateinit var viewmodel: SearchViewModel
    private val productListAdapter = ProductListAdapter()
    private var query = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            query = SearchFragmentArgs.fromBundle(it).query
        }
        viewmodel = ViewModelProvider(this).get(SearchViewModel::class.java)
        recyclerview.apply {
            layoutManager = GridLayoutManager(context, 2)
            // layoutManager = LinearLayoutManager(context)
            adapter = productListAdapter
        }
        viewmodel.searchForProduct(query, 1)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewmodel.products.observe(viewLifecycleOwner) { products ->
            products?.let {
                recyclerview.visibility = View.VISIBLE
                productListAdapter.submitList(products.metadata.results)
            }

        }
        viewmodel.loadError.observe(viewLifecycleOwner) { error ->
            error?.let {
                errorTextView.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
        viewmodel.loading.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    errorTextView.visibility = View.GONE
                    recyclerview.visibility = View.GONE
                }
            }
        }

    }

}