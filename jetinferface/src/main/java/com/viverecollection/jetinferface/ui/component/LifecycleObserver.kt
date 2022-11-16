package id.co.vivere.ecat.android.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle.Event.*
import androidx.lifecycle.LifecycleEventObserver

/**
 * Created by Annas Surdyanto on 22/07/22.
 *
 */

@Composable
fun LifecycleObserver(
    onPause: (() -> Unit)? = null,
    onResume: (() -> Unit)? = null,
    onStart: (() -> Unit)? = null,
    onDestroy: (() -> Unit)? = null,
    onCreate: (() -> Unit)? = null,
    onStop: (() -> Unit)? = null,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
            val observer = LifecycleEventObserver { lifecycleOwner, event ->
                if (event == ON_PAUSE) onPause?.invoke()
                else if (event == ON_RESUME) onResume?.invoke()
                else if (event == ON_START) onStart?.invoke()
                else if (event == ON_DESTROY) onDestroy?.invoke()
                else if (event == ON_STOP) onStop?.invoke()
                else if (event == ON_CREATE) onCreate?.invoke()
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    )
}