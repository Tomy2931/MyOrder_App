package com.example.myorder

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MenuActivity : AppCompatActivity() {

    data class Comida(
        val nombre: String,
        val descripcion: String,
        val precio: Int,
        val imagenResId: Int
    )

    private val carrito = mutableListOf<Comida>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Menú"

        // Lista de comidas
        val comidas = listOf(
            Comida("Hamburguesa", "Pan, carne y queso", 2500, R.drawable.hamburguesaimagen),
            Comida("Pizza", "Mozzarella y salsa de tomate", 3200, R.drawable.ic_launcher_foreground),
            Comida("Ensalada", "Lechuga, tomate y huevo", 1700, R.drawable.ic_launcher_foreground),
            Comida("Empanadas", "Jamón y queso o carne", 1800, R.drawable.ic_launcher_foreground),
            Comida("Ravioles", "Rellenos de ricota con tuco", 2000, R.drawable.ic_launcher_foreground),
            Comida("Super Pancho", "Con aderezos a elección", 1800, R.drawable.ic_launcher_foreground),
            Comida("Ñoquis", "Ñoquis de papa con tuco", 2000, R.drawable.ic_launcher_foreground),
            Comida("Choripan", "Chorizo de cerdo", 1700, R.drawable.ic_launcher_foreground),
            Comida("Milanesa Napolitana", "Con papas fritas", 4000, R.drawable.ic_launcher_foreground)
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

            view.setOnClickListener { mostrarDialogoAgregar(comida) }

            container.addView(view)
        }
    }

    private fun mostrarDialogoAgregar(comida: Comida) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Agregar al carrito")
        builder.setMessage("¿Agregar \"${comida.nombre}\" al carrito?")

        builder.setPositiveButton("Sí") { _, _ ->
            carrito.add(comida)
            Toast.makeText(this, "${comida.nombre} agregado al carrito", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No", null)
        builder.show()
    }

    private fun mostrarCarrito() {
        if (carrito.isEmpty()) {
            Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show()
            return
        }

        val mensaje = carrito.joinToString("\n") { "${it.nombre} - $${it.precio}" }
        val total = carrito.sumOf { it.precio }

        AlertDialog.Builder(this)
            .setTitle("Carrito de compras")
            .setMessage("$mensaje\n\nTotal: $$total")
            .setPositiveButton("Finalizar pedido") { _, _ ->
                finalizarPedido()
            }
            .setNeutralButton("Vaciar carrito") { _, _ ->
                carrito.clear()
                Toast.makeText(this, "Carrito vaciado", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cerrar", null)
            .show()
    }


    private fun finalizarPedido() {
        val intent = Intent(this, PedidosActivity::class.java)

        val nombres = carrito.map { it.nombre }
        val precios = carrito.map { it.precio }

        intent.putStringArrayListExtra("nombres", ArrayList(nombres))
        intent.putIntegerArrayListExtra("precios", ArrayList(precios))

        startActivity(intent)

        carrito.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.carrito_content, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_carrito -> {
                mostrarCarrito()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
