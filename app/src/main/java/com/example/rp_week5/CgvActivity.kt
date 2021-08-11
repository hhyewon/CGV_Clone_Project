package com.example.rp_week5

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.rp_week5.cgv_models.CgvData
import com.example.rp_week5.cgv_models.LOCALDATA031302
import com.example.rp_week5.databinding.TicketingBinding
import com.example.rp_week5.movies_models.MovieData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class Location(
    var cgv_location: String
)

class CgvActivity : AppCompatActivity() {

    var LocationArrayList = ArrayList<Location>()

    private lateinit var cgvAdapter: CgvAdapter

    private lateinit var binding: TicketingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TicketingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cgvAdapter = CgvAdapter(this, LocationArrayList)
        binding.locationRv.adapter = cgvAdapter


        binding.seoulTxt.setOnClickListener {
            getLocation()
            binding.locationRv.visibility=View.VISIBLE
            binding.seoulTxt.setBackgroundColor(Color.parseColor("#f5f5f5"))

        }

        binding.backIcon.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

//        LocationArrayList.add(
//            Location(
//                "어"
//            )
//        )
    }

    private fun getLocation() {

        val movieInterface = RetrofitClient.cRetrofit.create(CgvInterface::class.java)

        movieInterface.getCgv().enqueue(object :
            Callback<CgvData> {

            override fun onResponse(
                call: Call<CgvData>,
                response: Response<CgvData>,
            ) {
                if (response.isSuccessful) {

                    val result = response.body() as CgvData

                    Log.d("dd",result.LOCALDATA_031302.row.size.toString())
                        for (i in 0 until result.LOCALDATA_031302.row.size) {
                            if(result.LOCALDATA_031302.row[i].BPLCNM.contains("CGV")){

                                LocationArrayList.add(
                            Location(
                                result.LOCALDATA_031302.row[i].BPLCNM
                            )
                        )
                        }

                    }
                    cgvAdapter.notifyDataSetChanged()

                    Log.d("DataCheck2", LocationArrayList.toString())

                } else {
                    Log.d("CgvActicity", "getMovieData - onResponse : Error code")
                }
            }

            override fun onFailure(call: Call<CgvData>, t: Throwable) {
                Log.d("CgvActicity", t.message ?: "통신오류")
            }
        })
    }
}