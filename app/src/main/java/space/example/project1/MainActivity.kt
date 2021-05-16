package space.example.project1

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import java.util.*


class MainViewModel(mainActivity: MainActivity) : ViewModel() {
    class MainActivity : Activity() {
        var imSemafor: ImageView? = null
        var counter: Int = 0
        var timer: Timer? = null
        var is_run = false
        var imageArray: IntArray = intArrayOf(R.drawable.semafor_red, R.drawable.semafor_yellow, R.drawable.semafor_green)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            imSemafor = findViewById(R.id.imSemafor)
            val model: MainViewModel = MyViewModel(this).get(MainViewModel::class.java)
            val data: LiveData<String>?
        }

        fun onClickStart(view: View) {
            imSemafor?.setImageResource(R.drawable.semafor_grey)
            view as ImageButton
            view.setImageResource(R.drawable.button_stop)
            if (!is_run) {
                timer = Timer()
                startStop()
                view.setImageResource(R.drawable.button_stop)
                is_run = true
            } else {
                imSemafor?.setImageResource(R.drawable.semafor_grey)
                view.setImageResource(R.drawable.button_start)
                timer?.cancel()
                view.setImageResource(R.drawable.button_start)
                is_run = false
                counter = 0
            }
        }

        fun startStop() {
            timer = Timer()
            timer?.schedule(object : TimerTask() {
                override fun run() {
                    runOnUiThread {
                        imSemafor?.setImageResource(imageArray[counter])
                        counter++
                        if (counter == 3) counter = 0
                    }
                }
            }, 0, 1000)
        }
    }
}

