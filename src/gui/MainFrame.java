package gui;

import logic.LevelReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Yevhen on 24.06.2015.
 */
public class MainFrame extends JFrame {
    GamePanel panel;
    JLabel levelLabel;
    LevelReader levelReader;
    int levelNum = 1;


    public MainFrame(String title) {
        super(title);
        initComponents();
    }

    private void initComponents() {
        levelReader = new LevelReader();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        JPanel rightPanel = new JPanel();
        levelLabel = new JLabel("Уровень: 1");
        rightPanel.add(levelLabel);
        rightPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        getContentPane().add(rightPanel, BorderLayout.EAST);
        getContentPane().add(panel = new GamePanel());
        panel.setLevel(levelReader.readLevel(levelNum));
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                boolean moved = false;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT: moved = panel.moveMan(-1,0); break;
                    case KeyEvent.VK_RIGHT: moved = panel.moveMan(1,0); break;
                    case KeyEvent.VK_UP: moved = panel.moveMan(0,-1); break;
                    case KeyEvent.VK_DOWN: moved = panel.moveMan(0,1); break;
                    case KeyEvent.VK_ESCAPE: panel.setLevel(levelReader.readLevel(levelNum));
                        break;
                }
                if (moved) {
                    if (panel.checkEnd()) {
                        levelNum++;
                        if (levelNum>60) {
                            JOptionPane.showMessageDialog(MainFrame.this,"Поздравляем","Выигрыш",JOptionPane.INFORMATION_MESSAGE);
                            System.exit(0);
                        }
                        panel.setLevel(levelReader.readLevel(levelNum));
                    }
                }
                repaint();
            }
        });
        pack();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new MainFrame("Sokoban").setVisible(true));
    }
}
