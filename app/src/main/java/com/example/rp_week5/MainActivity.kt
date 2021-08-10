package com.example.rp_week5

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Movie
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.rp_week5.databinding.ActivityMainBinding
import com.example.rp_week5.models.MovieData
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create


data class Movies(
//    var img: Int,
    var img: String,
    var name: String,
    var egg_per: String,
    var ratio: String,
)

class MainActivity : AppCompatActivity() {

    var MoviesArrayList = ArrayList<Movies>()

    private lateinit var movieAdapter: MovieAdapter

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieAdapter = MovieAdapter(this, MoviesArrayList)
        binding.movieRv.adapter = movieAdapter

        binding.menuIcon.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        getMovieData("3f4eafbaa2f85e95ad46f8feff025dc9", "ko-KR", 1, "KR")


//        MoviesArrayList.add(0, movieAdapter.dataList[0])

//        binding.movieRv.layoutManager=LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
//
//        MoviesArrayList.add(
//            Movies(
//                R.drawable.movie1,
//                "싱크홀",
//                "ddd",
//                "qqq"
//            )
//        )
//
//        MoviesArrayList.add(
//            Movies(
//                R.drawable.movie1,
//                "싱크홀",
//                "ddd",
//                "qqq"
//            )
//        )


    }


    private fun getMovieData(api_key: String, language: String, page: Int, region: String) {

        val movieInterface = RetrofitClient.sRetrofit.create(MovieInterface::class.java)

        movieInterface.getNowPlaying(api_key, language, page, region).enqueue(object :
            Callback<MovieData> {

            override fun onResponse(
                call: Call<MovieData>,
                response: Response<MovieData>,
            ) {
                if (response.isSuccessful) {
//                    val results: List<Result>,
                    val result = response.body() as MovieData
//                    for(i in 0 until 1) {
//                        Glide.with(this@MainActivity).load(result.results[i].poster_path)
//                            .into(movieAdapter.binding.mvImg)
//                        result.results[i].title=dataList[position].name
//                        result.results[i].popularity.toString()=dataList[position].egg_per
//                        result.results[i].vote_average.toString()=dataList[position].ratio
//                        result.results[i].popularity.
//                        binding.mvImg.setImageURI(result.results[i].poster_path)
//                        binding.mvImg.setImageURI(result.results[i].poster_path)
//                        movieAdapter.binding.mvName.text = result.results[i].title
//                        movieAdapter.binding.eggTxt.text = result.results[i].popularity.toString()
//                        movieAdapter.binding.ratio.text = result.results[i].vote_average.toString()

                    for (i in 0..3) {
                        MoviesArrayList.add(
                            Movies(

                                "https://image.tmdb.org/t/p/w500"+result.results[i].poster_path,
//                                    R.drawable.movie1,
                                result.results[i].title,
                                result.results[i].popularity.toString(),
                                result.results[i].vote_average.toString()
                            )
                        )
                    }

                    movieAdapter.notifyDataSetChanged()

                    Log.d("DataCheck", MoviesArrayList.toString())

                } else {
                    Log.d("MainActivity", "getMovieData - onResponse : Error code")
                }
            }

            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                Log.d("MainActivity", t.message ?: "통신오류")
            }
        })
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }
    }


}