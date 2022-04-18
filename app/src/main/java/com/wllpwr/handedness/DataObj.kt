package com.wllpwr.handedness

import android.os.StrictMode
import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.reflect.Modifier
import java.net.URL

object DataObj{
    private val dataPoints: MutableList<String> = mutableListOf()
    var completed = 0
    var error = 0

    fun addData(Datapoint: String) {
        dataPoints.add(0,Datapoint)
    }

    fun clear() {
        dataPoints.clear()
    }

    fun printData() {
        println(dataPoints.joinToString(" "))
    }


    fun postData() {
        // allow networking on main thread
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        // serialize object
        var gson = GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create()
        val postData = gson.toJson(DataObj)

        // URL ~alexwaters.dev
        val url = URL("http://141.126.225.241:4444/")

        val conn = url.openConnection()
        conn.doOutput = true
        conn.setRequestProperty("Content-Type", "application/json")
        conn.setRequestProperty("Content-Length", postData.length.toString())

        DataOutputStream(conn.getOutputStream()).use { it.writeBytes(postData) }
        BufferedReader(InputStreamReader(conn.getInputStream())).use { bf ->
            var line: String?
            while (bf.readLine().also { line = it } != null) {
                println(line)
            }
        }
    }

    fun errorHit() {
        error += 1
    }

    fun getErrorCount(): Int {
        return error
    }

    fun completeTest() {
        addData("COMPLETED")
        error = 0
        completed += 1
        if (completed == 3)  {
            postData()
            completed = 0
            clear()
        }
    }
}