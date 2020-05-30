package cheema.hardeep.sahibdeep.studentify.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import cheema.hardeep.sahibdeep.studentify.R
import cheema.hardeep.sahibdeep.studentify.fragments.ClassesFragment
import cheema.hardeep.sahibdeep.studentify.fragments.ScheduleFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

fun createHomeActivityIntent(context: Context): Intent =
        Intent(context, HomeActivity::class.java)
                .also { Log.d("Sahib", "Created Home Activity Intent") } // expression function

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        bottomNav?.setOnNavigationItemSelectedListener(this)
        loadFragment(ClassesFragment())
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean =
        when(menuItem.itemId) {
            R.id.menu_classes -> true.also{ loadFragment(ClassesFragment()) }
            R.id.menu_schedule -> true.also{ loadFragment(ScheduleFragment()) }
            R.id.menu_user_information -> true.also{ startActivity(UserInformationActivity.createIntent(this)) }
            else -> false
        }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }
}
