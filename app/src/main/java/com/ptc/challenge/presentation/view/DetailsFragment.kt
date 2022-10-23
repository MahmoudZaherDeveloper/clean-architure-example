package com.ptc.challenge.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ptc.challenge.R
import com.ptc.challenge.databinding.FragmentDetailsBinding
import com.ptc.challenge.presentation.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_list.errorTextView

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private var receivedUuid = 0
    lateinit var detailsViewModel: DetailsViewModel
   // private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            receivedUuid = it.getInt("uuid")
        }
        detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        detailsViewModel.fetchProductDetailsFromRemote(receivedUuid)
        observeDetailsViewModel()
    }


    private fun observeDetailsViewModel() {
        detailsViewModel.productDetails.observe(viewLifecycleOwner) {
            it?.let {
                sv_product_details.visibility = View.VISIBLE
                binding.model = it
            }
        }
        detailsViewModel.loadError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                errorTextView.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
        detailsViewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                shimmer.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    //errorTextView.visibility = View.GONE
                    cl_product_data_group.visibility = View.GONE
                }
            }
        })

    }
}