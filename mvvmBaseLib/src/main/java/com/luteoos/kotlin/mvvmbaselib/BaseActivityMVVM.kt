package com.luteoos.kotlin.mvvmbaselib

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager

/**
 * Created by Luteoos on 13.09.2018
 */
abstract class BaseActivityMVVM<T: BaseViewModel> : AppCompatActivity() {

    /**
     * init it with getViewModel<T>(this)
     */
    lateinit var viewModel: T

    protected abstract fun getLayoutID(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideKeyboard()
        setContentView(getLayoutID())
    }

    protected fun connectToVMMessage(){
        /**
         * invoke this after creating viewmodel to observe message and use onVMMessage
         */
        viewModel.VMMessage().observe(this, Observer { value -> onVMMessage(value) })
    }

    /**
     * override it to handle message from ViewModel
     */
    open fun onVMMessage(msg: String?){

    }

    override fun onBackPressed() {
        hideKeyboard()
        super.onBackPressed()
    }

    override fun onStop() {
        viewModel.detachBus()
        super.onStop()
    }

    override fun onDestroy() {
        viewModel.detachDisposable()
        super.onDestroy()
    }

    fun setPortraitOrientation(isPortrait: Boolean) {
        requestedOrientation = when(isPortrait){
            true -> ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            false -> ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }

    fun hideKeyboard(){
        if(this.currentFocus != null){
            val inputMng = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMng.hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
        }
    }

    val isNetworkOnLine: Boolean
        get(){
            val activeNetInf = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                    .activeNetworkInfo
            return activeNetInf != null && activeNetInf.isConnected
        }

    companion object {
        inline fun <reified T : BaseViewModel?> getViewModel(fragment: FragmentActivity): T {
            return ViewModelProviders.of(fragment).get(T::class.java)
        }
    }
}
