package com.example.jjd_food_mobile.modelo;

public class comida {
  String direccion,id,impuesto,nombre,precio,telefono,total;
  public comida(){

  }

    public comida(String direccion, String id, String impuesto, String nombre, String precio, String telefono, String total) {
        this.direccion = direccion;
        this.id = id;
        this.impuesto = impuesto;
        this.nombre = nombre;
        this.precio = precio;
        this.telefono = telefono;
        this.total = total;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
