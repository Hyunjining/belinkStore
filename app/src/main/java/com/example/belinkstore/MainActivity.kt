package com.example.belinkstore

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.NfcAdapter.CreateNdefMessageCallback
import android.nfc.NfcEvent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.belinkstore.AlarmAdapter
import com.example.belinkstore.Message
//import com.example.belinkstore.MyHostApduService
import com.example.belinkstore.R
import com.example.belinkstore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CreateNdefMessageCallback {
    private var mBinding: ActivityMainBinding?=null
    private val binding get() = mBinding!!

    var nfcAdapter: NfcAdapter? = null
    var myHost: Intent? = null

    val DataList= listOf<Message>(
        Message("1명 입장했습니다."),
        Message("2명 입장했습니다."),
        Message("3명 입장했습니다."),
        Message("4명 입장했습니다.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager= LinearLayoutManager(this)
        val adapter = AlarmAdapter(this, DataList)
        binding.recyclerView.adapter=adapter



//        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
//        if (nfcAdapter == null) {
//            Toast.makeText(this, "Does not support NFC", Toast.LENGTH_LONG).show()
//            finish()
//            return
//        } else {
//            myHost = Intent(this, MyHostApduService::class.java)
//            startService(myHost)
//            //nfcAdapter.setNdefPushMessageCallback(this::createNdefMessage, this);
//            //nfcAdapter.invokeBeam(this);
//        }

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    override fun onDestroy() {
        mBinding=null
        super.onDestroy()
    }

    override fun createNdefMessage(event: NfcEvent?): NdefMessage {
        val text = "Sending msg over NFC FROM WRITER 2020-05"

        return NdefMessage(
            arrayOf(
                NdefRecord.createMime("text/plain", text.toByteArray())
            )
        )
    }
}