package StdExample;

import Graphics.GraphicsManager;

public class Main {
    public static void main(String[] args) {
        GraphicsManager manager = new GraphicsManager(4, 8, 100);
        manager.addObject(new TestObject(manager));
    }
}
