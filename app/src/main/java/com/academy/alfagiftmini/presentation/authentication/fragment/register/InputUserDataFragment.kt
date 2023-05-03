package com.academy.alfagiftmini.presentation.authentication.fragment.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.academy.alfagiftmini.databinding.FragmentInputUserDataBinding

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
                if (etEmail.text.isNullOrEmpty() || etFirstName.text.isNullOrEmpty() || etLastName.text.isNullOrEmpty() || etPassword.text.isNullOrEmpty()){
                    Toast.makeText(activity, "input all field data", Toast.LENGTH_SHORT).show()
                }
                else{
                    val data = InputUserDataFragmentDirections.actionInputUserDataFragmentToInputPhoneNumberFragment(RegistrationDataModel(etFirstName.text.toString(), etLastName.text.toString(), etEmail.text.toString(), etPassword.text.toString(), ""))
                    view.findNavController().navigate(data)
                }
            }
        }
    }
}