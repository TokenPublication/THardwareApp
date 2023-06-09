package com.tokeninc.thardwareapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.tokeninc.thardware.ej.EjController
import com.tokeninc.thardware.spi.SpiController
import com.tokeninc.thardwarenativewrapper.NativeLib

class MainActivity : AppCompatActivity() {

    private lateinit var spiController: SpiController
    private lateinit var ejController: EjController
    private val nativeLib = NativeLib()

    private val native = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spiController = SpiController.getInstance()
        ejController = EjController.getInstance()

        findViewById<Button>(R.id.spi_open_button).setOnClickListener {
            if (native) {
                nativeLib.spiInit()
            } else {
                val result = spiController.spiInit(7, 1000000, 8)
                Log.i("THardware-SpiClient", "Java lib SPI Init return value $result")

        }

        findViewById<Button>(R.id.spi_function_button).setOnClickListener {
            if (native) {
                val returnValue: Int = nativeLib.checkCommunication()
                Log.i("THardware-SpiClient", "Native lib Return value $returnValue")
                Toast.makeText(this@MainActivity, returnValue.toString(), Toast.LENGTH_SHORT).show()
            } else {
                checkComm()
            }
        }

        findViewById<Button>(R.id.spi_close_button).setOnClickListener {
            if (native) {
                nativeLib.spiClose()
            } else {
                spiController.spiClose()
            }
        }

        findViewById<Button>(R.id.get_fiscal_busy_button).setOnClickListener {
            for(i in 1..20)
            {
                Log.i("THardware-SpiClient","Fiscal busy res: ${spiController.fiscalBusy}")
                Thread.sleep(50)
            }
        }
    }

    fun checkComm()
    {
        val tx = byteArrayOf(0xE1.toByte(), 0x00.toByte(), 0x05.toByte(), 0xA0.toByte(), 0x5F.toByte())
        spiController.spiChipSelect(true)
        spiController.spiSend(tx)
        val rx = spiController.spiReceive(7)
        Log.i("THardware-SpiClient","check com res: ${"%02x".format(rx[5])}")
        spiController.spiChipSelect(false)
    }
    override fun onDestroy() {
        if (native) {
            nativeLib.spiClose()
        } else {
            spiController.spiClose()
        }
        super.onDestroy()
    }
}
