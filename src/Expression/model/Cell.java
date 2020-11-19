package Expression.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Cell {
    public static int radius = 5;
    private State state;
    private Position loc;
    private Mover mover;
    private Circle c;
    private Pane world;
    private int refractoryPeriodRate;
    private int refractoryPeriod;
    private boolean deleteSelf = false;

    /**Ways to make a cell **/
    public Cell(State state, Pane world, int refPeriod) {
        this.state = state;
        this.mover = new Mover();
        this.loc = new Position(world);
        this.c = new Circle(radius, state.getColor());
        this.world = world;
        this.refractoryPeriodRate = 28 * refPeriod;
        this.setRefractoryPeriod();
        c.setStroke(Color.BLACK);
        world.getChildren().add(c);
    }
    public Cell(State state, Pane world, int refPeriod, Position loc) {
        this.state = state;
        this.mover = new Mover();
        this.loc = new Position(loc.getX() + 3.0, loc.getY() +3.0);
        this.refractoryPeriodRate = 28 * refPeriod;
        this.c = new Circle(radius, state.getColor());
        this.world = world;
        this.refractoryPeriod = refractoryPeriodRate*2;
        c.setStroke(Color.BLACK);
        world.getChildren().add(c);
    }

    /**Actions **/
    public void setState(State state) {
        this.state = state;
        c.setFill(state.getColor());
    }
    public void move() {
        loc.move(mover, world);
    }
    public void draw() {
        c.setRadius(radius);
        c.setTranslateX(loc.getX());
        c.setTranslateY(loc.getY());
    }
    public void goAway() {
        world.getChildren().remove(this.c);
    }
    public void tickRefractoryPeriod() {
        if (refractoryPeriod > 0) {
            refractoryPeriod--;
        }
    }
    public void setRefractoryPeriod() {
        refractoryPeriod = refractoryPeriodRate;
    }

    public void markForDeletion() {
        deleteSelf = true;
    }

    /**Get Methods... **/
    public boolean mutationCheck(double mutationChance) {
        double myState = Math.random();
        return myState <= mutationChance/10000 && myState > 0;
    }
    public boolean collisionCheck(Cell other) {
        return loc.collision(other.loc);
    }
    public boolean mateCheck(Cell other) {
        return state.canMate(other);
    }
    public boolean neutralCheck(Cell other) {
        return state.isNeutral(other);
    }
    public State getState() {
        return state;
    }
    public int getRefractoryPeriod() {
        return refractoryPeriod;
    }
    public boolean checkDelete() {
        return deleteSelf;
    }
    public Position getLoc() {
        return this.loc;
    }
    public int getRefractoryPeriodRate() {
        return refractoryPeriodRate;
    }
}
