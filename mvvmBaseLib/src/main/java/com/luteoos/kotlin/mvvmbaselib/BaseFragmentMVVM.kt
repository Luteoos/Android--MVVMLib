package com.luteoos.kotlin.mvvmbaselib

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

/**
 * Created by Luteoos on 17.09.2018
 */
abstract class BaseFragmentMVVM<T: BaseViewModel> : Fragment(){
    lateinit var viewModel: T

    protected abstract fun getLayoutID(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        hideKeyboard()
        return inflater.inflate(getLayoutID(), container,false)
    }

    override fun onStop() {
        super.onStop()
        viewModel.detachBus()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.detachDisposable()
    }

    private fun hideKeyboard(){
        if(activity!!.currentFocus != null){
            val inputMng = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMng.hideSoftInputFromWindow(activity!!.currentFocus.windowToken, 0)
        }
    }

    val isNetworkOnLine: Boolean
        get(){
            val activeNetInf = (activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                    .activeNetworkInfo
            return activeNetInf != null && activeNetInf.isConnected
        }
}