package com.dsme_global_links.periodictask

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var interval: Long = 1000L
    private var changeColor = true
    private lateinit var textView : TextView
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        // initialize an instance of handler
        handler = Handler(Looper.getMainLooper())


        textView.setOnClickListener {
            textView.text = "UI interaction"
        }

    }

    //.... private method for task

    private fun doTask(handler: Handler) {

        // ... do your work here
        textView.text = counter.toString()
        counter++

        if (changeColor)
            window.decorView.setBackgroundColor(Color.BLACK)
        else
            window.decorView.setBackgroundColor(Color.WHITE)

        changeColor = !changeColor

        handler.postDelayed(runnable, interval)

    }


    //.... lifecycle callbacks

    override fun onResume() {
        super.onResume()

        runnable = Runnable { // do some task on delay
            // do the task after delay specified time
            doTask(handler)
        }

        // schedule the task to do after an interval
        handler.postDelayed(runnable, interval)
    }

    override fun onBackPressed() {
        handler.removeCallbacks(runnable)
        super.onBackPressed()
    }

    override fun onStop() {
        handler.removeCallbacks(runnable)
        super.onStop()
    }

    override fun onDestroy() {
        // edit according to need
        super.onDestroy()
    }

    override fun onPause() {
        // edit according to need
        super.onPause()
    }
}