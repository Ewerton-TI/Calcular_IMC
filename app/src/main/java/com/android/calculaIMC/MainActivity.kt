package com.android.calculaIMC

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners() {
        pesoEDT?.doOnTextChanged { text, _, _, _ ->
            titleTXT?.text = text
        }
        alturaEDT.doAfterTextChanged { text ->
            Toast.makeText(this, text.toString(), Toast.LENGTH_LONG).show()
        }
        calcularBTN.setOnClickListener {
            calcularIMC(pesoEDT.text.toString(), alturaEDT.text.toString())
        }
    }

    private fun  calcularIMC(pesoEDT: String, alturaEDT:String){
        val peso = pesoEDT.toFloatOrNull()
        val altura = alturaEDT.toFloatOrNull()

        if (peso != null && altura != null) {
            val imc = peso / (altura * altura)
            titleTXT.text = "Seu IMC é \n%.2f".format(imc)
            resultView.text = grauIMC(imc)
        }
    }

    private fun grauIMC(gImc: Float): String{
        return when(gImc){
            in 0.1..17.0 -> "Muito abaixo do peso."
            in 17.1..18.49 -> "Abaixo do peso."
            in 18.5..24.99 -> "Peso normal."
            in 25.0..29.99 ->  "Acima do peso."
            in 30.0..34.99 -> "Obesidade I."
            in 35.0..39.99 -> "Obesidade II(severa)."
            else -> "Obesidade III(mórbida)."
        }
    }
}