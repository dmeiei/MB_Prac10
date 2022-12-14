package kr.ac.hallym.prac10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.hallym.prac10.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {
    lateinit var binding: ActivityMain4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main4)

        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.settingButton.setOnClickListener {
            intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

    }
}