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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

        val root = findViewById<android.widget.ScrollView>(R.id.seminarRegistrationRoot)
        ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

        bindViews()
        setupSeminarSpinner()
        setupRealtimeValidation()

        btnSubmit.setOnClickListener {
            if (!validateForm(showError = true)) {
                return@setOnClickListener
            }

            MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.konfirmasi))
                .setMessage(getString(R.string.konfirmasi_data_benar))
                .setPositiveButton(getString(R.string.ya)) { _, _ ->
                    navigateToResult()
                }
                .setNegativeButton(getString(R.string.tidak), null)
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
        tilName.error = if (!valid && showError) getString(R.string.nama_wajib) else null
        return valid
    }

    private fun validateEmail(showError: Boolean): Boolean {
        val value = etEmail.text?.toString().orEmpty().trim()
        val valid = value.isNotEmpty() && value.contains("@")
        tilEmail.error = if (!showError || valid) {
            null
        } else if (value.isEmpty()) {
            getString(R.string.email_wajib)
        } else {
            getString(R.string.email_tidak_valid)
        }
        return valid
    }

    private fun validatePhone(showError: Boolean): Boolean {
        val value = etPhone.text?.toString().orEmpty().trim()
        val onlyDigits = value.all { it.isDigit() }
        val validLength = value.length in 10..13
        val validPrefix = value.startsWith("08")
        val valid = value.isNotEmpty() && onlyDigits && validLength && validPrefix

        tilPhone.error = if (!showError || valid) {
            null
        } else if (value.isEmpty()) {
            getString(R.string.phone_wajib)
        } else {
            getString(R.string.phone_tidak_valid)
        }
        return valid
    }

    private fun validateGender(showError: Boolean): Boolean {
        val valid = rgGender.checkedRadioButtonId != -1
        tvGenderError.text = if (!valid && showError) getString(R.string.gender_wajib) else ""
        return valid
    }

    private fun validateSeminar(showError: Boolean): Boolean {
        val valid = spSeminar.selectedItemPosition > 0
        tvSeminarError.text = if (!valid && showError) getString(R.string.seminar_wajib) else ""
        return valid
    }

    private fun validateAgreement(showError: Boolean): Boolean {
        val valid = cbAgreement.isChecked
        tvAgreementError.text = if (!valid && showError) {
            getString(R.string.agreement_wajib)
        } else {
            ""
        }
        return valid
    }

    private fun navigateToResult() {
        val selectedGender = findViewById<TextView>(rgGender.checkedRadioButtonId).text.toString()
        val selectedSeminar = spSeminar.selectedItem.toString()
        val resultData = SeminarResultData(
            username = loginUsername,
            name = etName.text?.toString().orEmpty().trim(),
            email = etEmail.text?.toString().orEmpty().trim(),
            phone = etPhone.text?.toString().orEmpty().trim(),
            gender = selectedGender,
            seminar = selectedSeminar,
        )

        SeminarSessionStore.latestSeminarResult = resultData

        val intent = Intent(this, SeminarResultActivity::class.java).apply {
            putExtra("username", resultData.username)
            putExtra("name", resultData.name)
            putExtra("email", resultData.email)
            putExtra("phone", resultData.phone)
            putExtra("gender", resultData.gender)
            putExtra("seminar", resultData.seminar)
        }
        startActivity(intent)
    }
}

