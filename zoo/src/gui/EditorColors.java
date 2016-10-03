/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import commandLine.TypeReturn;
import static commandLine.TypeReturn.ERROR;
import static commandLine.TypeReturn.QUESTION;
import static commandLine.TypeReturn.SUCCESS;
import java.awt.Color;

/**
 *
 * @author doyenm
 */
public enum EditorColors {

    CMD(Color.WHITE), EXCEPTION(Color.ORANGE), INFO(Color.CYAN), WAIT(Color.GREEN);

    private Color color;

    EditorColors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public Color getCorrespondingColor(TypeReturn type) {
        switch (type) {
            case SUCCESS:
                return EditorColors.INFO.getColor();
            case ERROR:
                return EditorColors.EXCEPTION.getColor();
            case QUESTION:
                return EditorColors.WAIT.getColor();
            default:
                return EditorColors.INFO.getColor();
        }
    }
}
