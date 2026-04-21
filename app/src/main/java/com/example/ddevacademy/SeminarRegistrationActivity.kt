package com.example.ddevacademy

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SeminarRegistrationActivity : AppCompatActivity() {

    private lateinit var tilName: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPhone: TextInputLayout
    private lateinit var etName: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPhone: TextInputEditText
    private lateinit var rgGender: RadioGroup
    private lateinit var spSeminar: Spinner
    private lateinit var cbAgreement: CheckBox
    private lateinit var tvGenderError: TextView
    private lateinit var tvSeminarError: TextView
    private lateinit var tvAgreementError: TextView
    private lateinit var btnSubmit: MaterialButton
    private var loginUsername: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seminar_registration)

        bindViews()
        setupSeminarSpinner()
        setupRealtimeValidation()

        btnSubmit.setOnClickListener {
            if (!validateForm(showError = true)) {
                return@setOnClickListener
            }

            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah data yang Anda isi sudah benar?")
                .setPositiveButton("Ya") { _, _ ->
                    navigateToResult()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }

    private fun bindViews() {
        tilName = findViewById(R.id.tilNama)
        tilEmail = findViewById(R.id.tilEmailSeminar)
        tilPhone = findViewById(R.id.tilPhone)
        etName = findViewById(R.id.etNama)
        etEmail = findViewById(R.id.etEmailSeminar)
        etPhone = findViewById(R.id.etPhone)
        rgGender = findViewById(R.id.rgGenderSeminar)
        spSeminar = findViewById(R.id.spSeminar)
        cbAgreement = findViewById(R.id.cbAgreement)
        tvGenderError = findViewById(R.id.tvGenderError)
        tvSeminarError = findViewById(R.id.tvSeminarError)
        tvAgreementError = findViewById(R.id.tvAgreementError)
        btnSubmit = findViewById(R.id.btnSubmitSeminar)

        loginUsername = intent.getStringExtra("username").orEmpty()
        if (loginUsername.isNotBlank()) {
            etName.setText(loginUsername)
        }
    }

    private fun setupSeminarSpinner() {
        val seminars = resources.getStringArray(R.array.seminar_options)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, seminars)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spSeminar.adapter = adapter
    }

    private fun setupRealtimeValidation() {
        etName.doAfterTextChanged { validateName(showError = true) }
        etEmail.doAfterTextChanged { validateEmail(showError = true) }
        etPhone.doAfterTextChanged { validatePhone(showError = true) }

        rgGender.setOnCheckedChangeListener { _, _ ->
            validateGender(showError = true)
        }

        spSeminar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                validateSeminar(showError = true)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                validateSeminar(showError = true)
            }
        }

        cbAgreement.setOnCheckedChangeListener { _, _ ->
            validateAgreement(showError = true)
        }
    }

    private fun validateForm(showError: Boolean): Boolean {
        val nameValid = validateName(showError)
        val emailValid = validateEmail(showError)
        val phoneValid = validatePhone(showError)
        val genderValid = validateGender(showError)
        val seminarValid = validateSeminar(showError)
        val agreementValid = validateAgreement(showError)
        return nameValid && emailValid && phoneValid && genderValid && seminarValid && agreementValid
    }

    private fun validateName(showError: Boolean): Boolean {
        val value = etName.text?.toString().orEmpty().trim()
        val valid = value.isNotEmpty()
        tilName.error = if (!valid && showError) "Nama wajib diisi" else null
        return valid
    }

    private fun validateEmail(showError: Boolean): Boolean {
        val value = etEmail.text?.toString().orEmpty().trim()
        val valid = value.isNotEmpty() && value.contains("@")
        tilEmail.error = if (!valid && showError) "Email harus valid dan mengandung @" else null
        return valid
    }

    private fun validatePhone(showError: Boolean): Boolean {
        val value = etPhone.text?.toString().orEmpty().trim()
        val onlyDigits = value.all { it.isDigit() }
        val validLength = value.length in 10..13
        val validPrefix = value.startsWith("08")
        val valid = value.isNotEmpty() && onlyDigits && validLength && validPrefix

        tilPhone.error = if (!valid && showError) {
            "Nomor HP harus angka, 10-13 digit, dan diawali 08"
        } else {
            null
        }
        return valid
    }

    private fun validateGender(showError: Boolean): Boolean {
        val valid = rgGender.checkedRadioButtonId != -1
        tvGenderError.text = if (!valid && showError) "Pilih jenis kelamin" else ""
        return valid
    }

    private fun validateSeminar(showError: Boolean): Boolean {
        val valid = spSeminar.selectedItemPosition > 0
        tvSeminarError.text = if (!valid && showError) "Pilih salah satu seminar" else ""
        return valid
    }

    private fun validateAgreement(showError: Boolean): Boolean {
        val valid = cbAgreement.isChecked
        tvAgreementError.text = if (!valid && showError) {
            "Anda harus menyetujui data yang diinput"
        } else {
            ""
        }
        return valid
    }

    private fun navigateToResult() {
        val selectedGender = findViewById<TextView>(rgGender.checkedRadioButtonId).text.toString()
        val selectedSeminar = spSeminar.selectedItem.toString()

        val intent = Intent(this, SeminarResultActivity::class.java).apply {
            putExtra("username", loginUsername)
            putExtra("name", etName.text?.toString().orEmpty().trim())
            putExtra("email", etEmail.text?.toString().orEmpty().trim())
            putExtra("phone", etPhone.text?.toString().orEmpty().trim())
            putExtra("gender", selectedGender)
            putExtra("seminar", selectedSeminar)
        }
        startActivity(intent)
    }
}

