package io.github.luteoos.mvvmbaselib

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.appcompat.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent

/**
 * Created by Luteoos on 13.09.2018
 */
abstract class BaseActivityMVVM<T: BaseViewModel> : BaseActivityMVVMWithoutVM() {

    /**
     * init it with getViewModel<T>(this)
     */
    lateinit var viewModel: T

    /**
     * To invoke when VM is assigned
     */
    fun connectToVMMessage(){
        viewModel.message().observe(this, Observer { onVMMessage(it) })
    }

    /**
     * override it to handle message from ViewModel
     *
     */
    open fun onVMMessage(msg: Event<Int>){
    }

    companion object {
        inline fun <reified T : BaseViewModel> getViewModel(activity: FragmentActivity): T {
            return ViewModelProviders.of(activity).get(T::class.java)
        }
    }
}
