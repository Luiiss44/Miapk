package com.example.miapk.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inicio.adapter.ContactAdapter
import com.example.miapk.R
import com.example.miapk.data.DataSet
import com.example.miapk.databinding.ActivitySecondBinding
import com.example.miapk.ui.dialog.InfoDialog

class SecondActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySecondBinding
    private lateinit var contactAdapter: ContactAdapter

    //lista y contexto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        instancias()

        obtenerDatos()

    }

    private fun instancias() {
        contactAdapter = ContactAdapter(DataSet.lista, applicationContext)
        binding.recycler.adapter = contactAdapter
        if(resources.configuration.orientation == 1){
            binding.recycler.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        }else{
            binding.recycler.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            
        }

        
        
    }

    private fun obtenerDatos() {
        val bundle  = intent.extras!!.getBundle("datos")
        val usuario: com.example.miapk.model.User = bundle!!.getSerializable("usuario") as com.example.miapk.model.User
        supportActionBar?.title = usuario.nombre
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.itemCerrarSesion ->{finish()}
            R.id.itemInformacion ->{
                // escribir el codigo del cuadro de dialogo aqui
                // escribir el codigo del cuadro de dialogo en una clase
                val dialogo: InfoDialog = InfoDialog()
                dialogo.show(supportFragmentManager,null)
            }
            R.id.itemAccion1 ->{}
            R.id.itemAccion2 ->{}
        }

        return true
    }
}