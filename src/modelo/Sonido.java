/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Usuario
 */
public class Sonido {

    private boolean pausa;
    Clip clip = null;

    public void setPausa(boolean pausa) {
        this.pausa = pausa;
        if (pausa) {
            clip.stop();
        }
        if (!pausa) {

            clip.start();
        }
    }

    public boolean isPausa() {
        return pausa;
    }

    public void reproducirSonido1() {

        try {
            File musica1 = new File("C:\\Users\\Usuario\\Desktop\\Java\\Tetris-final-Alex\\src\\Sonido\\sonido1.wav");
            if (musica1.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musica1);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();

            } else {
                System.out.println("Archivo no encontrado");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
