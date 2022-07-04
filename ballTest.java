package project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ballTest {

    static JPanel ball = new JPanel();
    static JPanel desk = new JPanel();
    static JLabel score = new JLabel();
    static int scoreCount = 0;
    static int desk_x = 450;
    static int desk_y = 630;
    static int ball_x = 470;
    static int ball_y = 0;
    static int maxX;
    static int maxY;
    static boolean boolx = false;
    static boolean booly = false;
    static int p = 0;
    static boolean gameOver = false;
    static Timer timer = new Timer(10, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            p++;
            if (p >= 50) {
                if (ball_x+30 >= maxX) {
                    boolx = true;
                }else if (ball_x <= 0) {
                    boolx = false;
                }
                if (boolx) {
                    ball_x -= 5;
                }else {
                    ball_x += 5;
                }
                if (ball_y+30 >= desk_y && (ball_x+30 <= desk_x+100 && ball_x >= desk_x)) {
                    scoreCount++;
                    score.setText("Score: "+scoreCount);
                    booly = true;
                }else if (ball_y <= 0) {
                    booly = false;
                }
                if (booly) {
                    ball_y -= 5;
                }else {
                    ball_y += 5;
                }
                if (ball_y+30 >= maxY) {
                    gameOver = true;
                    timer.stop();
                }
                ball.setLocation(ball_x,ball_y);
            }
        }
    });

    public static void main(String[] args) {

        JFrame jf = new JFrame("ballTest");

        jf.setSize(1000,700);
        jf.setVisible(true);
        jf.setLayout(null);

        ball.setBounds(470,0,30,30);
        ball.setBackground(Color.red);

        desk.setBounds(450,630,100,30);
        desk.setBackground(Color.black);

        score.setBounds(850,50,100,30);
        score.setText("Score: "+scoreCount);

        jf.add(ball);
        jf.add(desk);
        jf.add(score);

        jf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 32) {
                    maxX = jf.getWidth()-15;
                    maxY = jf.getHeight()-40;
                    if (gameOver) {
                        ball_x = 470;
                        ball_y = 0;
                        desk_x = 450;
                        p = 0;
                        ball.setLocation(ball_x,ball_y);
                        desk.setLocation(desk_x,desk_y);
                        scoreCount = 0;
                        score.setText("Score: 0");
                        timer.start();
                    }else {
                        timer.start();
                    }
                }
                if (e.getKeyCode() == 37) {
                    desk_x -= 35;
                }
                if (e.getKeyCode() == 39) {
                    desk_x += 35;
                }
                desk.setLocation(desk_x, 630);
            }
        });

    }

}