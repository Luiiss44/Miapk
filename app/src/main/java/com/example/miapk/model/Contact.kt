package com.example.miapk.model

import java.io.Serializable

class Contact(
    var nombre: String,
    var apellido: String,
    var telefono: Int,
    var direccion: String,
    var imagen: String
): Serializable{

}