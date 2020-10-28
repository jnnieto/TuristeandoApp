package com.example.turistiandoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class registro_turisteando : AppCompatActivity() {

    private val GOOGLE_SIGN_IN = 100
    lateinit var txtCorreo: EditText
    lateinit var txtContraseña: EditText
    lateinit var txtValidarContraseña: EditText

    lateinit var btn_Registrarme: Button
    lateinit var btn_RegistroGoogle: Button
    lateinit var btn_CuentaExistente: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_turisteando)

        txtCorreo=findViewById(R.id.PT_Correo)
        txtContraseña=findViewById(R.id.PT_Contraseña)
        txtValidarContraseña=findViewById(R.id.PT_ValidarContra)
        btn_Registrarme=findViewById(R.id.btn_Registrarme)
        btn_RegistroGoogle=findViewById(R.id.btn_RegistroGoogle)
        btn_CuentaExistente=findViewById(R.id.btn_cuentayacreada)

        setup()
    }

    private fun setup(){
        title="REGISTRO DE USUARIO"

        btn_Registrarme.setOnClickListener{

            if(txtContraseña.text.toString()!=txtValidarContraseña.text.toString()){
                showAlert2()
            }

            if(txtCorreo.text.isNotEmpty() && txtContraseña.text.isNotEmpty()){
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(txtCorreo.text.toString(),
                        txtContraseña.text.toString()).addOnCompleteListener{
                            if(it.isSuccessful){
                                showLogin()
                            }else{
                                showAlert()
                            }
                        }
                }
        }

        btn_RegistroGoogle.setOnClickListener{
            val googleconfi=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googlecliente= GoogleSignIn.getClient(this,googleconfi)
            googlecliente.signOut()
            startActivityForResult(googlecliente.signInIntent, GOOGLE_SIGN_IN)
        }

        btn_CuentaExistente.setOnClickListener{
            showLogin()
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage("Se ha producido un error en el registro de usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog=builder.create()
        dialog.show()
    }
    private fun showAlert2(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage("Upps! las contraseñas no coinciden...")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog=builder.create()
        dialog.show()
    }
    private fun showLogin(){
        val loginIntent:Intent=Intent(this,login_turisteando::class.java)
        startActivity(loginIntent)
        finish()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==GOOGLE_SIGN_IN){
            val task=GoogleSignIn.getSignedInAccountFromIntent(data)
            try {

                val account = task.getResult(ApiException::class.java)

                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener() {
                            if (it.isSuccessful) {
                                showLogin()
                            } else {
                                showAlert()
                            }
                        }
                }
            } catch(e: ApiException){
                    showAlert()
            }
        }
    }
}


