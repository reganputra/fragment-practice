package com.example.myflexiblefragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment

// Fragment untuk Dialog
class OptionDialogFragment : DialogFragment() { //obyek fragment sekarang merupakan obyek dialog yang akan tampil mengambang di layar.

    //  mengirimkan hasil pilihan pengguna dari dialog kembali ke fragment induk
    interface OnOptionDialogListener {
        fun onOptionChosen(text: String?)
    }

    private lateinit var btnChoose: Button
    private lateinit var btnClose: Button
    private lateinit var rgOptions: RadioGroup
    private lateinit var rbSaf: RadioButton
    private lateinit var rbMou: RadioButton
    private lateinit var rbLvg: RadioButton
    private lateinit var rbMoyes: RadioButton
    private var optionDialogListener: OnOptionDialogListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnChoose = view.findViewById(R.id.btn_choose)
        btnClose = view.findViewById(R.id.btn_close)
        rgOptions = view.findViewById(R.id.rg_options)
        rbSaf = view.findViewById(R.id.rb_saf)
        rbLvg = view.findViewById(R.id.rb_lvg)
        rbMou = view.findViewById(R.id.rb_mou)
        rbMoyes = view.findViewById(R.id.rb_moyes)
        btnChoose.setOnClickListener {
            val checkedRadioButtonId = rgOptions.checkedRadioButtonId
            if (checkedRadioButtonId != -1) {
                var coach: String? = when (checkedRadioButtonId) {
                    R.id.rb_saf -> rbSaf.text.toString().trim()
                    R.id.rb_mou -> rbMou.text.toString().trim()
                    R.id.rb_lvg -> rbLvg.text.toString().trim()
                    R.id.rb_moyes -> rbMoyes.text.toString().trim()
                    else -> null
                }

                optionDialogListener?.onOptionChosen(coach)//mengirimkan hasil pilihan pengguna dari dialog kembali ke fragment induk
                dialog?.dismiss()
            }
        }
        btnClose.setOnClickListener {
            dialog?.cancel()
        }
    }

    // untuk mengelola optionDialogListener ketika fragment dipanggil dan dimatikan
    override fun onAttach(context: Context) {
        super.onAttach(context)

        /*
        Saat attach maka set optionDialogListener dengan listener dari detailCategoryFragment
        */
        val fragment = parentFragment

        if (fragment is DetailCategoryFragment) {
            this.optionDialogListener = fragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        /*
        Saat detach maka set null pada optionDialogListener
        */
        this.optionDialogListener = null
    }




}

