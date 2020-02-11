package StdExample;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Graphics.*;
import java.util.Collection;

public class StdWorld implements World
{
    private final int rowCount;
    private final int colCount;
    private final int cellSize;

    private final WorldPanel panel;

    private final Collection<LogicObject> logicObjects;

    public StdWorld(int width, int height, int cellSize)
    {
        logicObjects = new ArrayList<>();

        //todo: Remove size and implement in Panel
        this.cellSize = cellSize;
        rowCount = height / cellSize;
        colCount = width / cellSize;

        JDialog dialog = new JDialog();
        panel = new WorldPanel(width, height, cellSize, this);

        dialog.setSize(width + 18, height + 41); // The top bar ist included in the height

        dialog.add(panel);
        dialog.setVisible(true);
    }

    @Override
    public void addObject(LogicObject obj)
    {
        logicObjects.add(obj);

        resize(obj);
        redraw();
    }

    //todo: Implement in World Panel
    private void resize(LogicObject obj)
    {
        Image imgOld = obj.getImage().getImage();
        Image imgNew = imgOld.getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
        obj.setResizedImage(new ImageIcon(imgNew));
    }

    @Override
    public void redraw()
    {
        panel.repaint();
    }

    @Override
    public void register(LogicObject obj, String c)
    {
        panel.register(obj, c);
    }

    @Override
    public Collection<LogicObject> getObjects()
    {
        return logicObjects;
    }

    @Override
    public int getRowCount()
    {
        return rowCount;
    }

    @Override
    public int getColCount()
    {
        return colCount;
    }

    public static void main(String[] args)
    {
        StdWorld world = new StdWorld(800, 500, 100);

        TestObject testObject = new TestObject(world);
        world.addObject(testObject);
    }
}
