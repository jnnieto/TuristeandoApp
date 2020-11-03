package com.example.turistiandoapp

class Publicaciones{
    var nomActividad =""
    var municipio = ""
    var direccion = ""
    var descripcion = ""
    var costo = 0
    var nombreUser = ""
    var telefono = 0.0
    var imagen = ""

    constructor(nomActividad: String, municipio: String, direccion: String, descripcion: String, costo: Int, nombreUser: String, telefono: Double, imagen: String) {
        this.nomActividad = nomActividad
        this.municipio = municipio
        this.direccion = direccion
        this.descripcion = descripcion
        this.costo = costo
        this.nombreUser = nombreUser
        this.telefono = telefono
        this.imagen = imagen
    }
}