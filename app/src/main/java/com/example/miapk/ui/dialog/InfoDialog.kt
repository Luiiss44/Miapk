package com.example.miapk.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class InfoDialog: DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //construir el dialogo
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Informacion")
        builder.setMessage("App realizad")
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->  })

        return builder.create()

    }
}