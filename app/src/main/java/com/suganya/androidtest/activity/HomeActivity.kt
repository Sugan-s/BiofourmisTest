package com.suganya.androidtest.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.suganya.androidtest.R
import com.suganya.androidtest.adapter.ApodDetailAdapter
import com.suganya.androidtest.model.ApodDetailModel
import com.suganya.androidtest.utils.UtilValidate
import com.suganya.androidtest.webservices.RestServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var apodRecycleView: RecyclerView
    private lateinit var fab : FloatingActionButton
    internal var myCalendar = Calendar.getInstance()
    internal lateinit var date11: DatePickerDialog.OnDateSetListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initviews()

        getApodForDateRageAPI()

        date11 = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            myCalendar.firstDayOfWeek = Calendar.SUNDAY

            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

            Log.e("date",sdf.format(myCalendar.time).toString())
            getCurrentDateApodAPI()
        }

        fab.setOnClickListener {
            var dialog = DatePickerDialog(
                    this@HomeActivity,
                    date11,
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH))

            dialog.datePicker.maxDate = Calendar.getInstance().timeInMillis
            dialog.show()
        }
    }



    fun getDaysAgo(daysAgo: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)

        return calendar.time
    }

    fun initviews() {
        apodRecycleView = findViewById(R.id.rv_apod)
        fab = findViewById(R.id.fab)


    }


    fun getApodForDateRageAPI() {
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            var currentDate = sdf.format(Date())
            var pastdate = sdf.format(getDaysAgo(30))

//            https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2017-07-08&end_date=2017-07-10
            var key = "api_key=DEMO_KEY&start_date=" + pastdate.toString() + "&end_date=" + currentDate.toString()


            if (UtilValidate.isConnectivity(this)) {
                RestServiceBuilder.apiService!!.getdateApod.enqueue(object :
                    Callback<JsonArray> {
                    override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {

                        Log.e("url" , call.request().url().toString())

                        if (response.isSuccessful) {

                            var gson = Gson()
                            var apodList1 = gson.fromJson(response.body().toString() , Array<ApodDetailModel>::class.java).toList()
                            var mLayoutManager: GridLayoutManager = GridLayoutManager(this@HomeActivity,1)

                            var productAdapter = ApodDetailAdapter(this@HomeActivity, apodList1)
                            apodRecycleView.setHasFixedSize(true)
                            apodRecycleView.layoutManager = mLayoutManager
                            apodRecycleView.adapter = productAdapter
                            apodRecycleView.isNestedScrollingEnabled = false
                        }
                    }

                    override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                        Log.e("failed ", " >> $t")
                        Toast.makeText(this@HomeActivity, "Sorry, Some error occured please try again later1..", Toast.LENGTH_LONG).show()
                    }
                })
            } else {
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show()
            }

        } catch (e: Exception) {
            Toast.makeText(this, "Sorry, Some error occured please try again later1..", Toast.LENGTH_LONG).show()
        }

    }



    fun getCurrentDateApodAPI() {
        try {
            if (UtilValidate.isConnectivity(this)) {
                RestServiceBuilder.apiService!!.getCurrentDateApod.enqueue(object :
                    Callback<JsonObject> {
                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                        if (response.isSuccessful) {

                            var gson = Gson()

                            var jsonarray = "[" + response.body().toString() + "]"
                            var apodList1 = gson.fromJson(jsonarray , Array<ApodDetailModel>::class.java).toList()

                            var mLayoutManager: GridLayoutManager = GridLayoutManager(this@HomeActivity,1)

                                var productAdapter = ApodDetailAdapter(this@HomeActivity, apodList1)
                                    apodRecycleView.setHasFixedSize(true)
                                    apodRecycleView.layoutManager = mLayoutManager
                                    apodRecycleView.adapter = productAdapter
                                    apodRecycleView.isNestedScrollingEnabled = false



                        }
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Log.e("failed ", " >> $t")
                        Toast.makeText(this@HomeActivity, "Sorry, Some error occured please try again later1..", Toast.LENGTH_LONG).show()
                    }
                })
            } else {
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show()
            }

        } catch (e: Exception) {
            Toast.makeText(this, "Sorry, Some error occured please try again later1..", Toast.LENGTH_LONG).show()
        }
    }

}