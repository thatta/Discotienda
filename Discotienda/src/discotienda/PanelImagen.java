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
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Es el panel donde se muestra una imagen decorativa
 */
public class PanelImagen extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Imagen del titulo
     */
    private JLabel imagen;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor sin parámetros
     */
    public PanelImagen( )
    {
        FlowLayout layout = new FlowLayout( );
        layout.setHgap( 500 );
        layout.setVgap( 100 );
        setLayout( layout );
        setBorder(new LineBorder(Color.BLUE));

        // Carga la imagen
        ImageIcon icono = new ImageIcon( "src/ImagenPrincipal/yes.jpeg" );

        // La agrega a la etiqueta
        imagen = new JLabel( "DiscoMusicShop");
        imagen.setIcon( icono );
        add( imagen );

        // Color de fondo blanco
        setBackground( Color.black);
        setBorder( new LineBorder( Color.ORANGE ) );
    }

    

}

