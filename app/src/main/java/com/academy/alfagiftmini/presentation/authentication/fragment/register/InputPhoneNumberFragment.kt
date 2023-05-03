package com.academy.alfagiftmini.presentation.authentication.fragment.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.academy.alfagiftmini.databinding.FragmentInputPhoneNumberBinding

class InputPhoneNumberFragment : Fragment() {
    private lateinit var binding: FragmentInputPhoneNumberBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInputPhoneNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments ?: return
        binding.etPhoneNumber.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId){
                EditorInfo.IME_ACTION_DONE -> {
                    val args = InputPhoneNumberFragmentArgs.fromBundle(bundle)
                    args.apply {
                        val data = RegistrationDataModel(registrationData.fName, registrationData.lName, registrationData.email, registrationData.pass, binding.etPhoneNumber.text.toString())
                        val finalData = InputPhoneNumberFragmentDirections.actionInputPhoneNumberFragmentToOtpVerificationFragment(data)
                        view.findNavController().navigate(finalData)
                    }
                    true
                }
                else -> false
            }
        }
    }
}