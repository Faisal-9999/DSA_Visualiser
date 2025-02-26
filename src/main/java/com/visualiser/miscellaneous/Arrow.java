package com.visualiser.miscellaneous;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class Arrow extends Group {

    public Arrow(double startX, double startY, double endX, double endY) {
        Line line = new Line(startX, startY, endX, endY);
        line.setStrokeWidth(2);

        Polygon arrowHead = new Polygon();
        arrowHead.getPoints().addAll(
                0.0, 0.0,
                -6.0, -4.0,
                -6.0, 4.0
        );

        arrowHead.setScaleX(1);
        arrowHead.setScaleY(1);

        double angle = Math.toDegrees(Math.atan2(endY - startY, endX - startX));
        arrowHead.setRotate(angle);

        arrowHead.setLayoutX(endX);
        arrowHead.setLayoutY(endY);

        getChildren().addAll(line, arrowHead);
    }
}
