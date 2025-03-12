package com.example.miapk

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.miapk.databinding.ActivityMainBinding
import com.example.miapk.model.User
import com.example.miapk.ui.activity.SecondActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val correo = binding.editCorreo.text.toString()
            val pass = binding.editPass.text.toString()
            val perfil = binding.spinnerPerfil.selectedItem.toString()

            if (correo.isNotEmpty() && pass.isNotEmpty()) {
                val usuario = User(correo, pass, perfil)
                val bundle = Bundle().apply {
                    putSerializable("usuario", usuario)
                }
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("datos", bundle)
                startActivity(intent)
            } else {
                Snackbar.make(binding.root, "Fallo en login", Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.btnRegistro.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.checkInicio.setOnCheckedChangeListener { _, isChecked ->
            binding.btnLogin.isEnabled = isChecked
        }
    }

    override fun onStop() {
        super.onStop()
        binding.editPass.text.clear()
        binding.editCorreo.text.clear()
        binding.checkInicio.isChecked = false
    }
}


