package cheema.hardeep.sahibdeep.studentify.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import cheema.hardeep.sahibdeep.studentify.R
import cheema.hardeep.sahibdeep.studentify.database.SharedPreferencesProvider


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar!!.hide()
        Handler().postDelayed({
            if (SharedPreferencesProvider.isFirstLaunch(this@SplashActivity)) {
                startActivity(Intent(this@SplashActivity, UserInformationActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            }
            finish()
        }, TRANSITION_TIME.toLong())
    }

    companion object {
        const val TRANSITION_TIME = 2000
    }
}



 class SplashActivity2 : AppCompatActivity() {
    val TRANSITION_TIME = 2000

    protected fun OnCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        Handler().postDelayed({
            if (SharedPreferencesProvider.isFirstLaunch(this)) {
                startActivity( Intent(this, UserInformationActivity::class.java))
            }
            else {
                startActivity(Intent (this, HomeActivity::class.java))
            }
            finish()
        }, TRANSITION_TIME.toLong()) // why long?
    }

}