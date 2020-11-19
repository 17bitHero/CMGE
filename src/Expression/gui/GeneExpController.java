package Expression.gui;

import Expression.model.Cell;
import Expression.model.Simulation;
import Expression.model.State;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.EnumMap;

public class GeneExpController {

    @FXML
    Pane world;
    @FXML
    Pane graphTypes;
    @FXML
    TextField countCells;
    @FXML
    Button startButton;
    @FXML
    Button stopButton;
    @FXML
    Button stepButton;
    @FXML
    TextField initialPop;
    @FXML
    TextField refPeriod;
    @FXML
    TextField mutChance;
    @FXML
    TextField maxPop;

    Simulation sim;

    int initialSize;

    EnumMap<State, Rectangle> histogram = new EnumMap<State, Rectangle>(State.class);

    private Movement clock;

    private class Movement extends AnimationTimer {
        private long FRAMES_PER_SEC = 50L;
        private long INTERVAL = 1000000000L / FRAMES_PER_SEC;
        private long last = 0;

        @Override
        public void handle(long now) {
            if (now - last > INTERVAL) {
                step();
                last = now;
            }
        }
    }

    @FXML
    public void initialize() {
        clock = new Movement();
        world.setBackground(new Background(new BackgroundFill(Color.WHITE, null ,null)));
        disableButtons(true,true,true);
        initialSize = Integer.parseInt(initialPop.getText());
    }

    @FXML
    public void reset() {
        stop();
        world.getChildren().clear();
        graphTypes.getChildren().clear();
        initialSize = Integer.parseInt(initialPop.getText());
        sim = new Simulation(world, initialSize, Double.parseDouble(mutChance.getText())/100, Integer.parseInt(refPeriod.getText()), Integer.parseInt(maxPop.getText()));

        countCells.setText(Integer.toString(sim.getCount()));
        int offset = 5;
        for (State s : State.values()) {
            Rectangle r = new Rectangle(23, 0, s.getColor());
            r.setTranslateX(offset);
            offset += 28;
            histogram.put(s, r);
            graphTypes.getChildren().add(r);
        }
        drawCharts();
    }

    @FXML
    public void start() {
        clock.start();
        disableButtons(true,false,true);
    }

    @FXML
    public void stop() {
        clock.stop();
        disableButtons(false,true,false);
    }

    @FXML
    public void step() {
        sim.move();
        sim.resolveCollisions();
        countCells.setText(Integer.toString(sim.getCount()));
        sim.draw();
        drawCharts();
    }

    public void disableButtons(boolean start, boolean stop, boolean step) {
        startButton.setDisable(start);
        stopButton.setDisable(stop);
        stepButton.setDisable(step);
    }

    public void drawCharts() {
        EnumMap<State, Integer> currentPop = new EnumMap<State, Integer>(State.class);
        for (Cell c : sim.getCells()) {
            if (!currentPop.containsKey(c.getState())) {
                currentPop.put(c.getState(), 0);
            }
            currentPop.put(c.getState(), 1+currentPop.get(c.getState()));
        }
        for (State state : histogram.keySet()) {
            if (currentPop.containsKey(state)) {
                histogram.get(state).setHeight(200 * (Double.valueOf(currentPop.get(state)) / sim.getCount()));
            }
        }
    }
}
