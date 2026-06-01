/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.fivenightsatfreddys4;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author bojunxu
 */
public class Main extends javax.swing.JFrame {

    public final static int renderInterval = 41;

    public final static Timer timer = new Timer();

    public static Bonnie bonnie;

    public static Chica chica;

    public static Freddy freddy;

    public static Foxy foxy;

    public static Fredbear fredbear;

    public static int hour;

    private JButton newGame;

    public Main() throws IOException {
        initComponents();
        setResizable(false);
    }

    @SuppressWarnings("unchecked")
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

        ImageIcon viewport = new ImageIcon(ImageIO.read(getClass().getResource("/mainMenu/1.png")));
        JLabel screen = new JLabel(viewport);
        FramePlayer.link(screen);
        this.setContentPane(screen);

        FrameSequences.introWarning.play();
        timer.schedule(showButtons,FrameSequences.introWarning.getLength());
        System.out.println(FrameSequences.introWarning.getLength());

        newGame = new JButton("New Game", new ImageIcon(ImageIO.read(getClass().getResource("/mainMenu/731.png"))));
        newGame.setBounds(433,360,168,22);
        newGame.setVisible(false);
        newGame.setContentAreaFilled(false);
        newGame.setFocusPainted(false);
        newGame.setOpaque(false);
        newGame.setBorderPainted(false);
        getContentPane().add(newGame);

        pack();
    }

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
                    TimerTask render = new TimerTask() {
                        @Override
                        public void run() {
                            FramePlayer.tick();
                        }
                    };
                    timer.scheduleAtFixedRate(render,0,renderInterval);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    private final TimerTask showButtons = new TimerTask() {
        @Override
        public void run() {
            newGame.setVisible(true);
            System.out.println("I show u new game");
        }
    };


}
