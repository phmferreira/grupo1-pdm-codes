package br.edu.ifpb.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.NumberFormatException

private const val OPERACAO_PENDENTE_CHAVE = "OperancaoPendente"
private const val OPERANDO1_CHAVE = "Operando1"
private const val OPERANDO1_STATUS_CHAVE = "Operando1Status"

class MainActivity : AppCompatActivity() {

    private lateinit var resultado : EditText
    private lateinit var novoNumero : EditText
    private lateinit var displayOperacao : TextView

    // variáveis para os cálculos
    private var operando1 : Double? = null
    private var operando2 : Double = 0.0
    private var operacaoPendente = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultado = findViewById(R.id.etnResultado)
        novoNumero = findViewById(R.id.etnNovoNumero)
        displayOperacao = findViewById(R.id.txtOperacao)

        // botoes para digitar o numero
        val bt0 : Button = findViewById(R.id.bt0)
        val bt1 : Button = findViewById(R.id.bt1)
        val bt2 : Button = findViewById(R.id.bt2)
        val bt3 : Button = findViewById(R.id.bt3)
        val bt4 : Button = findViewById(R.id.bt4)
        val bt5 : Button = findViewById(R.id.bt5)
        val bt6 : Button = findViewById(R.id.bt6)
        val bt7 : Button = findViewById(R.id.bt7)
        val bt8 : Button = findViewById(R.id.bt8)
        val bt9 : Button = findViewById(R.id.bt9)
        val btPonto : Button = findViewById(R.id.btPonto)

        val apendarTexto = View.OnClickListener {
            param ->
            // casting:
            // promovendo um tipo objeto para um Button
            val botao = (param as Button)
            novoNumero.append(botao.text)
        }

        bt0.setOnClickListener(apendarTexto)
        bt1.setOnClickListener(apendarTexto)
        bt2.setOnClickListener(apendarTexto)
        bt3.setOnClickListener(apendarTexto)
        bt4.setOnClickListener(apendarTexto)
        bt5.setOnClickListener(apendarTexto)
        bt6.setOnClickListener(apendarTexto)
        bt7.setOnClickListener(apendarTexto)
        bt8.setOnClickListener(apendarTexto)
        bt9.setOnClickListener(apendarTexto)
        btPonto.setOnClickListener(apendarTexto)


        // botões de operação
        val btSoma : Button = findViewById(R.id.btSoma)
        val btSubtracao : Button = findViewById(R.id.btSubtracao)
        val btMultiplicao : Button = findViewById(R.id.btMultiplicao)
        val btDivisao : Button = findViewById(R.id.btDivisao)
        val btResultado : Button = findViewById(R.id.btResultado)

        val operacaoListener = View.OnClickListener {
            botao ->
            val operacao = (botao as Button).text.toString()
            try {
                val valor = novoNumero.text.toString()
                realizarCalculo(valor)
            } catch (e: NumberFormatException){
                novoNumero.setText("")
            }

            operacaoPendente = operacao
            displayOperacao.text = operacaoPendente
        }
        btSoma.setOnClickListener(operacaoListener)
        btSubtracao.setOnClickListener(operacaoListener)
        btMultiplicao.setOnClickListener(operacaoListener)
        btDivisao.setOnClickListener(operacaoListener)
        btResultado.setOnClickListener(operacaoListener)

    }

    private fun realizarCalculo(valor: String){
        if (operando1 == null){
            operando1 = valor.toDouble()
        } else {
            operando2 = valor.toDouble()

            when(operacaoPendente){
                "=" -> operando1 = operando2
                "+" -> operando1 = operando1!! + operando2
                "-" -> operando1 = operando1!! - operando2
                "*" -> operando1 = operando1!! * operando2
                "/" -> if(operando2 == 0.0){
                        operando1 = Double.NaN
                    } else {
                        operando1 = operando1!! / operando2
                    }
            }
        }
        resultado.setText(operando1.toString())
        novoNumero.setText("")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(operando1 != null){
            outState.putDouble(OPERANDO1_CHAVE, operando1!!)
            outState.putBoolean(OPERANDO1_STATUS_CHAVE, true)
        }
        outState.putString(OPERACAO_PENDENTE_CHAVE, operacaoPendente)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if(savedInstanceState.getBoolean(OPERANDO1_STATUS_CHAVE)){
            operando1 =
                savedInstanceState.getDouble(OPERANDO1_CHAVE)
        }
        operacaoPendente = savedInstanceState.getString(
            OPERACAO_PENDENTE_CHAVE
        ).toString()
        displayOperacao.text = operacaoPendente
    }
}