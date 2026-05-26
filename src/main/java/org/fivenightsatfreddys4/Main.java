/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.fivenightsatfreddys4;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author bojunxu
 */
public class Main extends javax.swing.JFrame {

    public Main() throws IOException {
        initComponents();
        setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() throws IOException {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1024, 768));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        ImageIcon viewport = new ImageIcon(ImageIO.read(getClass().getResource("/mainMenu/627.png")));
        JLabel screen = new JLabel(viewport);
        FramePlayer.link(screen);
        this.setContentPane(screen);

        FrameSequence test = new FrameSequence("foxy/jumpscare");
        FramePlayer.playClip(test);

        Audio jumpscare = new Audio("scream2.wav");
        jumpscare.play();


        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main().setVisible(true);
                    Timer renderTimer = new Timer();
                    TimerTask render = new TimerTask() {
                        @Override
                        public void run() {
                            FramePlayer.tick();
                        }
                    };
                    renderTimer.scheduleAtFixedRate(render,0,41);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
