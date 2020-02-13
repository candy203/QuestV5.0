package Memory;

import Graphics.Event;
import Graphics.GraphicsManager;
import Graphics.LogicObject;

import javax.swing.*;

/**
 * Die Klasse einer Memorykarte
 */
public class MemoryCard extends LogicObject {
    private final ImageIcon defaultImage;
    private final ImageIcon memoryImage;
    private final int imageId;
    private boolean match = false;

    MemoryCard (GraphicsManager manager, ImageIcon icon, int posX, int posY, int imageId)
    {
        super(manager);

        this.imageId = imageId;
        defaultImage = new ImageIcon(MemoryCard.class.getResource("images/default.png"));
        memoryImage = icon;

        setImage(defaultImage);
        setPosX(posX);
        setPosY(posY);
        setShown(true);
    }

    /**
     * @param obj Die zweite Memorykarte
     * @return Ob obj mit this das gleiche Symbol inne haben
     */
    boolean isMatch(MemoryCard obj)
    {
        return obj.getImageId() == this.getImageId() && obj != this;
    }

    /**
     * Setzt die Karte auf immer offen
     */
    void match()
    {
        match = true;
        setImage(memoryImage);
    }

    /**
     * Öffnet die Karte
     */
    void open()
    {
        setImage(memoryImage);
    }

    /**
     * Schließt die Karte
     */
    void close()
    {
        setImage(defaultImage);
    }

    /**
     * Platzhalter
     * @param event Ein Eventobjekt mit Geschehnissen
     */
    @Override
    public void mouseOrKeyPressed(Event event) {

    }

    /**
     * @return Nummer des Bildes
     */
    public int getImageId() {
        return imageId;
    }

    /**
     * @return Ob die Karte immer offen ist
     */
    public boolean isAlreadyMatch() {
        return match;
    }
}
