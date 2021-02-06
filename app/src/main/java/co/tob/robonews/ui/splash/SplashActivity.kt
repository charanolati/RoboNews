package co.tob.robonews.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import co.tob.robonews.R
import co.tob.robonews.ui.base.BaseActivity
import co.tob.robonews.ui.main.MainActivity

class SplashActivity : BaseActivity() {

    override fun provideLayout(): Int = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed(
            {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }, 3000
        )
    }
}
