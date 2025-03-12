package com.example.inicio.adapter

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.miapk.R
import com.example.miapk.model.ContactJSON
import com.example.miapk.ui.activity.DetailActivity

class ContactAdapter(private val lista: MutableList<ContactJSON>, private val context: Context) :
    RecyclerView.Adapter<ContactAdapter.MyHolder>() {

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.imageCard)
        val toolbar: Toolbar = itemView.findViewById(R.id.toolbarCard)
        val textPhone: TextView = itemView.findViewById(R.id.textCard)

        init {
            toolbar.inflateMenu(R.menu.contact_menu)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val vista = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
        return MyHolder(vista)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val contact = lista[position]

        holder.toolbar.title = "${contact.firstName} ${contact.lastName}"
        holder.textPhone.text = contact.phone ?: ""
        Glide.with(context)
            .load(contact.image)
            .placeholder(R.drawable.base)
            .into(holder.imagen)

        // Configuramos el listener para el menú de cada toolbar
        holder.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menuContactLlamar -> {
                    val phone = contact.phone
                    if (!phone.isNullOrEmpty()) {
                        val callIntent = Intent(Intent.ACTION_CALL).apply {
                            data = Uri.parse("tel:$phone")
                        }
                        // Verificamos si se ha concedido el permiso CALL_PHONE
                        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                            == PackageManager.PERMISSION_GRANTED
                        ) {
                            context.startActivity(callIntent)
                        } else {
                            Toast.makeText(context, "Permiso de llamada no concedido", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Número no disponible", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                R.id.menuContactDetalle -> {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra("contact", contact)
                    }
                    context.startActivity(intent)
                    true
                }
                R.id.menuContactEliminar -> {
                    // Se obtiene la posición actual del holder para eliminar la carta
                    val pos = holder.adapterPosition
                    if (pos != RecyclerView.NO_POSITION) {
                        removeContact(pos)
                    }
                    true
                }
                else -> false
            }
        }
    }

    fun addConctact(contact: ContactJSON) {
        lista.add(contact)
        notifyItemInserted(lista.size - 1)
    }

    private fun removeContact(position: Int) {
        // Se elimina el contacto de la lista interna para que no se muestre en pantalla.
        lista.removeAt(position)
        notifyItemRemoved(position)
    }
}
