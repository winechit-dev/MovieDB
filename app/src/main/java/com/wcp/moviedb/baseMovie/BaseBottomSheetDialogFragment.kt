package com.wcp.moviedb.baseMovie

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wcp.moviedb.R
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject

open class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    val compositeDisposable : CompositeDisposable by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    fun showError(errorMessage: String?) {
        if (errorMessage != null) {
            context?.let {
                Toast.makeText(context, errorMessage,Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun showToast(message: String?) {
        try {
            context?.let {
                val toast = Toast.makeText(it, message?: "No message specified", Toast.LENGTH_SHORT)
                val view = toast.view

                view.background.setColorFilter(
                        ContextCompat.getColor(it, R.color.colorAccent), PorterDuff.Mode.SRC_IN)

                val textView = view.findViewById<TextView>(android.R.id.message)
                textView.setTextColor(ContextCompat.getColor(it, R.color.white))
                textView.gravity = Gravity.CENTER

                toast.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}