package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StickmanFight extends JPanel implements ActionListener, KeyListener {
    private static final int WIDTH = 800, HEIGHT = 500;
    private static final int STICKMAN_WIDTH = 50, STICKMAN_HEIGHT = 100;
    private static final int SPEED = 10, PUNCH_DAMAGE = 10;

    private int player1X = 100, player1Y = HEIGHT - STICKMAN_HEIGHT - 50;
    private int player2X = WIDTH - 150, player2Y = HEIGHT - STICKMAN_HEIGHT - 50;

    private int player1HP = 100, player2HP = 100;
    private boolean player1Punching = false, player2Punching = false;

    private Timer timer;

    public StickmanFight() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.addKeyListener(this);

        timer = new Timer(1000 / 60, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Chiziq (arena chegarasi)
        g.setColor(Color.GRAY);
        g.fillRect(0, HEIGHT - 50, WIDTH, 10);

        // O'yinchilarning HP statusi
        g.setColor(Color.RED);
        g.fillRect(50, 20, player1HP * 2, 20);
        g.fillRect(WIDTH - 250, 20, player2HP * 2, 20);
//

        g.setColor(Color.BLACK);
        g.fillOval(player1X, player1Y, STICKMAN_WIDTH, STICKMAN_HEIGHT); // Player 1
        g.fillOval(player2X, player2Y, STICKMAN_WIDTH, STICKMAN_HEIGHT); // Player 2


        if (player1Punching) {
            g.setColor(Color.RED);
            g.fillOval(player1X + STICKMAN_WIDTH, player1Y + 30, 20, 20);
        }
        if (player2Punching) {
            g.setColor(Color.RED);
            g.fillOval(player2X - 20, player2Y + 30, 20, 20);
        }


        if (player1HP <= 0) {
            g.setColor(Color.BLUE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Player 2 Wins!", WIDTH / 2 - 100, HEIGHT / 2);
            timer.stop();
        } else if (player2HP <= 0) {
            g.setColor(Color.BLUE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Player 1 Wins!", WIDTH / 2 - 100, HEIGHT / 2);
            timer.stop();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Player 1 Harakati
        if (e.getKeyCode() == KeyEvent.VK_A && player1X > 0) {
            player1X -= SPEED;
        }
        if (e.getKeyCode() == KeyEvent.VK_D && player1X < WIDTH - STICKMAN_WIDTH) {
            player1X += SPEED;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player1Y -= 50;
        }
        if (e.getKeyCode() == KeyEvent.VK_F) {
            player1Punching = true;
            if (Math.abs(player1X + STICKMAN_WIDTH - player2X) < 20) {
                player2HP -= PUNCH_DAMAGE;
            }
//        }
//
//        // Player 2 Harakati
//        if (e.getKeyCode() == KeyEvent.VK_LEFT && player2X > 0) {
//            player2X -= SPEED;
//        }
//        if (e.getKeyCode() == KeyEvent.VK_RIGHT && player2X < WIDTH - STICKMAN_WIDTH) {
//            player2X += SPEED;
//        }
//        if (e.getKeyCode() == KeyEvent.VK_UP) {
//            player2Y -= 50;
//        }
//        if (e.getKeyCode() == KeyEvent.VK_L) {
//            player2Punching = true;
//            if (Math.abs(player2X - (player1X + STICKMAN_WIDTH)) < 20) {
//                player1HP -= PUNCH_DAMAGE;
//            }
//        }
//
//        repaint();
//    }

//    @Override
//    public void keyReleased(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_F) {
//            player1Punching = false;
//        }
//        if (e.getKeyCode() == KeyEvent.VK_L) {
//            player2Punching = false;
//        }
//    }

//    @Override
//    public void keyTyped(KeyEvent e) {
//    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Stickman Fight");
//        StickmanFight game = new StickmanFight();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(game);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }

}