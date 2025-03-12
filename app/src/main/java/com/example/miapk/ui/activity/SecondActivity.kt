package com.example.miapk.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.inicio.adapter.ContactAdapter
import com.example.miapk.R
import com.example.miapk.databinding.ActivitySecondBinding
import com.example.miapk.model.ContactJSON
import com.example.miapk.model.User
import com.example.miapk.ui.dialog.InfoDialog
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import org.json.JSONArray

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var listaContacts: MutableList<ContactJSON>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        instancias()
        obtenerDatos()
        cargarDatos()
    }

    private fun cargarDatos() {
        val gson = Gson()
        val url = "https://dummyjson.com/users"
        val peticion = JsonObjectRequest(url, { response ->
            val users: JSONArray = response.getJSONArray("users")
            for (i in 0 until users.length()) {
                val userJSON = users.getJSONObject(i)
                val contact = gson.fromJson(userJSON.toString(), ContactJSON::class.java)
                contactAdapter.addConctact(contact)
                Log.v("datos", contact.firstName ?: "sin firstName")
            }
        }, {
            Snackbar.make(binding.root, "Error en la conexión con el API", Snackbar.LENGTH_SHORT).show()
        })
        Volley.newRequestQueue(this).add(peticion)
    }

    private fun instancias() {
        listaContacts = ArrayList()
        contactAdapter = ContactAdapter(listaContacts, this)
        binding.recycler.adapter = contactAdapter
        binding.recycler.layoutManager = if (resources.configuration.orientation == 1) {
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        } else {
            GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun obtenerDatos() {
        val bundle = intent.extras?.getBundle("datos")
        val usuario = bundle?.getSerializable("usuario") as? User
        supportActionBar?.title = usuario?.nombre ?: "Sin nombre"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemCerrarSesion -> finish()
            R.id.itemInformacion -> {
                val dialogo = InfoDialog()
                dialogo.show(supportFragmentManager, null)
            }
            R.id.itemAccion1 -> { }
            R.id.itemAccion2 -> { }
        }
        return true
    }
}
