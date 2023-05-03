package com.academy.alfagiftmini.presentation.authentication.fragment.register

import android.Manifest
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_MUTABLE
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentOtpVerificationBinding
import com.academy.alfagiftmini.presentation.authentication.activity.RegisterActivity
import kotlin.random.Random

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
        binding.pvOtpCode.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.pvOtpCode.removeTextChangedListener(this)
                Log.d("test", s.toString())
                if(binding.pvOtpCode.text.toString() == generatedOTP){
                    Toast.makeText(context, "verified", Toast.LENGTH_SHORT).show()
                }
                binding.pvOtpCode.setSelection(binding.pvOtpCode.text?.length!!)
                binding.pvOtpCode.addTextChangedListener(this)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun checkPermissions() {
        if (ActivityCompat.checkSelfPermission((requireActivity() as RegisterActivity), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions((requireActivity() as RegisterActivity), arrayOf(Manifest.permission.SEND_SMS), 101)
        }
    }

    private fun setOTPGenerator() {
        binding.btnGetOTPCode.setOnClickListener {
            //generate code
            (requireActivity() as RegisterActivity).getModel().generateOTP()
            generatedOTP = (requireActivity() as RegisterActivity).getModel().otp
            Log.d("OTP", generatedOTP)

            //create and send code via sms and broadcast receiver, still not working
            sendSMS(generatedOTP)
            //set countdown timer in viewmodel to observe
            (requireActivity() as RegisterActivity).getModel().otpCountdownTimer()
            observer()
        }
    }

    private fun sendSMS(message: String) {
        try {
            val sms: SmsManager = (requireActivity() as RegisterActivity).getSystemService(SmsManager::class.java)
            val sentPI = PendingIntent.getBroadcast(context, 0, Intent("SMS_SENT"), FLAG_IMMUTABLE)
            sms.sendTextMessage("+6281249400599", null, message, sentPI, null)
            Toast.makeText(context, "sent", Toast.LENGTH_SHORT).show()
        }catch (e: Exception){
            Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observer(){
        (requireActivity() as RegisterActivity).getModel().timer.observe(viewLifecycleOwner, Observer {
            val timerFormatted = DateUtils.formatElapsedTime(it.toLong())
            binding.btnGetOTPCode.text = getString(R.string.send_otp_again).plus(timerFormatted.toString())
        })
        (requireActivity() as RegisterActivity).getModel().finished.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.btnGetOTPCode.text = getString(R.string.send_otp_code)
                setOTPGenerator()
            }
            else binding.btnGetOTPCode.setOnClickListener(null)
        })
    }
}