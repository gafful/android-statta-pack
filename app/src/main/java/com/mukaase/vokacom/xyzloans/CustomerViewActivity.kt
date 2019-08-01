package com.mukaase.vokacom.xyzloans

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_customer_view.*

class CustomerViewActivity : AppCompatActivity(), Listener {

    private lateinit var viewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_view)
        initObservers()
        initViews()
    }

    private fun initObservers() {

// Get a new or existing ViewModel from the ViewModelProvider.
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
    }

    private fun initViews() {
        val adapter = CustomerListAdapter(this)
        customer_rv.adapter = adapter
        customer_rv.layoutManager = LinearLayoutManager(this)

        viewModel.allCustomers.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setWords(it) }
        })
    }

    override fun onCustomerClick(customer: Customer) {
        var dialogFragment = CustomerViewDialog().newInstance(customer)
//        dialogFragment.arguments = bundle
        val fragmentTransaction = supportFragmentManager.beginTransaction()
//        if (priorInstance != null) {
//            fragmentTransaction.remove(priorInstance!!)
//        }
        fragmentTransaction.addToBackStack(null)
        dialogFragment.show(fragmentTransaction, "CA")
    }

//    override fun onCustomerClick(customer: Customer) {
//            val dialog = Dialog(this)
//            dialog .requestWindowFeature(Window.FEATURE_NO_TITLE)
////            dialog .setCancelable(false)
//            dialog.setContentView(R.layout.dialog_customer_view)
//            val body = dialog.findViewById(R.id.customer_full_name) as TextView
//            body.text = title
//            val yesBtn = dialog .findViewById(R.id.yesBtn) as Button
//            val noBtn = dialog .findViewById(R.id.noBtn) as TextView
//            yesBtn.setOnClickListener {
//                dialog .dismiss()
//            }
//            noBtn.setOnClickListener { dialog .dismiss() }
//            dialog .show()
//    }

}
