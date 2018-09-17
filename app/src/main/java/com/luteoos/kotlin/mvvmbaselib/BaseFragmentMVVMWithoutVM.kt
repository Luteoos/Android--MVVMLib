package com.luteoos.kotlin.mvvmbaselib

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

/**
 * Created by Luteoos on 17.09.2018
 */
abstract class BaseFragmentMVVMWithoutVM : Fragment() {

    protected abstract fun getLayoutID(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        hideKeyboard()
        return inflater.inflate(getLayoutID(), container, false)
    }

    private fun hideKeyboard(){
        if(activity!!.currentFocus != null){
            val inputMng = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMng.hideSoftInputFromWindow(activity!!.currentFocus.windowToken, 0)
        }
    }
}