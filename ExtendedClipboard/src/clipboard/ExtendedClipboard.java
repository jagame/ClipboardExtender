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
    
    private static Clipboard DEFAULT_CLIPBOARD;
    private static LinkedList<Transferable> PILA;
    private static Transferable cache;
    
    static{
        reset();
    }
    
    private ExtendedClipboard() {
    }
    
    public final static void reset(){
        PILA = new LinkedList();
        DEFAULT_CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    public static void setContents() throws InterruptedException{
        Thread.sleep(150);// Dejamos tiempo suficiente para que el Portapapeles del SO haga sus operaciones
        
        PILA.add(getClipboardContents());
        if( cache == null )
            cache = PILA.pop();
        setClipboardContents(cache);
    }

    public static void getContents() throws InterruptedException{
        Thread.sleep(150);// Dejamos tiempo suficiente para que el Portapapeles del SO haga sus operaciones
        
        if( ! PILA.isEmpty() ){
            cache = PILA.pop();
            setClipboardContents(cache);
        }else{
            cache = null;
        }
    }
    
    private static Transferable getClipboardContents(){
        Transferable content = null;
        
        try{
            content = DEFAULT_CLIPBOARD.getContents(null);
        }catch( IllegalStateException ex ){
            System.out.println("Error obteniendo contenido del Clipboard, volviendo a intentar");
            getClipboardContents();
        }
        
        return content;
    }
    
    private static void setClipboardContents(Transferable content){
        try{
            DEFAULT_CLIPBOARD.setContents(content,null);
        }catch( IllegalStateException ex ){
            System.out.println("Error asignando contenido al Clipboard, volviendo a intentar");
            setClipboardContents(content);
        }
    }
    
    public List<Transferable> getElementsCopy(){
        return (List<Transferable>)PILA.clone();
    }
    
}
