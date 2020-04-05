package com.groundzero.github.feature.authentication.common

import android.os.Bundle
import com.groundzero.github.R
import com.groundzero.github.base.BaseActivity

class AuthenticationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
    }
}
