package cz.uhk.veditor.grobjekty;

import java.awt.*;

public class Triangle extends AbstractGeomObject {
    protected int a;

    public Triangle(Point position, int a, Color color) {
        super(position, color);
        this.a = a;
    }

    public Triangle(int a) {
        this.a = a;
    }

    @Override
    public boolean contains(int x, int y) {
        int height = (int)(Math.sqrt(3)/2 * a);

        // Vrcholy trojúhelníku
        Point A = new Point(position.x, position.y);                  // vrchní střed
        Point B = new Point(position.x + a / 2, position.y + height); // pravý spodní
        Point C = new Point(position.x - a / 2, position.y + height); // levý spodní
        Point P = new Point(x,y);

        double totalArea = triangleArea(A, B, C);
        double area1 = triangleArea(P, B, C);
        double area2 = triangleArea(A, P, C);
        double area3 = triangleArea(A, B, P);

        return Math.abs(totalArea - (area1 + area2 + area3)) < 0.5;
    }

    private double triangleArea(Point p1, Point p2, Point p3) {
        return Math.abs((p1.x*(p2.y - p3.y) + p2.x*(p3.y - p1.y) + p3.x*(p1.y - p2.y)) / 2.0);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        int height = (int)(Math.sqrt(3)/2 * a);
        int[] xPoints = {position.x, position.x + a / 2, position.x - a / 2};
        // kliknuti do leveho rohu, ne na horni vrchol
        // int[] xPoints = {position.x + a/2, position.x + a, position.x};
        int[] yPoints = {position.y, position.y + height, position.y + height};
        g.drawPolygon(xPoints, yPoints, 3);

    }
}
