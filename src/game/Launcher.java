package game;

import evergrove.Component;
import evergrove.Configuration;
import evergrove.Evergrove;
import evergrove.Vector2i;

public class Launcher {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setWindowTitle("Evergrove Test Build");
        config.setContentSize(new Vector2i(1000, 500));
        config.setWindowResizeable(true);
        config.setUpdatesPerSecond(60);
        config.setFPSCapped(false);

        PrimaryComponent primaryComponent = new PrimaryComponent();

        Evergrove evergrove = new Evergrove(config, primaryComponent);
    }
}
