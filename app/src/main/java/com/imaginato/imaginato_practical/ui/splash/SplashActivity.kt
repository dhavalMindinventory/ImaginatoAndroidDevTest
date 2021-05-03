package com.imaginato.imaginato_practical.ui.splash

import android.content.Intent
import com.imaginato.imaginato_practical.ui.MainActivity
import com.imaginato.imaginato_practical.R
import com.imaginato.imaginato_practical.extension.initViewModel
import com.imaginato.imaginato_practical.extension.safeObserve
import com.imaginato.imaginato_practical.ui.base.BaseViewModelActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseViewModelActivity<SplashViewModel>(R.layout.activity_splash) {

    override fun buildViewModel() = initViewModel<SplashViewModel>()

    override fun initLiveDataObservers() {
        super.initLiveDataObservers()
        with(viewModel) {
            sessionStateEvent.safeObserve(this@SplashActivity, ::handleSessionState)
        }
    }

    private fun handleSessionState(isLoggedId: Boolean) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
