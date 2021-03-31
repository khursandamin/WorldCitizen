package com.example.retrofitcountryapi


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitcountryapi.adaptor.CountryNameAdapter
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var layoutManager: RecyclerView.LayoutManager
    var countries: MutableList<Country> = ArrayList()
    var displayList: MutableList<Country> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        //Setting up retrofit for url responce
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://restcountries.eu/rest/v2/").build()

        val countryApi = retrofit.create(INetworkAPI::class.java)

        var response: Observable<List<Country>> = countryApi.getAllPosts()

        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe {
            layoutManager = LinearLayoutManager(this)
            rv__list_name.layoutManager = layoutManager
            rv__list_name.adapter = CountryNameAdapter(this, it)

            fun addData() {
                for (item in it) {
                    // Adding data to cuntries array for filtering
                    countries.add(item)
                }
            }
            addData()
        }
    }


    // Initiating Search option on toolbar
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.main, menu)
//        val searchItem = menu.findItem(R.id.menu_search)
//        if (searchItem != null) {
    //val searchView = searchItem.actionView as  SearchView
//            searchView.layoutParams = ActionBar.LayoutParams(Gravity.RIGHT)
    //  val editext = searchView.findViewById<EditText>(R.id.search_src_text)
    // editext.hint = "Search here..."

//            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    return true
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//
//                    displayList.clear()
//                    if (newText!!.isNotEmpty()) {
//                        val search = newText.toLowerCase()
//                        countries.forEach {
//
//                            if (it.name.toLowerCase().contains(search)) {
//                                Log.d("TAG", it.name)
//                                displayList.add(it)
//                            }
//                        }
//                    } else {
//                        displayList.addAll(countries)
//                    }
//                    rv__list_name.adapter = CountryNameAdapter(baseContext, displayList)
//                    rv__list_name.adapter?.notifyDataSetChanged()
//                    return true
//                }
//            })
//        }
//        return super.onCreateOptionsMenu(menu)
    //  }


}
