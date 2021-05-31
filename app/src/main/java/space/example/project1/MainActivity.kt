package space.example.project1

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import space.example.project1.R.drawable.button_stop
import java.util.*
import java.util.Timer as Timer1

class MainActivity : Activity() {
    var imSemafor: ImageView? = null
    var counter: Int = 0
    var timer: Timer1? = null
    var is_run = false
    var imageArray: IntArray = intArrayOf(R.drawable.semafor_red, R.drawable.semafor_yellow, R.drawable.semafor_green)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imSemafor = findViewById(R.id.imSemafor)
    }

    fun onClickStart(view: View) {
        view as ImageButton
        if (!is_run) {

            startStop()
            view.setImageResource(button_stop)
            is_run = true
        } else {
            imSemafor?.setImageResource(R.drawable.semafor_grey)
            view.setImageResource(R.drawable.button_start)
            timer?.cancel()
            is_run = false
            counter = 0
        }
    }

    fun startStop() {
        timer = Timer1()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    imSemafor?.setImageResource(imageArray[counter])
                    counter++
                    if (counter == 3) counter = 0
                }
            }

        }, 0, 500)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("savedInt", counter)
        outState.putBoolean("savedBoolean", is_run)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        var userNumber = savedInstanceState.getInt("savedInt", 0)
        counter = userNumber
        var userTimer = savedInstanceState.getBoolean("savedBoolean", is_run)
        is_run = userTimer
        startStop()
        val view = findViewById<ImageButton>(R.id.mybutton)
        view.setImageResource(R.drawable.button_stop)
    }
}