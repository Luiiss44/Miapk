package com.example.miapk.model

import java.io.Serializable

class User(
    var nombre: String,   // Nombre del usuario
    var correo: String,   // Correo electrónico
    var pass: String,     // Contraseña
    var perfil: String    // Perfil o rol
) : Serializable {

    private var telefono: Int? = null

    // Constructor secundario para crear un usuario sin especificar el nombre.
    // En este caso, se asigna el valor de 'correo' a 'nombre' por defecto.
    constructor(correo: String, pass: String, perfil: String) : this(correo, correo, pass, perfil)

    // Constructor secundario que permite incluir el teléfono.
    constructor(nombre: String, correo: String, pass: String, perfil: String, telefono: Int)
            : this(nombre, correo, pass, perfil) {
        this.telefono = telefono
    }
}

