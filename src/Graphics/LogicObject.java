package Graphics;

import javax.swing.ImageIcon;

/**
 * Die abstrakte Klasse zum vererben für alle Objekte
 */
public abstract class LogicObject
{
    private ImageIcon icon;
    private boolean isShown = false;
    private final GraphicsManager graphicsManager;

    private int posX;
    private int posY;

    protected LogicObject(GraphicsManager graphicsManager) {
        this.graphicsManager = graphicsManager;
    }

    /**
     * @return Sichtbarkeit des Objektes
     */
    public boolean isShown() {
        return isShown;
    }

    /**
     * @param isShown setzt Sichtbarkeit des Objektes
     */
    public void setShown(boolean isShown) {
        this.isShown = isShown;
    }

    /**
     * Zum implementieren der Reaktionen auf verschiedene Geschehnisse
     * @param event Ein Eventobjekt mit Geschehnissen
     */
    public abstract void mouseOrKeyPressed(Event event);

    /**
     * @param icon Zu setzendes Bild
     */
    public void setImage(ImageIcon icon) {
        this.icon = graphicsManager.resize(icon); // Anpassen der Größe des Bildes
    }

    /**
     * @return Das jetzige Bild
     */
    public ImageIcon getImage() {
        return icon;
    }

    /**
     * @param posX X-Koordinate setzen
     */
    public void setPosX(int posX)
    {
        this.posX = posX;
    }

    /**
     * @return X-Koordinate
     */
    public int getPosX() {
        return posX;
    }

    /**
     * @param posY Y-Koordinate setzen
     */
    public void setPosY(int posY)
    {
        this.posY = posY;
    }

    /**
     * @return Y-Koordinate
     */
    public int getPosY() {
        return posY;
    }

    /**
     * @return Graphikmanager
     */
    public GraphicsManager getGraphicsManager()
    {
        return graphicsManager;
    }
}
