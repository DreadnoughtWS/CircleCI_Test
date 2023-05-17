package com.academy.alfagiftmini.presentation.homepage.fragment

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.academy.alfagiftmini.databinding.FragmentAkunBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.akun.AboutAppActivity
import com.academy.alfagiftmini.presentation.akun.EditAkunActivity
import com.academy.alfagiftmini.presentation.authentication.activity.LoginActivity
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.MainActivityViewModel


class FragmentAkun : Fragment() {
    private lateinit var binding: FragmentAkunBinding
    private lateinit var mainViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAkunBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = (requireActivity() as MainActivity).getViewModelMain()
        setUI()
    }

    private fun setUI() {
        binding.apply {
            mainViewModel._getUserData.observe(requireActivity()) {
                tvFullNameAkun.text = it.getFullName()
                tvNoHpAkun.text = it.phone
            }

            llProfilAkun.setOnClickListener {
                val intent = Intent(activity, EditAkunActivity::class.java)
                var id: Int = -1
                mainViewModel._getUserData.observe(requireActivity()) {
                    id = it.id!!
                }
                intent.putExtra("ID", id)
                startActivity(intent)
                //(requireActivity() as MainActivity).startForResult.launch(intent)
            }

            llAboutApp.setOnClickListener {
                val intent = Intent(activity, AboutAppActivity::class.java)
                startActivity(intent)
            }

            //logout akun
            llLogoutApp.setOnClickListener {
                buildDialogAlert()
            }

            //binding apply end
        }
    }

    private fun buildDialogAlert() {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setMessage("Logout from this App?")
            .setCancelable(false)
            .setPositiveButton("Confirm") { dialog, which ->
                val sharedPreference =
                    (requireActivity() as MainActivity).application.getSharedPreferences(
                        PresentationUtils.SHARED_PREFERENCE,
                        Context.MODE_PRIVATE
                    )
                sharedPreference.edit().clear().apply()
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
                (requireActivity() as MainActivity).finish()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Log Out")
        // show alert dialog
        alert.show()
    }

}