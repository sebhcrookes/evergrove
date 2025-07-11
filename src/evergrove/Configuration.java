package evergrove;

public class Configuration {

    /* Configuration set by the game. Default values are also set here */

    private String windowTitle = "Evergrove";
    private Vector2i contentSize = new Vector2i(500, 500); // Cannot be changed at runtime
    private boolean windowResizeable = false;

    private int updatesPerSecond = 60;

    /* === Rendering === */

    // FPS
    private boolean isFPSCapped = true;
    private int FPSCap = 60;

    public String getWindowTitle() {
        return windowTitle;
    }

    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }

    public Vector2i getContentSize() {
        return contentSize;
    }

    public void setContentSize(Vector2i contentSize) {
        this.contentSize = contentSize;
    }

    public boolean isWindowResizeable() {
        return windowResizeable;
    }

    public void setWindowResizeable(boolean windowResizeable) {
        this.windowResizeable = windowResizeable;
    }

    public int getUpdatesPerSecond() {
        return updatesPerSecond;
    }

    public void setUpdatesPerSecond(int updatesPerSecond) {
        this.updatesPerSecond = updatesPerSecond;
    }

    public boolean isFPSCapped() {
        return isFPSCapped;
    }

    public void setFPSCapped(boolean FPSCapped) {
        isFPSCapped = FPSCapped;
    }

    public int getFPSCap() {
        return FPSCap;
    }

    public void setFPSCap(int FPSCap) {
        this.FPSCap = FPSCap;
    }
}
