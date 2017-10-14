package io.armcha.playtabs

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        regular.setOnClickListener {
            startActivity(Intent(this, RegularActivity::class.java))
        }

        withIcon.setOnClickListener {
            startActivity(Intent(this, WithIconActivity::class.java))
        }
    }

}
