/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.GregorianCalendar;

/**
 *
 * @author manue
 */
public class Cliente {
    private int numero;
    private String nombre;
    private GregorianCalendar fechaNac;
    private double saldo;
    private double saldoMax;
    

    public Cliente(int numero, String nombre, GregorianCalendar fechaNac, double saldo, double saldoMax) {
        this.numero = numero;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.saldoMax = saldoMax;
        setSaldo(saldo);
    }

    public int getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    public GregorianCalendar getFechaNac() {
        return fechaNac;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getSaldoMax() {
        return saldoMax;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaNac(GregorianCalendar fechaNac) {
        this.fechaNac = fechaNac;
    }

    public void setSaldo(double saldo) {
        if (saldo > this.saldoMax) {
        throw new IllegalArgumentException(
            "El saldo (" + saldo + ") no puede ser mayor que el saldo máximo (" + this.saldoMax + ")."
        );
    }
    this.saldo = saldo;
    }

    public void setSaldoMax(double saldoMax) {
        this.saldoMax = saldoMax;
        if(this.saldo>this.saldoMax){
            this.saldo=this.saldoMax;
        }
    }

    @Override
    public String toString() {
        return "Cliente{" + "numero=" + numero + ", nombre=" + nombre + ", fechaNac=" + fechaNac + ", saldo=" + saldo + ", saldoMax=" + saldoMax + '}';
    }
    
    
    
}
