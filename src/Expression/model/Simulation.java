package Expression.model;

import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class Simulation {
    private ArrayList<Cell> cells, tempCells = new ArrayList<Cell>();
    private int[] cellTypesCount = new int[7]; //RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE, MUTATED
    private Pane myWorld;
    private double mutationChance;
    private int populationThreshold, refPeriod; //Maximum amount of cells

    public Simulation(Pane world, int popSize, double mutChance, int refractory, int maxPop) {
        cells = new ArrayList<Cell>();
        myWorld = world;
        mutationChance = mutChance;
        populationThreshold = maxPop;
        refPeriod = refractory;

        for (int i = 0; i < popSize; i ++) {
            cells.add(new Cell(ranState(), world, refPeriod));
        }
        draw();
    }

    /**Actions **/
    public void move() {
        for (Cell c : cells) {
            if (c.mutationCheck(mutationChance)) {
                c.setState(ranState());
                //System.out.println("Mutation!");
            }
            c.move();
            c.tickRefractoryPeriod();
        }
    }
    public void draw() {
        for (Cell c : cells) {
            c.draw();

            if (c.getState() == State.RED) {
                cellTypesCount[0]++;
            } else if (c.getState() == State.ORANGE) {
                cellTypesCount[1]++;
            } else if (c.getState() == State.YELLOW) {
                cellTypesCount[2]++;
            } else if (c.getState() == State.GREEN) {
                cellTypesCount[3]++;
            } else if (c.getState() == State.BLUE) {
                cellTypesCount[4]++;
            } else if (c.getState() == State.PURPLE) {
                cellTypesCount[5]++;
            } else {
                cellTypesCount[6]++;
            }
        }
    }
    public void mate(Cell c, Cell d) {
        //System.out.println("Mate attempt: " + c.getState() + " and " + d.getState());
        State cellC = c.getState();
        State cellD = d.getState();
        int firstAll = variety(cellC.firstAllele());
        int secondAll = variety(cellD.secondAllele());

        if (firstAll + secondAll == 2) {
            tempCells.add(new Cell(State.MUTATED, myWorld, refPeriod, c.getLoc()));
        } else {
            if (cellC.equals(State.RED)) {
                if (cellD.equals(State.RED)) {
                    tempCells.add(new Cell(State.RED, myWorld, refPeriod, c.getLoc()));
                } else if (cellD.equals(State.BLUE)) {
                    tempCells.add(new Cell(State.PURPLE, myWorld, refPeriod, c.getLoc()));
                } else {
                    tempCells.add(new Cell(State.ORANGE, myWorld, refPeriod, c.getLoc()));
                }
            } else if (cellC.equals(State.BLUE)) {
                if (cellD.equals(State.RED)) {
                    tempCells.add(new Cell(State.PURPLE, myWorld, refPeriod, c.getLoc()));
                } else if (cellD.equals(State.BLUE)) {
                    tempCells.add(new Cell(State.BLUE, myWorld, refPeriod, c.getLoc()));
                } else {
                    tempCells.add(new Cell(State.GREEN, myWorld, refPeriod, c.getLoc()));
                }
            } else {
                if (cellD.equals(State.RED)) {
                    tempCells.add(new Cell(State.ORANGE, myWorld, refPeriod, c.getLoc()));
                } else if (cellD.equals(State.BLUE)) {
                    tempCells.add(new Cell(State.GREEN, myWorld, refPeriod, c.getLoc()));
                } else {
                    tempCells.add(new Cell(State.YELLOW, myWorld, refPeriod, c.getLoc()));
                }
            }
        }
        c.setRefractoryPeriod();
        d.setRefractoryPeriod();
    }
    public void resolveCollisions() {
        for (Cell c : cells) {
            for (Cell d: cells) {
                if (c != d && c.collisionCheck(d)) {
                    if (c.mateCheck(d)) {
                        if ((c.getRefractoryPeriod() == 0 && d.getRefractoryPeriod() == 0) && cells.size() < populationThreshold) {
                            mate(c, d);
                            //cells.add(new Cell(State.BLUE, myWorld, c.getLoc()));
                        }
                    } else if (!c.neutralCheck(d)) {
                        int rate = c.getRefractoryPeriodRate() / 2; //how long a cell must wait before they can be deleted
                        if (c.getRefractoryPeriod() <= rate && d.getRefractoryPeriod() <= rate) {
                            //System.out.println("Cells marked for deletion: " + c.getState() + " and " + d.getState());
                            c.markForDeletion();
                            d.markForDeletion();
                            c.goAway();
                            d.goAway();
                        }
                    } else {
                        //System.out.println("Friendly interaction between: " + c.getState() + " and " + d.getState());
                    }
                }
            }
        }
        if(tempCells.size() != 0) {
            cells.addAll(tempCells);
            tempCells = new ArrayList<Cell>();
        }
        cells.removeIf(Cell::checkDelete);
    }

    /**Get methods **/
    public int getCount() {
        return cells.size();
    }
    public int[] getCellTypesCount() {
        return cellTypesCount;
    }
    public State ranState() {
        int myState = ((int)(Math.random() * 10));

        if (myState <= 2) {
            return State.YELLOW;
        } else if (myState <= 5) {
            return State.BLUE;
        } else if (myState <= 8) {
            return State.RED;
        } else {
            return ranState();
        }
    }
    public int variety(int allele) {
        double chanceVar = Math.random();
        int newAllele = allele;
        if (chanceVar <= mutationChance) {        //chance of mutation
            if (newAllele == 0) {
                newAllele = 1;
            } else {
                newAllele = 0;
            }
        }
        return newAllele;
    }
    public ArrayList<Cell> getCells() {
        return cells;
    }
}