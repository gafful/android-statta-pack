package com.mukaase.vokacom.xyzloans

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_customer_reg.*
import java.util.*


class CustomerRegActivity : AppCompatActivity() {
    var maritalStatus = arrayOf("Married", "Not married", "Divorced")
    var employmentStatus = arrayOf("Employed", "Unemployed")
    var idCardTypes = arrayOf("Passport", "NHIA", "Drivers' License")
    private var dob = ""
    private lateinit var viewModel: DashboardViewModel
    private var marStatus = ""
    private var empStatus = ""
    private var idCardType = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_reg)
        initViews()
        init()
    }

    private fun initViews() {
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, maritalStatus)
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        cr_marital_status.adapter = adapter1
        cr_marital_status.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Display the selected item text on text view
                println("Spinner selected : ${parent.getItemAtPosition(position)}")
                marStatus = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                println("onNothingSelected")
            }
        }

        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, employmentStatus)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        cr_employment_status.adapter = adapter2
        cr_employment_status.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                empStatus = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        val adapter3 = ArrayAdapter(this, android.R.layout.simple_spinner_item, idCardTypes)
        adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        cr_id_card_type.adapter = adapter3
        cr_id_card_type.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                idCardType = parent.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>){}
        }
    }

    private fun init() {
        // Get a new or existing ViewModel from the ViewModelProvider.
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
    }

    fun onRegister(v: View) {
        val firstName = cr_first_name.text.toString()
        val lastName = cr_last_name.text.toString()

        val empName = cr_employer_name.text.toString()
        val dob = this.dob

        val address = cr_address.text.toString()
        val phone = cr_phone.text.toString()
        val principal = cr_principal.text.toString()


        if (firstName.isEmpty()) {
            cr_first_name.error = "Enter your first name"
            return
        }

        if (lastName.isEmpty()) {
            cr_last_name.error = "Enter your last name"
            return
        }

        if (marStatus.isEmpty()) {
//            cr_marital_status.error = "Choose your marital status"
//            Toast.makeText(this, "Choose your marital status", Toast.LENGTH_LONG).show()

            println("default selected is ${cr_marital_status.selectedItem.toString()}")
            marStatus = cr_marital_status.selectedItem.toString()
        }

        if (empStatus.isEmpty()) {
            Toast.makeText(this, "Choose your employment status", Toast.LENGTH_LONG).show()
//            return
        }

        if (empName.isEmpty()) {
            cr_employer_name.error = "Enter your employer's name"
            return
        }

        if (dob.isEmpty()) {
//            cr_employer_name.error = "Enter your employer's name"
            Toast.makeText(this, "Choose your date of birth", Toast.LENGTH_LONG).show()
            return
        }

        if (idCardType.isEmpty()) {
            Toast.makeText(this, "Choose your type of ID card", Toast.LENGTH_LONG).show()
//            return
        }

        if (address.isEmpty()) {
            cr_address.error = "Enter your address"
            return
        }

        if (phone.isEmpty()) {
            cr_phone.error = "Enter your phone number"
            return
        }

        if (principal.isEmpty()) {
            cr_principal.error = "Enter your principal"
            return
        }

        // Save to db
        val customer = Customer(firstName, lastName, marStatus, empStatus, empName, dob, idCardType, address, phone, principal.toFloat())
        viewModel.insert(customer)

        // Clear all entries
        cr_first_name.setText("")
        cr_last_name.setText("")
        cr_marital_status
        cr_employment_status
        cr_employer_name.setText("")
        this.dob
        cr_id_card_type
        cr_address.setText("")
        cr_phone.setText("")

        Toast.makeText(this, "Customer registered successfully :D", Toast.LENGTH_LONG).show()
//        viewModel.allCustomers()
    }

    fun onDob(v: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this@CustomerRegActivity,
            DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                println("year$year-- month --$month--day --$day")
                dob = day.toString() + "/" + (month + 1) + "/" + year
                cr_dob.text = dob
            }, year, month, dayOfMonth)
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }
}
