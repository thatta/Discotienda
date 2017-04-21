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
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import discotienda.mundo.Disco;

/**
 * Es el panel con los detalles de un disco
 */
public class PanelDiscos extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para el combo box con los discos
     */
    private static final String CAMBIAR_DISCO = "CambiarDisco";

    /**
     * Comando para el botón para agregar un disco
     */
    private static final String AGREGAR_DISCO = "AgregarDisco";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private DiscoMusicShop principal;

    /**
     * Es el disco que actualmente se presenta en el panel
     */
    private Disco disco;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el comboBox con la lista de discos
     */
    private JComboBox comboDisc;

    /**
     * Es la etiqueta "Artista"
     */
    private JLabel etiTituloArtista;

    /**
     * Es el campo con el artista del disco
     */
    private JTextField txtoArtista;

    /**
     * Es la etiqueta "Genero"
     */
    private JLabel etiTituloGenero;

    /**
     * Es el campo con el género del disco
     */
    private JTextField txtoGenero;

    /**
     * Es la etiqueta con el precio del disco
     */
    private JLabel etiTituloPrecio;

    /**
     * Es el campo con el precio del disco completo
     */
    private JTextField txtoPrecio;

    /**
     * Es el botón para mostrar el diálogo para agregar un nuevo disco
     */
    private JButton botonAgregarDisco;

    /**
     * Es el campo que muestra la imágen del disco
     */
    private JLabel etiquetaImagen;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel para la información de un disco de la discotienda
     * @param ventanaPrincipal es una referencia a la clase principal de la interfaz
     * @param discos unna lista con los nombres de los discos que se tienen en la discotienda
     */
    public PanelDiscos( DiscoMusicShop ventanaPrincipal, ArrayList discos )
    {
        principal = ventanaPrincipal;

        setLayout( new BorderLayout( ) );

        comboDisc = new JComboBox( discos.toArray( ) );
        comboDisc.setEditable( false );
        comboDisc.addActionListener( this );
        comboDisc.setActionCommand( CAMBIAR_DISCO );
        add( comboDisc, BorderLayout.NORTH );

        JPanel panelDatosDisco = new JPanel( );
        add( panelDatosDisco, BorderLayout.CENTER );

        panelDatosDisco.setLayout( new GridLayout( 6, 1, 0, 5 ) );

        etiTituloArtista = new JLabel( "Titulo Disco: " );
        txtoArtista = new JTextField( 10 );
        txtoArtista.setEditable( false );
        txtoArtista.setFont( txtoArtista.getFont( ).deriveFont( Font.PLAIN ) );
        panelDatosDisco.add( etiTituloArtista );
        panelDatosDisco.add( txtoArtista );

        etiTituloGenero = new JLabel( "Género: " );
        txtoGenero = new JTextField( 10 );
        txtoGenero.setEditable( false );
        txtoGenero.setFont( txtoGenero.getFont( ).deriveFont( Font.PLAIN ) );
        panelDatosDisco.add( etiTituloGenero );
        panelDatosDisco.add( txtoGenero );

        etiTituloPrecio = new JLabel( "Precio: " );
        txtoPrecio = new JTextField( 10 );
        txtoPrecio.setEditable( false );
        txtoPrecio.setFont( txtoPrecio.getFont( ).deriveFont( Font.PLAIN ) );
        panelDatosDisco.add( etiTituloPrecio );
        panelDatosDisco.add( txtoPrecio );

        panelDatosDisco.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );

        etiquetaImagen = new JLabel( );
        etiquetaImagen.setPreferredSize( new Dimension( 200, 200 ) );
        etiquetaImagen.setBorder( new CompoundBorder( new EmptyBorder( 5, 5, 5, 5 ), new TitledBorder( "" ) ) );
        add( etiquetaImagen, BorderLayout.EAST );

        botonAgregarDisco = new JButton( "Agregar Artista" );
        botonAgregarDisco.setActionCommand( AGREGAR_DISCO );
        botonAgregarDisco.addActionListener( this );
        add( botonAgregarDisco, BorderLayout.SOUTH );

        setBorder( new CompoundBorder( new EmptyBorder( 5, 5, 5, 5 ), new TitledBorder( "Artistas" ) ) );
        

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cambia el disco que se muestra en el panel
     * @param d el nuevo disco a mostrar en el panel
     */
    public void cambiarDisco( Disco d )
    {
        disco = d;
        if( disco != null )
        {
            etiquetaImagen.setIcon( new ImageIcon( disco.darImagen( ) ) );

            txtoArtista.setText( disco.darArtista( ) );
            txtoGenero.setText( disco.darGenero( ) );
            txtoPrecio.setText( Double.toString( disco.darPrecioDisco( ) ) );
        }
        else
        {
            setBorder( new CompoundBorder( new EmptyBorder( 5, 5, 5, 5 ), new TitledBorder( "Detalles del Disco" ) ) );
        }
    }

    /**
     * Actualiza la información en el panel con la lista de discos
     * @param discos la lista de discos a desplegar
     */
    public void refrescarDiscos( ArrayList discos )
    {
        comboDisc.removeAllItems( );

        for( int i = 0; i < discos.size( ); i++ )
        {
            comboDisc.addItem( discos.get( i ) );
        }
    }

    /**
     * Ejecuta las acciones asociadas a los eventos
     * @param evento es el evento del click sobre un botón
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( AGREGAR_DISCO.equals( comando ) )
        {
            principal.mostrarDialogoAgregarDisco( );
        }
        else if( CAMBIAR_DISCO.equals( comando ) )
        {
            String nombreDisco = ( String )comboDisc.getSelectedItem( );
            principal.cambiarDiscoSeleccionado( nombreDisco );
        }
        
    }

}