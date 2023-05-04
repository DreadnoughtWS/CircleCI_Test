package com.academy.alfagiftmini.presentation.authentication.fragment.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
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
        val bundle = arguments ?: return
        binding.etPhoneNumber.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId){
                EditorInfo.IME_ACTION_DONE -> {
                    //view model to check phone number length
                    Log.d("v", v.toString())
                    val phoneCheck = (requireActivity() as RegisterActivity).getModel().checkPhoneLength(binding.etPhoneNumber.text.toString())
                    if (phoneCheck){
                        val args = InputPhoneNumberFragmentArgs.fromBundle(bundle)
                        args.apply {
                            val phoneNumberFormatted = binding.etPhoneNumber.text.toString().replace("0", "+62")
                            val data = RegistrationDataModel(registrationData.fName, registrationData.lName, registrationData.email, registrationData.pass, phoneNumberFormatted)
                            val finalData = InputPhoneNumberFragmentDirections.actionInputPhoneNumberFragmentToOtpVerificationFragment(data)
                            view.findNavController().navigate(finalData)
                        }
                        true
                    }
                    else {
                        Toast.makeText(activity, "length must be between 11 to 13", Toast.LENGTH_SHORT).show()
                        false
                    }
                }
                else -> false
            }
        }
    }
}