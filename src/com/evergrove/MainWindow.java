package com.evergrove;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainWindow extends JFrame {

    JPanel panel;

    private Configuration config;

    private BufferedImage buffer;

    boolean repainting = false;

    private int lastWidth = 0;
    private int lastHeight = 0;

    public MainWindow(Configuration pConfig) {
        config = pConfig;

        updateConfiguration(config);
        this.setResizable(config.isWindowResizeable());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBackground(Color.BLACK);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                //g.drawImage(buffer, 0, 0, null);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(config.getContentSize().getX(), config.getContentSize().getY());
            }
        };

        this.add(panel);
        this.pack();

        // Renderer sets the window to visible when it is ready
    }

    public void updateConfiguration(Configuration pConfig) {
        config = pConfig;

        this.setTitle(config.getWindowTitle());
    }

    public void setBuffer(BufferedImage pBuffer) {
        Graphics g = panel.getGraphics();

        int windowSizeX = this.getContentPane().getSize().width;
        int windowSizeY = this.getContentPane().getSize().height;

        int imageSizeX = windowSizeX;
        int imageSizeY = windowSizeY;

        if(config.isWindowResizeable()) {
            /* First, clear whole window if resized because there may be old bits of the JPanel left behind */

            if(lastWidth != windowSizeX || lastHeight != windowSizeY) {
                Rectangle screen = new Rectangle(0, 0, panel.getWidth(), panel.getHeight());
                g.clearRect(0, 0, (int) screen.getWidth(), (int) screen.getHeight());
            }

            lastWidth = windowSizeX;
            lastHeight = windowSizeY;

            /* Scaling image + calculations to determine how the content should be displayed in the window */

            double targetAspectRatio = (double)(pBuffer.getWidth()) / (double)(pBuffer.getHeight());
            double windowAspectRatio = (double)(windowSizeX) / (double)(windowSizeY);

            // If the window is wider than the target aspect ratio
            if(windowAspectRatio > targetAspectRatio) {
                // Resize the width so that the panel fits within the window (height is completely filled)
                imageSizeX = (int)((double)(windowSizeY) * targetAspectRatio);
            } else {
                // Resize the height so that the panel fits within the window (width is completely filled)
                imageSizeY = (int)((double)(windowSizeX) / targetAspectRatio);
            }

            // Scale image to given size, and draw the image centered in the window
            Image scaledImage = pBuffer.getScaledInstance(imageSizeX, imageSizeY, Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, (windowSizeX - imageSizeX) / 2, (windowSizeY - imageSizeY) / 2, this);
        } else {
            // If resizing not supported, simply draw the image to the window
            g.drawImage(pBuffer, 0, 0, this);
        }

        g.dispose();
    }
}
