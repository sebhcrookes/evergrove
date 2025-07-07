public class Launcher {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setWindowTitle("Testing");
        config.setWindowSize(new Vector2i(500, 500));

        Evergrove evergrove = new Evergrove(config);

        while(true) {}

    }
}
