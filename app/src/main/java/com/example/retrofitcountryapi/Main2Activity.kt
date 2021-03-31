package com.example.retrofitcountryapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitcountryapi.adaptor.CountryNameAdapter
import com.example.retrofitcountryapi.adaptor.DetailViewAdaptor
import com.google.gson.GsonBuilder
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.Icon
import com.mapbox.mapboxsdk.annotations.IconFactory
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions
import com.mapbox.mapboxsdk.constants.Style
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main2.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Suppress("DEPRECATION")
class Main2Activity : AppCompatActivity() {
    private lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var mapview: MapView
    lateinit var mapBox:MapboxMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(
            this@Main2Activity,
            "pk.eyJ1Ijoia2h1cnNhbmRhbWluIiwiYSI6ImNrbXhlbnN0NzA1bmwyb3BrbXNsaTQ2M2gifQ.5ph8HO6ItzGdpJs3_-FkZw"
        )

        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)

        mapview = findViewById(R.id.mapview)
        mapview .onCreate(savedInstanceState)

        mapview.setStyleUrl(Style.MAPBOX_STREETS)
        mapview.getMapAsync {
            it?.addMarker(MarkerOptions()
                .position(LatLng(48.85819, 2.29458))
                .title("Eiffel Tower"))
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

            //Getting Country name from Country Name Adapter
        var fullname =  intent.getStringExtra(CountryNameAdapter.CustomViewHolder.country_name)

        //Changing Title of tool bar according to Country Name
        supportActionBar?.title = fullname

        // Using retrofit to search detail of country by full Name
        var retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()
            )
        )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://restcountries.eu/rest/v2/name/").build()

        var FullNameApi = retrofit.create(INetworkAPI_detail::class.java)

        var response: Observable<List<Country>> = FullNameApi.getAllCountryByName(
            fullname!!,
            "true"
        )


        response.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe {
            layoutManager = LinearLayoutManager(this)
            detail_view.layoutManager = layoutManager
            detail_view.adapter = DetailViewAdaptor(this, it)

        }


    }


    override fun onStart() {
        super.onStart()
        mapview?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapview.onStop()
    }

    override fun onResume() {
        super.onResume()
        mapview.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapview.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapview.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapview.onSaveInstanceState(outState)
    }

}
