package Memory;

import Graphics.Event;
import Graphics.GraphicsManager;
import Graphics.LogicObject;

/**
 * Klasse zum Empfangen des Inputs
 */
public class InputObject extends LogicObject {
    private final MemoryGame game;
    private final GraphicsManager manager;

    InputObject(GraphicsManager manager, MemoryGame game) {
        super(manager);

        this.game = game;
        this.manager = manager;
        manager.register(this, "MOUSE");

        setShown(false);
    }

    /**
     * Reaktion aufs Geschehene
     * @param event Ein Eventobjekt mit Geschehnissen
     */
    @Override
    public void mouseOrKeyPressed(Event event) {
        if (event.mouseButtonPressed == 1)
        {
            game.pressed(event.mousePosX, event.mousePosY);
            manager.redraw();
        }
    }
}
