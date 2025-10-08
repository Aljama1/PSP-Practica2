
package Vista;

import Controlador.MiLista;
import Modelo.Cliente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.GregorianCalendar;
public class App extends JFrame{
    private MiLista clientes;
    
    //etiquetas
    private JLabel numLabel;
    private JLabel nombreLabel;
    private JLabel fechaLabel;
    private JLabel saldoLabel;
    private JLabel saldoMaxLabel;
    private JLabel errorLabel;//Para que aparezca en rojo el error
    
    //Campos en los que irá el texto
    private JTextField numField;
    private JTextField nombreField;
    private JTextField diaField, mesField, añoField;//Un campo para cada numero del calendario
    private JTextField saldoField;
    private JTextField saldoMaxField;
    
    //Botones
    private JButton avanzarBtn;
    private JButton retrocederBtn;
    private JButton irPrimeroBtn;
    private JButton irUltimoBtn;
    private JButton insertarBtn;
    private JButton borrarBtn;
    private JButton modificarBtn;
    private JButton ordenarBtn;
    
    
    
    public App(){
    super("Manuel Aljama");
    clientes=new MiLista();
    clientes.cargarClientes();
    
    
    //doi texto a las etiquetas
    numLabel = new JLabel("Numero: ");
    nombreLabel = new JLabel("Nombre: ");
    fechaLabel = new JLabel("Fecha de nacimiento: ");
    saldoLabel = new JLabel("Saldo: ");
    saldoMaxLabel = new JLabel("Saldo máximo: ");
    
    //inicializo los campos de texto
    numField = new JTextField(10);
    nombreField = new JTextField(10);
    diaField = new JTextField(2);
    mesField = new JTextField(2);
    añoField = new JTextField(4);

    saldoField = new JTextField(10);
    saldoMaxField = new JTextField(10);
    
    
    //Creo panel para los label
    JPanel panelLabel = new JPanel();
    panelLabel.setLayout(new GridLayout(0, 1));
    panelLabel.add(numLabel);
    panelLabel.add(nombreLabel);
    panelLabel.add(fechaLabel);
    panelLabel.add(saldoLabel);
    panelLabel.add(saldoMaxLabel);
    
    
    
    //Panel para los campos con texto
    JPanel panelTextos = new JPanel();
    panelTextos.setLayout(new GridLayout(0,1));
    panelTextos.add(numField);
    panelTextos.add(nombreField);
    
    //Creo aqui que es donde quiero otro panel para la fecha
    JPanel panelFecha = new JPanel();
    panelFecha.setLayout(new GridLayout(1,3,5,0));
    
    //Meto subpanel de la fecha
    panelFecha.add(diaField);
    panelFecha.add(mesField);
    panelFecha.add(añoField);
    panelTextos.add(panelFecha);
    
    panelTextos.add(saldoField);
    panelTextos.add(saldoMaxField);
    
    //Creamos un panel para el contenido:
    JPanel contenidoPanel = new JPanel();
    contenidoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    contenidoPanel.setLayout(new BorderLayout());
    
    //Panel botones
    retrocederBtn = new JButton("<");
    insertarBtn = new JButton("insertar");
    irPrimeroBtn = new JButton("Ir primero");
    irUltimoBtn = new JButton("Ir ultimo");
    avanzarBtn = new JButton(">");
    borrarBtn=new JButton("Borrar");
    modificarBtn=new JButton("Modificar");
    ordenarBtn=new JButton("Ordenar");
    
         
    
    JPanel panelBotones = new JPanel();
    panelBotones.setLayout(new GridLayout(0,4));
    
    
    panelBotones.add(retrocederBtn);
    panelBotones.add(insertarBtn);
    panelBotones.add(borrarBtn);
    panelBotones.add(avanzarBtn);
    panelBotones.add(irUltimoBtn);
    panelBotones.add(irPrimeroBtn);
    panelBotones.add(modificarBtn);
    panelBotones.add(ordenarBtn);
    
    
    //Para que se muestren los paneles
    contenidoPanel.add(panelLabel, BorderLayout.WEST);
    contenidoPanel.add(panelTextos, BorderLayout.CENTER);
    contenidoPanel.add(panelBotones, BorderLayout.SOUTH);
    setContentPane(contenidoPanel);
    actualizarVista();
    
    retrocederBtn.addActionListener(e -> {//Abajo explico por que se escribe asi con una e.
    clientes.retroceder();
    actualizarVista();
    });

    avanzarBtn.addActionListener(e -> {
        clientes.avanzar();
        actualizarVista();
    });

    irPrimeroBtn.addActionListener(e -> {
        clientes.irPrimero();
        actualizarVista();
    });
    
    
    //Esta seria la manera antigua sin usar el lambda "e"
    irUltimoBtn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        clientes.irUltimo();
        actualizarVista();
    }
});
    
    insertarBtn.addActionListener(e-> {
        //Vamos a usar un try-catch
        try{
            //copiamos todos los atributos de un cliente
            int numero = Integer.parseInt(numField.getText());
            String nombre = nombreField.getText();
            double saldo = Double.parseDouble(saldoField.getText());
            double saldoMax = Double.parseDouble(saldoMaxField.getText());
            int dia = Integer.parseInt(diaField.getText());
            int mes = Integer.parseInt(mesField.getText())-1;//para que se reste uno ya que nero empieza es0
            int año = Integer.parseInt(añoField.getText());
            
            //creamos fecha
            GregorianCalendar fecha = new GregorianCalendar(dia, mes, año);
            
            //Creamos el nuevo cliente
            Cliente cliente = new Cliente(numero, nombre, fecha, saldo, saldoMax);
            
            clientes.añadirCliente(cliente);
            
            actualizarVista();
            
            
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Error al insertar cliente: " + ex.getMessage());
        }
        
        
    });
    
    modificarBtn.addActionListener(e->{
        try{
            Cliente cliente = clientes.obtenerClienteActual();
            
            if(cliente == null){
                JOptionPane.showMessageDialog(this, "No hay cliente seleccionado.");
                return;
            }
            int numero = Integer.parseInt(numField.getText());
            String nombre = nombreField.getText();
            double saldo = Double.parseDouble(saldoField.getText());
            double saldoMax = Double.parseDouble(saldoMaxField.getText());
            int dia = Integer.parseInt(diaField.getText());
            int mes = Integer.parseInt(mesField.getText())-1;//para que se reste uno ya que nero empieza es0
            int año = Integer.parseInt(añoField.getText());
            
            cliente.setNumero(numero);
            cliente.setNombre(nombre);
            cliente.setFechaNac(new GregorianCalendar(año, mes, dia));
            cliente.setSaldo(saldo);
            cliente.setSaldoMax(saldoMax);

            actualizarVista();

            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Error");
        }
    });
    
    ordenarBtn.addActionListener(e->{
        try {
        // Ordenar la lista de clientes por número
        clientes.ordenarNumero();

        // Después de ordenar, mostrar el primer cliente
        clientes.irPrimero();
        actualizarVista();

        JOptionPane.showMessageDialog(this, "Clientes ordenados por número");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error al ordenar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    });
    
    borrarBtn.addActionListener(e -> {
    Cliente c = clientes.obtenerClienteActual();
    if (c != null) {
        clientes.borrarActual();
        actualizarVista();
    }
});
    }
    
    
    //Metodos para los botones
    
    public void actualizarVista(){
        Cliente c = clientes.obtenerClienteActual();
        //Para que aparezcan los clientes
        if(c !=null){
            numField.setText(String.valueOf(c.getNumero()));
            nombreField.setText((c.getNombre()));
            saldoField.setText(String.valueOf(c.getSaldo()));
            saldoMaxField.setText(String.valueOf(c.getSaldoMax()));
            
            GregorianCalendar fecha = c.getFechaNac();//Sacamos la fecha con un tipo GregorianCalendar
            diaField.setText(String.valueOf(fecha.get(GregorianCalendar.DAY_OF_MONTH)));
            mesField.setText(String.valueOf(fecha.get(GregorianCalendar.MONTH)+1));//El calendario coge enero como 0 asi que le sumamos 1
            añoField.setText(String.valueOf(fecha.get(GregorianCalendar.YEAR)));
            
            
            //Actualizamos botones dependiendo del caso:
            retrocederBtn.setEnabled(!clientes.esPrimero());
            avanzarBtn.setEnabled(!clientes.esUltimo());
        }else{
            //En el caso de no haber cliente, c==null:
            numField.setText("");
            nombreField.setText("");
            saldoField.setText("");
            saldoMaxField.setText("");
            diaField.setText("");
            mesField.setText("");
            añoField.setText("");
            
            retrocederBtn.setEnabled(false);
            avanzarBtn.setEnabled(false);
        }
        
        
        
    }
    
    
    
    
 
    
    
    
    
    
    
    
    
    
//MAIN
    public static void main(String[] args) {
        final App app = new App();

        //Lo que pasa si cerramos la ventana
        app.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            
        });
        app.pack();
        app.setVisible(true);
    }

    
}
