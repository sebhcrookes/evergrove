package game;

import evergrove.Component;
import evergrove.Evergrove;
import evergrove.Vector2i;
import evergrove.rendering.Renderer;

public class PrimaryComponent implements Component {

   /* This class' purpose is to update and render all other components in the game.
      Therefore, this class can act as a state manager, deciding which objects to
      update and render based on which level/menu screen is currently open.
    */

    public PrimaryComponent() {

    }

    public void update(Evergrove e, float dt) {

    }

    public void render(Evergrove e, Renderer r) {
        r.fillRect(new Vector2i(100, 100), new Vector2i(200, 50), 0xFF00FF00);

        r.drawText("This is being drawn from the primary component", new Vector2i(10, 10), 0xFFFF0000);
    }

}
