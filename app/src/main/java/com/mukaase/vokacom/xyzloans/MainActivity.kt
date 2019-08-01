package com.mukaase.vokacom.xyzloans

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onLogin(v: View) {
        // Check if empty
        val username = txtEmail.text.toString()
        val password = txtPwd.text.toString()

        if (username.isEmpty()) {
            txtEmail.error = "Please enter your username"
            return
        } else if ("admin" != username) {
            txtEmail.error = "Wrong username"
            return
        }
//        txtEmail.error = ""

        if (password.isEmpty()) {
            txtPwd.error = "Please enter your password"
            return
        } else if ("admin" != password) {
            txtPwd.error = "Wrong password"
            return
        }
//        txtPwd.error = ""

        // Verify from db
        // If yes, open dashboard
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()

        // Else show error

    }
}
