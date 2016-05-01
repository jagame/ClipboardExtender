/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clipboard.keyListener;

import clipboard.ExtendedClipboard;
import extendedNativeHook.KeyboardKey;
import extendedNativeHook.MultipleNativeKeyListener;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;

/**
 *
 * @author jgavilan
 */
public class ExtendedClipboardKeyListener extends MultipleNativeKeyListener{
    
            @Override
            public void keyTyped(NativeKeyEvent e) {
                
            }

            @Override
            public void keyReleased(NativeKeyEvent e) {
            }

            @Override
            public void keyPressed(NativeKeyEvent e) {
                try{
                    if( getNumTeclasPulsadas() > 1 ){
                        if( isControlPressed() ){
                            if( isPressed(KeyboardKey.C) )
                                ExtendedClipboard.setContents();
                            else if( isPressed(KeyboardKey.V) )
                                ExtendedClipboard.getContents();
                            else if( isPressed(KeyboardKey.ESCAPE) )
                                ExtendedClipboard.reset();
                        }
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
            
            private boolean isControlPressed(){
                return isPressed(KeyboardKey.CONTROL_L) || isPressed(KeyboardKey.CONTROL_R);
            }
            
            private boolean isPressed( KeyboardKey k ){
                return EVENT_LIST.contains(k);
            }
}
