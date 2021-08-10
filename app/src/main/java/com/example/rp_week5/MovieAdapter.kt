package com.example.rp_week5

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.example.rp_week5.databinding.MovieItemBinding


class MovieAdapter(private val context: Context, private var MoviesArrayList: ArrayList<Movies>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    var dataList: ArrayList<Movies> = MoviesArrayList

    //    var dataSet : ArrayList<Movies> = arrayListOf()
    lateinit var binding: MovieItemBinding

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
//        https://api.themoviedb.org/
        val url = "https://image.tmdb.org/t/p/w500" + dataList[position].img
        Glide.with(context)
            .load(url)
            .into(holder.binding.mvImg)

//        holder.binding.mvImg.setImageResource(dataList[position].img)
//        holder.binding.mvImg.setImageURI()
        holder.binding.eggTxt.text = dataList[position].egg_per
        holder.binding.ratio.text = dataList[position].ratio


//        getMovieData("3f4eafbaa2f85e95ad46f8feff025dc9", "ko-KR", 1, "KR", holder,position)


    }

    override fun getItemCount(): Int {
        return dataList.size
    }

//    private fun getMovieData(api_key: String, language: String, page: Int, region: String, holder: ViewHolder, position: Int) {
//        val movieInterface = RetrofitClient.sRetrofit.create(MovieInterface::class.java)
//
//        movieInterface.getNowPlaying(api_key, language, page, region).enqueue(object :
//            Callback<MovieData> {
//
//            override fun onResponse(
//                call: Call<MovieData>,
//                response: Response<MovieData>,
//            ) {
//                if (response.isSuccessful) {
////                    val results: List<Result>,
//                    val result = response.body() as MovieData
//                    for(i in 0 until 1) {
//                        Glide.with(context).load(result.results[i].poster_path)
//                            .into(holder.binding.mvImg)
//
//                        result.results[i].title=dataList[position].name
////                        result.results[i].popularity.toString()=dataList[position].egg_per
////                        result.results[i].vote_average.toString()=dataList[position].ratio
////                        result.results[i].popularity.
////                        binding.mvImg.setImageURI(result.results[i].poster_path)
////                        binding.mvImg.setImageURI(result.results[i].poster_path)
//                        holder.binding.mvName.text = result.results[i].title
//                        holder.binding.eggTxt.text = result.results[i].popularity.toString()
//                        holder.binding.ratio.text = result.results[i].vote_average.toString()
//                    }
//
//                } else {
//                    Log.d("MainActivity", "getMovieData - onResponse : Error code")
//                }
//            }
//
//            override fun onFailure(call: Call<MovieData>, t: Throwable) {
//                Log.d("MainActivity", t.message ?: "통신오류")
//            }
//        })
//    }

}