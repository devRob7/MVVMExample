package com.lazyandroid.mvvmexample

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lazyandroid.mvvmexample.databinding.FragmentABinding
import com.lazyandroid.mvvmexample.viewmodel.ClientViewModel
import org.json.JSONArray


class FragmentA : Fragment() {

    private lateinit var binding: FragmentABinding
    private lateinit var viewModel: ClientViewModel

    private var postsObserver = Observer<JSONArray> {
        d("test","ApI Results :::")
        d("test","$it")
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentABinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ClientViewModel::class.java)

        viewModel.postsData.observe(viewLifecycleOwner,postsObserver)


        binding.nextButton.setOnClickListener {
            viewModel.getPosts(requireContext()){

                d("test","$it")
            }

//            if (binding.clientName.text.isBlank()) {
//                binding.clientName.error = "This field Cannot be blank"
//            } else {
//                viewModel.addClientInfo(ClientInfo(binding.clientName.text.toString()))
//                replaceFragment(requireActivity(),FragmentB())
//            }

        }

        return binding.root
    }

}