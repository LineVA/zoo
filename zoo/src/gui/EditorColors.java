/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;

/**
 *
 * @author doyenm
 */
public enum EditorColors {
    CMD(Color.WHITE), INFO(Color.CYAN);
    
    private Color color;
    EditorColors(Color color){
        this.color = color;
    }
    
    public Color getColor(){
        return this.color;
    }
}
