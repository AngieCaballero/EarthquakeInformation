package com.example.earthquakeinformation.ui.signin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.earthquakeinformation.databinding.FragmentSigninBinding
import com.example.earthquakeinformation.ui.base.BaseFragment
import com.example.earthquakeinformation.ui.main.tools.setGone
import com.example.earthquakeinformation.ui.main.tools.setVisible
import com.example.earthquakeinformation.ui.signin.viewmodel.SigninViewModel
import com.example.earthquakeinformation.ui.signup.view.SignupDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SigninFragment: BaseFragment<FragmentSigninBinding>() {

    private val viewModel: SigninViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObserve()
        viewModel.countUser()
    }

    override fun onCreateBinding(inflater: LayoutInflater): FragmentSigninBinding =
        FragmentSigninBinding.inflate(inflater)

    private fun setListeners(){
        binding.signinButton.setOnClickListener {
            val name = binding.signinUserEditText.text.toString()
            val password = binding.signinPasswordEditText.text.toString()
            viewModel.authenticateUser(name, password)
        }
    }

    private fun setObserve(){
        viewModel.isEmptyUserTable.observe(viewLifecycleOwner) { isEmptyUserTable ->
            if (isEmptyUserTable) openSignupDialogFragment()
        }

        viewModel.isUserRegistered.observe(viewLifecycleOwner) { isUserRegistered ->
            when(isUserRegistered) {
                true -> {
                    binding.signinErrorText.setGone()
                    findNavController().navigate(
                        SigninFragmentDirections.actionSigninFragmentToHomeFragment()
                    )
                }
                false -> {
                    binding.signinErrorText.setVisible()
                }

                else -> { /* */ }
            }
        }
    }

    private fun openSignupDialogFragment() {
        val signupDialogFragment = SignupDialogFragment()
        signupDialogFragment.show(parentFragmentManager, signupDialogFragment.tag)
    }
}