/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import clipboard.ExtendedClipboard;
import extendedNativeHook.KeyboardKey;
import extendedNativeHook.MultipleNativeKeyListener;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;


/**
 *
 * @author jgavilan
 */
public class Main {

    
    public static String mensaje;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NativeHookException {
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new MultipleNativeKeyListener() {

            @Override
            public void keyTyped(NativeKeyEvent e) {
                
            }

            @Override
            public void keyReleased(NativeKeyEvent e) {
                System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()) + " suelto");
            }

            @Override
            public void keyPressed(NativeKeyEvent e) {
                // PROBAR SI YA FUNCIONA BIEN Y EL CAPTURAR LAS TECLAS NO BLOQUEA EL COPIAR Y EL PEGAR DEL SISTEMA
                
                try{
                    boolean isImprPant = getEventList().contains(KeyboardKey.PRINTSCREEN);
                    if( getNumTeclasPulsadas() > 1 ){
                        boolean iscontrolL = getEventList().contains(KeyboardKey.CONTROL_L);
                        boolean iscontrolR = getEventList().contains(KeyboardKey.CONTROL_R);
                        boolean isc = getEventList().contains(KeyboardKey.C);
                        boolean isv = getEventList().contains(KeyboardKey.V);
                        System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
                        if( ( iscontrolL || iscontrolR ) && isc ){
                            ExtendedClipboard.setContents();
                            System.out.println("Pulsado Crtl + C");
                        }
                        else if( ( iscontrolL || iscontrolR ) && isv ){
                            Transferable tr = ExtendedClipboard.getContents();
                            System.out.println("Pulsado Crtl + V -----------------------"+ getData(tr));
                        }
                    }
                    else if( isImprPant ){
                        ExtendedClipboard.setContents();
                        System.out.println("Pulsado imprPant");
                    }
                    else if( e.getKeyCode() == NativeKeyEvent.VC_ESCAPE )
                        GlobalScreen.unregisterNativeHook();
                }catch(NativeHookException|UnsupportedFlavorException|IOException|InterruptedException exa){}
                System.out.println(MultipleNativeKeyListener.getNumTeclasPulsadas());
            }
        });
        while( GlobalScreen.isNativeHookRegistered() );
    }
    
    public static Object getData(Transferable tr) throws IOException, UnsupportedFlavorException{
        return tr.getTransferData(tr.getTransferDataFlavors()[0]);
    }
    
}
