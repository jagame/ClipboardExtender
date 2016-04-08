/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import clipboard.keyListener.ExtendedClipboardKeyListener;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;


/**
 *
 * @author jgavilan
 */
public class Main {

    
    public static String mensaje;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, NativeHookException {
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new ExtendedClipboardKeyListener());
        while( GlobalScreen.isNativeHookRegistered() ){
            Thread.sleep(500);
        }
    }
    
    public static Object getData(Transferable tr) throws IOException, UnsupportedFlavorException{
        return tr.getTransferData(tr.getTransferDataFlavors()[0]);
    }
    
}
