package com.peruapps.christopher_elias.ui.base

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.peruapps.christopher_elias.R
import com.peruapps.christopher_elias.ui.compound.Loading
import com.peruapps.christopher_elias.utils.VersionUtils
import dagger.android.support.DaggerFragment
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

abstract class BaseFragment<T : ViewDataBinding, out V : BaseViewModel<*>> : DaggerFragment() {

    private var baseActivity: BaseActivity<*, *>? = null

    private lateinit var viewDataBinding: T
    private lateinit var rootView: View

    private var viewModel: V? = null
    private var loading: Loading? = null

    var resultCallback: OnResult? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    //region Base
    /**
     * This function associate the xml file to the class.
     * In order to pass it to onCreateView and bind it.
     * @return layout id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Return the binding variable of the XML
     * associated to this class.
     * @return binding variable ID
     */
    abstract fun getBindingVariable(): Int

    /**
     * Init viewModel class
     * @return class that extends ViewModel
     */
    abstract fun getViewModel(): V

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            val activity = (context)
            this.baseActivity = activity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Binding the viewModel
        if (viewModel == null) {
            viewModel = getViewModel()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        rootView = viewDataBinding.root
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()
    }
    //endregion

    protected fun hideKeyboard() {
        try {

            val imm = view?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view!!.windowToken, 0)

        }catch (e: Exception){
            //logE("Exception hiding keyboard: ${e.message}")
        }
    }

    /**
     * Creates an instance of Loading class which is a indeterminate circular
     * progress dialog.
     *
     * @param isLoading      indicator for show or hide progress dialog.
     * @param resourceString some string for show as message under the dialog
     */
    fun handleProgress(isLoading: Boolean, @StringRes resourceString: Int? = null) {
        if (isLoading) {
            showProgress(resourceString)
        } else {
            hideProgress()
        }
    }

    private fun showProgress(@StringRes resourceString: Int? = null) {
        loading = Loading(view!!.context, resourceString)
        loading?.show()
    }

    private fun hideProgress() {
        loading?.hide()
        loading?.dismiss()
    }

    fun showToast(message: String?){
        Toast.makeText(context, message?:"N U L L", Toast.LENGTH_LONG).show()
    }

    fun showToast(@StringRes resourceString: Int){
        Toast.makeText(context, getString(resourceString), Toast.LENGTH_LONG).show()
    }

    /**
     * Replace a fragment
     *
     * @param fragment fragment to replace.
     * @param container frame layout view ID, this is located in the parent container.
     * @param addToBackStack if this is true the fragment will be added to the back stack. else will be replaced.
     * @param transitionAnimation Transition from [TransitionAnimation].
     *
     */
    fun replaceFragment(fragment: Fragment, container: Int, addToBackStack : Boolean = true, transitionAnimation: TransitionAnimation = TransitionAnimation.SLIDE_IN_FROM_RIGHT) {
        try {

            val transaction = activity?.supportFragmentManager?.beginTransaction()
            when(transitionAnimation){
                /**
                 * Appears: RIGHT to LEFT
                 * Dismiss: LEFT to RIGHT
                 */
                TransitionAnimation.SLIDE_IN_FROM_RIGHT-> {
                    transaction?.setCustomAnimations(
                        R.anim.slide_in_from_right, //enter
                        R.anim.slide_out_from_left, //exit
                        R.anim.slide_in_from_right, //pop enter
                        R.anim.slide_out_from_left) //pop(on back pressed) exit
                }

                /**
                 * Appears: LEFT to RIGHT
                 * Dismiss: RIGHT to LEFT
                 */
                TransitionAnimation.SLIDE_IN_FROM_LEFT-> {
                    transaction?.setCustomAnimations(
                        R.anim.slide_in_from_left,   //enter
                        R.anim.slide_out_from_right, //exit
                        R.anim.slide_in_from_left,   //pop enter
                        R.anim.slide_out_from_right) //pop(on back pressed) exit
                }

                /**
                 * Appears: TOP to BOTTOM
                 * Dismiss: BOTTOM to TOP
                 */
                TransitionAnimation.SLIDE_IN_FROM_TOP-> {
                    transaction?.setCustomAnimations(
                        R.anim.slide_in_from_top,       //enter
                        R.anim.slide_out_from_bottom,   //exit

                        R.anim.slide_in_from_top,       //pop enter
                        R.anim.slide_out_from_bottom)   //pop(on back pressed) exit
                }

                /**
                 * Appears: BOTTOM to TOP
                 * Dismiss: TOP to BOTTOM
                 */
                TransitionAnimation.SLIDE_IN_FROM_BOTTOM-> {
                    transaction?.setCustomAnimations(
                        R.anim.slide_in_from_bottom, //enter
                        R.anim.slide_out_from_top,  //exit

                        R.anim.slide_in_from_bottom, //pop enter
                        R.anim.slide_out_from_top) //pop(on back pressed) exit
                }
            }

            if (addToBackStack) {

                transaction?.add(container, fragment)
                transaction?.addToBackStack(fragment.javaClass.simpleName)

            } else {

                transaction?.replace(container, fragment)
            }

            transaction?.commit()

        }catch (e: IllegalArgumentException){ logE(e.message) }
    }

    //region Logs
    fun logE(@Nullable message: String?){
        Log.e(this.javaClass.simpleName, message?:"message is null")
    }

    fun logD(@Nullable message: String?){
        Log.d(this.javaClass.simpleName, message?:"message is null")
    }

    fun logI(@Nullable message: String?){
        Log.i(this.javaClass.simpleName, message?:"message is null")
    }
    //endregion

    fun checkLocationPermissionsGranted() : Boolean{
        if (VersionUtils.isMarshmallow()){
            return if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 10)
                false

            } else {
                true
            }
        }
        return true
    }

    interface OnResult {
        fun onResult(resultCode: Int)
    }

    enum class TransitionAnimation {
        SLIDE_IN_FROM_LEFT,
        SLIDE_IN_FROM_RIGHT,
        SLIDE_IN_FROM_TOP,
        SLIDE_IN_FROM_BOTTOM
    }
}