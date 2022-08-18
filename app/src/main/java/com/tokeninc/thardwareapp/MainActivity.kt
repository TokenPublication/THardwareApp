package com.tokeninc.thardwareapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.tokeninc.thardware.SpiSocketConnector

class MainActivity : AppCompatActivity() {

    private lateinit var spiSocketConnector: SpiSocketConnector


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spiSocketConnector = SpiSocketConnector.getInstance()

        findViewById<Button>(R.id.spi_open_button).setOnClickListener {
            val result = spiSocketConnector.spiInit(7, 1000000, 8)
            Log.i("THardware-SpiClient", "Java lib SPI Init return value $result")

        }

        findViewById<Button>(R.id.spi_function_button).setOnClickListener {
            val returnValue = spiSocketConnector.checkCommunication()
            Log.i("THardware-SpiClient", "Java lib Return value $returnValue")
        }

        findViewById<Button>(R.id.spi_close_button).setOnClickListener {
            spiSocketConnector.spiClose()
        }
    }

    override fun onDestroy() {
        spiSocketConnector.spiClose()
        super.onDestroy()
    }
}