package io.github.luteoos.mvvmbaselib

import android.content.Context
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivityMVVMWithoutVM : AppCompatActivity() {

    /**
     * override and set layoutId here
     */
    abstract fun getLayoutID(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
    }

    /**
     * true to portrait <-> default
     * false to landscape
     */
    fun setPortraitOrientation(isPortrait: Boolean = true) {
        requestedOrientation = when(isPortrait){
            true -> ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            false -> ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }

    /**
     * cal for hide keyboard from this activity
     */
    fun hideKeyboard(){
        if(this.currentFocus != null){
            val inputMng = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMng.hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
        }
    }

    /**
     * check if network connection is possible
     */
    val isNetworkOnLine: Boolean
        get(){
            val activeNetInf = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                    .activeNetworkInfo
            return activeNetInf != null && activeNetInf.isConnected
        }

}