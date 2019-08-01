package com.mukaase.vokacom.xyzloans

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)
    }

    fun registerCustomer(v: View){
        // Check if empty
        startActivity(Intent(this, CustomerRegActivity::class.java))
    }

    fun viewCustomer(v: View){
        // Check if empty
        startActivity(Intent(this, CustomerViewActivity::class.java))
    }
}
