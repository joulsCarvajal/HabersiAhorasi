//Esta clase será la responsable de todo el dibujo
package com.example.dibujando

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dibujando.MainActivity.Companion.paintBrush
import com.example.dibujando.MainActivity.Companion.path


//Ponemos el view, sin embargo el constructor no viene en las ayudas de a.s., entonces toca ir
//a google y buscar el "contructor of view in android studio kotlin"

class PaintView: View {

    var params: ViewGroup.LayoutParams? = null // Con esto sabe cual es la altura y el ancho de nuestro lienzo con respecto al diseño principal

    companion object{
        var pathList = ArrayList<Path>() //El path list sirve como un contenedor de las lineas que haremos
        var colorList = ArrayDeque<Int>()
        var currentBrush = Color.BLACK
    }

    constructor(context: Context) : this(context, null){
        init()
    }
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0){
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    //Inicializamos la brocha o el pincel o laìz o como quiera llamarse
    private fun init(){
        paintBrush.isAntiAlias = true //Permite que la textura sea suave o rigida
        paintBrush.color = currentBrush
        paintBrush.style = Paint.Style.STROKE
        paintBrush.strokeJoin = Paint.Join.ROUND //Con este finalizamos el path del dibujo
        paintBrush.strokeWidth = 8f

        params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    //Inicializamos el registro del movimiento del dedo sobr la pantalla para get de la información
    // Obtendremos las coordenadas x y y

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var x = event.x
        var y = event.y

        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x,y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x,y)
                pathList.add(path)
                colorList.add(currentBrush)
            }
            else -> return false
        }
        postInvalidate()
        return false
    }

    override fun onDraw(canvas: Canvas) {
        val textA = findViewById<TextView>(R.id.textAltura)
        val textAn = findViewById<TextView>(R.id.textAncho)
        //var ancho = canvas.width
        //var altito = canvas.height

        //textA.height
        //textAn.width

        for(i in pathList.indices){
            paintBrush.setColor(colorList[i])
            canvas.drawPath(pathList[i], paintBrush)
            textA.height = canvas.height
            textAn.width = canvas.width

            invalidate()
        }
    }
}