package com.evergrove.rendering;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Font {

    Image fontImage;

    ArrayList<BufferedImage> glyphs;

    public Font(String path) {
        fontImage = new Image(path);

        glyphs = new ArrayList<BufferedImage>();

        splitFont(fontImage.getHeight() - 1);
    }

    private void splitFont(int glyphHeight) {
        // The font has a 'locator strip' along the top, telling us where each character starts and ends
        // We move onto next character by a green pixel being present
        int charNum = 0;
        int lastLocatorPixel = 0;
        for(int x = 0; x < fontImage.getWidth(); x++) {
            int locatorStripValue = fontImage.getPixel(x, 0);

            if(locatorStripValue == 0xFF00FF00) { // We are at a 'locator pixel'
                glyphs.add(fontImage.getInternalImage().getSubimage(lastLocatorPixel, 1, x - lastLocatorPixel, glyphHeight));
                charNum++;
                lastLocatorPixel = x;
            }
        }
    }

    public BufferedImage getChar(char character) throws IndexOutOfBoundsException {
        int ascii = (int) character;
        return glyphs.get(ascii);
    }

    public int getFontHeight() {
        return fontImage.getHeight() - 1;
    }
}
