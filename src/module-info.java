//The file itself needs to be "module-info.java"
module GeneExp {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    //Points to where your GUI is
    exports Expression.gui;
    opens Expression.gui;
}