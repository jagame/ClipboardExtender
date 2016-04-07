/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clipboard;

import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;

/**
 *
 * @author jgavilan
 */
class ExtendedContent {
    
    private Transferable content;
    private ClipboardOwner owner;
    
    ExtendedContent(Transferable content, ClipboardOwner owner){
        this.content = content;
        this.owner = owner;
    }

    Transferable getContent() {
        return content;
    }

    void setContent(Transferable content) {
        this.content = content;
    }

    ClipboardOwner getOwner() {
        return owner;
    }

    void setOwner(ClipboardOwner owner) {
        this.owner = owner;
    }
    
}
