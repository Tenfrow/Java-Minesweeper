package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;

class ResourceManager {

    private static String[] colorHashCodes = {
            "#0100FE", "#017F01", "#FE0000", "#010080", "#800000", "#008081", "#000000", "#808080"
    };
    private static HashMap<String, ImageIcon> icons = new HashMap<>();


    static Color getNumberColor(int number) {
        return Color.decode(colorHashCodes[number - 1]);
    }

    static Color getRandomColor() {
        Random rand = new Random();
        return new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
    }

    static ImageIcon getIcon(String name) {
        if (!icons.containsKey(name)) {
            icons.put(name, new ImageIcon(System.class.getResource("/icons/" + name + ".png")));
        }
        return icons.get(name);
    }
}
