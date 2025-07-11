package evergrove.rendering;

import evergrove.Configuration;
import evergrove.MainWindow;
import evergrove.Vector2i;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {

    private Configuration config;
    private final MainWindow window;

    private final Vector2i panelSize;

    private final BufferedImage buffer;

    private Font currentFont;

    public Renderer(Configuration pConfig, MainWindow pWindow) {
        window = pWindow;
        updateConfiguration(pConfig);
        panelSize = pConfig.getContentSize();

        // Setting up canvas

        buffer = new BufferedImage(panelSize.getX(), panelSize.getY(), BufferedImage.TYPE_INT_ARGB);

        currentFont = new Font("res/font.png");

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

        int colourBefore = buffer.getRGB(x, y);

        double alpha = (colour >> 24) & 0xFF;

        int r = ((colourBefore >> 16) & 0xFF) - (int)((((colourBefore >> 16) & 0xFF) - ((colour >> 16) & 0xFF)) * (alpha / 255.0));
        int g = ((colourBefore >> 8) & 0xFF) - (int)((((colourBefore >> 8) & 0xFF) - ((colour >> 8) & 0xFF)) * (alpha / 255.0));
        int b = (colourBefore & 0xFF) - (int)(((colourBefore & 0xFF) - (colour & 0xFF)) * (alpha / 255.0));
        buffer.setRGB(x, y, 0xFF000000 | (r << 16) | (g << 8) | (b));
    }

    public void clear() {
        fill(0xFF000000);
    }

    public void fill(int fillColour) {
        Graphics2D graphics = (Graphics2D) buffer.getGraphics();
        Rectangle screen = new Rectangle(0, 0, panelSize.getX(), panelSize.getY());
        graphics.setColor(new Color(fillColour, true));
        graphics.fillRect(0,0, (int)screen.getWidth(), (int)screen.getHeight());
    }

    public void drawRect(Vector2i pos, Vector2i size, int colour) {
        Graphics2D graphics = (Graphics2D) buffer.getGraphics();
        graphics.setColor(new Color(colour, true));
        graphics.drawRect(pos.getX(), pos.getY(), (int)size.getX() - 1, (int)size.getY() - 1);
    }

    public void fillRect(Vector2i pos, Vector2i size, int colour) {
        Graphics2D graphics = (Graphics2D) buffer.getGraphics();
        graphics.setColor(new Color(colour, true));
        graphics.fillRect(pos.getX(), pos.getY(), (int)size.getX(), (int)size.getY());
    }

    public void drawImage(Image image, Vector2i pos) {
        Graphics2D graphics = (Graphics2D) buffer.getGraphics();
        graphics.drawImage(image.getInternalImage(), pos.getX(), pos.getY(), null);
    }

    public void drawText(String text, Vector2i pos, int colour) {
        Graphics2D graphics = (Graphics2D) buffer.getGraphics();
        int xOff = 0;
        int yOff = 0;
        for(int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if(c == '\n') {
                yOff += currentFont.getFontHeight();
                xOff = 0;
                continue;
            }

            try {
                BufferedImage cImg = currentFont.getChar(c);

                // Goes through each pixel of the image and draws any which are filled, in the given colour
                for(int y = 0; y < cImg.getHeight(); y++) {
                    for(int x = 0; x < cImg.getWidth(); x++) {
                        int pixel = cImg.getRGB(x, y);
                        if(pixel != 0) {
                            setPixel(pos.getX() + xOff + x, pos.getY() + yOff + y, colour);
                        }
                    }
                }

                xOff += cImg.getWidth() - 1; // -1 to reduce spacing between characters
            } catch(IndexOutOfBoundsException e) {}
        }
    }
}