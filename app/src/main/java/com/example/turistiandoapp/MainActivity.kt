package com.example.turistiandoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.core.text.set
import androidx.core.view.get
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.cos

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var database = FirebaseDatabase.getInstance().reference
        val act = (findViewById(R.id.textNomActividad) as EditText).text
        val dire = (findViewById(R.id.textDirecccion) as EditText).text
        val desc = (findViewById(R.id.textDescripcion) as EditText).text
        val costo = (findViewById(R.id.textCosto) as EditText).text
        val btn = findViewById(R.id.btnPublicar) as Button
        var nomMun :String = ""
        var mun = (findViewById(R.id.spinner) as Spinner)

        mun.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                val prueba = ""
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                nomMun = parent?.getItemAtPosition(position) as String;
                //Toast.makeText(applicationContext,"$prueba2",Toast.LENGTH_LONG).show()
            }
        }
        btn.setOnClickListener{
            var numero = true
            val costo2 = costo.toString().toInt()
            if(TextUtils.isEmpty(act)){
                Toast.makeText(applicationContext,"Llena el campo de actividad",Toast.LENGTH_LONG).show()
            }else if(TextUtils.isEmpty(dire)){
                Toast.makeText(applicationContext,"Llena el campo de dirección",Toast.LENGTH_LONG).show()
            }else if (TextUtils.isEmpty(desc)){
                Toast.makeText(applicationContext,"Llena el campo de descripción",Toast.LENGTH_LONG).show()
            }else if(TextUtils.isEmpty(costo)){
                Toast.makeText(applicationContext,"Llena el campo de costo",Toast.LENGTH_LONG).show()
            }else if((TextUtils.isEmpty(dire))&&(TextUtils.isEmpty(act))&&(TextUtils.isEmpty(desc))&&(TextUtils.isEmpty(costo))){
                Toast.makeText(applicationContext,"Falta algún dato",Toast.LENGTH_LONG).show()
            }else{
                numero = costo.matches("-?\\d+(\\.\\d+)?".toRegex())
                if (numero)
                    database.child(act.toString()).setValue(Publicaciones(act.toString(),nomMun,dire.toString(),desc.toString(),costo2))
                else
                    Toast.makeText(applicationContext,"El costo debe ser un dato de tipo numerico",Toast.LENGTH_LONG).show()
            }
        }
    }
}