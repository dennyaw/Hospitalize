package com.example.hospitalize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import edmt.dev.videoplayer.adapter.VideoPlayerRecyclerAdapter
import edmt.dev.videoplayer.model.MediaObject
import edmt.dev.videoplayer.utils.VerticalSpacingItemDecorator
import kotlinx.android.synthetic.main.activity_gallery.*

class gallery : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        img1.setOnClickListener{
            imageview.setImageResource(R.drawable.foto1)
        }
        img2.setOnClickListener{
            imageview.setImageResource(R.drawable.foto2)
        }
        img3.setOnClickListener{
            imageview.setImageResource(R.drawable.foto3)
        }

        initVideos()

        val balik = findViewById<ImageButton>(R.id.balik)
        balik.setOnClickListener {
            finish()
        }
    }
    private fun initVideos() {
        val layoutManager = LinearLayoutManager(this)
        video_player.layoutManager = layoutManager
        val verticalItemDecor = VerticalSpacingItemDecorator(48)
        video_player.addItemDecoration(verticalItemDecor)

        val sourceVideos : ArrayList<MediaObject> = ArrayList(sampleVideoList())
        video_player.setMediaObjects(sourceVideos)
        val adapter = VideoPlayerRecyclerAdapter(sourceVideos,initGlide())
        video_player.adapter = adapter
    }

    private fun initGlide(): RequestManager? {
        val options = RequestOptions()
            .placeholder(R.drawable.white_background)
            .error(R.drawable.white_background)
        return Glide.with(this).setDefaultRequestOptions(options)
    }

    private fun sampleVideoList(): List<MediaObject>? {
        return listOf(
            MediaObject(
                "Mari Donor Darah Unit Donor Darah",
                "https://ik.imagekit.io/adiharka/Android/Iklan_Mari_Donor_Darah.mp4?updatedAt=1639643220436",
                "https://i.ytimg.com/vi/fpGJmO1IlEc/maxresdefault.jpg",
                "Video ini adalah animasi mengajak masyarakat untuk mendonorkan darahnya demi kemanusiaan. \"Setetes darahmu, nyawa bagi saudaramu\""),
            MediaObject(
                "Penyuluhan Donor Darah PMI Kota Yogyakarta",
                "https://ik.imagekit.io/adiharka/Android/Penyuluhan_Donor_Darah_PMI.mp4?updatedAt=1639643269465",
                "https://i.ytimg.com/vi/Uo36fV_bMxo/sddefault.jpg",
                "Video's Title: Penyuluhan Donor Darah PMI Kota Yogyakarta\n" +
                        "Video Editor: Amalia Ekanissa Perdana\n" +
                        "Narator: Amalia Ekanissa Perdana\n" +
                        "\n" +
                        "Thank's to :\n" +
                        "PMI Kota Yogyakarta\n" +
                        "Universitas AMIKOM Yogyakarta"),
            MediaObject(
                "Donor Darah - Motion Graphic Media Penyuluhan Donor Darah\n",
                "https://ik.imagekit.io/adiharka/Android/Donor_Darah_-_Motion_Graphic.mp4?updatedAt=1639643320973",
                "https://i.ytimg.com/vi/9HmwL2987BU/sddefault.jpg",
                "Sebuah media penyuluhan tentang donor darah. Mulai dari pengertian donor darah, penggolongan darah, cara kerja sel darah dalam menerima darah, donor darah apheresis, manfaat donor darah, dan kriteria umum pendonor.\n")
        )
    }
}