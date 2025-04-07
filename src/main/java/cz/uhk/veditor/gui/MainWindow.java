package cz.uhk.veditor.gui;

import cz.uhk.veditor.grobjekty.*;
import cz.uhk.veditor.grobjekty.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {

    private List<AbstractGeomObject> objekty = new ArrayList<>();
    private AbstractGeomObject draggedObj = null;
    private Point lastMousePos = null;

    private JToolBar toolBar;
    private JToggleButton btSquare;
    private JToggleButton btCircle;
    private JToggleButton btRectangle;
    private JToggleButton btTriangle;
    private JToggleButton btMove;

    public MainWindow() {
        super("Vektorový editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initTest();

        createToolBar();

        GraphPanel panel = new GraphPanel(objekty);
        add(panel, BorderLayout.CENTER);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // kliknuto na leve tlacitko mysi
                    if (btCircle.isSelected()) {
                        objekty.add(
                                new Circle(new Point(e.getX(), e.getY()), 25, Color.RED)
                        );
                    } else if (btSquare.isSelected()) {
                        objekty.add(
                                new Square(new Point(e.getX(), e.getY()), 50, Color.BLUE)
                        );
                    } else if (btRectangle.isSelected()) {
                        objekty.add(
                                new Rectangle(new Point(e.getX(), e.getY()), 50, 30, Color.GREEN)
                        );
                    }
                    else if (btTriangle.isSelected()) {
                        objekty.add(
                                new Triangle(new Point(e.getX(), e.getY()), 75, Color.PINK)
                        );
                    }
                    repaint();
                }
            }

        });

        panel.addMouseListener(
            new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        if (btMove.isSelected()) {
                            for (AbstractGeomObject o : objekty) {
                                if (o.contains(e.getX(), e.getY())) {
                                    draggedObj = o;
                                    lastMousePos = e.getPoint();
                                    System.out.println(draggedObj + ", " + lastMousePos);
                                }
                            }
                        }
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        if (btMove.isSelected()) {
                            draggedObj = null;
                            lastMousePos = null;
                        }
                    }
                }
            }
        );

        panel.addMouseMotionListener(
            new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (btMove.isSelected() && draggedObj != null && lastMousePos != null) {
                        // draggedObj.setPosition(new Point(e.getX(), e.getY()));

                        int moovedX = e.getX() - lastMousePos.x;
                        int moovedY = e.getY() - lastMousePos.y;

                        Point currentPos = draggedObj.getPosition();
                        draggedObj.setPosition(new Point(currentPos.x + moovedX, currentPos.y + moovedY));

                        lastMousePos = e.getPoint();
                        repaint();
                        // System.out.println("posun o: " + moovedX + ", " + moovedY);
                    }
                }
            }
        );

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void createToolBar() {
        toolBar = new JToolBar(JToolBar.HORIZONTAL);
        add(toolBar, BorderLayout.NORTH);
        btSquare = new JToggleButton("▢");
        btCircle = new JToggleButton("○");
        btRectangle = new JToggleButton("▭");
        btTriangle = new JToggleButton("△");
        btMove = new JToggleButton("⇱");
        toolBar.add(btSquare);
        toolBar.add(btCircle);
        toolBar.add(btRectangle);
        toolBar.add(btTriangle);
        toolBar.add(btMove);
        ButtonGroup group = new ButtonGroup();
        group.add(btSquare);
        group.add(btCircle);
        group.add(btRectangle);
        group.add(btTriangle);
        group.add(btMove);
    }

    private void initTest() {
        objekty.add(new Circle(new Point(100,100),50, Color.BLUE));
        objekty.add(new Circle(new Point(200,100),30, Color.RED));
        objekty.add(new Square(new Point(100,300),60, Color.BLACK));
        objekty.add(new Square(new Point(400,400),100, Color.GREEN));
        objekty.add(new Rectangle(new Point(300,200),100, 50, Color.ORANGE));
        objekty.add(new Triangle(new Point(600,400), 100, Color.PINK));
    }
}
