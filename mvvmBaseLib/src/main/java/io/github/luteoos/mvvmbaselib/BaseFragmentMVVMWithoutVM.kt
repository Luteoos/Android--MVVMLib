package io.github.luteoos.mvvmbaselib

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment

/**
 * Created by Luteoos on 17.09.2018
 */
abstract class BaseFragmentMVVMWithoutVM : Fragment() {

    /**
     * override and set layoutId here
     */
    abstract fun getLayoutID(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutID(), container, false)
    }

    /**
     * call to hide keyboard
     */
    fun hideKeyboard(){
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    /**
     * check if network connection is possible
     */
    val isNetworkOnLine: Boolean
        get(){
            val activeNetInf = (activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                    .activeNetworkInfo
            return activeNetInf != null && activeNetInf.isConnected
        }
}