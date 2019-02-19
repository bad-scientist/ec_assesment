package com.kevinolivera.ec.fragments

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.kevinolivera.ec.R
import android.content.Intent
import com.kevinolivera.ec.activities.ProductsActivity


class SuccessDialogFragment: DialogFragment()  {

    @BindView(R.id.btnContinue)
    lateinit var btnContinue: Button

    @BindView(R.id.tvAmount)
    lateinit var tvAmount: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_payment_success, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ButterKnife.bind(this, view)
        tvAmount.text = resources.getString(R.string.dialog_payment_success_message1, arguments!!.getString(PAID_TOTAL, "0.00"))

        btnContinue.setOnClickListener{
            val intent = Intent(activity, ProductsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    companion object {

        const val PAID_TOTAL = "PAID_TOTAL"

        fun newInstance (total: String) : SuccessDialogFragment {
            var fragmentDialog = SuccessDialogFragment()
            val args = Bundle()
            args.putString(PAID_TOTAL, total)
            fragmentDialog.arguments = args
            return fragmentDialog
        }
    }

}