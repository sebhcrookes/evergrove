package evergrove;

public class Configuration {

    private String windowTitle;
    private Vector2i contentSize; // Cannot be changed at runtime
    private boolean windowResizeable;

    /* === Rendering === */

    // FPS
    private boolean isFPSCapped;
    private int FPSCap;

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
