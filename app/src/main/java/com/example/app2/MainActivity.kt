package com.example.app2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import android.R.string.no
import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries


class MainActivity : AppCompatActivity() {

  lateinit var series: LineGraphSeries<DataPoint>
    lateinit var series1: LineGraphSeries<DataPoint>
    var x : Double = 0.0;  var y : Double = 0.0; var y1 : Double = 0.0
    var Gas1:String=" "
    var Temp1:String=" "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val database = FirebaseDatabase.getInstance().reference
       val Temp:TextView=findViewById(R.id.Temp)
        val Gas:TextView=findViewById(R.id.Gas)
       var getdata=object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {

                 Gas1 = p0.child("Gas Sensor").getValue().toString()
                 Temp1 = p0.child("Temperature").getValue().toString()
                 Gas.text=Gas1
                Temp.text=Temp1
                var graphview : GraphView = findViewById(R.id.idGraphView) as GraphView
                var graphview1 : GraphView = findViewById(R.id.idGraphView1) as GraphView
                series = LineGraphSeries<DataPoint>()
                series1 = LineGraphSeries<DataPoint>()
                for (i in 0..1000)
                {
                    x += 0.1
                    y=Temp1.toDouble()
                    y1=Gas1.toDouble()
                    series.appendData(DataPoint(x,y), true, 1000)
                    series1.appendData(DataPoint(x,y1), true, 1000)
                }
                graphview.addSeries(series)
                graphview1.addSeries(series1)
            }


            override fun onCancelled(p0: DatabaseError) {

            }

        }
       database.addValueEventListener(getdata)



    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.About -> {
                val intent= Intent(this,About::class.java)
                startActivity(intent)
                true
                true
            }
            R.id.Help -> {

                true
            }
            R.id.Cam -> {
              val intent= Intent(this,CamCast::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}