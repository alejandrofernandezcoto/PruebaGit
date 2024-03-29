/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package iu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import modelo.Xogo;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Timer;
import modelo.Sonido;

/**
 *
 * @author a22alejandrofc
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private Xogo xogo;
    private Sonido sing;
    private Timer timer;
    private int centesimas = 0;
    private int segundos = 0;
    private int minutos = 0;
    private int horas = 0;
    private int dificultad = 1;
    private int caidaFicha;
    private ArrayList<Integer> nivelesAlcanzados = new ArrayList();

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {

        initComponents();
        botonMedio.setSelected(true);
        declararImagenes();
    }

    public Xogo getXogo() {
        return xogo;
    }

    public void setXogo(Xogo xogo) {
        this.xogo = xogo;
    }

    public void mostrarNumeroLinas() {
        String valorAnterior = lblLblnumlinas.getText();
        lblLblnumlinas.setText(xogo.getNumeroLineas() + "");
        if (!valorAnterior.equals(lblLblnumlinas.getText()) && xogo.getNumeroLineas() % 5 == 0) {
            dificultad++;
        }
    }

    public void pintarCadrado(JLabel lblCadrado) {
        panelXogo.add(lblCadrado);
        panelXogo.updateUI();
    }

    public void borrarCadrado(JLabel lblCadrado) {
        panelXogo.remove(lblCadrado);
        panelXogo.updateUI();
    }

    public void mostrarFinDoXogo() {
        gameOver.setVisible(true);
        exit.setVisible(true);
        panelXogo.setVisible(false);
        cajaPuntuación.setVisible(false);
        cajaTiempo.setVisible(false);
        lblLblnumlinas.setVisible(false);
        temporizador.setVisible(false);
        tqlbtnPausa.setVisible(false);
        botonSalir.setVisible(false);
        timer.stop();
    }

    private void timer() {
        String texto = (minutos <= 9 ? "0" : "") + minutos + ":"
                + (segundos <= 9 ? "0" : "") + segundos + ":" + (centesimas <= 9 ? "0" : "") + centesimas;

        temporizador.setText(texto);
    }

    private void declararImagenes() {
        botonFacil.setSelectedIcon(new javax.swing.ImageIcon(getClass()
                .getResource("/Images/facil_verde.png")));
        botonFacil.setIcon(new javax.swing.ImageIcon(getClass()
                .getResource("/Images/facil_negro.png")));
        botonMedio.setSelectedIcon(new javax.swing.ImageIcon(getClass()
                .getResource("/Images/medio_amarillo.png")));
        botonMedio.setIcon(new javax.swing.ImageIcon(getClass()
                .getResource("/Images/medio_negro.png")));
        botonDificil.setSelectedIcon(new javax.swing.ImageIcon(getClass()
                .getResource("/Images/dificil_rojo.png")));
        botonDificil.setIcon(new javax.swing.ImageIcon(getClass()
                .getResource("/Images/dificil_negro.png")));
    }

    private ActionListener acciones = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            centesimas++;
            caidaFicha++;
            mostrarNumeroLinas();
            if (centesimas == 100) {
                segundos++;
                centesimas = 0;
            }
            if (comprobarCaida()) {
                xogo.moverFichaAbaixo();
                caidaFicha = 0;
                aumentarVelocidad();
            }
            if (dificultad >= 5 && segundos == 30 && centesimas == 00) {
                xogo.engadirLineas();
            }
            if (segundos == 60) {
                minutos++;
                segundos = 0;
                if (dificultad >= 5) {
                    xogo.engadirLineas();
                }
            }
            if (minutos == 60) {
                horas++;
                minutos = 0;
            }

            timer();
        }

    };

    private boolean comprobarCaida() {
        return caidaFicha == 85 - 5 * dificultad;
    }

    private void aumentarVelocidad() {
        if (!nivelAlcanzado(xogo.getNumeroLineas())) {
            nuevosNivelesAlcanzados(xogo.getNumeroLineas());
        }
    }

    private void nuevosNivelesAlcanzados(int nivel) {
        while (nivel > 0 && dificultad + 2 <= 15) {
            if (nivel % 5 == 0 && !nivelesAlcanzados.contains(nivel)) {
                dificultad += 2;
            }
            if (!nivelesAlcanzados.contains(nivel)) {
                nivelesAlcanzados.add(nivel);
            }
            nivel--;
        }
        if (dificultad > 15) {
            dificultad = 15;
        }
    }

    private boolean nivelAlcanzado(int nivel) {
        Iterator<Integer> niveles = nivelesAlcanzados.iterator();
        while (niveles.hasNext()) {
            if (niveles.next() == nivel) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        panelPrincipal = new javax.swing.JPanel();
        exit = new javax.swing.JButton();
        gameOver = new javax.swing.JLabel();
        panelXogo = new javax.swing.JPanel();
        labelFondo = new javax.swing.JLabel();
        panelLabels = new javax.swing.JPanel();
        temporizador = new javax.swing.JLabel();
        cajaTiempo = new javax.swing.JLabel();
        lblLblnumlinas = new javax.swing.JLabel();
        cajaPuntuación = new javax.swing.JLabel();
        botonSalir = new javax.swing.JButton();
        tqlbtnPausa = new javax.swing.JToggleButton();
        botonesDificultad = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        iniciar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        botonFacil = new javax.swing.JRadioButton();
        botonMedio = new javax.swing.JRadioButton();
        botonDificil = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();

        jFrame1.setBackground(new java.awt.Color(153, 153, 153));
        jFrame1.setLocation(new java.awt.Point(250, 0));
        jFrame1.setMinimumSize(new java.awt.Dimension(700, 1000));
        jFrame1.setResizable(false);
        jFrame1.getContentPane().setLayout(null);

        panelPrincipal.setBackground(new java.awt.Color(153, 153, 255));
        panelPrincipal.setLayout(null);

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/es.png"))); // NOI18N
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        panelPrincipal.add(exit);
        exit.setBounds(230, 390, 220, 100);

        gameOver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Diseño sin título.png"))); // NOI18N
        panelPrincipal.add(gameOver);
        gameOver.setBounds(170, 150, 350, 430);
        gameOver.getAccessibleContext().setAccessibleParent(panelPrincipal);

        panelXogo.setBackground(new java.awt.Color(0, 0, 0));
        panelXogo.setMaximumSize(new java.awt.Dimension(320, 640));
        panelXogo.setMinimumSize(new java.awt.Dimension(320, 640));
        panelXogo.setPreferredSize(new java.awt.Dimension(320, 640));
        panelXogo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                panelXogoKeyPressed(evt);
            }
        });

        labelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tetris4_transparente.png"))); // NOI18N
        labelFondo.setText("jLabel4");
        labelFondo.setMaximumSize(new java.awt.Dimension(320, 640));
        labelFondo.setMinimumSize(new java.awt.Dimension(320, 640));
        labelFondo.setPreferredSize(new java.awt.Dimension(320, 640));

        javax.swing.GroupLayout panelXogoLayout = new javax.swing.GroupLayout(panelXogo);
        panelXogo.setLayout(panelXogoLayout);
        panelXogoLayout.setHorizontalGroup(
            panelXogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXogoLayout.createSequentialGroup()
                .addComponent(labelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelXogoLayout.setVerticalGroup(
            panelXogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelXogoLayout.createSequentialGroup()
                .addComponent(labelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelPrincipal.add(panelXogo);
        panelXogo.setBounds(6, 16, 320, 640);

        panelLabels.setBackground(new java.awt.Color(153, 153, 255));
        panelLabels.setLayout(null);

        temporizador.setFont(new java.awt.Font("Source Serif Pro Black", 1, 36)); // NOI18N
        temporizador.setLabelFor(cajaTiempo);
        temporizador.setText("00:00:00:00");
        panelLabels.add(temporizador);
        temporizador.setBounds(110, 300, 210, 70);

        cajaTiempo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/scorre (3).png"))); // NOI18N
        panelLabels.add(cajaTiempo);
        cajaTiempo.setBounds(30, 220, 320, 202);

        lblLblnumlinas.setFont(new java.awt.Font("Source Serif Pro Black", 1, 36)); // NOI18N
        lblLblnumlinas.setText("0");
        panelLabels.add(lblLblnumlinas);
        lblLblnumlinas.setBounds(180, 80, 120, 80);

        cajaPuntuación.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/scorre (2).1.png"))); // NOI18N
        panelLabels.add(cajaPuntuación);
        cajaPuntuación.setBounds(30, 10, 320, 187);

        botonSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/button_exit.png"))); // NOI18N
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });
        panelLabels.add(botonSalir);
        botonSalir.setBounds(110, 550, 190, 50);

        tqlbtnPausa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/button (2).png"))); // NOI18N
        tqlbtnPausa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tqlbtnPausaActionPerformed(evt);
            }
        });
        panelLabels.add(tqlbtnPausa);
        tqlbtnPausa.setBounds(110, 460, 194, 60);
        tqlbtnPausa.getAccessibleContext().setAccessibleParent(panelLabels);

        panelPrincipal.add(panelLabels);
        panelLabels.setBounds(337, 6, 370, 860);

        jFrame1.getContentPane().add(panelPrincipal);
        panelPrincipal.setBounds(-5, -2, 1110, 1430);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1160, 1050));
        setPreferredSize(new java.awt.Dimension(1160, 900));
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(1160, 1050));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/button (1).png"))); // NOI18N
        iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarActionPerformed(evt);
            }
        });
        iniciar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                iniciarKeyPressed(evt);
            }
        });
        jPanel1.add(iniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 170, 190, 70));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tetris.png"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(300, 300));
        jLabel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel2KeyPressed(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 540, 600));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/628a738ebc2ec7ad957f4072.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 320, 110));

        botonesDificultad.add(botonFacil);
        botonFacil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/facil_negro.png"))); // NOI18N
        botonFacil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFacilActionPerformed(evt);
            }
        });
        jPanel1.add(botonFacil, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, -1, -1));

        botonesDificultad.add(botonMedio);
        botonMedio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/medio_negro.png"))); // NOI18N
        botonMedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMedioActionPerformed(evt);
            }
        });
        jPanel1.add(botonMedio, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 350, -1, -1));

        botonesDificultad.add(botonDificil);
        botonDificil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/dificil_negro.png"))); // NOI18N
        botonDificil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDificilActionPerformed(evt);
            }
        });
        jPanel1.add(botonDificil, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 460, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondo1.jpg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 1050));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarActionPerformed
        iniciarPartida();
    }//GEN-LAST:event_iniciarActionPerformed

    private void iniciarPartida() {
        sing = new Sonido();
        panelXogo.removeAll();
        panelXogo.updateUI();
        gameOver.setVisible(false);
        exit.setVisible(false);
        jFrame1.setVisible(true);
        xogo = new Xogo(this);
        xogo.xenerarNovaFicha();
        xogo.setPausa(false);
        panelXogo.setFocusable(true);
        panelXogo.setVisible(true);
        cajaPuntuación.setVisible(true);
        cajaTiempo.setVisible(true);
        lblLblnumlinas.setVisible(true);
        temporizador.setVisible(true);
        tqlbtnPausa.setVisible(true);
        botonSalir.setVisible(true);
        panelXogo.requestFocus();
        timer = new Timer(10, acciones);
        caidaFicha = 0;
        centesimas = 0;
        segundos = 0;
        minutos = 0;
        horas = 0;
        panelXogo.add(labelFondo);
        timer.start();
        nivelesAlcanzados.removeAll(nivelesAlcanzados);
        nivelesAlcanzados.add(0);

        if (botonMedio.isSelected()) {
            dificultad = 3;
        } else if (botonFacil.isSelected()) {
            dificultad = 1;
        } else if (botonDificil.isSelected()) {
            dificultad = 5;
        }

    }

    private void jLabel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2KeyPressed

    private void iniciarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_iniciarKeyPressed

        // TODO add your handling code here:
    }//GEN-LAST:event_iniciarKeyPressed

    private void panelXogoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_panelXogoKeyPressed

        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_DOWN) {
            getXogo().moverFichaAbaixo();
        }
        if (key == KeyEvent.VK_RIGHT) {
            getXogo().moverFichaDereita();
        }
        if (key == KeyEvent.VK_LEFT) {
            getXogo().moverFichaEsquerda();
        }
        if (key == KeyEvent.VK_UP) {
            getXogo().rotarFicha();
        }
        repaint();

        // TODO add your handling code here:
    }//GEN-LAST:event_panelXogoKeyPressed

    private void botonFacilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFacilActionPerformed

    }//GEN-LAST:event_botonFacilActionPerformed

    private void botonMedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMedioActionPerformed

    }//GEN-LAST:event_botonMedioActionPerformed

    private void botonDificilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDificilActionPerformed

    }//GEN-LAST:event_botonDificilActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        
        timer.stop();
        xogo=null;
        jFrame1.dispose();
        this.dispose();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_exitActionPerformed

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
    
        timer.stop();
        xogo.setPausa(true);
        xogo=null;
        jFrame1.dispose();
        this.dispose();
        
        
       

        // TODO add your handling code here:
    }//GEN-LAST:event_botonSalirActionPerformed

    private void tqlbtnPausaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tqlbtnPausaActionPerformed

        if (timer.isRunning()) {
            timer.stop();
            xogo.setPausa(true);

        } else {
            timer.start();
            panelXogo.requestFocus();
            xogo.setPausa(false);

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_tqlbtnPausaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton botonDificil;
    private javax.swing.JRadioButton botonFacil;
    private javax.swing.JRadioButton botonMedio;
    private javax.swing.JButton botonSalir;
    private javax.swing.ButtonGroup botonesDificultad;
    private javax.swing.JLabel cajaPuntuación;
    private javax.swing.JLabel cajaTiempo;
    private javax.swing.JButton exit;
    private javax.swing.JLabel gameOver;
    private javax.swing.JButton iniciar;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelFondo;
    private javax.swing.JLabel lblLblnumlinas;
    private javax.swing.JPanel panelLabels;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelXogo;
    private javax.swing.JLabel temporizador;
    private javax.swing.JToggleButton tqlbtnPausa;
    // End of variables declaration//GEN-END:variables

}
