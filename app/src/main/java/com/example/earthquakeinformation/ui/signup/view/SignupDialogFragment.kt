package com.example.earthquakeinformation.ui.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.earthquakeinformation.R
import com.example.earthquakeinformation.databinding.DialogFragmentSignupBinding
import com.example.earthquakeinformation.ui.base.BaseDialogFragment
import com.example.earthquakeinformation.ui.main.tools.setGone
import com.example.earthquakeinformation.ui.main.tools.setVisible
import com.example.earthquakeinformation.ui.signup.viewmodel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupDialogFragment: BaseDialogFragment<DialogFragmentSignupBinding>() {

    private val viewModel: SignupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onCreateBinding(inflater: LayoutInflater): DialogFragmentSignupBinding =
        DialogFragmentSignupBinding.inflate(inflater)

    private fun setListeners(){
        binding.signupButton.setOnClickListener {
            val userName = binding.signupUserEditText.text.toString()
            val password = binding.signupPasswordEditText.text.toString()
            val confirmPassword = binding.signupConfirmPasswordEditText.text.toString()

            when{
                viewModel.isDataEmpty(userName, password, confirmPassword) -> {
                    showMessageError(resources.getString(R.string.empty_data))
                }
                viewModel.isPasswordIncorrect(password, confirmPassword) -> {
                    showMessageError(resources.getString(R.string.passwords_dont_match))
                }
                else -> {
                    binding.signupErrorText.setGone()
                    viewModel.saveUserData(userName, password)
                    dismiss()
                }
            }
        }
    }

    private fun showMessageError(messageError: String = "") {
        binding.signupErrorText.setVisible()
        binding.signupErrorText.text = messageError
    }

    companion object {
        const val TAG = "SignupDialogFragment"
    }
}