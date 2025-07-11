package com.evergrove;

import com.evergrove.rendering.Image;
import com.evergrove.rendering.Renderer;

public class Evergrove {

    private Configuration config;
    private MainWindow window;
    private Renderer renderer;

    Evergrove(Configuration pConfig) {
        config = pConfig;
        window = new MainWindow(config);
        renderer = new Renderer(config, window);

        renderLoop();
    }

    public void updateConfiguration(Configuration pConfig) {
        config = pConfig;
        window.updateConfiguration(config);
        renderer.updateConfiguration(config);
    }

    public void renderLoop() {

        // Timer for calculating frametime
        long startTime;

        // FPS trackers
        double averageFPS = 0;
        int frameCount = 0;

        // Main rendering loop
        while(window.isVisible()) {
            startTime = System.nanoTime();

            // Clear buffer
            renderer.clear();

            // Render all components and objects

            renderer.drawRect(new Vector2i(0, 0), config.getContentSize(), 0xFFFFFFFF);
            renderer.fillRect(new Vector2i(100, 100), new Vector2i(200, 50), 0xFF00FF00);

            renderer.drawText("This is a test of the text rendering system!\n... and newline capabilities", new Vector2i(10, 10), 0xFFFF0000);

            // Swap buffers to display on window
            renderer.swapBuffers();

            /* Calculating frametime and FPS */
            long endTime = System.nanoTime();

            double frameTimenS = endTime - startTime; // Frametime in nanoseconds
            double frameTimeS = frameTimenS / 1000000000.0;
            double FPS = 1.0 / frameTimeS;
            averageFPS = (averageFPS * frameCount + FPS) / (double)(frameCount + 1);
            frameCount++;
        }

        System.out.println("Average FPS: " + averageFPS);
    }
}
