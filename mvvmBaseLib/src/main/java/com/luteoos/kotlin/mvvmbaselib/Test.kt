package com.luteoos.kotlin.mvvmbaselib

import android.annotation.SuppressLint
import io.realm.Realm

@SuppressLint("ValidFragment")
private class Test() {
    private val vm = object : BaseViewModel(Realm.getDefaultInstance()){}
    private val a = object : BaseActivityMVVM<BaseViewModel>(){
        override fun getLayoutID(): Int {
            viewModel = vm
            return 0
        }
    }
    private var b = object : BaseFragmentMVVM<BaseViewModel>(){
        override fun getLayoutID(): Int {
            viewModel = vm
            return 0
        }
    }
    private var c = object : BaseFragmentMVVMWithoutVM(){
        override fun getLayoutID(): Int = 0
    }

}