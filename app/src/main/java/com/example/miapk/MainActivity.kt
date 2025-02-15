package com.example.inicio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.example.miapk.R
import com.example.miapk.RegisterActivity
import com.example.miapk.SecondActivity
import com.example.miapk.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var check: CheckBox
    private lateinit var butonLogin: Button
    // FALTA ESTE
    private lateinit var butonRegistro: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        check = findViewById(R.id.checkInicio)
        butonLogin = findViewById(R.id.btnLogin)
        // ENLAZAMOS TAMBIÉN btnRegistro
        butonRegistro = findViewById(R.id.btnRegistro)

        acciones()
    }

    private fun acciones() {
        check.setOnCheckedChangeListener { _, b ->
            butonLogin.isEnabled = b
        }

        // Acción para botón "Inicio"
        butonLogin.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        // ACCIÓN PARA BOTÓN "REGISTRO"
        butonRegistro.setOnClickListener {
            // Aquí abrimos la RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
