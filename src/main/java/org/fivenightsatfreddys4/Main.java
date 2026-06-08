/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.fivenightsatfreddys4;

import org.fivenightsatfreddys4.animation.FramePlayer;
import org.fivenightsatfreddys4.animation.FrameSequences;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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

    private final static Timer renderTimer = new Timer();

    public static Player player;

    public static Bonnie bonnie;

    public static Chica chica;

    public static Freddy freddy;

    public static Foxy foxy;

    public static Fredbear fredbear;

    public static int hour = -1;

    private JButton newGame;

    private JButton leftDoor;

    private JButton rightDoor;

    private JButton center;

    private JButton lower;

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

        ImageIcon viewport = new ImageIcon(ImageIO.read(getClass().getResource("/mainMenu/000.png")));
        JLabel screen = new JLabel(viewport);
        FramePlayer.link(screen);
        this.setContentPane(screen);

        FrameSequences.introWarning.play();
        timer.schedule(showButtons,FrameSequences.introWarning.getLength());

        newGame = new JButton("New Game", new ImageIcon(ImageIO.read(getClass().getResource("/mainMenu/003.png"))));
        newGame.setBounds(433,360,168,22);
        newGame.setVisible(false);
        newGame.setContentAreaFilled(false);
        newGame.setFocusPainted(false);
        newGame.setOpaque(false);
        newGame.setBorderPainted(false);
        newGame.addActionListener(e -> {
            Nights.loadNight(1);
            System.out.println("loading night sir");
            newGame.setVisible(false);
            lower.setVisible(true);
            center.setVisible(true);
            rightDoor.setVisible(true);
            leftDoor.setVisible(true);
        });
        getContentPane().add(newGame);

        leftDoor = new JButton();
        leftDoor.setBounds(0,0,256,768);
        leftDoor.setContentAreaFilled(false);
        leftDoor.setFocusPainted(false);
        leftDoor.setOpaque(false);
        leftDoor.setBorderPainted(false);
        leftDoor.setVisible(false);
        leftDoor.addActionListener(e -> {
            if (player.getPos() == Position.BEDROOM) {
                player.move(Position.LEFT_DOOR);
            } else if (player.getPos() == Position.LEFT_DOOR) {
                player.toggleDoor();
            }
        });
        leftDoor.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent evt) {
                if (player.getPos() == Position.BEDROOM) {
                    player.pan(Position.LEFT_DOOR);
                }
            }
        });
        getContentPane().add(leftDoor);

        rightDoor = new JButton();
        rightDoor.setBounds(768,0,256,768);
        rightDoor.setContentAreaFilled(false);
        rightDoor.setFocusPainted(false);
        rightDoor.setOpaque(false);
        rightDoor.setBorderPainted(false);
        rightDoor.setVisible(false);
        rightDoor.addActionListener(e -> {
            if (player.getPos() == Position.BEDROOM) {
                player.move(Position.RIGHT_DOOR);
            }else if (player.getPos() == Position.RIGHT_DOOR) {
                player.toggleDoor();
            }
        });
        rightDoor.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent evt) {
                if (player.getPos() == Position.BEDROOM) {
                    player.pan(Position.RIGHT_DOOR);
                }
            }
        });
        getContentPane().add(rightDoor);

        center = new JButton();
        center.setBounds(256,0,512,600);
        center.setContentAreaFilled(false);
        center.setFocusPainted(false);
        center.setOpaque(false);
        center.setBorderPainted(false);
        center.setVisible(false);
        center.addActionListener(e -> {
            if (player.getPos() == Position.BEDROOM) {
                player.move(Position.CLOSET);
            } else if (player.getPos() == Position.CLOSET) {
                player.toggleDoor();
            } else if (player.getPos() == Position.BED) {
                // TODO: showBed()
            } else if (player.getPos() == Position.LEFT_DOOR) {
                FrameSequences.showLeftDoor();
            } else if (player.getPos() == Position.RIGHT_DOOR) {
                FrameSequences.showRightDoor();
            }
        });
        center.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent evt) {
                System.out.println("centre");
                if (player.getPos() == Position.BEDROOM) {
                    player.pan(Position.BEDROOM);
                }
            }
        });
        getContentPane().add(center);

        lower = new JButton();
        lower.setBounds(256,600,512,168);
        lower.setContentAreaFilled(false);
        lower.setFocusPainted(false);
        lower.setOpaque(false);
        lower.setBorderPainted(false);
        lower.setVisible(false);
        lower.addActionListener(e -> {
            if (player.getPos() == Position.BEDROOM) {
                player.move(Position.BED);
            } else {
                player.move(Position.BEDROOM);
            }
        });
        lower.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent evt) {
                if (player.getPos() == Position.BEDROOM) {
                    player.pan(Position.BEDROOM);
                }
            }
        });

        getContentPane().add(lower);

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
                    renderTimer.scheduleAtFixedRate(render,0,renderInterval);
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
            FrameSequences.introWarning = null;
        }
    };


}
