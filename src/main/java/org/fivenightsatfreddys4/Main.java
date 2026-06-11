/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.fivenightsatfreddys4;

import org.fivenightsatfreddys4.animation.FramePlayer;
import org.fivenightsatfreddys4.animation.FrameSequences;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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

    private static JLabel screen;

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

    private boolean inNight = false;

    static private JPanel bonnieSquare;

    static private JPanel foxySquare;

    static private JPanel chicaSquare;

    private static JPanel fredbearSquare;


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
        screen = new JLabel(viewport);
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
            inNight = true;
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
        center.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Check if BUTTON3 (the right mouse button) was pressed
                if (e.getButton() == MouseEvent.BUTTON3) {
                    if (player.getDoorCooldown() <= 0 && player.getMoveCooldown() <= 0) {
                        if (player.getPos() == Position.CLOSET && !player.isDoorClosed(Position.CLOSET)) {
                            FrameSequences.showCloset();
                        }
                        else if (player.getPos() == Position.LEFT_DOOR && !player.isDoorClosed(Position.LEFT_DOOR)) {
                            FrameSequences.showLeftDoor();
                        }
                        else if (player.getPos() == Position.RIGHT_DOOR && !player.isDoorClosed(Position.RIGHT_DOOR)) {
                            FrameSequences.showRightDoor();
                        }
                    }
                } else if (e.getButton() == MouseEvent.BUTTON1) {
                    if (player.getPos() == Position.BEDROOM) {
                        player.move(Position.CLOSET);
                    } else if (player.getPos() != Position.BED) {
                        player.toggleDoor();
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    if (player.getMoveCooldown() <= 0) {
                        try {
                            if (player.getPos() == Position.CLOSET && !player.isDoorClosed(Position.CLOSET)) {
                                screen.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/checkClosetEnd/047.png"))));
                            }
                            else if (player.getPos() == Position.LEFT_DOOR && !player.isDoorClosed(Position.LEFT_DOOR)) {
                                screen.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/checkLeftEnd/062.png"))));
                            }
                            else if (player.getPos() == Position.RIGHT_DOOR && !player.isDoorClosed(Position.RIGHT_DOOR)) {
                                screen.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/checkRightEnd/065.png"))));
                            }
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                else if (e.getButton() == MouseEvent.BUTTON1) {
                    if (player.getPos() != Position.BED && player.getPos() != Position.BEDROOM) {
                        player.toggleDoor();
                    }
                }
            }
        });
        center.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent evt) {
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

        for (Position pos : Position.values()) {
            JPanel room = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    // Clear the graphics object safely without destroying underlying paint
                    super.paintComponent(g);

                    Graphics2D g2d = (Graphics2D) g.create();
                    // Set the background color and an Alpha value (0 = clear, 255 = solid)
                    g2d.setColor(new Color(150, 150, 150, 165)); // Semi-transparent black
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                    g2d.dispose();
                }
            };
            int x = pos.getCol();
            int y = pos.getRow();
            room.setBounds(802 + x * 50, 20 + y * 50, 40, 40);
            getContentPane().add(room);
            room.setOpaque(false);

        }

        bonnieSquare = new JPanel();
        bonnieSquare.setBackground(new Color(0x575AFF));

        chicaSquare = new JPanel();
        chicaSquare.setBackground(new Color(0xFFFF38));

        foxySquare = new JPanel();
        foxySquare.setBackground(new Color(0xFF5626));

        fredbearSquare = new JPanel();
        fredbearSquare.setBackground(new Color(0x7E6800));

        getContentPane().add(bonnieSquare);
        getContentPane().add(chicaSquare);
        getContentPane().add(foxySquare);
        getContentPane().add(fredbearSquare);

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
                            if (player != null) {
                                player.tick();
                                if (bonnie != null) {
                                    bonnieSquare.setBounds(810 + bonnie.currentPos.getCol() * 50, 30 + bonnie.currentPos.getRow() * 50, 10, 10);
                                }
                                if (foxy != null) {
                                    foxySquare.setBounds(817 + foxy.currentPos.getCol() * 50, 40 + foxy.currentPos.getRow() * 50, 10, 10);
                                }
                                if (chica != null) {
                                    chicaSquare.setBounds(824 + chica.currentPos.getCol() * 50, 30 + chica.currentPos.getRow() * 50, 10, 10);
                                }
                                if (fredbear != null && fredbear.aggressionLevel > 0) {
                                    fredbearSquare.setBounds(817 + fredbear.currentPos.getCol() * 50, 20 + fredbear.currentPos.getRow() * 50, 10, 10);
                                }
                            }
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
