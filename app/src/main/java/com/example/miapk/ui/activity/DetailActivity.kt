package com.example.miapk.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.miapk.R
import com.example.miapk.databinding.ActivityDetailBinding
import com.example.miapk.model.ContactJSON

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperamos el objeto enviado por el Intent
        val contact = intent.getSerializableExtra("contact") as? ContactJSON

        contact?.let { c ->
            // Cargamos la imagen
            Glide.with(this)
                .load(c.image)
                .placeholder(R.drawable.base)
                .into(binding.imageViewDetail)

            binding.textViewId.text = "ID: ${c.id}"
            binding.textViewNombre.text = "Nombre: ${c.firstName} ${c.lastName}"
            binding.textViewMaidenName.text = "Nombre de soltera: ${c.maidenName}"
            binding.textViewAge.text = "Edad: ${c.age}"
            binding.textViewGender.text = "Género: ${c.gender}"
            binding.textViewEmail.text = "Email: ${c.email}"
            binding.textViewPhone.text = "Teléfono: ${c.phone}"
            binding.textViewUsername.text = "Username: ${c.username}"
            binding.textViewPassword.text = "Password: ${c.password}"
            binding.textViewBirthDate.text = "Fecha de nacimiento: ${c.birthDate}"
            binding.textViewBloodGroup.text = "Grupo sanguíneo: ${c.bloodGroup}"
            binding.textViewHeight.text = "Altura: ${c.height}"
            binding.textViewWeight.text = "Peso: ${c.weight}"
            binding.textViewEyeColor.text = "Color de ojos: ${c.eyeColor}"
            binding.textViewHair.text = "Cabello: Color ${c.hair?.color}, Tipo ${c.hair?.type}"
            binding.textViewIp.text = "IP: ${c.ip}"
            binding.textViewAddress.text = "Dirección: ${c.address?.address}, ${c.address?.city}, ${c.address?.state}"
            binding.textViewCoordinates.text = "Coordenadas: ${c.address?.coordinates?.lat}, ${c.address?.coordinates?.lng}"
            binding.textViewPostalCode.text = "Código postal: ${c.address?.postalCode}"
            binding.textViewCountry.text = "País: ${c.address?.country}"
            binding.textViewUniversity.text = "Universidad: ${c.university}"
            binding.textViewBank.text = "Banco: ${c.bank?.cardNumber} - ${c.bank?.cardType} - ${c.bank?.currency}"
            binding.textViewCompany.text = "Empresa: ${c.company?.name} - ${c.company?.department} - ${c.company?.title}"
            binding.textViewEin.text = "EIN: ${c.ein}"
            binding.textViewSsn.text = "SSN: ${c.ssn}"
            binding.textViewUserAgent.text = "User Agent: ${c.userAgent}"
            binding.textViewCrypto.text = "Crypto: ${c.crypto?.coin}, ${c.crypto?.wallet}, ${c.crypto?.network}"
            binding.textViewRole.text = "Role: ${c.role}"
        }
    }
}


