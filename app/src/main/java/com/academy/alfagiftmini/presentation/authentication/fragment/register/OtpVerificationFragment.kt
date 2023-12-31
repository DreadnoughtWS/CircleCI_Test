package com.academy.alfagiftmini.presentation.authentication.fragment.register

import android.Manifest
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
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentOtpVerificationBinding
import com.academy.alfagiftmini.domain.register.RegisterDataDomain
import com.academy.alfagiftmini.presentation.PresentationUtils.isNetworkAvailable
import com.academy.alfagiftmini.presentation.authentication.activity.RegisterActivity
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class OtpVerificationFragment : Fragment() {
    private lateinit var binding: FragmentOtpVerificationBinding
    private lateinit var progressBar: AlertDialog
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
        setProgressBar()
        checkPermissions()
        setButton(view)
        setOTPInput(view)
    }

    private fun setOTPInput(view: View) {
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
                        val inputMethodManager = activity().getInputManager()
                        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                        progressBar.show()
                        if (checkInternet()) {
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
                                        memberId = null,
                                        RegistrationDataModel.transforms(args.registrationData.alamat)
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
                        } else {
                            // on below line hiding our keyboard
                            activity().hideKeyboard(view)
                            progressBar.dismiss()
                            pvOtpCode.setText("")
                            networkDialog(view)
                        }
                    }

                    //fail
                    else if (pvOtpCode.text.toString() != generatedOTP && pvOtpCode.text?.length == pvOtpCode.itemCount) {
                        tvOtpError.visibility = View.VISIBLE
                        tvOtpError.text = getString(R.string.otp_error_msg)
                        pvOtpCode.setText("")

                        // on below line hiding our keyboard
                        activity().hideKeyboard(view)
                    }
                    pvOtpCode.setSelection(pvOtpCode.text?.length!!)
                    pvOtpCode.addTextChangedListener(this)
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })
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

    private fun setButton(view: View) {
        setOTPGenerateButton(view)
        setBackButton()
    }

    private fun setBackButton() {
        val bundle = arguments ?: return
        binding.btnOtpBack.setOnClickListener {
            val args = OtpVerificationFragmentArgs.fromBundle(bundle)
            args.apply {
                val data = RegistrationDataModel(
                    registrationData.fName,
                    registrationData.lName,
                    registrationData.email,
                    registrationData.pass,
                    registrationData.phoneNumber,
                    registrationData.alamat
                )
                val finalData =
                    OtpVerificationFragmentDirections.actionOtpVerificationFragmentToInputPhoneNumberFragment(
                        data
                    )
                findNavController().navigate(finalData)
            }
        }
    }

    private fun checkInternet(): Boolean {
        return isNetworkAvailable(requireContext())
    }

    private fun generateOTPCode(view: View) {
        //generate code
        activity().getModel().generateOTP()
        generatedOTP = activity().getModel().otp
        Log.d("OTP", generatedOTP)
        //create and send code via sms and broadcast receiver, still not working

        //set countdown timer in view model to observe
        activity().getModel().otpCountdownTimer()
        observer(view)
    }

    private fun networkDialog(view: View) {
        val dialogBuilder = AlertDialog.Builder(requireContext(), R.style.NetworkAlertDialogTheme)
        dialogBuilder.setMessage("No Network Connection detected, Please make sure you have a stable connection to the internet, then press retry to refresh the app and try again.")
        dialogBuilder.setCancelable(false)
        dialogBuilder.setIcon(R.drawable.no_internet_logo)
        dialogBuilder.setTitle("No Network Connection")
        dialogBuilder.setPositiveButton("RETRY") { dialog, _ ->
            if (!checkInternet()) {
                networkDialog(view)
            } else dialog.cancel()
        }
        dialogBuilder.setNegativeButton("CLOSE") { dialog, _ ->
            dialog.cancel()
        }
        val connectionAlertDialog = dialogBuilder.create()
        connectionAlertDialog.window?.setBackgroundDrawableResource(R.drawable.connection_dialog_background)
        connectionAlertDialog.show()
    }

    private fun setOTPGenerateButton(view: View) {
        binding.btnGetOTPCode.setOnClickListener {
            //check internet
            checkInternetOTPGenerate(view)
        }
    }

    private fun checkInternetOTPGenerate(view: View) {
        if (checkInternet()) {
            generateOTPCode(view)
        } else {
            networkDialog(view)
        }
    }

    private fun observer(view: View) {
        binding.apply {
            activity().getModel().timer.observe(
                viewLifecycleOwner
            ) {
                val timerFormatted = DateUtils.formatElapsedTime(it.toLong())
                btnGetOTPCode.text =
                    getString(R.string.send_otp_again).plus(timerFormatted.toString())
            }
            activity().getModel().finished.observe(
                viewLifecycleOwner
            ) {
                if (it) {
                    btnGetOTPCode.text = getString(R.string.send_otp_code)
                    setOTPGenerateButton(view)
                } else btnGetOTPCode.setOnClickListener(null)
            }
        }
    }

    private fun activity(): RegisterActivity {
        return (requireActivity() as RegisterActivity)
    }

    private fun setProgressBar() {
        progressBar = AlertDialog.Builder(requireContext(), R.style.NetworkAlertDialogTheme)
            .setCancelable(false).setView(R.layout.progress).create()
    }
}