package com.example.rp_week5

import android.content.Context
import android.graphics.Movie
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rp_week5.databinding.MovieItemBinding
import com.example.rp_week5.models.Dates
import com.example.rp_week5.models.MovieData
import com.example.rp_week5.models.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieAdapter(private val context: Context, private var MoviesArrayList: ArrayList<Movies>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    lateinit var dataList: ArrayList<Movies>
    lateinit var binding: MovieItemBinding

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    init {
        dataList = MoviesArrayList
    }

    class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: Movies) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//       holder.onBind(dataList[position])
        holder.binding.mvName.text = dataList[position].name
        holder.binding.mvImg.setImageResource(dataList[position].img)
        holder.binding.eggTxt.text = dataList[position].egg_per
        holder.binding.ratio.text = dataList[position].ratio

        getMovieData("3f4eafbaa2f85e95ad46f8feff025dc9", "ko-KR", 1, "KR", holder)


    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    private fun getMovieData(api_key: String, language: String, page: Int, region: String, holder: ViewHolder) {
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
                    for(i in 0 until 1) {
                        Glide.with(context).load(result.results[i].poster_path)
                            .into(holder.binding.mvImg)

//                        result.results[i].popularity.
//                        binding.mvImg.setImageURI(result.results[i].poster_path)
//                        binding.mvImg.setImageURI(result.results[i].poster_path)
                        holder.binding.mvName.text = result.results[i].title
                        holder.binding.eggTxt.text = result.results[i].popularity.toString()
                        holder.binding.ratio.text = result.results[i].vote_average.toString()
                    }

                } else {
                    Log.d("MainActivity", "getMovieData - onResponse : Error code")
                }
            }

            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                Log.d("MainActivity", t.message ?: "통신오류")
            }
        })
    }

}