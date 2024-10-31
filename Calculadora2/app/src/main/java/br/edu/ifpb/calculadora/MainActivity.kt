package br.edu.ifpb.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bt0 : Button = findViewById(
            R.id.bt0
        )

        val bt1 = findViewById<Button>(
            R.id.bt1
        )
    }
}