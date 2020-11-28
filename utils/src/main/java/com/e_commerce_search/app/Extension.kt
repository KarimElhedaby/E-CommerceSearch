package com.e_commerce_search.app.utils


import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.TextAppearanceSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Matcher
import java.util.regex.Pattern


fun View.showSnackbar(
    @SuppressLint("SupportAnnotationUsage") @StringRes snackbarText: String, timeLength: Int
) {
    Snackbar.make(this, snackbarText, timeLength).show()
}

fun androidx.constraintlayout.widget.Group.setAllOnClickListener(listener: View.OnClickListener?) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnClickListener(listener)
    }
}

fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}


fun ImageView?.loadImage(src: Any?, corner: Int? = null) {
    this?.context?.let {
        Glide.with(it)
            .load(src)
//            .apply {
//                corner?.let {
//                    this.transform(CenterCrop(), RoundedCorners(corner))
//                } ?: this.transform(CenterCrop(), CircleCrop())
//            }
            .into(this)
    }
}

fun ImageView?.loadImage(bitmap: Bitmap?) {
    this?.context?.let {
        Glide.with(it)
            .load(bitmap)
            .into(this)
    }

}


fun ImageView?.loadImage(uri: Drawable?) {
    this?.context?.let {
        Glide.with(it)
            .load(uri)
            .into(this)
    }

}

fun ImageView?.loadImage(src: Int?) {
    this?.context?.let {
        Glide.with(it)
            .load(src)
            .into(this)
    }
}

fun EditText.focus() {
    requestFocus()
    setSelection(length())
}


fun EditText?.ItsText(): String {
    return this?.text.toString()
}

fun setSearchTextHighlightSimpleSpannable(
    textView: TextView,
    searchText: String?
) {

    // highlight search text
    if (null != searchText && !searchText.isEmpty()) {
        val wordSpan = SpannableStringBuilder(textView.text.toString())
        val p: Pattern = Pattern.compile(searchText, Pattern.CASE_INSENSITIVE)
        val m: Matcher = p.matcher(textView.text.toString())
        while (m.find()) {
            val wordStart: Int = m.start()
            val wordEnd: Int = m.end()

            // Now highlight based on the word boundaries
            val redColor =
                ColorStateList(arrayOf(intArrayOf()), intArrayOf(-0x5ef6ff))
            val highlightSpan =
                TextAppearanceSpan(null, Typeface.BOLD, -1, redColor, null)
            wordSpan.setSpan(highlightSpan, wordStart, wordEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            wordSpan.setSpan(
                BackgroundColorSpan(-0x300b8),
                wordStart,
                wordEnd,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            wordSpan.setSpan(
                RelativeSizeSpan(1.25f),
                wordStart,
                wordEnd,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        textView.setText(wordSpan, TextView.BufferType.SPANNABLE)
    } else {
        textView.text = textView.text.toString()
    }
}


