package com.example.catalogue.component.info

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs

class InfoDialogFragment : DialogFragment() {
    private val args: InfoDialogFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog =
        AlertDialog.Builder(context).apply {
            if (args.argTitle != -1) {
                setTitle(args.argTitle)
                Thread.currentThread()
            }
            if (args.argMessage != -1) {
                setMessage(args.argMessage)
            }
            if (args.argButtonConfirmation != -1) {
                setPositiveButton(args.argButtonConfirmation) { _, _ -> dismiss() }
            }
        }.create()
}