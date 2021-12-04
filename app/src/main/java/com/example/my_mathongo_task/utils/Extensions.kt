package com.example.my_mathongo_task.utils

import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

fun View.showSnackBar(message: String, action: (() -> Unit)? = null, actionMsg: String? = null) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).apply {
        setAction(actionMsg) {
            action?.invoke()
        }
        show()
    }
}

fun Fragment.showSnackBar(
    message: String,
    action: (() -> Unit)? = null,
    actionMsg: String? = null
) = requireView().showSnackBar(message, action, actionMsg)

fun Fragment.safeFragmentNavigation(
    navController: NavController,
    currentFragmentId: Int,
    actionId: Int,
) {
    if (navController.currentDestination?.id == currentFragmentId) {
        navController.navigate(actionId)
    }
}