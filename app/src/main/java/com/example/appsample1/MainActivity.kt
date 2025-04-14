package com.example.appsample1

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appsample1.databinding.ActivityMainBinding
import io.flutter.embedding.android.FlutterActivity

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)

//        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.mainButton.text = "App! Talk to Us!"
        binding.mainButton.setOnClickListener {
            println("Here we go")
            startActivity(
                FlutterActivity
                    .withNewEngine()
                    .initialRoute("/")
                    .build(this)
            )
        }
//        val btn: Button = findViewById(R.id.main_button)
//        btn.setOnClickListener {
//            println("Here we go")
//            FlutterActivity.withNewEngine().initialRoute("/").build(this)
//        }
    }
}