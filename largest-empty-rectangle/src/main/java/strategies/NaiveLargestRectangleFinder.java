package strategies;

import models.Plane;
import models.Point;
import utils.RectangleUtils;

public class NaiveLargestRectangleFinder implements LargestRectangleFinderStrategy {

    @Override
    public int find(Plane plane, Point[] points) {
        if (points.length == 0) {
            return plane.getWidth() * plane.getHeight();
        }
        int currentBiggestRectangleArea = 0;
        Point shortestPoint = points[0];

        for (int i = 0; i < points.length; i++) {
            Point firstPoint = points[i];
            if (firstPoint.getY() < shortestPoint.getY()) {
                shortestPoint = firstPoint;
            }

            // Start at i + 1 to avoid picking the same point twice and avoid duplicate rectangles
            for (int j = i + 1; j < points.length; j++) {
                Point secondPoint = points[j];
                int maxHeight = plane.getHeight();

                // Loop trough all points between the first one and the second one
                for (int k = i + 1; k < j; k++) {
                    Point thirdPoint = points[k];
                    // Limit the rectangle by the shortest point
                    maxHeight = Math.min(thirdPoint.getY(), maxHeight);
                }

                int rectangleArea = RectangleUtils.calculateArea(
                        new Point(firstPoint.getX(), 0),
                        new Point(secondPoint.getX(), maxHeight)
                );

                currentBiggestRectangleArea = Math.max(currentBiggestRectangleArea, rectangleArea);
            }
        }

        return currentBiggestRectangleArea;
    }
}
