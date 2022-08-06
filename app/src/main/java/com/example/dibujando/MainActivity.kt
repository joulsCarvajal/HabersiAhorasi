package com.example.dibujando

import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.example.dibujando.PaintView.Companion.colorList
import com.example.dibujando.PaintView.Companion.currentBrush
import com.example.dibujando.PaintView.Companion.pathList
import kotlin.io.path.Path

class MainActivity : AppCompatActivity() {

    //Necesitaremos dos cosas importantes para dibujar, el path y el actual paint
    //El companion object es lo que conocimos en Java como public static, que nos permitirá llamar
    //las variables desde cualquier parte del código como public.

    companion object{
        var path = android.graphics.Path() //De android graphics no de Java
        var paintBrush = Paint() //De android graphics
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide() // Esto permite que se oculte el nombre de la app

        //Inicializamos todos nuestros views que tenemos en nuestro activity_main.xml
        val redBtn = findViewById<ImageButton>(R.id.redColor)
        val blueBtn = findViewById<ImageButton>(R.id.blueColor)
        val blackBtn = findViewById<ImageButton>(R.id.blackColor)
        val whiteBtn = findViewById<ImageButton>(R.id.whiteColor)
        val sizeBtn = findViewById<ImageButton>(R.id.sizeLine)

        redBtn.setOnClickListener {
            Toast.makeText(this,"Rojo", Toast.LENGTH_SHORT).show()
            paintBrush.color = Color.RED
            currentColor(paintBrush.color)
        }

        blueBtn.setOnClickListener {
            Toast.makeText(this,"Azul", Toast.LENGTH_SHORT).show()
            paintBrush.color = Color.BLUE
            currentColor(paintBrush.color)
        }

        blackBtn.setOnClickListener {
            Toast.makeText(this,"Negro", Toast.LENGTH_SHORT).show()
            paintBrush.color = Color.BLACK
            currentColor(paintBrush.color)
        }

        whiteBtn.setOnClickListener {
            Toast.makeText(this,"Borrar", Toast.LENGTH_SHORT).show()
            pathList.clear()
            colorList.clear()
            path.reset()
        }

        sizeBtn.setOnClickListener {
            Toast.makeText(this, "NO MAMEES", Toast.LENGTH_LONG).show()
            paintBrush.color = Color.GRAY
            currentColor(paintBrush.color)
        }
    }

    private fun currentColor(color: Int){
        currentBrush = color
        path = android.graphics.Path()
    }
}