package com.example.retrofitcountryapi.adaptor

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
 import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitcountryapi.Country
import com.example.retrofitcountryapi.R
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.detail_layout.view.*

class DetailViewAdaptor(var context: Context, var CountryList: List<Country>) :
    RecyclerView.Adapter<DetailViewAdaptor.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        println("got it")
        Log.e(TAG,"got it")
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.detail_layout,
            parent, false))
    }

    override fun getItemCount(): Int {
        return CountryList.size
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Getting All Data And passing to ViewHolder


        holder?.area?.text = "Area         - "+CountryList[position].area.toString()

        holder?.population?.text = "Population   - "+CountryList[position].population.toString()




        val callingCodes_list = CountryList[position].callingCodes
        if(callingCodes_list.isEmpty()){
            var callingCodes:String = " Calling Codes Not Available"
            holder?.callingCodes?.text = callingCodes.trim()
        }else{
            var callingCodes:String = ""
            callingCodes+="\n"
            var count_calling = 1
            for(item in callingCodes_list){
                callingCodes= callingCodes +count_calling +"."+ "`"+item+"`"
                callingCodes+="\n"
            }
            holder?.callingCodes?.text = callingCodes.trim()
        }




        val language_list = CountryList[position].languages
        var name:String = ""
        var count_language = 1
        for(item in language_list){
            name= name + count_language +"."+"Name        - "+item.name
            name+="\n"
            name= name + "  Native Name - "+item.nativeName
            name+="\n"
            name = name +"  iso6391     - "+ item.iso6391
            name+="\n"
            name= name +"  iso6392     - "+item.iso6392
            name+="\n"
            name+="\n"
            count_language++
        }
        holder?.lang_name?.text = name.trim()


        val Currency_list = CountryList[position].currencies
        var Currency:String = " "
            Currency+= "\n"
        var count_currency = 1
        for(item in Currency_list){
            Currency= Currency+ count_currency + ".Name   - "+item.name
            Currency+="\n"
            Currency= Currency + "  Code   - "+item.code
            Currency+="\n"
            Currency= Currency + "  Symbol - "+item.symbol
            Currency+="\n"
            count_currency++
        }
        holder?.Currency?.text = Currency.trim()


        val RegionalBloc_list = CountryList[position].regionalBlocs
        if(RegionalBloc_list.isEmpty()){
            var RegionalBloc:String = " Regional Bloc Not Available"
            holder?.regionalBloc?.text = RegionalBloc.trim()
        }else{
            var RegionalBloc:String = " "
            RegionalBloc+="\n"
            var count_RegionalBloc = 1
            for(item in RegionalBloc_list){
                RegionalBloc =RegionalBloc + count_RegionalBloc+ "."+ "Name         - "+item.name
                RegionalBloc+="\n"
                RegionalBloc= RegionalBloc + "  Acronym      - "+ item.acronym
                RegionalBloc+="\n"
                val otherAcronyms_list = item.otherAcronyms
                if(otherAcronyms_list.isEmpty()){
                    var otherAcronyms:String = ""
                    RegionalBloc = RegionalBloc +"  \uD835\uDC0E\uD835\uDC2D\uD835\uDC21\uD835\uDC1E\uD835\uDC2B \uD835\uDC00\uD835\uDC1C\uD835\uDC2B\uD835\uDC28\uD835\uDC27\uD835\uDC32\uD835\uDC26 -\n     Other Acronyms Not Available " + otherAcronyms
                    RegionalBloc+="\n"
                }else{
                    var otherAcronyms:String = ""
                    var count_otherAcronyms = 1
                    for(newItem in otherAcronyms_list){
                        otherAcronyms= otherAcronyms+"     "+ count_otherAcronyms +". "+newItem
                        otherAcronyms+="\n"
                        count_otherAcronyms++
                    }
                    RegionalBloc = RegionalBloc +"  \uD835\uDC0E\uD835\uDC2D\uD835\uDC21\uD835\uDC1E\uD835\uDC2B \uD835\uDC00\uD835\uDC1C\uD835\uDC2B\uD835\uDC28\uD835\uDC27\uD835\uDC32\uD835\uDC26      -" + otherAcronyms
                    RegionalBloc+="\n"
                }
                val otherNames_list = item.otherNames
                if(otherNames_list.isEmpty()){
                    var otherNames:String = ""
                    RegionalBloc= RegionalBloc +"  \uD835\uDC0E\uD835\uDC2D\uD835\uDC21\uD835\uDC1E\uD835\uDC2B \uD835\uDC0D\uD835\uDC1A\uD835\uDC26\uD835\uDC1E -\n     Other Names Not Available "+otherNames
                    RegionalBloc+="\n"
                }else{
                    var otherNames:String = ""
                    var count_othername = 1
                    for(newItems in otherNames_list){
                        otherNames= otherNames+"     "  + count_othername + ". "+newItems
                        otherNames+="\n"
                        count_othername++
                    }
                    RegionalBloc= RegionalBloc +"  \uD835\uDC0E\uD835\uDC2D\uD835\uDC21\uD835\uDC1E\uD835\uDC2B \uD835\uDC0D\uD835\uDC1A\uD835\uDC26\uD835\uDC1E   -\n"+otherNames
                    RegionalBloc+="\n"
                }
                count_RegionalBloc++
            }
            holder?.regionalBloc?.text = RegionalBloc.trim()
        }
        // Uses Glide to load SVG image in detail activity
        var thumbnailImageView = holder?.image
        GlideToVectorYou.justLoadImage(context as Activity?, Uri.parse(CountryList[position].flag), thumbnailImageView)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        var image: ImageView = view.flagimageView
        var lang_name: TextView = view.lang_name
        var regionalBloc: TextView = view.country_regionalBloc
        var area: TextView = view.country_area

        var population: TextView = view.country_population

        var callingCodes: TextView = view.country_callingCodes

        var Currency: TextView = view.country_Currency

    }
}



