package com.peruapps.christopher_elias.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

abstract class BaseActivity<T : ViewDataBinding, out V : BaseViewModel<*>> : DaggerAppCompatActivity()
{
    private lateinit var viewDataBinding: T
    private var viewModel: V? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    //region Base
    @LayoutRes
    abstract fun getLayoutId() : Int

    abstract fun getViewModel() : V

    abstract fun getBindingVariable() : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Perform Data Binding Using Factory
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())

        // Binding the viewModel
        viewModel = if (viewModel == null) getViewModel() else viewModel

        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()
    }
    //endregion

    protected fun hideKeyboard() {
        val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = this.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun logE(@Nullable message: String?){
        Log.e(this.javaClass.simpleName, message?:"message is null")
    }

    protected fun startNewActivity(activity: Class<*>, clearTask : Boolean? = null) {
        startActivity(Intent(this, activity).apply {
            if (clearTask != null && clearTask){
                addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        })
    }

    protected fun startNewActivityForResult(activity: Class<*>, requestCode: Int){
        startActivityForResult(Intent(this, activity), requestCode)
    }

    /*override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }*/
}