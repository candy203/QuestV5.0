package Graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class GraphicsManager {
    private int cellSize;
    private final int rowCount;
    private final int colCount;

    private final GraphicsPanel panel;

    private final Collection<LogicObject> logicObjects;

    public GraphicsManager(int rowCount, int colCount, int cellSize)
    {
        logicObjects = new ArrayList<>();

        this.cellSize = cellSize;
        this.rowCount = rowCount;
        this.colCount = colCount;

        JDialog dialog = new JDialog();
        panel = new GraphicsPanel(rowCount, colCount, cellSize, this);

        dialog.setSize(colCount * cellSize + 18, rowCount * cellSize + 41); // The top bar ist included in the height

        dialog.add(panel);
        dialog.setVisible(true);
    }

    public void addObject(LogicObject obj)
    {
        logicObjects.add(obj);

        redraw();
    }

    Collection<LogicObject> getObjects() {
        return logicObjects;
    }

    public void redraw()
    {
        panel.repaint();
    }

    public void register(LogicObject obj, String c)
    {
        panel.register(obj, c);
    }

    ImageIcon resize(ImageIcon icon)
    {
        Image imgOld = icon.getImage();
        Image imgNew = imgOld.getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
        return new ImageIcon(imgNew);
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColCount() {
        return colCount;
    }
}
