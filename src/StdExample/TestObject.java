package StdExample;

import Graphics.Event;
import Graphics.LogicObject;
import Graphics.World;

import javax.swing.*;

public class TestObject implements LogicObject {
    private final World world;
    private int posX;
    private int posY;

    private final ImageIcon stdIcon;
    private ImageIcon icon;

    public TestObject(World world) {
        this.world = world;

        posX = 4;
        posY = 4;

        stdIcon = new ImageIcon("D:\\OneDrive\\Schule\\QI\\Info\\QuestV5.0\\imgs\\X.png");
        icon = stdIcon;

        world.register(this, "w");
        world.register(this, "MOUSE");
    }

    @Override
    public boolean isShown() {
        return true;
    }

    @Override
    public void mouseOrKeyPressed(Event event) {
        if (event.keyPressed == 'w') {
            posX = (int) (Math.random() * 8);
            posY = (int) (Math.random() * 5);
            world.redraw();
        }
        if (event.mouseButtonPressed == 1)
        {
            posX = event.mousePosX;
            posY = event.mousePosY;
            world.redraw();
        }
    }

    @Override
    public ImageIcon getImage() {
        return icon;
    }

    @Override
    public void setResizedImage(ImageIcon icon) {
        this.icon = icon;
    }

    @Override
    public int getXPos() {
        return posX;
    }

    @Override
    public int getYPos() {
        return posY;
    }
}
