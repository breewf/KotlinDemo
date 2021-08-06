package com.hy.kotlindemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.hy.kotlindemo.databinding.ActivityResultContract2Binding
import com.hy.kotlindemo.databinding.ActivityResultContractBinding

class ActivityResultContract2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityResultContract2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultContract2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tv1.setOnClickListener {
            val intent = Intent()
            intent.putExtra("ResultContract", "Hello world")
            setResult(1, intent)
            finish()
        }

        binding.tv2.setOnClickListener {
            val intent = Intent()
            intent.putExtra("ResultContract", "返回结果2")
            setResult(2, intent)
            finish()
        }
    }
}