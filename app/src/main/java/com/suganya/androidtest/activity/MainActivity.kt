package com.suganya.androidtest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.suganya.androidtest.R
import com.suganya.androidtest.utils.UtilValidate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {

            Handler().postDelayed({
                if (UtilValidate.isConnectivity(this@MainActivity)) {
                    startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                } else {
                    Toast.makeText(applicationContext, "" + "You're Offline.Check your Connection", Toast.LENGTH_SHORT).show()
                }
            }, 2000)                                                          // 5 sec delay before going to Home Activity.
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "" + e, Toast.LENGTH_SHORT).show()
        }
    }
}