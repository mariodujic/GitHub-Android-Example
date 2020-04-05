package com.groundzero.github.feature.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.groundzero.github.base.BaseActivity
import com.groundzero.github.di.helper.injectViewModel
import com.groundzero.github.feature.authentication.common.AuthenticationActivity
import com.groundzero.github.feature.content.common.MainActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class SplashActivity : BaseActivity() {

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        CoroutineScope(Dispatchers.IO).launch {
            viewModel = injectViewModel(viewModelFactory)
            delay(1000)
            withContext(Main) {
                viewModel.accessTokenLive.observe(this@SplashActivity, Observer {
                    nextActivity(
                        if (it != null) MainActivity::class.java else AuthenticationActivity::class.java
                    )
                })
            }
        }
    }
}
