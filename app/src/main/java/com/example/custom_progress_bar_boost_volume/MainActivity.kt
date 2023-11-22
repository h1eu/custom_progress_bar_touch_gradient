package com.example.custom_progress_bar_boost_volume

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.media.MediaPlayer
import android.media.audiofx.AudioEffect
import android.media.audiofx.BassBoost
import android.media.effect.Effect
import android.os.Bundle
import android.provider.MediaStore.Audio
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.media3.common.AuxEffectInfo
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



//        val trackSelector = DefaultTrackSelector(this)
//
//        val videoUri = RawResourceDataSource.buildRawResourceUri(R.raw.mp4_test)
//        val mediaItem = MediaItem.fromUri(videoUri)
//        val exoPlayer = ExoPlayer
//            .Builder(this)
//            .setTrackSelector(trackSelector)
//            .build()
//        exoPlayer.setMediaItem(mediaItem)
//        exoPlayer.prepare()

 //       val mediaPlayer = MediaPlayer.create(this, R.raw.mp3_test)


//        val steamType = AudioManager.STREAM_MUSIC
//        val sampleRate = 44100
//        val channelConfig = AudioFormat.CHANNEL_OUT_STEREO
//        val audioFormat = AudioFormat.ENCODING_PCM_16BIT
//        val bufferSize = AudioTrack.getMinBufferSize(sampleRate,channelConfig,audioFormat)
//        val audioTrack = AudioTrack(steamType,sampleRate,channelConfig,audioFormat,bufferSize,AudioTrack.MODE_STREAM)
//        audioTrack.setAuxEffectSendLevel(1f)

//
//        val bassBoost = BassBoost(Int.MAX_VALUE , mediaPlayer.audioSessionId)
//        bassBoost.enabled = true
//        bassBoost.setStrength(1000)
//        mediaPlayer.setAuxEffectSendLevel(1f)
//        mediaPlayer.isLooping = true;

     //   exoPlayer.setAuxEffectInfo(AuxEffectInfo(audioTrack.attachAuxEffect(bassBoost.id),1f))


//        val effects: Array<AudioEffect.Descriptor> = AudioEffect.queryEffects()
//
//        for (effect in effects) {
//            Log.d("lll", effect.name.toString() + ", type: " + effect.type.toString())
//        }
//        var lengt = -1
//        var isFirst = true

     //   binding.mediaPlayerView.player = exoPlayer

//
//        binding.mediaPlayerView.setOnClickListener {
//            if (isFirst){
//                isFirst = false
//                mediaPlayer.start()
//                Log.e("xvv", "onCreate: ${bassBoost.roundedStrength}", )
//                Log.e("xvv", "onCreate: ${bassBoost.strengthSupported}", )
//
//            }
//            else{
//
//                if (mediaPlayer.isPlaying){
//                    mediaPlayer.pause()
//                    lengt = mediaPlayer.currentPosition
//                }
//                else{
//                    mediaPlayer.seekTo(lengt)
//                    mediaPlayer.start()
//                }
//            }
//
//        }






        binding.btn.setOnClickListener {
            binding.point.startTransition()
//            this.showDialog {
//                onSaveValueBooster(it)
//            }
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

