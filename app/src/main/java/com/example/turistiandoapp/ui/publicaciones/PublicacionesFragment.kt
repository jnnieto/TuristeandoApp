package com.example.turistiandoapp.ui.publicaciones

import android.app.Activity
import android.app.ProgressDialog
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.net.toFile
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.turistiandoapp.Actividades
import com.example.turistiandoapp.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.File
import java.text.SimpleDateFormat
import java.util.jar.Manifest
import android.net.Uri as Uri1

class PublicacionesFragment : Fragment() {

    private lateinit var publicacionesViewModel: PublicacionesViewModel
    lateinit var filePath: Uri1
    private  val REQUEST_GALERY = 1001
    /*internal var storage: FirebaseStorage ?= null
    internal var storageReference: StorageReference ?=null*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        publicacionesViewModel =
            ViewModelProvider(this).get(PublicacionesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_publicaciones, container, false)
        publicacionesViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        var database = FirebaseDatabase.getInstance().reference
        val act = (root.findViewById<View>(R.id.textNomActividad) as EditText)
        val dire = (root.findViewById<View>(R.id.textDirecccion) as EditText)
        val desc = (root.findViewById<View>(R.id.textDescripcion) as EditText)
        val costo = (root.findViewById<View>(R.id.textCosto) as EditText)
        val usuario = (root.findViewById<View>(R.id.textUser) as EditText)
        val tel = (root.findViewById<View>(R.id.textTelefono) as EditText)
        val imagePhoto = root.findViewById<View>(R.id.imageViewPhoto) as ImageView
        val btn = root.findViewById<View>(R.id.btnPublicar) as Button
        val cargar = root.findViewById<View>(R.id.btnCargarImagen) as Button
        var mostarImaen = root.findViewById<View>(R.id.imageViewMostrar) as ImageView

        var storage = FirebaseStorage.getInstance()

        //var nose = Firebase.fit
        //databaseReference.orderByChild('_searchLastName') .startAt(queryText) .endAt(queryText+"\uf8ff") .once("value")

        var cargarImagen = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/turisteandoprueba1.appspot.com/o/image%2FPRUEBA%20CARGAR%20IMAGENJWJS.jpg?alt=media&token=e4987fe4-0775-4c7a-a2af-8e9d433068a4")
        //storage.getReferenceFromUrl("gs://turisteandoprueba1.appspot.com/image/").child("NOSEHPMI CASAJHSH.jpg")
        var file = File.createTempFile("image","jpg")

        cargarImagen.getFile(file).addOnSuccessListener {
            var bitpath = BitmapFactory.decodeFile(file.absolutePath)
            mostarImaen.setImageBitmap(bitpath)
        }
        var nomMun :String = ""
        val mun = (root.findViewById<View>(R.id.spinner) as Spinner)
        mun.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                val prueba = ""
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                nomMun = parent?.getItemAtPosition(position) as String;
                //Toast.makeText(applicationContext,"$prueba2",Toast.LENGTH_LONG).show()
            }
        }
        cargar.setOnClickListener{
            cargarFotoImagen()

        }

        btn.setOnClickListener{
            validarFomularioSubirDatos(act, dire, desc, costo, imagePhoto, nomMun,usuario,tel)
        }
        return root
    }



    //------ El siguiente metodo permite ver las validaciones del fomulario y subir los datos--

    private fun validarFomularioSubirDatos(act:EditText,dire :EditText, desc: EditText,costo: EditText, imagePhoto:ImageView,nomMun: String,usuario: EditText,telefono: EditText){
        var numero = true
        var telefonoVe = true
        if(TextUtils.isEmpty(act.text)){
            Toast.makeText(activity,"Llena el campo de actividad",Toast.LENGTH_LONG).show()
        }else if(TextUtils.isEmpty(dire.text)){
            Toast.makeText(activity,"Llena el campo de dirección",Toast.LENGTH_LONG).show()
        }else if (TextUtils.isEmpty(desc.text)){
            Toast.makeText(activity,"Llena el campo de descripción",Toast.LENGTH_LONG).show()
        }else if(costo.text.toString() == ""|| costo.text.toString().toInt() < 0){
            Toast.makeText(activity,"Llena el campo de costo o debe ser mayor a 0",Toast.LENGTH_LONG).show()
        }else if(nomMun.equals("Seleccione")){
            Toast.makeText(activity,"Seleccione un Municipio",Toast.LENGTH_LONG).show()
        }else if(TextUtils.isEmpty(usuario.text)){
            Toast.makeText(activity,"Llena el campo de tu nombre",Toast.LENGTH_LONG).show()
        }else if(TextUtils.isEmpty(telefono.text)){
            Toast.makeText(activity,"Llena el campo de telefono",Toast.LENGTH_LONG).show()
        }else if((TextUtils.isEmpty(dire.text))&&(TextUtils.isEmpty(act.text))&&(TextUtils.isEmpty(desc.text))){//(TextUtils.isEmpty(dire.text))&&(TextUtils.isEmpty(act.text))&&(TextUtils.isEmpty(desc.text))
            Toast.makeText(activity,"Escoge una imagen",Toast.LENGTH_LONG).show()
        }else{
            val costo2 = costo.text.toString().toInt()
            val celular = telefono.text.toString().toDouble()
            numero = costo.text.matches("-?\\d+(\\.\\d+)?".toRegex())
            telefonoVe = telefono.text.matches("-?\\d+(\\.\\d+)?".toRegex())
            if (numero|| telefonoVe){
                subirImagen(act.text.toString().toUpperCase(),dire.text.toString().toUpperCase(),desc.text.toString().toUpperCase(),costo2,nomMun.toUpperCase(),usuario.text.toString().toUpperCase(),celular)
                act.setText("")
                dire.setText("")
                desc.setText("")
                costo.setText("")
                usuario.setText("")
                telefono.setText("")
                usuario.setText("")
                imagePhoto.setImageBitmap(null)



            }
            else{
                Toast.makeText(activity,"Tiene que ser un numero el (Costo)",Toast.LENGTH_LONG).show()
            }
        }
    }

    //------ Los siguientes metodos son usando para cargar la imagen y subir a el servidor --
    //Metodo que permite subir la imagen
    private fun subirImagen(act:String,dire :String, desc: String,costo: Int,nomMun: String,usuario: String,telefono: Double){
        var downloadUri = ""
        var database = FirebaseDatabase.getInstance().reference
        if(filePath != null){
            var pd = ProgressDialog(activity)
            pd.setTitle("Subiendo Imagen")
            pd.show()
            val nomImagen = act+usuario
            var imageRef = FirebaseStorage.getInstance().reference.child("image/"+nomImagen+".jpg")

            var uploadTask = imageRef.putFile(filePath)
                .addOnSuccessListener {p0 ->
                    //pd.dismiss()
                }
                .addOnFailureListener{p0 ->
                    pd.dismiss()
                    Toast.makeText(activity,p0.message ,Toast.LENGTH_LONG).show()
                }
                .addOnProgressListener {p0 ->
                    var progress =(100.0 * p0.bytesTransferred) / p0.totalByteCount
                    pd.setMessage("Subido ${progress.toInt()}%")
                }
            val urlTask = uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                imageRef.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var downloadUrl = task.result.toString()
                    downloadUri = downloadUrl
                    database.child("Actividades").child(act).setValue(Actividades(act,nomMun,dire,desc,costo,usuario,telefono,downloadUri))
                    Toast.makeText(activity,"Publicacion exitosa!",Toast.LENGTH_LONG).show()
                } else {
                    // Handle failures
                    // ...
                }
            }
        }
        }
    //Metodo que permite cargar imagen desde la galeria
    private fun cargarFotoImagen(){
        var i = Intent()
        i.setType("image/*")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(i,"Elegir Imagen"),111)
    }
    //metodo que se dispara cuando se necesita cargar la imagen que se escogio desde la galeria
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==111&& resultCode== Activity.RESULT_OK&&data != null){

            filePath = data.data!!
            val imagePhoto = activity?.findViewById<View>(R.id.imageViewPhoto) as ImageView
            var bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, filePath)
            imagePhoto.setImageBitmap(bitmap)

        }
    }

    /*var btn_pub: Button?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_pub=view.findViewById(R.id.btnPublicar) as Button
        btn_pub!!.setOnClickListener{
            it.findNavController().navigate(R.id.action_nav_publicaciones_to_nav_actividades)
        }
    }*/

}