package Expression.model;

public class Mover {
    public static final double SPEED = 2;   //Initial speed
    private double dX, dY;                  //Change in X, change in Y

    public Mover(double dX, double dY) {
        this.dX = dX;
        this.dY = dY;
    }
    public Mover() {
        double direction = Math.random() * 2 * Math.PI; //Gives random direction in radians
        dX = Math.sin(direction);
        dY = Math.cos(direction);
    }

    public double getDX() {
        return dX;
    }
    public double getDY() {
        return dY;
    }

    //When a cell hits a wall
    public void bounceX() {
        dX *= -1;
    }
    public void bounceY() {
        dY *= -1;
    }
}
