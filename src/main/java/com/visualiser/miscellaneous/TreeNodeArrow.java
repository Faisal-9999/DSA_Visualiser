package com.visualiser.miscellaneous;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class TreeNodeArrow extends Group {
    public TreeNodeArrow(StackPane parent, StackPane child, boolean isLeft, double nodeRadius) {
        // 1. Get the center of each circle (StackPane is 2*nodeRadius wide/high).
        double pxCenter = parent.getLayoutX() + nodeRadius;
        double pyCenter = parent.getLayoutY() + nodeRadius;
        double cxCenter = child.getLayoutX() + nodeRadius;
        double cyCenter = child.getLayoutY() + nodeRadius;

        // 2. Compute direction from parent center to child center.
        double dx = cxCenter - pxCenter;
        double dy = cyCenter - pyCenter;
        double dist = Math.sqrt(dx * dx + dy * dy);
        if (dist < 0.0001) dist = 0.0001; // Avoid division by zero

        // 3. “Start” is on the boundary of the parent circle.
        double startX = pxCenter + (dx * (nodeRadius / dist));
        double startY = pyCenter + (dy * (nodeRadius / dist));

        // 4. “End” is on the boundary of the child circle.
        double endX = cxCenter - (dx * (nodeRadius / dist));
        double endY = cyCenter - (dy * (nodeRadius / dist));

        // 5. Draw the line
        Line line = new Line(startX, startY, endX, endY);
        line.setStrokeWidth(2);

        // 6. Create and rotate the arrowhead so it “points” along the line
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