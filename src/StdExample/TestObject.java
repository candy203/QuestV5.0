package StdExample;

import Graphics.Event;
import Graphics.GraphicsManager;
import Graphics.LogicObject;

import javax.swing.*;

public class TestObject extends LogicObject {

    public TestObject(GraphicsManager manager) {
        super(manager);

        setPosX(4);
        setPosY(4);

        setShown(true);

        setImage(new ImageIcon(TestObject.class.getResource("imgs/X.png")));

        getGraphicsManager().register(this, "w");
        getGraphicsManager().register(this, "MOUSE");
    }

    @Override
    public void mouseOrKeyPressed(Event event) {
        if (event.keyPressed == 'w') {
            setPosX((int) (Math.random() * getGraphicsManager().getColCount()));
            setPosY((int) (Math.random() * getGraphicsManager().getRowCount()));
            getGraphicsManager().redraw();
        }
        if (event.mouseButtonPressed == 1)
        {
            setPosX(event.mousePosX);
            setPosY(event.mousePosY);
            getGraphicsManager().redraw();
        }
    }
}
