package com.luteoos.kotlin.mvvmbaselib

import android.annotation.SuppressLint

@SuppressLint("ValidFragment")
private class Test() {
    private val vm = @SuppressLint("StaticFieldLeak")
    object : BaseViewModel(){}
    private val a = object : BaseActivityMVVM<BaseViewModel>(){

        override fun getLayoutID(): Int {
            viewModel = getViewModel(this)
            return 0
        }
    }
    private var b = object : BaseFragmentMVVM<BaseViewModel>(){

        override fun getLayoutID(): Int {
            viewModel = getViewModel(this)
            return 0
        }
    }
    private var c = object : BaseFragmentMVVMWithoutVM(){
        override fun getLayoutID(): Int = 0
    }

}