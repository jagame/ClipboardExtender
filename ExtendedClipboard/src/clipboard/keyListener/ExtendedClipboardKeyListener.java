/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clipboard.keyListener;

import clipboard.ExtendedClipboard;
import extendedNativeHook.KeyboardKey;
import extendedNativeHook.MultipleNativeKeyListener;
import static extendedNativeHook.MultipleNativeKeyListener.EVENT_LIST;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import static main.Main.getData;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;

/**
 *
 * @author jgavilan
 */
public class ExtendedClipboardKeyListener implements MultipleNativeKeyListener{
    
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
                    if( MultipleNativeKeyListener.getNumTeclasPulsadas() > 1 ){
                        if( isControlPressed() )
                            multipleClipboardKeyProcess();
                    }
                    else if( isPressed(KeyboardKey.PRINTSCREEN) ){
                        ExtendedClipboard.setContents();
                    }
                    else if( isPressed(KeyboardKey.ESCAPE) ){
                        GlobalScreen.unregisterNativeHook();
                    }
                    
                }catch(NativeHookException|InterruptedException exa){
                    exa.printStackTrace();
                }
            }
            
            private void multipleClipboardKeyProcess() throws InterruptedException{
                if( isPressed(KeyboardKey.C) )
                            ExtendedClipboard.setContents();
                else if( isPressed(KeyboardKey.V) )
                    ExtendedClipboard.getContents();
            }
            
            private boolean isControlPressed(){
                return EVENT_LIST.contains(KeyboardKey.CONTROL_L) || EVENT_LIST.contains(KeyboardKey.CONTROL_R);
            }
            
            private boolean isPressed( KeyboardKey k ){
                return EVENT_LIST.contains(k);
            }
}
