package com.academy.alfagiftmini.presentation.authentication.fragment.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentInputPhoneNumberBinding
import com.academy.alfagiftmini.presentation.authentication.activity.RegisterActivity

class InputPhoneNumberFragment : Fragment() {
    private lateinit var binding: FragmentInputPhoneNumberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputPhoneNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI(view)
    }

    private fun setUI(view: View) {
        val bundle = arguments ?: return
        binding.apply {
            tvPhoneFormatError.visibility = View.GONE
            etPhoneNumber.setOnEditorActionListener { v, actionId, event ->
                return@setOnEditorActionListener when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        //view model to check phone number length
                        val phoneCheck = (requireActivity() as RegisterActivity).getModel()
                            .checkPhoneLength(etPhoneNumber.text.toString())
                        if (phoneCheck) {
                            val phoneNumberFormatted =
                                (requireActivity() as RegisterActivity).getModel()
                                    .phoneNumberFormatted(etPhoneNumber.text.toString())
                            val args = InputPhoneNumberFragmentArgs.fromBundle(bundle)
                            args.apply {
                                val data = RegistrationDataModel(
                                    registrationData.fName,
                                    registrationData.lName,
                                    registrationData.email,
                                    registrationData.pass,
                                    phoneNumberFormatted
                                )
                                val finalData =
                                    InputPhoneNumberFragmentDirections.actionInputPhoneNumberFragmentToOtpVerificationFragment(
                                        data
                                    )
                                view.findNavController().navigate(finalData)
                            }
                        } else {
                            tvPhoneFormatError.visibility = View.VISIBLE
                            tvPhoneFormatError.text = getString(R.string.phone_format_error)
                            etPhoneNumber.setBackgroundResource(R.drawable.edit_text_error_border)
                        }
                        true
                    }
                    else -> false
                }
            }
        }

    }
}