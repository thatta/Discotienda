/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discotienda;

/**
 *
 * @Juan David Ojeda Bernal
 * @Tatiana Almansa
 */


import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

import discotienda.mundo.*;

/**
 * Es la clase principal de la interfaz
 */
public class DiscoMusicShop extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * La ruta donde deben guardarse las facturas
     */
    private static final String RUTA_FACTURAS = "./data/facturas";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la discotienda
     */
    private Discotienda discotienda;

    /**
     * Es una referencia al disco de la cual se están mostrando los datos
     */
    private Disco discoSeleccionado;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel con los botones para las extensiones de la aplicación
     */
   

    /**
     * Es el panel con la información del disco seleccionado
     */
    private PanelDiscos panelDiscos;

    /**
     * Es el panel para presentar las canciones del disco
     */
    private PanelDatosCanciones panelDatosCanciones;

    /**
     * Es el panel donde se muestra una imagen decorativa
     */
    private PanelImagen panelImagen;

    /**
     * Es el panel donde se realizan los pedidos
     */
   

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la interfaz de la aplicación
     * @param d es la discotienda que se va a mostrar
     */
    public DiscoMusicShop( Discotienda d )
    {
        
        discotienda = d;

      
        // Panel con la Imagen
        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH);

        // Panel central con los datos del disco, de las canciones y el botón para cargar un pedido
        JPanel panelCentral = new JPanel( new BorderLayout( ) );
        add( panelCentral, BorderLayout.CENTER );

        panelDiscos = new PanelDiscos( this, discotienda.darDiscos( ) );
        panelCentral.add( panelDiscos, BorderLayout.CENTER );
       

        panelDatosCanciones = new PanelDatosCanciones( this );
        panelCentral.add( panelDatosCanciones, BorderLayout.EAST );
       

        ArrayList discos = discotienda.darDiscos( );
        if( discos.size( ) > 0 )
        {
            cambiarDiscoSeleccionado( ( ( String )discos.get( 0 ) ) );
        }

        


        setTitle( "DiscoMusicShop" );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        pack( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cambia el disco seleccionado en el panel de detalles del disco
     * @param nombreDisco el nombre del disco a mostrar los detalles
     */
    public void cambiarDiscoSeleccionado( String nombreDisco )
    {
        discoSeleccionado = discotienda.darDisco( nombreDisco );
        panelDiscos.cambiarDisco( discoSeleccionado );
        panelDatosCanciones.cambiarDisco( discoSeleccionado );
    }

    /**
     * Muestra el diálogo para agregar un nuevo disco a la discotienda
     */
    public void mostrarDialogoAgregarDisco( )
    {
        DialCrearDisco dialogo = new DialCrearDisco( this );
        dialogo.setLocationRelativeTo( this );
        dialogo.setVisible( true );
    }

    /**
     * Muestra el diálogo para agregar una nueva canción al disco en el panel de detalles del disco
     */
    public void mostrarDialogoAgregarCancion( )
    {
        DialCrearCancion dialogo = new DialCrearCancion( this );
        dialogo.setLocationRelativeTo( this );
        dialogo.setVisible( true );
    }

    /**
     * Crea un nuevo disco en la discotienda y actualiza el panel con la lista de discos <br>
     * <b>pre: <b>No debe haber otro disco con el mismo nombre en la discotienda
     * @param nombreDisco El nombreDisco del disco a crear
     * @param artista el artista del nuevo disco
     * @param genero el genero del nuevo disco
     * @param imagen el nombre de la imagen asociada al nuevo disco
     * @return Retorna true si la canción se pudo agregar. Esto sirve para saber si se debe cerrar el diálogo.
     */
    public boolean crearDisco( String nombreDisco, String artista, String genero, String imagen )
    {
        boolean ok = false;
        try
        {
            discotienda.agregarDisco( nombreDisco, artista, genero, imagen );
            panelDiscos.refrescarDiscos( discotienda.darDiscos( ) );
            ok = true;
        }
        catch( ElementoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ) );
        }

        return ok;
    }

    /**
     * Crea una nueva canción en el disco que se muestra en los detalles de disco en la discotienda <br>
     * <b>pre: <b>No debe haber otra canción con el mismo nombre en el disco
     * @param nombre el nombre de la nueva canción
     * @param minutos el número de minutos de duración de la canción
     * @param segundos el número de segundos de duración de la canción
     * @param precio el precio de la canción
     * @param tamano el tamaño en MB de la canción
     * @param calidad la calidad de la canción en KBps
     * @return Retorna true si la canción se pudo agregar. Esto sirve para saber si se debe cerrar el diálogo.
     */
    public boolean crearCancion( String nombre, int minutos, int segundos, double precio, double tamano, int calidad )
    {
        boolean ok = false;

        if( discoSeleccionado != null )
        {
            try
            {
                discotienda.agregarCancionADisco( discoSeleccionado.darNombreDisco( ), nombre, minutos, segundos, precio, tamano, calidad );
                discoSeleccionado = discotienda.darDisco( discoSeleccionado.darNombreDisco( ) );
                panelDiscos.cambiarDisco( discoSeleccionado );
                ok = true;
            }
            catch( ElementoExisteException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ) );
            }
        }

        return ok;
       
    }
    

    /**
     * Vende una canción a una persona
     * @param disco el disco al que pertenece la canción que se va a vender - disco != null
     * @param cancion la canción que se va a vender - cancion != null
     */
    public void venderCancion( Disco disco, Cancion cancion )
    {
        String email = JOptionPane.showInputDialog( this, "Indique el email del comprador", "Email", JOptionPane.QUESTION_MESSAGE );
        if( email != null )
        {
            if( discotienda.validarEmail( email ) )
            {
                try
                {
                    String archivoFactura = discotienda.venderCancion( disco, cancion, email, RUTA_FACTURAS );
                    JOptionPane.showMessageDialog( this, "dirijase a carpeta **factura** en carpeta **data** de este programa para ver su factura, La factura se guardó con el nombre: " + archivoFactura, "Factura Guardada", JOptionPane.INFORMATION_MESSAGE );
                }
                catch( IOException e )
                {
                    JOptionPane.showMessageDialog( this, "Se presentó un problema guardando el archivo de la factura:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                }
            }
            else
            {
                JOptionPane.showMessageDialog( this, "El email indicado no es válido", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Este método se encarga de cargar la información de un pedido
     */
    public void cargarPedido( )
    {
        JFileChooser fc = new JFileChooser( "./data/facturas" );
        fc.setDialogTitle( "Pedido" );
        int resultado = fc.showOpenDialog( this );
        if( resultado == JFileChooser.APPROVE_OPTION )
        {
            File archivo = fc.getSelectedFile( );
            if( archivo != null )
            {
                try
                {
                    String archivoFactura = discotienda.venderListaCanciones( archivo, RUTA_FACTURAS );
                    JOptionPane.showMessageDialog( this, "La factura se guardó en el archivo: " + archivoFactura, "Factura Guardada", JOptionPane.INFORMATION_MESSAGE );
                }
                catch( FileNotFoundException e )
                {
                    JOptionPane.showMessageDialog( this, "Se presentó un problema leyendo el archivo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                }
                catch( IOException e )
                {
                    JOptionPane.showMessageDialog( this, "Se presentó un problema leyendo el archivo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                }
                catch( ArchivoVentaException e )
                {
                    JOptionPane.showMessageDialog( this, "Se presentó un problema debido al formato del archivo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
                }
            }
        }
    }

    /**
     * Este método se encarga de salvar la información de la discotienda, justo antes de cerrar la aplicación
     */
    

    /**
     * Ejecuta la aplicación
     * @param args son parámetros de ejecución de la aplicación. No se usan en este programa
     */
    public static void main( String[] args )
    {
        Discotienda discotienda = null;
        try
        {
            discotienda = new Discotienda( "./data/discotienda.discos" );
        }
        catch( PersistenciaException e )
        {
            e.printStackTrace( );
            System.exit( 1 );
            
        }
        DiscoMusicShop id = new DiscoMusicShop( discotienda );
        id.setVisible( true );
        System.out.close();
    }
}
