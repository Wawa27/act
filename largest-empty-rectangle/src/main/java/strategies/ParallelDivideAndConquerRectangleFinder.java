package strategies;

import models.Plane;
import models.Point;
import utils.RectangleUtils;

import java.util.concurrent.*;

public class ParallelDivideAndConquerRectangleFinder implements LargestRectangleFinderStrategy, Runnable {
    public static ExecutorService executorService = Executors.newFixedThreadPool(16);

    @Override
    public int find(Plane plane, Point[] points) {
        if (points.length == 0) {
            // No point in plane so the maximum rectangle possible is the plane itself
            return RectangleUtils.calculateArea(
                    new Point(plane.getX(), 0),
                    new Point(plane.getX() + plane.getWidth(), plane.getHeight())
            );
        }

        // Check the area of the shortest point
        Point shortestPoint = points[0];
        int shortestPointIndex = 0;
        for (int i = 0, pointsLength = points.length; i < pointsLength; i++) {
            Point p = points[i];
            if (shortestPoint.getY() > p.getY()) {
                shortestPoint = p;
                shortestPointIndex = i;
            }
        }

        // Make a rectangle of the whole plane up to the shortest point
        int biggestRectangleArea = RectangleUtils.calculateArea(
                new Point(plane.getX(), 0),
                new Point(plane.getWidth() + plane.getX(), shortestPoint.getY())
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

        Point[] rightPoints = new Point[points.length - shortestPointIndex - 1];
        Plane rightPlane = new Plane(plane.getWidth() - shortestPoint.getX(), plane.getHeight(), points[shortestPointIndex].getX(), 0);
        System.arraycopy(points, shortestPointIndex + 1, rightPoints, 0, rightPoints.length);

        FutureTask<Integer> leftFuture =
                new FutureTask(new Callable<Integer>() {
                    public Integer call() {
                        return new ParallelDivideAndConquerRectangleFinder().find(leftPlane, leftPoints);
                    }
                });
        FutureTask<Integer> rightFuture =
                new FutureTask(new Callable<Integer>() {
                    public Integer call() {
                        return new ParallelDivideAndConquerRectangleFinder().find(rightPlane, rightPoints);
                    }
                });
        executorService.execute(leftFuture);
        executorService.execute(rightFuture);

        try {
            return Math.max(Math.max(leftFuture.get(), rightFuture.get()), biggestRectangleArea);
        } catch (Exception e) {
            throw new RuntimeException("Problème multithreading...");
        }
    }

    @Override
    public void run() {

    }
}
