package com.example.turistiandoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class login_turisteando : AppCompatActivity() {

    lateinit var txtCorreoLogin: EditText
    lateinit var txtContraLogin: EditText

    lateinit var btn_Ingresar: Button
    lateinit var btn_OlvideContraseña: Button
    lateinit var btn_SinCuenta: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_turisteando)

        txtCorreoLogin = findViewById(R.id.PT_CorreoLogin)
        txtContraLogin = findViewById(R.id.PT_ContraseñaLogin)
        btn_Ingresar = findViewById(R.id.btn_SignInLogin)
        btn_OlvideContraseña = findViewById(R.id.btn_OlvideContraseña)
        btn_SinCuenta = findViewById(R.id.btn_SinCuenta)

        setup_login()
    }
    private fun setup_login(){
        title="INICIA SESIÓN"

        btn_Ingresar.setOnClickListener{
            if(txtCorreoLogin.text.isNotEmpty() && txtContraLogin.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(txtCorreoLogin.text.toString()
                ,txtContraLogin.text.toString()).addOnCompleteListener{
                    if(it.isSuccessful){
                        showHomeTuristeando()
                    }else{
                        showAlertLogin()
                    }
                }
            }
        }
    }
    private fun showAlertLogin(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage("Upps!!! Inicio de sesión inválido...")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }
    private fun showHomeTuristeando(){
        val homeIntent: Intent = Intent(this,MainAppTuristeando::class.java)
        startActivity(homeIntent)
        finish()
    }
}