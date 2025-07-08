package com.evergrove;

public class Launcher {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setWindowTitle("Testing");
        config.setContentSize(new Vector2i(1000, 500));
        config.setWindowResizeable(true);

        Evergrove evergrove = new Evergrove(config);
    }
}
