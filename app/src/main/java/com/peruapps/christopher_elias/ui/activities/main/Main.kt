package com.peruapps.christopher_elias.ui.activities.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.peruapps.christopher_elias.BR
import com.peruapps.christopher_elias.R
import com.peruapps.christopher_elias.databinding.ActivityMainBinding
import com.peruapps.christopher_elias.services.LocationService
import com.peruapps.christopher_elias.ui.base.BaseActivity
import com.peruapps.christopher_elias.ui.fragments.home.Home
import com.peruapps.christopher_elias.ui.fragments.login.LogIn

class Main : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    private lateinit var viewModel: MainViewModel

    //region Base
    override fun getLayoutId() = R.layout.activity_main

    override fun getBindingVariable() = BR.mainViewModel

    override fun getViewModel(): MainViewModel {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        return viewModel
    }
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        if (intent.getBooleanExtra("background_service", false)){
            val service = Intent(this, LocationService::class.java)
            stopService(service)
        }
    }

    override fun showHomeView() {
        setFragment(Home())
    }

    override fun showLoginView() {
        setFragment(LogIn())
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFrameLayout, fragment)
            .commit()
    }
}
