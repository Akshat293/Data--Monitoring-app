package com.example.app2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class CamCast : AppCompatActivity() {
    private lateinit var Cam:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cam_cast)
       Cam=findViewById(R.id.But)
        Cam.setOnClickListener {
            val uri = Uri.parse("http://192.168.29.93") // missing 'http://' will cause crashed

            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }


    }

}