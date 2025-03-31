package cz.uhk.veditor.gui;

import cz.uhk.veditor.grobjekty.AbstractGeomObject;
import cz.uhk.veditor.grobjekty.Circle;
import cz.uhk.veditor.grobjekty.Square;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {

    private List<AbstractGeomObject> objekty = new ArrayList<>();

    public MainWindow() {
        super("Vektorový editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initTest();

        add(new GraphPanel(objekty), BorderLayout.CENTER);

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void initTest() {
        objekty.add(new Circle(new Point(100,100),50, Color.BLUE));
        objekty.add(new Circle(new Point(200,100),30, Color.RED));
        objekty.add(new Square(new Point(100,300),60, Color.BLACK));
        objekty.add(new Square(new Point(400,400),100, Color.GREEN));
    }
}
