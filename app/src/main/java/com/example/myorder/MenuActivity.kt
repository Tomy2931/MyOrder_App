package com.example.myorder

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    data class Comida(
        val nombre: String,
        val descripcion: String,
        val precio: Int,
        val imagenResId: Int
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val comidas = listOf(
            Comida("Hamburguesa", "Pan, carne y queso", 2500, R.drawable.hamburguesaimagen),
            Comida("Pizza", "Mozzarella y salsa de tomate", 3200, R.drawable.ic_launcher_foreground),
            Comida("Ensalada", "Lechuga, tomate y huevo", 1700, R.drawable.ic_launcher_foreground),
            Comida("Empanadas", "Jamón y queso o carne", 1800, R.drawable.ic_launcher_foreground),
            Comida("Ravioles", "Ravioles rellenos de ricota con tuco", 2000, R.drawable.ic_launcher_foreground),
            Comida("Super Pancho", "Super pancho con aderezos a elección", 1800, R.drawable.ic_launcher_foreground),
            Comida("Ñoquis", "Ñoquis de papa con tuco", 2000, R.drawable.ic_launcher_foreground),
            Comida("Choripan", "Chorizo de cerdo", 1700, R.drawable.ic_launcher_foreground),
            Comida("Milanesa Napolitana", "Milanesa de pollo con papas fritas", 4000, R.drawable.ic_launcher_foreground)
        )

        val container = findViewById<LinearLayout>(R.id.containerComidas)

        for (comida in comidas) {
            val view = LayoutInflater.from(this).inflate(R.layout.item_comida, container, false)

            val ivFoto = view.findViewById<ImageView>(R.id.ivFoto)
            val tvNombre = view.findViewById<TextView>(R.id.tvNombre)
            val tvDescripcion = view.findViewById<TextView>(R.id.tvDescripcion)
            val tvPrecio = view.findViewById<TextView>(R.id.tvPrecio)

            ivFoto.setImageResource(comida.imagenResId)
            tvNombre.text = comida.nombre
            tvDescripcion.text = comida.descripcion
            tvPrecio.text = "$${comida.precio}"

            container.addView(view)
        }
    }
}
