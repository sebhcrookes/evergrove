package evergrove;

import evergrove.rendering.Renderer;

public interface Component {

    public void update(Evergrove e, float dt);
    public void render(Evergrove e, Renderer r);
}
