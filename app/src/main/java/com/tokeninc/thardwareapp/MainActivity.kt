package com.tokeninc.thardwareapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.tokeninc.thardware.spi.SpiController
import com.tokeninc.thardware.ej.EjController

class MainActivity : AppCompatActivity() {

    private lateinit var spiController: SpiController
    private lateinit var ejController: EjController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spiController = SpiController.getInstance()
        ejController = EjController.getInstance()

        findViewById<Button>(R.id.spi_open_button).setOnClickListener {
            val result = spiController.spiInit(7, 1000000, 8)
            Log.i("THardware-SpiClient", "SPI Init return value $result")

        }

        findViewById<Button>(R.id.spi_function_button).setOnClickListener {
            //
        }

        findViewById<Button>(R.id.spi_close_button).setOnClickListener {
            spiController.spiClose()
        }

        findViewById<Button>(R.id.ej_function_button).setOnClickListener {
            val status = ejController.ejStatus
            Log.i("THardware-SpiClient", "EJ Status $status")
        }
    }

    override fun onDestroy() {
        spiController.spiClose()
        super.onDestroy()
    }
}