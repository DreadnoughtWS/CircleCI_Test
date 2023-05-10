package com.academy.alfagiftmini.presentation.homepage.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityRegisterBinding
import com.academy.alfagiftmini.databinding.FragmentAkunBinding
import com.academy.alfagiftmini.databinding.FragmentInputUserDataBinding


class FragmentAkun : Fragment() {
    private lateinit var binding: FragmentAkunBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAkunBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}