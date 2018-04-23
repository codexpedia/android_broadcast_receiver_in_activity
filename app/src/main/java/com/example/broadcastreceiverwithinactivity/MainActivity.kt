package com.example.broadcastreceiverwithinactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    companion object {
        val ACTION1 = "com.example.broadcastreceiverwithinactivity.Greeting1"
        val ACTION2 = "com.example.broadcastreceiverwithinactivity.Greeting2"
    }

    private lateinit var receiver : BroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // create the broadcast receiver and register it with this activity
        // By creating the broadcast receiver within the activity,
        // the logic in the onReceive function can be tailored specifically for this activity
        val filter = IntentFilter()
        filter.addAction(ACTION1)
        filter.addAction(ACTION2)
        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (intent.action == ACTION1) {
                    Toast.makeText(applicationContext, "Greeting 1", Toast.LENGTH_SHORT).show()
                } else if (intent.action == ACTION2) {
                    Toast.makeText(applicationContext, "Greeting 2", Toast.LENGTH_SHORT).show()
                }
            }
        }
        registerReceiver(receiver, filter)




        // button click listeners, send broadcast when clicked
        btn_send_broadcast1.setOnClickListener {
            val intent = Intent()
            intent.action = ACTION1
            sendBroadcast(intent)
        }

        btn_send_broadcast2.setOnClickListener {
            val intent = Intent()
            intent.action = ACTION2
            sendBroadcast(intent)
        }
    }

    // unregister the broadcast when the activity is about to get destroyed
    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }
}
