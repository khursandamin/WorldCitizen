package com.example.retrofitcountryapi.adaptor

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
 import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitcountryapi.Country
import com.example.retrofitcountryapi.Main2Activity
import com.example.retrofitcountryapi.R
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.country_name_layout.view.*
import kotlinx.android.synthetic.main.detail_layout.view.*
import kotlinx.android.synthetic.main.country_name_layout.view.*
class CountryNameAdapter(val context: Context, var CountryList: List<Country>) :
    RecyclerView.Adapter<CountryNameAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):CustomViewHolder {
        val layoutInflater = LayoutInflater.from(context).inflate(
            R.layout.country_name_layout,
            parent, false
        )
        return CustomViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return CountryList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        var name = CountryList[position].name

        holder.view.name?.text = CountryList[position].name
        holder.view.capital?.text = "Capital - " +CountryList[position].capital

        // Using Glide to show SVG images
        var thumbnailImageView = holder.view.flag_imageView
        GlideToVectorYou.justLoadImage(holder.view.context as Activity?, Uri.parse(CountryList[position].flag), thumbnailImageView)

        //Passing name parameter in CustomViewHolder to be sent to next activity
        holder?.name = name
    }

    class CustomViewHolder(var view: View,var name: String? = null): RecyclerView.ViewHolder(view){
        companion object {
            var country_name = "name"
        }
        init{
            view.setOnClickListener{
                var intent = Intent(view.context, Main2Activity::class.java)
                intent.putExtra(country_name, name)
                view.context.startActivity(intent)
            }
        }
    }

}