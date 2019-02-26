package io.github.deweyreed.scrollhmspicker.sample

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.github.deweyreed.scrollhmspicker.ScrollHmsPickerBuilder
import io.github.deweyreed.scrollhmspicker.ScrollHmsPickerDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ScrollHmsPickerDialog.HmsPickHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDialog.setOnClickListener {
            ScrollHmsPickerBuilder(supportFragmentManager, this).show()
        }
        btnCustomDialog.setOnClickListener {
            ScrollHmsPickerBuilder(supportFragmentManager, this)
                .setReference(255)
                .setTime(1, 23, 45)
                .setAutoStep(true)
                .setColorNormal(android.R.color.holo_blue_light)
                .setColorSelected(android.R.color.black)
                .setColorBackground(android.R.color.holo_orange_light)
                .setDismissListener(DialogInterface.OnDismissListener {
                    Toast.makeText(this, "Dismiss", Toast.LENGTH_SHORT).show()
                })
                .show()
        }
        var isShown = false
        btnXml.setOnClickListener {
            val visibility = if (isShown) View.GONE else View.VISIBLE
            isShown = !isShown
            arrayOf(scrollHmsPicker, btnGetTime).forEach {
                it.visibility = visibility
            }
        }
        btnGetTime.setOnClickListener {
            onHmsPick(
                -1,
                scrollHmsPicker.hours, scrollHmsPicker.minutes, scrollHmsPicker.seconds
            )
        }
    }

    override fun onHmsPick(reference: Int, hours: Int, minutes: Int, seconds: Int) {
        Toast.makeText(
            this,
            "reference: $reference, hours: $hours, minutes: $minutes, seconds: $seconds",
            Toast.LENGTH_SHORT
        ).show()
    }
}
