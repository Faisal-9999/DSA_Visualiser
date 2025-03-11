package com.visualiser.miscellaneous;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class TreeNodeArrow extends Group {
    public TreeNodeArrow(StackPane parent, StackPane child, boolean isLeft, double nodeRadius) {
        double px = parent.getLayoutX();
        double py = parent.getLayoutY();
        double cx = child.getLayoutX();
        double cy = child.getLayoutY();

        double startX;
        double startY;
        double endX;
        double endY;

        // Left edge vs. right edge
        if (isLeft) {
            startX = px;
            startY = py + nodeRadius;
            endX = cx;
            endY = cy + nodeRadius;
        } else {
            startX = px + 2 * nodeRadius;
            startY = py + nodeRadius;
            endX = cx + 2 * nodeRadius;
            endY = cy + nodeRadius;
        }

        Line line = new Line(startX, startY, endX, endY);
        line.setStrokeWidth(2);

        Polygon arrowHead = new Polygon(
                0.0, 0.0,
                -6.0, -4.0,
                -6.0, 4.0
        );
        double angle = Math.toDegrees(Math.atan2(endY - startY, endX - startX));
        arrowHead.setRotate(angle);
        arrowHead.setLayoutX(endX);
        arrowHead.setLayoutY(endY);

        getChildren().addAll(line, arrowHead);
    }
}


