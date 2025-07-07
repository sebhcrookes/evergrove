import javax.swing.*;

public class Window {

    private JFrame window;

    private Configuration config;

    public Window(Configuration pConfig) {
        config = pConfig;
        window = new JFrame(config.getWindowTitle());
        window.setSize(config.getWindowSize().getX(), config.getWindowSize().getY());
        window.setResizable(false);
        setVisible(true);
    }

    public void setVisible(boolean visible) {
        window.setVisible(visible);
    }
}
