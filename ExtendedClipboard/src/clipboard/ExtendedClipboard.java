/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clipboard;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jgavilan
 */
public class ExtendedClipboard {
    
    private final static Clipboard DEFAULT_CLIPBOARD;
    private final static LinkedList<Transferable> PILA;
    private static int repeaterGet;
    
    static{
        PILA = new LinkedList();
        DEFAULT_CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();
    }
    
    private ExtendedClipboard() {
    }

    public static void setContents() throws InterruptedException{
        Thread.sleep(100);// Dejamos tiempo suficiente para que el Portapapeles del SO haga sus operaciones
        
        Transferable content;
        
        try{
            content = DEFAULT_CLIPBOARD.getContents(null);
            // obtenemos el contenido del Clipboard

            PILA.add( content );
            //Añadimos el contenido a la cola

            content = PILA.peek();
            //Obtenemos el primer elemento de la cola sin eliminarlo

            DEFAULT_CLIPBOARD.setContents(content, null);
            //Asignamos al Clipboard el primer elemento de la cola
        }catch(IllegalStateException e){
            setContents();
            // Despues de copiar todos los elementos si queremos volver a copiar contenido al portapapeles da error IllegalStateException la primera vez.
            // Basta con ejecutarlo una segunda vez para que ya si, funcione correctamente
        }
        
        repeaterGet=0;
    }

    public static Transferable getContents() throws InterruptedException{
        Thread.sleep(100);// Dejamos tiempo suficiente para que el Portapapeles del SO haga sus operaciones
        
        Transferable content;
        content = DEFAULT_CLIPBOARD.getContents(null); // Se almacena el content (por si quiere ser gestionado)
        
        if( repeaterGet > 0 && PILA.size() > 0 )
            DEFAULT_CLIPBOARD.setContents(PILA.pop(), null);
        // Si el get ha sido repetido varias veces se devuelve el primer elemento de la pila
        else if( PILA.size() > 0 ){
            PILA.pop();
            DEFAULT_CLIPBOARD.setContents(PILA.pop(), null);
            // Si el get no ha sido repetido varias veces se descarta el primer elemento de la pila (ya que es el que actualmente está en el Clipboard y ya ha sido imprimido)
            // y se asigna el siguiente que se querrá devolver
        }
        
        repeaterGet++;
        
        return content;
    }
    
    public List<Transferable> getElementsCopy(){
        return (List<Transferable>)PILA.clone();
    }
    
}
