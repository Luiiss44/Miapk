package com.example.inicio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.inicio.model.User
import com.example.miapk.R
import com.example.miapk.RegisterActivity
import com.example.miapk.SecondActivity
import com.example.miapk.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var check: CheckBox
    private lateinit var butonLogin: Button
    private lateinit var spinnerPerfil: Spinner
    private lateinit var editCorreo: EditText
    private lateinit var editPass: EditText

    // FALTA ESTE
    private lateinit var butonRegistro: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        // Enlazamos vistas
        check = findViewById(R.id.checkInicio)
        butonLogin = findViewById(R.id.btnLogin)
        butonRegistro = findViewById(R.id.btnRegistro)
        spinnerPerfil = findViewById(R.id.spinnerPerfil)
        editCorreo = findViewById(R.id.editCorreo)
        editPass = findViewById(R.id.editPass)

        check = findViewById(R.id.checkInicio)
        butonLogin = findViewById(R.id.btnLogin)

        // ENLAZAMOS TAMBIÉN btnRegistro
        butonRegistro = findViewById(R.id.btnRegistro)



        acciones()
    }

    override fun onRestart() {
        super.onRestart()
        editCorreo.text.clear()
        editPass.text.clear()
        check.isChecked = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private fun acciones() {
        check.setOnCheckedChangeListener { _, b ->
            butonLogin.isEnabled = b
        }



        // Acción para botón "Inicio"
        butonLogin.setOnClickListener {
            //cambio de pantalla

            val user = User(editCorreo.text.toString(), editPass.text.toString(),spinnerPerfil.selectedItem.toString())

            val bundle: Bundle = Bundle()

            bundle.putSerializable("user",user)
            bundle.putString("name", "Borja")



            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("datos", bundle)
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
