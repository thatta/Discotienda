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

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Es el diálogo para crear un nuevo disco
 */
public class DialCrearDisco extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private DiscoMusicShop principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel con los datos para crear el nuevo disco
     */
    private PanelCrearDisco panelDatos;

    /**
     * Es el panel con los botones para guardar el disco
     */
    private PanlBotDisco panelBotones;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el diálogo
     * @param id es una referencia a la clase principal de la interfaz
     */
    public DialCrearDisco( DiscoMusicShop id )
    {
        super( id, true );
        principal = id;

        panelDatos = new PanelCrearDisco( );
        panelBotones = new PanlBotDisco( this );

        getContentPane( ).add( panelDatos, BorderLayout.CENTER );
        getContentPane( ).add( panelBotones, BorderLayout.SOUTH );

        setTitle( "Crear Disco" );
        pack( );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Guarda el disco
     */
    public void crearDisco( )
    {
        boolean parametersOk = true;
        String artista = panelDatos.darArtista( );
        String titulo = panelDatos.darTitulo( );
        String genero = panelDatos.darGenero( );
        String imagen = panelDatos.darImagen( );

        if( ( artista.equals( "" ) || titulo.equals( "" ) ) || ( genero.equals( "" ) || imagen.equals( "" ) ) )
        {
            parametersOk = false;
            JOptionPane.showMessageDialog( this, "Todos los campos deben ser llenados para crear el disco" );
        }
        if( parametersOk )
        {
            boolean ok = principal.crearDisco( titulo, artista, genero, imagen );
            if( ok )
                dispose( );
        }
    }
}
