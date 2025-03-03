package com.example.miapk


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miapk.model.User

class RegisterActivity : AppCompatActivity() {

    // Declaración de variables para las vistas
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
        setContentView(R.layout.activity_register)

        // Enlaza las vistas con findViewById
        editNombre = findViewById(R.id.editNombre)
        editCorreoReg = findViewById(R.id.editCorreoReg)
        editPassReg = findViewById(R.id.editPassReg)
        editPassConfirm = findViewById(R.id.editPassConfirm)
        spinnerPerfilReg = findViewById(R.id.spinnerPerfilReg)
        checkTerminos = findViewById(R.id.checkTerminos)
        btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario)
        btnCancelarRegistro = findViewById(R.id.btnCancelarRegistro)

        // Aquí colocas el listener para el botón de registrar:
        btnRegistrarUsuario.setOnClickListener {
            val nombre = editNombre.text.toString()
            val correo = editCorreoReg.text.toString()
            val pass = editPassReg.text.toString()
            val passConfirm = editPassConfirm.text.toString()
            val perfil = spinnerPerfilReg.selectedItem.toString()

            // Validación simple
            if (pass == passConfirm && checkTerminos.isChecked) {
                // Creamos el objeto User (usa los datos que necesites)
                val user = User(correo, pass, perfil)
                // Creamos el Intent para abrir ShowUserActivity
                val intent = Intent(this, ShowUserActivity::class.java)
                // Pasamos el objeto user
                intent.putExtra("user", user)
                startActivity(intent)
            } else {
                // Si falla la validación, mostramos un mensaje
                Toast.makeText(this, "Verifica que las contraseñas coincidan y acepta los términos", Toast.LENGTH_SHORT).show()
            }
        }

        // Listener para el botón de cancelar (opcional)
        btnCancelarRegistro.setOnClickListener {
            finish() // Cierra la actividad y vuelve a la anterior
        }
    }
}


