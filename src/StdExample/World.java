package StdExample;

import Graphics.LogicObject;

import java.util.Collection;

public interface World
{
    void addObject(LogicObject obj);

    void redraw();

    void register(LogicObject obj, String c);

    Collection<LogicObject> getObjects();

    int getRowCount();

    int getColCount();
}
