package com.example.instagram.presenter.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.instagram.databinding.FragmentLoginBinding
import com.example.instagram.presenter.base.BaseFragment
import com.example.instagram.util.setLocalApp

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding :: inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLanguageSpinner()
    }

    private fun setupLanguageSpinner(){

        binding.spLanguages.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0) requireContext().setLocalApp("en")
                else requireContext().setLocalApp("fa")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }
}