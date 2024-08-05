package com.globaldesign.textinputs.components.toolbar

import android.view.View
import androidx.transition.Visibility

sealed class DesignToolbarBehavior (val goBackVisibility: Int = View.GONE, val iconVisibility: Int = View.GONE) {

    data object Default : DesignToolbarBehavior()

    data object MiddleScreen : DesignToolbarBehavior(goBackVisibility = View.VISIBLE, iconVisibility = View.VISIBLE)

    data object LastScreen: DesignToolbarBehavior(goBackVisibility = View.GONE, iconVisibility = View.VISIBLE)

    data object ClearScreen: DesignToolbarBehavior(goBackVisibility = View.GONE, iconVisibility = View.GONE)
}