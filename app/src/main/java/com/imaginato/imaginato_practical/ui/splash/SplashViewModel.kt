package com.imaginato.imaginato_practical.ui.splash

import androidx.lifecycle.viewModelScope
import com.imaginato.imaginato_practical.domain.usecase.SplashUseCase
import com.imaginato.imaginato_practical.ui.base.BaseViewModel
import com.imaginato.imaginato_practical.ui.base.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val splashUseCase: SplashUseCase
) : BaseViewModel() {

    internal val sessionStateEvent = SingleLiveEvent<Boolean>()

    override fun loadPage(multipleTimes: Boolean): Boolean {
        navigateNextScreen()
        return super.loadPage(multipleTimes)
    }

    private fun navigateNextScreen() {
        val params = SplashUseCase.Params(TimeUnit.SECONDS.toMillis(2))
        splashUseCase.invoke(scope = viewModelScope, params = params) {
            it.result(sessionStateEvent::setValue) { throwable ->
                Timber.e(throwable)
            }
        }
    }
}
