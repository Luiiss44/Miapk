package com.example.miapk


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miapk.databinding.ActivityRegisterBinding
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    // Vistas
    private lateinit var editNombre: EditText
    private lateinit var editCorreoReg: EditText
    private lateinit var editPassReg: EditText
    private lateinit var editPassConfirm: EditText
    private lateinit var spinnerPerfilReg: Spinner
    private lateinit var checkTerminos: CheckBox
    private lateinit var btnRegistrarUsuario: Button
    private lateinit var btnCancelarRegistro: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        // Si quieres usar ViewBinding, podrías usar setContentView(binding.root)
        setContentView(R.layout.activity_register)

        // findViewById de cada vista
        editNombre = findViewById(R.id.editNombre)
        editCorreoReg = findViewById(R.id.editCorreoReg)
        editPassReg = findViewById(R.id.editPassReg)
        editPassConfirm = findViewById(R.id.editPassConfirm)
        spinnerPerfilReg = findViewById(R.id.spinnerPerfilReg)
        checkTerminos = findViewById(R.id.checkTerminos)
        btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario)
        btnCancelarRegistro = findViewById(R.id.btnCancelarRegistro)

        acciones()
    }

    private fun acciones() {
        btnRegistrarUsuario.setOnClickListener {
            val pass = editPassReg.text.toString()
            val passConfirm = editPassConfirm.text.toString()

            if (pass == passConfirm && checkTerminos.isChecked) {
                // Ejemplo: Navegar a SecondActivity
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            } else {
                // Mostrar un aviso (Toast) si no cumple
                Toast.makeText(
                    this,
                    "Verifica que las contraseñas coincidan y acepta los términos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnCancelarRegistro.setOnClickListener {
            // Cierra esta pantalla y regresa a la anterior
            finish()
        }
    }
}

