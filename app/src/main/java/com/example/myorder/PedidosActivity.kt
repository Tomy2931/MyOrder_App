package com.example.myorder

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PedidosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos)

        val pedidosLayout = findViewById<LinearLayout>(R.id.pedidosLayout)

        val nombres = intent.getStringArrayListExtra("nombres") ?: arrayListOf()
        val precios = intent.getIntegerArrayListExtra("precios") ?: arrayListOf()

        for (i in nombres.indices) {
            val tvPedido = TextView(this)
            tvPedido.text = "${nombres[i]} - $${precios[i]}"
            tvPedido.textSize = 18f
            tvPedido.setPadding(20, 10, 20, 10)
            pedidosLayout.addView(tvPedido)
        }

        val total = precios.sum()
        val tvTotal = TextView(this)
        tvTotal.text = "\nTotal: $$total"
        tvTotal.textSize = 20f
        tvTotal.setPadding(20, 40, 20, 20)
        pedidosLayout.addView(tvTotal)
    }
}
