package com.academy.alfagiftmini.presentation.homepage.components.fragment.customdialog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.DialogLogoutBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.authentication.activity.LoginActivity
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity

class LogoutCustomDialog : DialogFragment() {
    private lateinit var binding:DialogLogoutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DialogLogoutBinding.inflate(inflater, container, false)
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.shape_logout_dialog);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBtn()
    }

    private fun setBtn() {
        binding.apply {
            btnBatal.setOnClickListener {
                dismiss()
            }
            btnYa.setOnClickListener {
                val sharedPreference =
                    (requireActivity() as MainActivity).application.getSharedPreferences(
                        PresentationUtils.SHARED_PREFERENCE, Context.MODE_PRIVATE
                    )
                sharedPreference.edit().clear().apply()
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
                (requireActivity() as MainActivity).finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

}