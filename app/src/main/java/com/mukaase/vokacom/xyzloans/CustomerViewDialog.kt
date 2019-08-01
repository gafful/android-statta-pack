package com.mukaase.vokacom.xyzloans

import android.os.Bundle
import android.view.*
import android.view.Window.*
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_customer_view.view.*

class CustomerViewDialog : DialogFragment() {

    private lateinit var content: Customer

    fun newInstance(content: Customer): CustomerViewDialog {
        val f = CustomerViewDialog()
        val args = Bundle()
        args.putParcelable("content", content)
        f.arguments = args
        return f
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        content = arguments!!.getParcelable("content")

//        // Pick a style based on the num.
//        val style = DialogFragment.STYLE_NO_FRAME
//        val theme = R.style.Base_ThemeOverlay_AppCompat_Dialog
//        setStyle(style, theme)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        dialog?.window?.requestFeature(FEATURE_NO_TITLE)
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)

//        btn_back?.setOnClickListener {
//            mOnClickListener?.onClickCancel()
//            dismiss()
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_customer_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.customer_full_name.text = "Name: ${content.firstName} ${content.lastName}"
        view.customer_marital_status.text = "Marital Status: ${content.marStatus}"
        view.customer_employment_status.text = "Employment Status: ${content.empStatus}"
        view.customer_name_of_employer.text = "Employer: ${content.empName}"
        view.customer_dob.text = "D.O.B: ${content.dob}"
        view.customer_id_type.text = "ID Type: ${content.idCardType}"
        view.customer_address.text = "Address: ${content.address}"
        view.customer_phone.text = "Phone: ${content.phone}"

        view.customer_principal.text = "Principal: ${content.principal}"

        val interest = (content.principal * 0.05 * 1).div(100)
        view.customer_interest.text = "Interest: $interest"
    }
}