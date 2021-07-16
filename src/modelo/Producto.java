package modelo;

import java.util.Objects;

public class Producto {

//    @Override
//    public int hashCode() {
//        int hash = 7;
//        return hash;
//    }
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Producto other = (Producto) obj;
//        if (this.edad != other.edad) {
//            return false;
//        }
//        if (!Objects.equals(this.nombre, other.nombre)) {
//            return false;
//        }
//        if (!Objects.equals(this.apellido, other.apellido)) {
//            return false;
//        }
//        return true;
//    }
    private String codigo;
    private String nombre;
    private double precio;

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    private double subTotal;

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    private double cantidad;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Producto(String codigo, String nombre, double precio, double cantidad, double subTotal) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

}
