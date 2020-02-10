package Graphics;

import javax.swing.ImageIcon;

public interface LogicObject
{
    boolean isShown();

    void mouseOrKeyPressed(Event event);

    ImageIcon getImage();

    void setResizedImage(ImageIcon icon);

    int getXPos();

    int getYPos();
}
