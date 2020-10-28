package com.example.turistiandoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class InicioTuristeando : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val btn_Registro: Button
        var btn_Ingreso: Button

        setTheme(R.style.Theme_TuristiandoApp)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_turisteando)

        btn_Registro=findViewById(R.id.BtnRegister)
        btn_Ingreso=findViewById(R.id.BtnLogin)

        btn_Registro.setOnClickListener{
            val intent:Intent=Intent(this,registro_turisteando::class.java)
            startActivity(intent)
            finish()
        }

        btn_Ingreso.setOnClickListener{
            val intent:Intent=Intent(this,login_turisteando::class.java)
            startActivity(intent)
            finish()
        }
    }
}