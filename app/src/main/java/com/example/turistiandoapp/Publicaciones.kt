package com.example.turistiandoapp

class Publicaciones{
    var nomActividad =""
    var municipio = ""
    var direccion = ""
    var descripcion = ""
    var costo = 0

    constructor(nomActividad: String, municipio: String, direccion: String, descripcion: String, costo: Int) {
        this.nomActividad = nomActividad
        this.municipio = municipio
        this.direccion = direccion
        this.descripcion = descripcion
        this.costo = costo
    }


}