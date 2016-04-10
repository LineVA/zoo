/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import commandLine.CommandLineParser;
import gui.MainGUI;

/**
 *
 * @author doyenm
 */
public class Main {

    public static void main(String[] args) {
        CommandLineParser parser = new CommandLineParser();
        MainGUI mainGUI = new MainGUI(parser);
    }
}
