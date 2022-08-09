package com.tokeninc.thardwareapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.tokeninc.thardware.SpiSocketConnector
import com.tokeninc.thardwarenativewrapper.NativeLib

class MainActivity : AppCompatActivity() {

    private lateinit var spiSocketConnector: SpiSocketConnector
    private val nativeLib = NativeLib()

    private val native = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spiSocketConnector = SpiSocketConnector.getInstance()

        findViewById<Button>(R.id.spi_open_button).setOnClickListener {
            if (native) {
                nativeLib.spiInit()
            } else {
                val result = spiSocketConnector.spiInit(7, 1000000, 8)
                Log.i("THardware-SpiClient", "Java lib SPI Init return value $result")

            }
        }

        findViewById<Button>(R.id.spi_function_button).setOnClickListener {
            if (native) {
                val returnValue: Int = nativeLib.checkCommunication()
                Log.i("THardware-SpiClient", "Native lib Return value $returnValue")
                Toast.makeText(this@MainActivity, returnValue.toString(), Toast.LENGTH_SHORT).show()
            } else {
                val returnValue = spiSocketConnector.checkCommunication()
                Log.i("THardware-SpiClient", "Java lib Return value $returnValue")
            }
        }

        findViewById<Button>(R.id.spi_close_button).setOnClickListener {
            if (native) {
                nativeLib.spiClose()
            } else {
                spiSocketConnector.spiClose()
            }
        }
    }

    override fun onDestroy() {
        if (native) {
            nativeLib.spiClose()
        } else {
            spiSocketConnector.spiClose()
        }
        super.onDestroy()
    }
}