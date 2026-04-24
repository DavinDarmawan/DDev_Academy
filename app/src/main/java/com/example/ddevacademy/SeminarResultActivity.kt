package com.example.ddevacademy
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class SeminarResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seminar_result)

        val root = findViewById<android.widget.ScrollView>(R.id.seminarResultRoot)
        ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

        val tvNameValue = findViewById<TextView>(R.id.tvNameValue)
        val tvEmailValue = findViewById<TextView>(R.id.tvEmailValue)
        val tvPhoneValue = findViewById<TextView>(R.id.tvPhoneValue)
        val tvGenderValue = findViewById<TextView>(R.id.tvGenderValue)
        val tvSeminarValue = findViewById<TextView>(R.id.tvSeminarValue)
        val btnBackHome = findViewById<MaterialButton>(R.id.btnBackHome)

        SeminarSessionStore.latestSeminarResult = SeminarResultData(
            username = intent.getStringExtra("username").orEmpty(),
            name = intent.getStringExtra("name").orEmpty(),
            email = intent.getStringExtra("email").orEmpty(),
            phone = intent.getStringExtra("phone").orEmpty(),
            gender = intent.getStringExtra("gender").orEmpty(),
            seminar = intent.getStringExtra("seminar").orEmpty(),
        )

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
