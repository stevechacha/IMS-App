package com.example.imsapp.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.imsapp.R
import com.example.imsapp.databinding.LoginFragmentBinding
import com.example.imsapp.utils.observeEvent
import com.example.imsapp.utils.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.login_fragment) {
    private val viewModel: LoginViewModel by viewModels()
    private val binding by viewBinding(LoginFragmentBinding::bind)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObservers()

    }

    private fun setUpObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                LoginUIState.Loading -> {
                    //renderLoading()
                }
                is LoginUIState.Error -> {
                    //renderError()
                    renderError(it.message)
                }
                is LoginUIState.Message -> {
                    renderSuccess(it.message)
                }
                else -> {}
            }
        }
        viewModel.interactions.observeEvent(viewLifecycleOwner) {
            when (it) {
                is LoginActions.Navigate -> findNavController().navigate(it.destination)
                is LoginActions.BottomNavigate -> showDialog(it.bottomSheetDialogFragment)
            }
        }
    }

    private fun renderError(message: String) {
        binding.apply {
//            loading.isVisible = false
//            loginBtn.isEnable = true
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDialog(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        bottomSheetDialogFragment.show(parentFragmentManager, bottomSheetDialogFragment.tag)

    }

    private fun renderSuccess(message: String) {
        binding.apply {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

}