package com.example.hospitalize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class bank_map : AppCompatActivity()  {
    lateinit var mapFragment : SupportMapFragment
    lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_map)

        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback {
            googleMap = it

            var location1 = LatLng(-7.6626370694481, 109.60926814706741)
            googleMap.addMarker(MarkerOptions().position(location1).title("Rumah Sakit"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1,15f))
        })
    }
}