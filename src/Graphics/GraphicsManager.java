package Graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Managed die Graphik und dient als Schnittstelle zur Logik
 */
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

    /**
     * Fügt ein neues Objekt hinzu
     * @param obj Das anzuzeigende Objekt
     */
    public void addObject(LogicObject obj)
    {
        logicObjects.add(obj);

        redraw();
    }

    /**
     * @return Collection aller Objekte
     */
    Collection<LogicObject> getObjects() {
        return logicObjects;
    }

    /**
     * Aktualisieren des Fensters
     */
    public void redraw()
    {
        panel.repaint();
    }

    /**
     * LogicObjects können sich für bestimmte Informationen registrieren
     * @param obj ein LogicObject
     * @param m die zu empfangene Information, mögliche Parameter: Ein Buchstabe bzw. Zeichen von der Tastatur,
     *          'MOUSE' für Mausdrücke
     */
    public void register(LogicObject obj, String m)
    {
        panel.register(obj, m);
    }

    /**
     * Passt die größe an cellsize an
     * @param icon Das anzupassenden icon
     * @return Das angepasste icon
     */
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
