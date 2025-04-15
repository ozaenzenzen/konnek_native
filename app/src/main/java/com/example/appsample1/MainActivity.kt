package com.example.appsample1

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appsample1.databinding.ActivityMainBinding
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var flutterEngine: FlutterEngine

    private fun initFlutterEngine() {
        flutterEngine = FlutterEngine(this)
        flutterEngine.navigationChannel.setInitialRoute("/");

        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )
        FlutterEngineCache.getInstance().put(
            "sag_main_engine",
            flutterEngine,
        )
        println("initFlutterEngine")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFlutterEngine()

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
//                    .withNewEngine()
//                    .initialRoute("/")
                    .withCachedEngine("sag_main_engine")
                    .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
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