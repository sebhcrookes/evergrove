package com.evergrove;

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

            renderer.setPixel(10, 10, 0xFF00FF00);
            renderer.drawRect(new Vector2i(0, 0), config.getContentSize(), 0xFFFFFFFF);

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
