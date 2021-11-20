package com.example.coursewokr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import android.os.AsyncTask
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.net.URL



class MenuFragment : Fragment() {

   private  var aboutButton: Button ?= null
   private  var resulInfoCity: TextView ?= null
   private  var userField: EditText?= null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)



        userField = view.findViewById(R.id.user_field)
        aboutButton = view.findViewById(R.id.button)
        resulInfoCity = view.findViewById(R.id.result_info)


       aboutButton?.setOnClickListener{
           if (userField?.text?.toString()?.trim()?.equals("")!!){
               Toast.makeText(activity?.applicationContext, "Введите город", Toast.LENGTH_LONG).show()
               resulInfoCity?.text = ""
           }
           else {
               val city: String = userField?.text.toString()
               val key: String = "499690dc8b9a6039ac39a3366f7dbea1"
               val url_us = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$key&units=metric&lang=ru"

               doAsync {
                   val apiResponse = URL(url_us).readText()


                   val weather = JSONObject(apiResponse).getJSONArray("weather")
                   val desc = weather.getJSONObject(0).getString("description")

                   val main = JSONObject(apiResponse).getJSONObject("main")
                   val temp = main.getString("temp")

                   resulInfoCity?.text = "Температура: $temp\n$desc"
               }


           }


       }



/*

        aboutButton = view.findViewById(R.id.abou_button)

        val aboutFragment = AboutFragment()
        aboutButton.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragment_container, aboutFragment)
                ?.commit()

        } */
        return view

    }



}


