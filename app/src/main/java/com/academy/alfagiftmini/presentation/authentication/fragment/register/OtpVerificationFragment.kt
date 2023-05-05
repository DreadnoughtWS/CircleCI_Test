package com.academy.alfagiftmini.presentation.authentication.fragment.register

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentOtpVerificationBinding
import com.academy.alfagiftmini.domain.register.RegisterDataDomain
import com.academy.alfagiftmini.presentation.authentication.activity.RegisterActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class OtpVerificationFragment : Fragment() {
    private lateinit var binding: FragmentOtpVerificationBinding
    private var generatedOTP = "123456"
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
        //belum di tes
        val bundle = arguments ?: return
        val args = InputPhoneNumberFragmentArgs.fromBundle(bundle)
        Log.d("check", args.registrationData.phoneNumber)

        checkPermissions()
        setOTPGenerator()
        binding.pvOtpCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.pvOtpCode.removeTextChangedListener(this)
                Log.d("test", s.toString())
                if (binding.pvOtpCode.text.toString() == generatedOTP) {
                    Toast.makeText(context, "verified", Toast.LENGTH_SHORT).show()
                    //post to server and go to home activity
                    lifecycleScope.launch {
                        (requireActivity() as RegisterActivity).getModel().registerNewUser(
                            RegisterDataDomain(
                                id = null,
                                args.registrationData.email,
                                args.registrationData.pass,
                                args.registrationData.fName,
                                args.registrationData.lName,
                                args.registrationData.phoneNumber,
                                memberId = null
                            )
                        ).collectLatest {
                            //collect the response from api including access token and id for PUT update
                        }
                    }

                }
                binding.pvOtpCode.setSelection(binding.pvOtpCode.text?.length!!)
                binding.pvOtpCode.addTextChangedListener(this)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun checkPermissions() {
        if (ActivityCompat.checkSelfPermission(
                (requireActivity() as RegisterActivity),
                Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                (requireActivity() as RegisterActivity),
                arrayOf(Manifest.permission.SEND_SMS),
                101
            )
        }
    }

    private fun setOTPGenerator() {
        binding.btnGetOTPCode.setOnClickListener {
            //generate code
            (requireActivity() as RegisterActivity).getModel().generateOTP()
            generatedOTP = (requireActivity() as RegisterActivity).getModel().otp
            Log.d("OTP", generatedOTP)

            //create and send code via sms and broadcast receiver, still not working

            //set countdown timer in viewmodel to observe
            (requireActivity() as RegisterActivity).getModel().otpCountdownTimer()
            observer()
        }
    }

    private fun observer() {
        (requireActivity() as RegisterActivity).getModel().timer.observe(
            viewLifecycleOwner,
            Observer {
                val timerFormatted = DateUtils.formatElapsedTime(it.toLong())
                binding.btnGetOTPCode.text =
                    getString(R.string.send_otp_again).plus(timerFormatted.toString())
            })
        (requireActivity() as RegisterActivity).getModel().finished.observe(
            viewLifecycleOwner,
            Observer {
                if (it) {
                    binding.btnGetOTPCode.text = getString(R.string.send_otp_code)
                    setOTPGenerator()
                } else binding.btnGetOTPCode.setOnClickListener(null)
            })
    }
}