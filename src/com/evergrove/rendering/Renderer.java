package com.evergrove.rendering;

import com.evergrove.Configuration;
import com.evergrove.MainWindow;
import com.evergrove.Vector2i;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {

    private Configuration config;
    private MainWindow window;

    private Vector2i panelSize;

    private BufferedImage buffer;

    public Renderer(Configuration pConfig, MainWindow pWindow) {
        window = pWindow;
        updateConfiguration(pConfig);
        panelSize = pConfig.getContentSize();

        // Setting up canvas

        buffer = new BufferedImage(panelSize.getX(), panelSize.getY(), BufferedImage.TYPE_INT_ARGB);

        window.setVisible(true);
    }

    public void updateConfiguration(Configuration pConfig) {
        config = pConfig;
    }

    public void swapBuffers() {
        window.setBuffer(buffer);
    }

    /* ===== Rendering functions ===== */

    public void setPixel(int x, int y, int colour) {
        buffer.setRGB(x, y, colour);
    }

    public void clear() {
        fill(0);
    }

    public void fill(int fillColour) {
        Graphics2D graphics = (Graphics2D) buffer.getGraphics();
        Rectangle screen = new Rectangle(0, 0, panelSize.getX(), panelSize.getY());
        graphics.setColor(Color.BLUE);
        graphics.fillRect(0,0, (int)screen.getWidth(), (int)screen.getHeight());
    }

    public void drawRect(Vector2i pos, Vector2i size, int colour) {
        Graphics2D graphics = (Graphics2D) buffer.getGraphics();
        graphics.setColor(Color.RED);
        graphics.drawRect(pos.getX(), pos.getY(), (int)size.getX() - 1, (int)size.getY() - 1);
    }
}