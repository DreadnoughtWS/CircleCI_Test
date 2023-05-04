package com.academy.alfagiftmini.presentation.authentication.fragment.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.academy.alfagiftmini.databinding.FragmentInputUserDataBinding
import com.academy.alfagiftmini.presentation.authentication.activity.RegisterActivity

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
        binding.apply {
            btnSubmitUserData.setOnClickListener {
                //view model to check edit text content

                if (etEmail.text.isNullOrEmpty() || etFirstName.text.isNullOrEmpty() || etLastName.text.isNullOrEmpty() || etPassword.text.isNullOrEmpty()) {
                    //view model to show error for each edit text
                    (requireActivity() as RegisterActivity).getModel().userDataValidate(binding)
                } else {
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