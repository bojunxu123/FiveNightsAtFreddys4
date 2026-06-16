package org.fivenightsatfreddys4;

import org.fivenightsatfreddys4.animation.FramePlayer;
import org.fivenightsatfreddys4.animation.FrameSequences;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 *  Our awesome recreation of five nights at freddy's 4, some say the better version
 * @author bojunxu
 */
public class Main extends javax.swing.JFrame {

    // The time in milliseconds between frames, 41 ~= 24
    public final static int renderInterval = 41;

    // The label used to displays the images on the screen
    private static JLabel screen;

    // The timer used for scheduling things during a night
    public static Timer timer = new Timer();

    // The timer used only for rendering
    private final static Timer renderTimer = new Timer();

    // The player
    public static Player player;

    // Bonnie
    public static Bonnie bonnie;

    // Chica
    public static Chica chica;

    // Freddy
    public static Freddy freddy;

    // Foxy
    public static Foxy foxy;

    // Fredbear
    public static Fredbear fredbear;

    // The current ingame hour
    public static int hour = 0;

    // The night buttons
    private static JButton[] nightButtons = new JButton[5];

    // The left door clickbox
    private static JButton leftDoor;

    // The right door clickbox
    private static JButton rightDoor;

    // The centre clickbox
    private static JButton center;

    // The low clickbox
    private static JButton lower;

    // The map icon for Bonnie
    static private JPanel bonnieSquare;

    // The map icon for Fxoxy
    static private JPanel foxySquare;

    // The map icon for Chica
    static private JPanel chicaSquare;

    // The map icon for Fredbear
    private static JPanel fredbearSquare;

    // The label that tells the player the current time
    public static JLabel amLabel;

    // The creation of the jFrame
    public Main() throws IOException {
        initComponents();
        setResizable(false);
    }

    /*
    * inits the components
     */
    private void initComponents() throws IOException {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1024, 768));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        // Creates the objects used to display the screen.
        ImageIcon viewport = new ImageIcon(ImageIO.read(getClass().getResource("/mainMenu/00.png")));
        screen = new JLabel(viewport);
        FramePlayer.link(screen);
        this.setContentPane(screen);

        // PLays the opening warning
        FrameSequences.introWarning.play();
        timer.schedule(showButtons,FrameSequences.introWarning.getLength());

        // Creates all the night buttons
        for (int i = 0; i < 5; i++) {
            JButton button = new JButton("Night " + (i+1));
            button.setName(i+1 + "");
            button.setFont(new Font("Times New Roman",Font.BOLD,22));
            button.setForeground(new Color(255 - i * 50,10,0));
            nightButtons[i] = button;
            button.setBounds(433,360 + i * 45,168,30);
            button.setVisible(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setOpaque(false);
            button.setBorderPainted(false);
            // Add an event on click to start the corresponding night
            button.addActionListener(e -> {
                hour = 0;
                Nights.loadNight(Integer.parseInt(((JButton)e.getSource()).getText().split(" ")[1]));
                System.out.println("loading night sir");
                lower.setVisible(true);
                amLabel.setVisible(true);
                amLabel.setText("12 am");
                center.setVisible(true);
                rightDoor.setVisible(true);
                leftDoor.setVisible(true);
                for (JButton b : nightButtons) {
                    b.setVisible(false);
                }
            });
            getContentPane().add(button);
        }

        // Creates the left door clickbox
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

        // Creates the right door clickbox
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

        // Creates the centre clickbox
        center = new JButton();
        center.setBounds(256,0,512,600);
        center.setContentAreaFilled(false);
        center.setFocusPainted(false);
        center.setOpaque(false);
        center.setBorderPainted(false);
        center.setVisible(false);
        center.addMouseListener(new MouseAdapter() {
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
                        if (player.getPos() == Position.BED) {
                            FrameSequences.showBed();
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
                                screen.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/checkClosetEnd/17.png"))));
                            }
                            else if (player.getPos() == Position.LEFT_DOOR && !player.isDoorClosed(Position.LEFT_DOOR)) {
                                screen.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/checkLeftEnd/30.png"))));
                            }
                            else if (player.getPos() == Position.RIGHT_DOOR && !player.isDoorClosed(Position.RIGHT_DOOR)) {
                                screen.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/checkRightEnd/33.png"))));
                            }
                            else if (player.getPos() == Position.BED) {
                                screen.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/checkBed/19.png"))));
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

        // Creates the lower clickbox
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

        // Creates the squares used for the map
        bonnieSquare = new JPanel();
        bonnieSquare.setBackground(new Color(0x575AFF));

        chicaSquare = new JPanel();
        chicaSquare.setBackground(new Color(0xFFFF38));

        foxySquare = new JPanel();
        foxySquare.setBackground(new Color(0xFF5626));

        fredbearSquare = new JPanel();
        fredbearSquare.setBackground(new Color(0xD48900));

        bonnieSquare.setBounds(860, 30, 10, 10);

        foxySquare.setBounds(867, 40, 10, 10);

        chicaSquare.setBounds(874, 30, 10, 10);

        fredbearSquare.setBounds(867, 35, 10, 10);


        getContentPane().add(bonnieSquare);
        getContentPane().add(chicaSquare);
        getContentPane().add(foxySquare);
        getContentPane().add(fredbearSquare);

        // Creates the time display
        amLabel = new JLabel();
        amLabel.setBounds(20,10,100,50);
        amLabel.setText("12 am");
        amLabel.setFont(new Font("Times New Roman",Font.BOLD,30));
        amLabel.setForeground(Color.WHITE);

        amLabel.setVisible(false);

        getContentPane().add(amLabel);

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
                    // Creates a task to update all the required display info every render frame
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
                                    fredbearSquare.setBounds(817 + fredbear.currentPos.getCol() * 50, 35 + fredbear.currentPos.getRow() * 50, 10, 10);
                                }
                            }
                        }
                    };
                    // adds it to the render timer
                    renderTimer.scheduleAtFixedRate(render,0,renderInterval);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    // Shows the buttons when the screen is switched to the main menu
    private final TimerTask showButtons = new TimerTask() {
        @Override
        public void run() {
            for (JButton b : nightButtons) {
                b.setVisible(true);
            }
            FrameSequences.introWarning = null;
        }
    };

    // Used when the player wins or dies to reset everything and bring them back to the menu
    public static void endGame() {
        timer.cancel();
        timer = new Timer();

        player = null;
        chica = null;
        foxy = null;
        freddy = null;
        fredbear = null;
        bonnie = null;

        lower.setVisible(false);
        center.setVisible(false);
        rightDoor.setVisible(false);
        leftDoor.setVisible(false);

        amLabel.setVisible(false);

        timer.schedule(showMenu,FramePlayer.getDuration() * renderInterval + 130);
    }

    // Shows the menu
    private static TimerTask showMenu = new TimerTask() {
        @Override
        public void run() {
            for (JButton b : nightButtons) {
                b.setVisible(true);
            }
            try {
                screen.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/mainMenu/warning/03.png"))));
            } catch (IOException e) {
                System.out.println("errpor");
            }
        }
    };

}
