package com.example.jjd_food_mobile.platos;

public class Plato {
        private String nombre;
        private double precio;

        public Plato(String nombre, double precio) {
            this.nombre = nombre;
            this.precio = precio;
        }

        public String getNombre() {
            return nombre;
        }

        public double getPrecio() {
            return precio;
        }

}
