package Expression.model;

import javafx.scene.paint.Color;

public enum State {
    //Different states cells could be and their attributes

    RED {
        @Override
        public Color getColor() { return Color.RED; }

        @Override
        public int firstAllele() {
            return 1;
        }

        @Override
        public int secondAllele() {
            return 0;
        }

        @Override
        public boolean canMate(Cell other) {
            State otherState = other.getState();
            return (otherState.equals(RED) || otherState.equals(YELLOW) || otherState.equals(BLUE));
        }

        @Override
        public boolean isNeutral(Cell other) {
            State otherState = other.getState();
            return (otherState.equals(ORANGE) || otherState.equals(PURPLE));
        }
    }, ORANGE {
        @Override
        public Color getColor() {
            return Color.ORANGE;
        }

        @Override
        public int firstAllele() {
            return 0;
        }

        @Override
        public int secondAllele() {
            return 0;
        }

        @Override
        public boolean canMate(Cell other) {
            State otherState = other.getState();
            return otherState.equals(ORANGE);
        }

        @Override
        public boolean isNeutral(Cell other) {
            State otherState = other.getState();
            return (otherState.equals(ORANGE) || otherState.equals(RED) || otherState.equals(YELLOW));
        }
    }, YELLOW {
        @Override
        public Color getColor() {
            return Color.YELLOW;
        }

        @Override
        public int firstAllele() {
            return 0;
        }

        @Override
        public int secondAllele() {
            return 0;
        }

        @Override
        public boolean canMate(Cell other) {
            State otherState = other.getState();
            return (otherState.equals(RED) || otherState.equals(YELLOW) || otherState.equals(BLUE));
        }

        @Override
        public boolean isNeutral(Cell other) {
            State otherState = other.getState();
            return (otherState.equals(ORANGE) || otherState.equals(GREEN));
        }
    }, GREEN {
        @Override
        public Color getColor() {
            return Color.GREEN;
        }

        @Override
        public int firstAllele() {
            return 0;
        }

        @Override
        public int secondAllele() {
            return 0;
        }

        @Override
        public boolean canMate(Cell other) {
            State otherState = other.getState();
            return otherState.equals(GREEN);
        }

        @Override
        public boolean isNeutral(Cell other) {
            State otherState = other.getState();
            return (otherState.equals(GREEN) || otherState.equals(BLUE) || otherState.equals(YELLOW));
        }
    }, BLUE {
        @Override
        public Color getColor() {
            return Color.BLUE;
        }

        @Override
        public int firstAllele() {
            return 1;
        }

        @Override
        public int secondAllele() {
            return 0;
        }

        @Override
        public boolean canMate(Cell other) {
            State otherState = other.getState();
            return (otherState.equals(RED) || otherState.equals(YELLOW) || otherState.equals(BLUE));
        }

        @Override
        public boolean isNeutral(Cell other) {
            State otherState = other.getState();
            return (otherState.equals(PURPLE) || otherState.equals(GREEN));
        }
    }, PURPLE {
        @Override
        public Color getColor() {
            return Color.PURPLE;
        }

        @Override
        public int firstAllele() {
            return 0;
        }

        @Override
        public int secondAllele() {
            return 0;
        }

        @Override
        public boolean canMate(Cell other) {
            State otherState = other.getState();
            return otherState.equals(PURPLE);
        }

        @Override
        public boolean isNeutral(Cell other) {
            State otherState = other.getState();
            return (otherState.equals(PURPLE) || otherState.equals(RED) || otherState.equals(BLUE));
        }
    }, MUTATED {
        @Override
        public Color getColor() {
            return Color.GRAY;
        }

        @Override
        public int firstAllele() {
            return 1;
        }

        @Override
        public int secondAllele() {
            return 1;
        }

        @Override
        public boolean canMate(Cell other) {
            return false;
        }

        @Override
        public boolean isNeutral(Cell other) {
            State otherState = other.getState();
            return (otherState.equals(MUTATED));
        }
    };

    public abstract Color getColor();
    //1 means allele for mutation is present, 0 means it is not
    public abstract int firstAllele();
    public abstract int secondAllele();
    public abstract boolean canMate(Cell other);
    public abstract boolean isNeutral(Cell other);
}
