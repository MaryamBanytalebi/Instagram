package com.example.instagram.presenter.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.instagram.util.showToast

open class BaseFragment<T : ViewBinding>(
    private val inflate:(LayoutInflater,ViewGroup?,Boolean) -> T) : Fragment() {

    private var _binding : T? = null
    open val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater,container,false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun showToast(message: String) = binding.root.showToast(message)

}