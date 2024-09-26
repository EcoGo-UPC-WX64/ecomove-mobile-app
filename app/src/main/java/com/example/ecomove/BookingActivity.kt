package com.example.ecomove

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ecomove.R
import java.util.Calendar

class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fechaInicio = findViewById<EditText>(R.id.fechaInicio)
        val btnFechaInicio = findViewById<ImageButton>(R.id.btnFechaInicio)
        val fechaFin = findViewById<EditText>(R.id.fechaFin)
        val btnFechaFin = findViewById<ImageButton>(R.id.btnFechaFin)
        val edtDistrito = findViewById<EditText>(R.id.edtDistrito)
        val btnComenzarReserva = findViewById<Button>(R.id.btnComenzarReserva)

        // Listener para seleccionar la fecha de inicio
        btnFechaInicio.setOnClickListener {
            showDatePickerDialog(fechaInicio)
        }

        // Listener para seleccionar la fecha de fin
        btnFechaFin.setOnClickListener {
            showDatePickerDialog(fechaFin)
        }

        // Botón para comenzar la reserva
        btnComenzarReserva.setOnClickListener {
            val distrito = edtDistrito.text.toString()
            val inicio = fechaInicio.text.toString()
            val fin = fechaFin.text.toString()

            if (inicio.isNotEmpty() && fin.isNotEmpty() && distrito.isNotEmpty()) {
                // Lógica para procesar la reserva
                Toast.makeText(this, "Reserva iniciada para el distrito $distrito", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_LONG).show()
            }

           val intent = Intent(this, RentVehicleActivity::class.java)
              startActivity(intent)
        }
    }

    // Función para mostrar el DatePickerDialog
    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "${selectedMonth + 1}/$selectedDay/$selectedYear"
                editText.setText(selectedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()

    }
}