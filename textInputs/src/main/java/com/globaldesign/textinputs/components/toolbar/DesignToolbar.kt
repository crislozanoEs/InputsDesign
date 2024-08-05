package com.globaldesign.textinputs.components.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources

class DesignToolbar(context: Context, attrs: AttributeSet?): LinearLayout(context, attrs){

    private var title: String = ""
    private var type: Int = 0
    private var icon: Int = 0

    private var onGoBack: () -> Unit = {}

    private lateinit var goBackView: ImageView
    private lateinit var titleView: TextView
    private lateinit var iconView: ImageView

    init {
        LayoutInflater.from(context).inflate(com.globaldesign.textinputs.R.layout.design_toolbar, this, true)
        context.theme.obtainStyledAttributes(
            attrs, com.globaldesign.textinputs.R.styleable.DesignToolbar, 0, 0).apply {
                try {
                    title = getString(com.globaldesign.textinputs.R.styleable.DesignToolbar_toolbar_title) ?: ""
                    type = getInteger(com.globaldesign.textinputs.R.styleable.DesignToolbar_toolbar_type, 0)
                    icon = getInteger(com.globaldesign.textinputs.R.styleable.DesignToolbar_toolbar_icon, 0)
                } finally {
                    recycle()
                }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        goBackView = findViewById(com.globaldesign.textinputs.R.id.arrow_back_toolbar)
        titleView = findViewById(com.globaldesign.textinputs.R.id.title_toolbar)
        iconView = findViewById(com.globaldesign.textinputs.R.id.icon_toolbar)

        with(getBehavior()) {
            goBackView.visibility = goBackVisibility
            titleView.text = title
            iconView.visibility = iconVisibility
        }
        goBackView.setOnClickListener{
            onGoBack()
        }

        elevation = 2F
        outlineProvider = ViewOutlineProvider.BOUNDS
        iconView.setImageDrawable(AppCompatResources.getDrawable(context, icon))
    }

    private fun getBehavior(): DesignToolbarBehavior {
        return when(type) {
            0 -> DesignToolbarBehavior.MiddleScreen
            1 -> DesignToolbarBehavior.LastScreen
            2 -> DesignToolbarBehavior.ClearScreen
            else -> DesignToolbarBehavior.Default
        }
    }

    fun setOnGoBack(callback: () -> Unit) {
        onGoBack = callback
    }
}