package strategies;

import models.Plane;
import models.Point;
import utils.RectangleUtils;

public class DivideAndConquerRectangleFinder implements LargestRectangleFinderStrategy {

    @Override
    public int find(Plane plane, Point[] points) {
        if (points.length == 0) {
            // No point in plane so the maximum rectangle possible is the plane itself
            return RectangleUtils.calculateArea(
                    new Point(plane.getX(), 0),
                    new Point(plane.getX() + plane.getWidth(), plane.getHeight())
            );
        }

        // Find index of the shortest point
        Point shortestPoint = points[0];
        int shortestPointIndex = 0;
        int sameShortestHeight = 0;
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            if (shortestPoint.getY() > p.getY()) {
                shortestPoint = p;
                shortestPointIndex = i;
                sameShortestHeight = 0;
            }

            if (shortestPoint.getY() == p.getY()) {
                shortestPoint = p;
                shortestPointIndex = i;
                sameShortestHeight++;
            } else {
                sameShortestHeight = 0;
            }
        }

        if (sameShortestHeight != 0) {
            shortestPointIndex = shortestPointIndex - sameShortestHeight / 2;
        }

        // Make a rectangle of the whole plane up to the shortest point
        int biggestRectangleArea = RectangleUtils.calculateArea(
                new Point(plane.getX(), 0),
                new Point(plane.getX() + plane.getWidth(), shortestPoint.getY())
        );

        if (points.length == 1) {
            // 2 possible rectangles
            int leftRectangleArea = RectangleUtils.calculateArea(
                    plane.getX(), 0,
                    points[0].getX(), plane.getHeight()
            );
            biggestRectangleArea = Math.max(biggestRectangleArea, leftRectangleArea);

            int rightRectangleArea = RectangleUtils.calculateArea(
                    points[0].getX(), 0,
                    plane.getWidth() + plane.getX(), plane.getHeight()
            );
            biggestRectangleArea = Math.max(biggestRectangleArea, rightRectangleArea);

            return biggestRectangleArea;
        }

        Plane leftPlane = new Plane(shortestPoint.getX() - plane.getX(), plane.getHeight(), plane.getX(), 0);
        Point[] leftPoints = new Point[shortestPointIndex];
        System.arraycopy(points, 0, leftPoints, 0, leftPoints.length);

        Plane rightPlane = new Plane(plane.getWidth() + plane.getX() - shortestPoint.getX(), plane.getHeight(), shortestPoint.getX(), 0);
        Point[] rightPoints = new Point[points.length - shortestPointIndex - 1];
        System.arraycopy(points, shortestPointIndex + 1, rightPoints, 0, rightPoints.length);

        int leftBiggestRectangleArea = new DivideAndConquerRectangleFinder().find(leftPlane, leftPoints);
        int rightBiggestRectangleArea = new DivideAndConquerRectangleFinder().find(rightPlane, rightPoints);

        return Math.max(Math.max(leftBiggestRectangleArea, rightBiggestRectangleArea), biggestRectangleArea);
    }
}
