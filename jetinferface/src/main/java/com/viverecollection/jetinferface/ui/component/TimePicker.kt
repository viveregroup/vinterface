package com.viverecollection.jetinferface.ui.component

import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.viverecollection.jetinferface.R
import com.viverecollection.jetinferface.util.toTime
import java.util.*

/**
 * Created by Annas Surdyanto on 09/02/22.
 *
 */
@Composable
fun TimePickerView(
    modifier: Modifier = Modifier,
    label: String,
    state: MutableState<String>,
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
                val time = state.value.toTime()
                val myCalendar = Calendar.getInstance()
                myCalendar.time = Date(time)
                val hour: Int = myCalendar[Calendar.HOUR_OF_DAY]
                val minute: Int = myCalendar[Calendar.MINUTE]
                val timePicker = TimePickerDialog(
                    context,
                    theme,
                    { _, selectedHour, selectedMinute ->
                        state.value = "$selectedHour:$selectedMinute"
                    },
                    hour,
                    minute,
                    true
                )
                timePicker.show()
                timePicker.setOnCancelListener { dialog: DialogInterface? -> dialog?.dismiss() }
            })
    }
}
