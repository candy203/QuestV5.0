package Memory;

import Graphics.GraphicsManager;

import javax.swing.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MemoryGame {
    private final GraphicsManager manager;
    private  final Map<String, MemoryCard> memoryCardMap;

    private ArrayList<MemoryCard> openMemoryCards = new ArrayList<>();

    /**
     * Maximum 30 Felder
     * @param rowCount Anzahl der Reihen
     * @param colCount Anzahl der Spalten
     */
    public MemoryGame(int rowCount, int colCount) {
        proofSize(rowCount, colCount);

        manager = new GraphicsManager(rowCount, colCount, 100);
        memoryCardMap = new HashMap<>();

        initMemory(rowCount, colCount);
    }

    /**
     * Prüft, ob gerade Anzahl der Felder, und Anzahl der Felder unter 30
     * @param rowCount Anzahl der Reihen
     * @param colCount Anzahl der Spalten
     */
    private void proofSize(int rowCount, int colCount)
    {
        if (rowCount * colCount > 30)
        {
            throw new IllegalArgumentException("Zu viele Felder!!!");
        }
        if (rowCount * colCount % 2 != 0)
        {
            throw new IllegalArgumentException("Die Anzahl der Felder muss gerade sein!!!");
        }
    }

    /**
     * Initialisiert das Spiel
     * @param rowCount Anzahl der Reihen
     * @param colCount Anzahl der Spalten
     */
    private void initMemory(int rowCount, int colCount)
    {

        int halfSize = rowCount*colCount / 2;

        List<Integer> nums = IntStream.range(1,halfSize+1)
                .flatMap(i->IntStream.of(i,i))
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(nums,new SecureRandom());

        Iterator<Integer> iter = nums.iterator();
        for (int i = 0; i < colCount; i++) {
            for (int k = 0; k < rowCount; k++) {
                int imageNum = iter.next();
                ImageIcon icon = new ImageIcon(MemoryGame.class.getResource("images/" + imageNum + ".png"));
                MemoryCard card = new MemoryCard(manager, icon, i, k, imageNum);
                manager.addObject(card);

                memoryCardMap.put(i + "," + k, card);
            }
        }

        InputObject obj = new InputObject(manager, this);
        manager.addObject(obj);
    }

    /**
     * Aufgerufen, wenn ein Objekt gedrückt wurde
     * @param posX x-Koordinate des Objektes
     * @param posY y-Koordinate des Objektes
     */
    public void pressed(int posX, int posY)
    {
        MemoryCard pressedCard = memoryCardMap.get(posX + "," + posY);

        if (openMemoryCards.size() < 2)
        {
            pressedCard.open();
            openMemoryCards.add(pressedCard);
        }
        else if (openMemoryCards.size() == 2)
        {
            if (openMemoryCards.get(0).isMatch(openMemoryCards.get(1)))
            {
                openMemoryCards.get(0).match();
                openMemoryCards.get(1).match();

                if (allMatched()) // Spiel vorbei
                {
                    System.exit(0);
                }
            }
            else
            {
                openMemoryCards.get(0).close();
                openMemoryCards.get(1).close();
            }
            openMemoryCards.clear();
        }
    }

    /**
     * @return Ob spiel vorbei
     */
    private boolean allMatched()
    {
        for (MemoryCard obj : memoryCardMap.values()) {
            if (!obj.isAlreadyMatch())
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Startet das Spiel
     * @param args Komandozeilenparameter
     */
    public static void main(String[] args) {
        new MemoryGame(5, 6);
    }
}
