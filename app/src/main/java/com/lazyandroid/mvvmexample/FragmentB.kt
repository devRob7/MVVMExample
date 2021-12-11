package com.lazyandroid.mvvmexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lazyandroid.mvvmexample.databinding.FragmentBBinding
import com.lazyandroid.mvvmexample.model.ClientInfo
import com.lazyandroid.mvvmexample.viewmodel.ClientViewModel


class FragmentB : Fragment() {

    private lateinit var binding: FragmentBBinding
    private lateinit var viewModel: ClientViewModel


    private val observer = Observer<MutableList<ClientInfo>> { data ->
        binding.clientName.text = data.toString()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ClientViewModel::class.java)
        viewModel.clientsData.observe(viewLifecycleOwner,observer)

        binding.nextButton.setOnClickListener {
            replaceFragment(requireActivity(),FragmentA())
        }

        return binding.root
    }


}