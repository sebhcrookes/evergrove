package com.evergrove.rendering;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {

    BufferedImage internalImage;

    public Image(String path) {
        try {
            internalImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.err.println("Requested image does not exist at given location");
            e.printStackTrace();
        }
    }

    public BufferedImage getInternalImage() {
        return internalImage;
    }

    public int getPixel(int x, int y) {
        return internalImage.getRGB(x, y);
    }

    public int getWidth() {
        return internalImage.getWidth();
    }

    public int getHeight() {
        return internalImage.getHeight();
    }
}
