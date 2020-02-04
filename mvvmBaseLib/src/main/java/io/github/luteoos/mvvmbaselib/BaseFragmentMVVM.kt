package io.github.luteoos.mvvmbaselib

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

/**
 * Created by Luteoos on 17.09.2018
 */
abstract class BaseFragmentMVVM<T: BaseViewModel> : BaseFragmentMVVMWithoutVM(){
    /**
     * init it with getViewModel<T>(this)
     */
    lateinit var viewModel: T

    /**
     * invoke when VM is assigned
     */
    fun connectToVMMessage(){
        viewModel.message().observe(this, Observer { onVMMessage(it) })
    }

    /**
     * override it to handle message from ViewModel
     */
    open fun onVMMessage(msg: Event<Int>){

    }

    companion object {
        inline fun <reified T : BaseViewModel?> getViewModel(fragment: Fragment): T {
            return ViewModelProviders.of(fragment).get(T::class.java)
        }

        inline fun <reified T : BaseViewModel?> getViewModel(activity: FragmentActivity): T {
            return ViewModelProviders.of(activity).get(T::class.java)
        }
    }
}