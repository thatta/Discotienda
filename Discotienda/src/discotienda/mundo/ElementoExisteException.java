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
public class ElementoExisteException extends Exception
{
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye la excepción indicando el disco o la canción que causó el error
     * @param nomElem nombre del elemento repetido
     */
    public ElementoExisteException( String nomElem )
    {
        super( nomElem );
    }
}
