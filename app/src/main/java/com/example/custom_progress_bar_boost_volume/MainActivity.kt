package com.example.custom_progress_bar_boost_volume

import android.annotation.SuppressLint
import android.content.Context
import android.media.audiofx.AudioEffect
import android.media.audiofx.BassBoost
import android.media.effect.Effect
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import com.example.custom_progress_bar_boost_volume.databinding.ActivityMainBinding


@UnstableApi class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        val trackSelector = DefaultTrackSelector(this)

        val videoUri = RawResourceDataSource.buildRawResourceUri(R.raw.mp3_test)
        val mediaItem = MediaItem.fromUri(videoUri)
        val exoPlayer = ExoPlayer
            .Builder(this)
            .setTrackSelector(trackSelector)
            .build()
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()


        val bassBoost = BassBoost(0 , exoPlayer.audioSessionId)

        bassBoost.setStrength(1000)
        bassBoost.enabled = true
        exoPlayer.deviceComponent

//        val effects: Array<AudioEffect.Descriptor> = AudioEffect.queryEffects()
//
//        for (effect in effects) {
//            Log.d("lll", effect.name.toString() + ", type: " + effect.type.toString())
//        }

        binding.mediaPlayerView.player = exoPlayer






        binding.btn.setOnClickListener {
            this.showDialog {
                onSaveValueBooster(it)
            }
        }
    }

    private fun onSaveValueBooster(value: Int) {
        val sharedPreferences =
            this.getSharedPreferences(Constants.SHARED_PRE, Context.MODE_PRIVATE)
        sharedPreferences.edit {
            this.putInt(Constants.VALUE_BOOSTER, value)
            this.commit()
        }
    }
}

