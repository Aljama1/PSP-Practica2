/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Cliente;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

/**
 *
 * @author manue
 */
public class MiLista extends ArrayList<Cliente>{
    private int actual;
    
    public MiLista(){
        super();
    }
    public void cargarClientes() {
    this.clear(); // limpia la lista si ya había clientes

    this.add(new Cliente(1, "Ana", new GregorianCalendar(1990, Calendar.JUNE, 15), 150.0, 500.0));
    this.add(new Cliente(2, "Luis", new GregorianCalendar(1985, Calendar.JULY, 22), 50.0, 200.0));
    this.add(new Cliente(3, "Marta", new GregorianCalendar(1998, Calendar.DECEMBER, 10), 400.0, 1000.0));
    this.add(new Cliente(4, "Carlos", new GregorianCalendar(2001, Calendar.MARCH, 5), 0.0, 300.0));

    actual = 0; // muestra el primero al iniciar
    }


    //Indicar si la coleccion esta vacia
    public boolean esVacia(){
         return this.isEmpty();
    }
    
    
    public void avanzar() {
    if (actual < size() - 1) {
        actual++;
    }
}
    
public void retroceder() {
    if (actual > 0) {
        actual--;
    }
}
    
    public void irPrimero(){
        if(!isEmpty()) actual = 0;
    }
    
    public void irUltimo(){
        if(!isEmpty()) actual= size()-1;
    }
    
    public boolean esPrimero(){
        return !isEmpty() && actual == 0;
        //Si no esta vacia y a su vez el contador es 0 devolvera true.
    }
    public boolean esUltimo(){
        return !isEmpty() && actual == size() -1;
        //Si no esta vacia y a su vez el contador es 0 devolvera true.
    }
    
    //Podemos añadir una excepcion en el caso de que el objeto sea null
    public void añadirCliente(Cliente c){
        if( c == null)throw  new IllegalArgumentException("El Cliente no puede ser añadido.");
        this.add(c);
        this.actual = this.size() - 1;
    }
    
    public void borrarCliente(){
        this.remove(actual);
        //ajustamos el indice
        if(this.isEmpty()){
            actual = -1;
        }else if(actual >= this.size()){
            actual = this.size()-1;
            //En caso de ser el ultimo el que sea borrado, el indice actual pasara al que era el penultimo
        }
    }
    
    public Cliente obtenerClienteActual(){
        if (!isEmpty() && actual >= 0 && actual < size()) {
        return this.get(actual);
    } else {
        return null; // lista vacía o índice inválido
    }
    }
    
    public void ordenarNumero(){
        this.sort(new Comparator<Cliente>(){
            @Override
            public int compare(Cliente o1, Cliente o2) {
                return Integer.compare(o1.getNumero(), o2.getNumero());
            }
            
        });
    }
    
    public boolean esFechaValida(GregorianCalendar fecha){
        if(fecha == null) return false;
        int año = fecha.get(Calendar.YEAR);
        if(año < 1900 || año > 2015)return false;
        //Comprobamos si es una fecha valida, por ejemplo que febrero no tenga 30 dias.
        GregorianCalendar fechaPrueba = new GregorianCalendar(
        año,
        fecha.get(Calendar.MONTH),
        fecha.get(Calendar.DAY_OF_MONTH)
        );
        return fechaPrueba.get(Calendar.DAY_OF_MONTH)==fecha.get(Calendar.DAY_OF_MONTH);
    }
    
    public void borrarActual() {
    if (!isEmpty()) {
        this.remove(actual);
        if (actual >= this.size()) {
            actual = this.size() - 1;
        }
    }
}
    
}
