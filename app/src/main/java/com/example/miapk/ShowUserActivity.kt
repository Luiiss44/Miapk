package com.example.miapk


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.miapk.model.User

class ShowUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_user)

        val tvCorreo = findViewById<TextView>(R.id.tvCorreo)
        val tvPass = findViewById<TextView>(R.id.tvPass)
        val tvPerfil = findViewById<TextView>(R.id.tvPerfil)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        val user = intent.getSerializableExtra("user") as? User
        user?.let {
            tvCorreo.text = "Correo: ${it.correo}"
            tvPass.text = "Contraseña: ${it.pass}"
            tvPerfil.text = "Perfil: ${it.perfil}"
        }

        btnVolver.setOnClickListener {
            finish()
        }
    }
}

