package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

class GraphicsPanel extends JPanel implements KeyListener, MouseListener {
    private final int cellSize;
    private final int rowCount;
    private final int colCount;

    private final GraphicsManager graphicsManager;
    private final Map<String, Collection<LogicObject>> logicObjectStringMap;

    //todo: colCount and rowCount instead of width and height
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

    public void register(LogicObject obj, String c) {
        logicObjectStringMap.computeIfAbsent(c, k -> new ArrayList<>()).add(obj);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawRects(g);

        redraw(graphicsManager.getObjects(), g);
    }

    private void redraw(Collection<LogicObject> logicObjects, Graphics g) {
        for (LogicObject obj : logicObjects) {
            if (obj.isShown()) {
                ImageIcon icon = obj.getImage();
                icon.paintIcon(this, g, obj.getPosX() * cellSize, obj.getPosY() * cellSize);
            }
        }
    }

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
