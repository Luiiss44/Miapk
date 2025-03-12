package com.example.miapk.ui.activity

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.miapk.R
import com.example.miapk.databinding.ActivityDetailBinding
import com.example.miapk.model.ContactJSON
import com.google.gson.Gson
import com.google.gson.JsonObject

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperamos el objeto enviado por el Intent
        val contact = intent.getSerializableExtra("contact") as? ContactJSON

        contact?.let {
            // Cargamos la imagen del contacto
            Glide.with(this)
                .load(it.image)
                .placeholder(R.drawable.base)
                .into(binding.imageViewDetail)

            // Convertimos el objeto a un JsonObject para recorrer sus propiedades dinámicamente
            val gson = Gson()
            val jsonElement = gson.toJsonTree(it)
            if (jsonElement.isJsonObject) {
                // Se añaden los campos dinámicamente al contenedor
                addPropertiesToLayout(jsonElement.asJsonObject, binding.layoutDetail)
            }
        }
    }

    /**
     * Función recursiva que recorre un JsonObject y añade un TextView por cada propiedad.
     * Maneja propiedades primitivas, objetos anidados y arrays.
     *
     * @param jsonObject El objeto JSON a recorrer.
     * @param layout El LinearLayout donde se agregarán los TextViews.
     * @param indent Nivel de indentación (en píxeles) para propiedades anidadas.
     */
    private fun addPropertiesToLayout(jsonObject: JsonObject, layout: LinearLayout, indent: Int = 0) {
        val context = layout.context
        for ((key, value) in jsonObject.entrySet()) {
            val textView = TextView(context).apply {
                textSize = 16f
                setPadding(indent, 8, 0, 8)
            }
            when {
                value.isJsonPrimitive -> {
                    textView.text = "$key: ${value.asString}"
                    layout.addView(textView)
                }
                value.isJsonObject -> {
                    textView.text = "$key:"
                    layout.addView(textView)
                    // Llamada recursiva para mostrar los campos del objeto anidado
                    addPropertiesToLayout(value.asJsonObject, layout, indent + 16)
                }
                value.isJsonArray -> {
                    textView.text = "$key:"
                    layout.addView(textView)
                    // Iteramos sobre cada elemento del array
                    value.asJsonArray.forEach { element ->
                        if (element.isJsonPrimitive) {
                            val tv = TextView(context).apply {
                                textSize = 16f
                                setPadding(indent + 16, 8, 0, 8)
                                text = "- ${element.asString}"
                            }
                            layout.addView(tv)
                        } else if (element.isJsonObject) {
                            addPropertiesToLayout(element.asJsonObject, layout, indent + 16)
                        }
                    }
                }
            }
        }
    }
}
