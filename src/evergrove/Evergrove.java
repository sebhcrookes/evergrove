package evergrove;

import evergrove.rendering.Renderer;

public class Evergrove {

    private Configuration config;
    private Component primaryComponent; // Could be a state manager, or just the current level (renders all other components in a tree-like manner)
    private MainWindow window;
    private Renderer renderer;

    private final int numFramesAveraged = 10;
    private double rollingAverageFPS = 0;

    public Evergrove(Configuration pConfig, Component pPrimaryComponent) {
        config = pConfig;
        primaryComponent = pPrimaryComponent;
        window = new MainWindow(config);
        renderer = new Renderer(config, window);

        // First, start the update loop
        Thread thread = new Thread(this::updateLoop);
        thread.start();

        renderLoop();
    }

    public void updateConfiguration(Configuration pConfig) {
        config = pConfig;
        window.updateConfiguration(config);
        renderer.updateConfiguration(config);
    }

    public void updateLoop() {
        // Timer for keeping the loop running at fixed rate
        long startTime;

        double dt = 1.0 / (float)config.getUpdatesPerSecond();
        while(window.isVisible()) {
            startTime = System.nanoTime();

            // Update all components
            primaryComponent.update(this, (float)dt);

            // Delay until next update is due
            while((System.nanoTime() - startTime) / 1000000000.0 < dt) {}
        }
    }

    public void renderLoop() {

        // Timer for calculating frametime
        long startTime;

        // FPS trackers
        int frameCount = 0;

        // Main rendering loop
        while(window.isVisible()) {
            startTime = System.nanoTime();

            // Clear buffer
            renderer.clear();

            // Render all components
            primaryComponent.render(this, renderer);

            // Swap buffers to display on window
            renderer.swapBuffers();

            // Delay until next frame is due (if FPS is capped)
            if(config.isFPSCapped()) {
                while((System.nanoTime() - startTime) / 1000000000.0 < 1.0 / (double)config.getFPSCap()) {}
            }

            /* Calculating frametime and FPS */
            long endTime = System.nanoTime();

            double frameTimenS = endTime - startTime; // Frametime in nanoseconds
            double frameTimeS = frameTimenS / 1000000000.0;
            double FPS = 1.0 / frameTimeS;

            rollingAverageFPS = ((rollingAverageFPS * numFramesAveraged - rollingAverageFPS) + FPS) / numFramesAveraged;

            frameCount++;
        }
    }

    public int getCurrentFPS() {
        return (int) rollingAverageFPS;
    }
}
