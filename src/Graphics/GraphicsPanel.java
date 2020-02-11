package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

/**
 * Die Klasse GraphicsPanel zeichnet die Quadrate und die LogicObjects.
 */
class GraphicsPanel extends JPanel implements KeyListener, MouseListener {
    private final int cellSize;
    private final int rowCount;
    private final int colCount;

    private final GraphicsManager graphicsManager;
    private final Map<String, Collection<LogicObject>> logicObjectStringMap;

    public GraphicsPanel(int rowCount, int colCount, int cellSize, GraphicsManager graphicsManager) {
        this.cellSize = cellSize;
        this.rowCount = rowCount;
        this.colCount = colCount;

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
        addMouseListener(this);

        logicObjectStringMap = new HashMap<>();
        this.graphicsManager = graphicsManager;
    }

    /**
     * LogicObjects können sich für bestimmte Informationen registrieren
     * @param obj ein LogicObject
     * @param m die zu empfangene Information, mögliche Parameter: Ein Buchstabe bzw. Zeichen von der Tastatur,
     *          'MOUSE' für Mausdrücke
     */
    public void register(LogicObject obj, String m) {
        logicObjectStringMap.computeIfAbsent(m, k -> new ArrayList<>()).add(obj);
    }

    /**
     * Malkomponennte
     * @param g Übergebende Malkomponennte
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Radieren

        drawRects(g);

        redraw(graphicsManager.getObjects(), g);
    }

    /**
     * Zeichnet alle sichtbaren Objekte
     * @param logicObjects Collection aller Objekte
     * @param g Malkomponennte
     */
    private void redraw(Collection<LogicObject> logicObjects, Graphics g) {
        for (LogicObject obj : logicObjects) {
            if (obj.isShown()) {
                ImageIcon icon = obj.getImage();
                icon.paintIcon(this, g, obj.getPosX() * cellSize, obj.getPosY() * cellSize);
            }
        }
    }

    /**
     * Zeichnet die Rechtecke
     * @param g Malkomponennte
     */
    private void drawRects(Graphics g) {
        for (int i = 0; i < colCount; i++) {
            for (int k = 0; k < rowCount; k++) {
                g.drawRect(i * cellSize, k * cellSize, cellSize, cellSize);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {


    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        Event event = new Event();
        event.keyPressed = keyEvent.getKeyChar();
        notifyObjects(String.valueOf(keyEvent.getKeyChar()), event);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    private void notifyObjects(String key, Event event) {
        Collection<LogicObject> logicObjects = logicObjectStringMap.getOrDefault(key, new ArrayList<>());

        for (LogicObject obj : logicObjects) {
            obj.mouseOrKeyPressed(event);
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Event event = new Event();
        event.mouseButtonPressed = mouseEvent.getButton();
        event.mousePosX = mouseEvent.getX() / cellSize;
        event.mousePosY = mouseEvent.getY() / cellSize;

        notifyObjects("MOUSE", event);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
