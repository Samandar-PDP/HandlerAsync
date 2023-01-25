package uz.digital.handleraysnctask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.SeekBar
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var pr = 0
    private var isDoubleClicked = false
    private lateinit var seekBar: SeekBar
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar = findViewById(R.id.seekBar)
        Thread(myThread).start()
    }

    private val myThread: Runnable = object : Runnable {
        override fun run() {
            while (pr < 100) {
                try {
                    myHandler.sendMessage(myHandler.obtainMessage())
                    Thread.sleep(1000)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
        }

        var myHandler: Handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                pr++
                seekBar.progress = pr
            }
        }
    }

    override fun onBackPressed() {
        if (isDoubleClicked) {
            super.onBackPressed()
            return
        }
        handler = Handler(Looper.getMainLooper())
        this.isDoubleClicked = true
        Toast.makeText(this, "Please press again to exit", Toast.LENGTH_SHORT).show()
        handler.postDelayed({
            isDoubleClicked = false
        }, 2000)
    }
}