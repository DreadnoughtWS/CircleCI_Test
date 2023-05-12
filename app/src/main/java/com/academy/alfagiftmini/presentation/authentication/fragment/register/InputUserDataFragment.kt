package com.academy.alfagiftmini.presentation.authentication.fragment.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.academy.alfagiftmini.databinding.FragmentInputUserDataBinding
import com.academy.alfagiftmini.domain.register.RegisterResponseDomain
import com.academy.alfagiftmini.presentation.authentication.activity.RegisterActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class InputUserDataFragment : Fragment() {
    private lateinit var binding: FragmentInputUserDataBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInputUserDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI(view)
    }

    private fun setUI(view: View) {
        binding.apply {
            var checkInput = false
            btnSubmitUserData.setOnClickListener {
                //view model to check edit text content
                lifecycleScope.launch {
                    var check: RegisterResponseDomain? = null
                    (requireActivity() as RegisterActivity).getModel()
                        .checkAvailableEmail(etEmail.text.toString()).collectLatest {
                            check = it
                        }
                    checkInput = (requireActivity() as RegisterActivity).getModel()
                        .userDataValidate(binding, requireContext(), check)
                }.invokeOnCompletion {
                    if (!checkInput) {
                        val data = InputUserDataFragmentDirections
                            .actionInputUserDataFragmentToInputPhoneNumberFragment(
                                RegistrationDataModel(
                                    etFirstName.text.toString(),
                                    etLastName.text.toString(),
                                    etEmail.text.toString(),
                                    etPassword.text.toString(),
                                    ""
                                )
                            )
                        view.findNavController().navigate(data)
                    }
                }
            }
        }
    }
}