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
import com.academy.alfagiftmini.presentation.authentication.viewmodel.RegisterViewModel
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
            //fill edit text from viewmodel
            activity().getModel().firstName.observe(viewLifecycleOwner){
                etFirstName.setText(it)
            }
            activity().getModel().lastName.observe(viewLifecycleOwner){
                etLastName.setText(it)
            }
            activity().getModel().eMail.observe(viewLifecycleOwner){
                etEmail.setText(it)
            }

            //btn submit
            btnSubmitUserData.setOnClickListener {
                //view model to check edit text content
                lifecycleScope.launch {
                    var check: RegisterResponseDomain? = null

                    //check if email inputted already exists
                    activity().getModel()
                        .checkAvailableEmail(etEmail.text.toString()).collectLatest {
                            check = it
                        }

                    //input validation
                    checkInput = activity().getModel()
                        .userDataValidate(binding, requireContext(), check)
                }.invokeOnCompletion {
                    //check if there any invalid input
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

            //btn back
            btnRegisterBack.setOnClickListener{
                activity().finish()
            }
            // end of binding apply
        }

    }

    private fun activity(): RegisterActivity {
        return (requireActivity() as RegisterActivity)
    }
}