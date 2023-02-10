package com.example.imsapp.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.imsapp.R
import com.example.imsapp.databinding.LoginFragmentBinding
import com.example.imsapp.databinding.RegisterFragmentBinding
import com.example.imsapp.utils.observeEvent
import com.example.imsapp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.register_fragment) {
    private val viewModel: RegisterViewModel by viewModels()

    private lateinit var binding: RegisterFragmentBinding
//    private val binding by viewBinding(RegisterFragmentBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= RegisterFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            confirmBtm.setOnClickListener {
                findNavController().navigate(R.id.registerFragmentToLoginFragment)
            }

        }

//        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                SignUpUIState.Loading -> {}  //renderLoading()
                is SignUpUIState.Error -> {
                    //renderError(errorTitle = it.title, errorMessage = it.message)
                }
                else -> {}
            }
        }

        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is SignUpActions.Navigate -> findNavController().navigate(it.destination)
            }
        }
    }

}