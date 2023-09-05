package com.example.custom_progress_bar_boost_volume

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.example.custom_progress_bar_boost_volume.databinding.ActivityMainBinding
import com.example.custom_progress_bar_boost_volume.databinding.DialogBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btn.setOnClickListener {
            this.showDialog {
            }
        }
    }
}


@SuppressLint("InflateParams")
fun Activity.showDialog(onAccept: () -> Unit) {

    val view: View = LayoutInflater.from(this).inflate(R.layout.dialog, null)
    val dialog = Dialog(this)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.setContentView(view)
    dialog.window?.setLayout(
        this.window.decorView.width * 9 / 10,
        WindowManager.LayoutParams.WRAP_CONTENT
    )
    if (!dialog.isShowing) {
        dialog.show()
    }
    val binding = DialogBinding.bind(view)

    binding.progressBarController.onTouchController = {
        binding.progressBar.drawGradient(it)
    }

    binding.progressBarController.onProgress = {
        binding.tvPercentVolume.text = it.toInt().toString()
    }


    binding.btnCancel.setOnClickListener {
        dialog.dismiss()
    }
    binding.btnOk.setOnClickListener {
        onAccept.invoke()
        dialog.dismiss()
    }
}