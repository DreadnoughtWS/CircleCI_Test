package com.academy.alfagiftmini.presentation.authentication.fragment.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentOtpVerificationBinding
import com.academy.alfagiftmini.presentation.authentication.activity.RegisterActivity
import kotlin.random.Random

class OtpVerificationFragment : Fragment() {
    private lateinit var binding: FragmentOtpVerificationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View {
            // Inflate the layout for this fragment
        binding = FragmentOtpVerificationBinding.inflate(inflater, container, false)
        return binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGetOTPCode.setOnClickListener {
            //generate code
            (requireActivity() as RegisterActivity).getModel().generateOTP()
            val generatedOTP = (requireActivity() as RegisterActivity).getModel().otp
            //create and send code via sms and broadcast receiver
            //set countdown timer in viewmodel to observe
        }
    }
}