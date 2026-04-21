package com.example.ddevacademy
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
class SeminarResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seminar_result)
        val tvNameValue = findViewById<TextView>(R.id.tvNameValue)
        val tvEmailValue = findViewById<TextView>(R.id.tvEmailValue)
        val tvPhoneValue = findViewById<TextView>(R.id.tvPhoneValue)
        val tvGenderValue = findViewById<TextView>(R.id.tvGenderValue)
        val tvSeminarValue = findViewById<TextView>(R.id.tvSeminarValue)
        val btnBackHome = findViewById<MaterialButton>(R.id.btnBackHome)
        tvNameValue.text = intent.getStringExtra("name").orEmpty()
        tvEmailValue.text = intent.getStringExtra("email").orEmpty()
        tvPhoneValue.text = intent.getStringExtra("phone").orEmpty()
        tvGenderValue.text = intent.getStringExtra("gender").orEmpty()
        tvSeminarValue.text = intent.getStringExtra("seminar").orEmpty()
        btnBackHome.setOnClickListener {
            val homeIntent = Intent(this, HomeActivity::class.java).apply {
                putExtra("username", intent.getStringExtra("username"))
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(homeIntent)
            finish()
        }
    }
}
