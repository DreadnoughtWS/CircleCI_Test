package com.academy.alfagiftmini.presentation.homepage.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.academy.alfagiftmini.databinding.FragmentAkunBinding
import com.academy.alfagiftmini.presentation.akun.AboutAppActivity
import com.academy.alfagiftmini.presentation.akun.EditAkunActivity
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

            //logout and delete akun
        }
    }

}