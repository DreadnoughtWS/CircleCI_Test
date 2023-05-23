package com.academy.alfagiftmini.presentation.authentication.fragment.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentInputUserDataBinding
import com.academy.alfagiftmini.domain.register.RegisterResponseDomain
import com.academy.alfagiftmini.presentation.PresentationUtils.isNetworkAvailable
import com.academy.alfagiftmini.presentation.authentication.activity.LoginActivity
import com.academy.alfagiftmini.presentation.authentication.activity.RegisterActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class InputUserDataFragment : Fragment() {
    private lateinit var binding: FragmentInputUserDataBinding
    private lateinit var progressBar: AlertDialog
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
        setProgressBar()
        setUI(view)
    }

    private fun setUI(view: View) {
        binding.apply {
            //fill edit text from view model
            activity().getModel().firstName.observe(viewLifecycleOwner) {
                etFirstName.setText(it)
            }
            activity().getModel().lastName.observe(viewLifecycleOwner) {
                etLastName.setText(it)
            }
            activity().getModel().eMail.observe(viewLifecycleOwner) {
                etEmail.setText(it)
            }

            //btn submit
            setSubmitButton(view)

            //btn back
            setBackButton()
        }

    }

    private fun setBackButton() {
        binding.btnRegisterBack.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity().finish()
        }
    }

    private fun setSubmitButton(view: View) {
        binding.apply {
            btnSubmitUserData.setOnClickListener {
                //check internet
                checkInternet(view)

            }
        }

        //end of function
    }

    private fun checkInternet(view: View) {
        if (isNetworkAvailable(requireContext())) {
            //view model to check edit text content
            dataValidationAndNavigation(view)
        } else {
            activity().hideKeyboard(view)
            networkDialog(view)
        }
    }

    private fun dataValidationAndNavigation(view: View) {
        var checkInput = false
        binding.apply {
            lifecycleScope.launch {
                //loading start
                progressBar.show()
                //check if email inputted already exists
                var check: RegisterResponseDomain? = null
                activity().getModel()
                    .checkAvailableEmail(etEmail.text.toString()).collectLatest {
                        check = it
                    }

                //input validation
                checkInput = activity().getModel()
                    .userDataValidate(binding, requireContext(), check)
            }.invokeOnCompletion {
                //end loading
                progressBar.dismiss()
                //check if there any invalid input
                if (!checkInput) {
                    val data = InputUserDataFragmentDirections
                        .actionInputUserDataFragmentToInputPhoneNumberFragment(
                            RegistrationDataModel(
                                etFirstName.text.toString(),
                                etLastName.text.toString(),
                                etEmail.text.toString(),
                                etPassword.text.toString(),
                                "",
                                listOf(),
                            )
                        )
                    view.findNavController().navigate(data)
                }
            }
        }
    }

    private fun networkDialog(view: View) {
        val dialogBuilder = AlertDialog.Builder(requireContext(), R.style.NetworkAlertDialogTheme)
        dialogBuilder.setMessage("No Network Connection detected, Please make sure you have a stable connection to the internet, then press retry to refresh the app and try again.")
        dialogBuilder.setCancelable(false)
        dialogBuilder.setIcon(R.drawable.no_internet_logo)
        dialogBuilder.setTitle("No Network Connection")
        dialogBuilder.setPositiveButton("RETRY") { _, _ ->
            checkInternet(view)
        }
        dialogBuilder.setNegativeButton("CLOSE") { dialog, _ ->
            dialog.cancel()
        }
        val connectionAlertDialog = dialogBuilder.create()
        connectionAlertDialog.window?.setBackgroundDrawableResource(R.drawable.connection_dialog_background)
        connectionAlertDialog.show()
    }

    private fun activity(): RegisterActivity {
        return (requireActivity() as RegisterActivity)
    }

    private fun setProgressBar() {
        progressBar = AlertDialog.Builder(requireContext(), R.style.NetworkAlertDialogTheme)
            .setCancelable(false).setView(R.layout.progress).create()
    }
}