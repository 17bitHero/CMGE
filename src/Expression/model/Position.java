package Expression.model;

import javafx.scene.layout.Pane;

public class Position {
    private  double x, y;   //X and Y coordinates

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Position(Pane world) {
        this(Cell.radius + Math.random() * (world.getWidth() - 2 * Cell.radius),
                Cell.radius + Math.random() * (world.getHeight() - 2 * Cell.radius));
    }

    /**Actions **/
    public void move(Mover mover, Pane world) {
        x += mover.getDX();
        y += mover.getDY();
        if (x < Cell.radius || x > world.getWidth() - Cell.radius) {
            mover.bounceX();
            x += mover.getDX();
        }
        if (y < Cell.radius || y > world.getHeight() - Cell.radius) {
            mover.bounceY();
            y += mover.getDY();
        }
    }
    public void newDirection(Mover mover) {
        mover.bounceX();
        mover.bounceY();
    }

    /**Get methods **/
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double distance(Position other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
    public boolean collision(Position other) {
        return distance(other) < 2 * Cell.radius;
    }
}
