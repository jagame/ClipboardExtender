/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extendedNativeHook;

import java.util.HashSet;
import java.util.Set;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 *
 * @author jgavilan
 */
public abstract class MultipleNativeKeyListener implements NativeKeyListener{
    private final static Set<KeyboardKey> EVENT_LIST = new HashSet<>();

    public static Set<KeyboardKey> getEventList() {
        return EVENT_LIST;
    }

    public static void addNativeKeyEvent(NativeKeyEvent event) {
        EVENT_LIST.add(KeyboardKey.getKey(event));
    }
    
    public static void removeNativeKeyEvent(NativeKeyEvent event) {
        EVENT_LIST.remove(KeyboardKey.getKey(event));
    }

    public static int getNumTeclasPulsadas() {
        return EVENT_LIST.size();
    }
    
    public abstract void keyTyped(NativeKeyEvent e);
    
    public abstract void keyReleased(NativeKeyEvent e);
    
    public abstract void keyPressed(NativeKeyEvent e);

    @Override
    public void nativeKeyTyped(NativeKeyEvent e){
        keyTyped(e);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e){
        keyReleased(e);
        removeNativeKeyEvent(e);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e){
        addNativeKeyEvent(e);
        keyPressed(e);
    }
    
    
}
