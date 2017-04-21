/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discotienda.mundo;

/**
 *
  * @Juan David Ojeda Bernal
 * @Tatiana Almansa
 */
public class ArchivoVentaException extends Exception
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Número de canciones que se pudieron vender a pesar del error
     */
    private int cancionesVendidas;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye la excepción con una causa del error y la cantidad de canciones vendidas a pesar del error
     * @param causa El mensaje que describe el problema que se presentó
     * @param ventas La cantidad de canciones vendidas a pesar del error
     */
    public ArchivoVentaException( String causa, int ventas )
    {
        super( causa );
        cancionesVendidas = ventas;
    }

    /**
     * Retorna la cantidad de canciones vendidas a pesar del error
     * @return La cantidad de canciones vendidas a pesar del error
     */
    public int darCancionesVendidas( )
    {
        return cancionesVendidas;
    }
}
