package Graphics;

import javax.swing.ImageIcon;

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

    public boolean isShown() {
        return isShown;
    }

    public void setShown(boolean isShown) {
        this.isShown = isShown;
    }

    public abstract void mouseOrKeyPressed(Event event);

    public void setImage(ImageIcon icon) {
        this.icon = graphicsManager.resize(icon);
    }

    ImageIcon getImage() {
        return icon;
    }

    public void setPosX(int posX)
    {
        this.posX = posX;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosY(int posY)
    {
        this.posY = posY;
    }

    public int getPosY() {
        return posY;
    }

    public GraphicsManager getGraphicsManager()
    {
        return graphicsManager;
    }
}
