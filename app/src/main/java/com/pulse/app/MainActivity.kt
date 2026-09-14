package com.pulse.app

import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tv = TextView(this).apply {
            text = "Pulse\nготово к разработке"
            textSize = 22f
            gravity = Gravity.CENTER
        }
        setContentView(tv)
    }
}
