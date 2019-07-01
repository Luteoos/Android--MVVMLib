package io.github.luteoos.mvvmbaselib

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Luteoos on 17.09.2018
 */
abstract class BaseFragmentMVVM<T: BaseViewModel> : BaseFragmentMVVMWithoutVM(){
    /**
     * init it with getViewModel<T>(this)
     */
    lateinit var viewModel: T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        hideKeyboard()
        return inflater.inflate(getLayoutID(), container,false)
    }

    /**
     * invoke when VM is assigned
     */
    fun connectToVMMessage(){
        viewModel.VMMessage().observe(this, Observer { value -> onVMMessage(value) })
    }

    /**
     * override it to handle message from ViewModel
     * 'null' or '0' skips method body
     */
    open fun onVMMessage(msg: Int?){
        if(msg == null || msg == 0)
            return
    }

    companion object {
        inline fun <reified T : BaseViewModel?> getViewModel(fragment: Fragment): T {
            return ViewModelProviders.of(fragment).get(T::class.java)
        }

        inline fun <reified T : BaseViewModel?> getViewModel(fragment: FragmentActivity): T {
            return ViewModelProviders.of(fragment).get(T::class.java)
        }
    }
}