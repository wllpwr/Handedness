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
        var gson = GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create()

        val postData = gson.toJson(DataObj)

        // posting data
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)

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
}