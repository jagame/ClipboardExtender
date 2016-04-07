/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clipboard;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author jgavilan
 */
public class ExtendedClipboard {
    
    private static ExtendedContent extContent;
    private final static Clipboard DEFAULT_CLIPBOARD;
    private final static Stack<ExtendedContent> PILA;
    
    static{
        PILA = new Stack();
        DEFAULT_CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();
    }
    
    private ExtendedClipboard() {
    }

    public static void setContents() throws InterruptedException{
        Thread.sleep(100);// Dejamos tiempo suficiente para que el Portapapeles del SO haga sus operaciones
        
        Transferable content;
        content = DEFAULT_CLIPBOARD.getContents(null);
        
        if( extContent != null )
            PILA.push(extContent);
        
        extContent = new ExtendedContent(content, null);
        
    }

    public static Transferable getContents() throws InterruptedException{
        Thread.sleep(100);// Dejamos tiempo suficiente para que el Portapapeles del SO haga sus operaciones
        
        Transferable content;
        content = DEFAULT_CLIPBOARD.getContents(null);
        
        if( PILA.size() > 0 ){
            ExtendedContent exCon = PILA.pop();
            DEFAULT_CLIPBOARD.setContents(exCon.getContent(), exCon.getOwner());;
        }
        
        return content;
    }
    
    public List<Transferable> getElements(){
        return Arrays.asList((Transferable[])PILA.stream().map(e->e.getContent()).toArray());
    }
    
}
