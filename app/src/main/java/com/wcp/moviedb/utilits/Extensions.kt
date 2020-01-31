/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.wcp.moviedb.utilits

import android.content.Context
import android.graphics.PorterDuff
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.wcp.moviedb.R

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
  return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
fun toast(context : Context ,message: String) {
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

fun Context.showToast(toastMessag : String = "A",toastDuration : Int = Toast.LENGTH_LONG) {
  Toast.makeText(this, toastMessag, toastDuration).show()
}
