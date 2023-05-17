package com.academy.alfagiftmini.presentation.authentication.fragment.register

import android.Manifest
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentOtpVerificationBinding
import com.academy.alfagiftmini.domain.register.RegisterDataDomain
import com.academy.alfagiftmini.presentation.authentication.activity.LoginActivity
import com.academy.alfagiftmini.presentation.authentication.activity.RegisterActivity
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
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
        checkPermissions()
        setOTPGenerator()
        setOTPInput()
    }

    private fun setOTPInput() {
        val bundle = arguments ?: return
        val args = InputPhoneNumberFragmentArgs.fromBundle(bundle)
        binding.apply {
            //otp input field
            pvOtpCode.isCursorVisible = false
            pvOtpCode.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    pvOtpCode.removeTextChangedListener(this)
                    Log.d("test", s.toString())
                    //success
                    if (pvOtpCode.text.toString() == generatedOTP) {
                        tvOtpError.visibility = View.INVISIBLE
                        //post to server and go to home activity
                        lifecycleScope.launch {
                            activity().getModel().registerNewUser(
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
                                //collect the response from api including access token and id for shared preference update
                                if (it.error.isNullOrBlank()) {
                                    activity()
                                        .getModel()
                                        .putDataToSharedPreference(
                                            activity(),
                                            it
                                        )
                                    val intent = Intent(activity, MainActivity::class.java)
                                    startActivity(intent)
                                    activity().finish()
                                }
                            }
                        }

                    }

                    //fail
                    else if (pvOtpCode.text.toString() != generatedOTP && pvOtpCode.text?.length == pvOtpCode.itemCount) {
                        tvOtpError.visibility = View.VISIBLE
                        tvOtpError.text = getString(R.string.otp_error_msg)
                        pvOtpCode.setText("")

                        // on below line hiding our keyboard
                        val inputMethodManager = activity().getInputManager()
                        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
                    }
                    pvOtpCode.setSelection(pvOtpCode.text?.length!!)
                    pvOtpCode.addTextChangedListener(this)
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })

            //btn back
            btnOtpBack.setOnClickListener {
                findNavController().navigate(R.id.action_otpVerificationFragment_to_inputPhoneNumberFragment)
            }
            //binding apply end
        }
    }

    private fun checkPermissions() {
        if (ActivityCompat.checkSelfPermission(
                activity(),
                Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity(),
                arrayOf(Manifest.permission.SEND_SMS),
                101
            )
        }
    }

    private fun setOTPGenerator() {
        binding.btnGetOTPCode.setOnClickListener {
            //generate code
            activity().getModel().generateOTP()
            generatedOTP = activity().getModel().otp
            Log.d("OTP", generatedOTP)

            //create and send code via sms and broadcast receiver, still not working

            //set countdown timer in viewmodel to observe
            activity().getModel().otpCountdownTimer()
            observer()
        }
    }

    private fun observer() {
        activity().getModel().timer.observe(
            viewLifecycleOwner
        ) {
            val timerFormatted = DateUtils.formatElapsedTime(it.toLong())
            binding.btnGetOTPCode.text =
                getString(R.string.send_otp_again).plus(timerFormatted.toString())
        }
        activity().getModel().finished.observe(
            viewLifecycleOwner
        ) {
            if (it) {
                binding.btnGetOTPCode.text = getString(R.string.send_otp_code)
                setOTPGenerator()
            } else binding.btnGetOTPCode.setOnClickListener(null)
        }
    }

    private fun activity(): RegisterActivity {
        return (requireActivity() as RegisterActivity)
    }
}