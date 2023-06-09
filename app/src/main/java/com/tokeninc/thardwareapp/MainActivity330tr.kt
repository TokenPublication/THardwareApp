package com.tokeninc.thardwareapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.tokeninc.thardware.tr330.SpiController

class MainActivity330tr : AppCompatActivity() {

    private lateinit var spiController: SpiController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity330tr)

        spiController = SpiController.getInstance()

        findViewById<Button>(R.id.spi_open_button).setOnClickListener {
            val result = spiController.spiInit(7, 1000000, 8)
            Log.i("THardware330tr", "Java lib SPI Init return value $result")

        }

        findViewById<Button>(R.id.spi_function_button).setOnClickListener {
            checkComm()
        }

        findViewById<Button>(R.id.spi_close_button).setOnClickListener {
            spiController.spiClose()
        }

        findViewById<Button>(R.id.get_fiscal_busy_button).setOnClickListener {
            for(i in 1..20)
            {
                Log.i("THardware330tr","Fiscal busy res: ${spiController.fiscalBusy}")
                Thread.sleep(50)
            }
        }
    }

    private fun checkComm()
    {
        val tx = byteArrayOf(0xE1.toByte(), 0x00.toByte(), 0x05.toByte(), 0xA0.toByte(), 0x5F.toByte())
        spiController.spiChipSelect(true)
        spiController.spiSend(tx)
        val rx = spiController.spiReceive(7)
        Log.i("THardware330tr","check com res: ${"%02x".format(rx[5])}")
        spiController.spiChipSelect(false)
    }
    override fun onDestroy() {
        spiController.spiClose()
        super.onDestroy()
    }
}