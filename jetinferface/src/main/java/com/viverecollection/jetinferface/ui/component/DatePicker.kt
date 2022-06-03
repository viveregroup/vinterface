package com.viverecollection.jetinferface.ui.component

import android.app.DatePickerDialog
import android.content.DialogInterface
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.viverecollection.jetinferface.R
import com.viverecollection.jetinferface.util.toDate
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Annas Surdyanto on 09/02/22.
 *
 */
@Composable
fun DatePickerView(
    modifier: Modifier = Modifier,
    label: String,
    state: MutableState<String>,
    dateFormat: String = stringResource(R.string.default_date_format),
    isMandatory: Boolean = false,
    darkTheme: Int = R.style.AlertDialogDark,
    lightTheme: Int = R.style.AlertDialogLight,
    invalidState: MutableState<Boolean> = mutableStateOf(false),
    isSupportDarkTheme: Boolean = true,
    useBorderStroke: Boolean = true
) {
    val context = LocalContext.current
    val theme = if (isSupportDarkTheme && isSystemInDarkTheme()) darkTheme else lightTheme
    Box(modifier = modifier) {
        if (useBorderStroke) BorderedClickableTextField(
            modifier = modifier,
            label = label,
            state = state,
            isInvalid = if (isMandatory) state.value.isEmpty() else false,
            invalidState = invalidState
        )
        else ClickableTextField(
            modifier = modifier,
            label = label,
            state = state,
            isInvalid = if (isMandatory) state.value.isEmpty() else false,
            invalidState = invalidState
        )
        Box(modifier = Modifier
            .matchParentSize()
            .clickable {
                val time = state.value.toDate()
                val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
                val myCalendar = Calendar.getInstance()
                myCalendar.time = Date(time)
                val datePicker = DatePickerDialog(
                    context,
                    theme,
                    { _, year, monthOfYear, dayOfMonth ->
                        myCalendar.set(year, monthOfYear, dayOfMonth)
                        val date = formatter.format(myCalendar.time)
                        state.value = date
                    },
                    myCalendar[Calendar.YEAR],
                    myCalendar[Calendar.MONTH],
                    myCalendar[Calendar.DAY_OF_MONTH]
                )
                datePicker.show()
                datePicker.setOnCancelListener { dialog: DialogInterface? -> dialog?.dismiss() }
            })
    }
}