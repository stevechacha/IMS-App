package com.example.imsapp.welcome

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.imsapp.R
import com.example.imsapp.databinding.WelcomeFragmentBinding

class WelcomeFragment : Fragment() {

    private lateinit var viewModel: WelcomeViewModel
    private lateinit var binding: WelcomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= WelcomeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            signInBtn.setOnClickListener {
                findNavController().navigate(R.id.welcomeFragmentToLoginFragment)
            }

            signUpBtn.setOnClickListener {
                findNavController().navigate(R.id.welcomeFragmentToRegisterFragment)
            }
        }
    }

}