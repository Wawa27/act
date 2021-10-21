import models.Plane;
import models.Point;
import strategies.DivideAndConquerRectangleFinder;
import strategies.LargestRectangleFinderStrategy;
import strategies.LinearRectangleFinder;
import strategies.NaiveLargestRectangleFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LargestRectangleContestMain {

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        String planeSize = scanner.nextLine();
        int width = Integer.parseInt(planeSize.split(" ")[0]);
        int height = Integer.parseInt(planeSize.split(" ")[1]);

        int pointCount = Integer.parseInt(scanner.nextLine().trim());

        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        for (int i = 0; i < pointCount; i++) {
            String pointCoordinates = scanner.nextLine();
            int x = Integer.parseInt(pointCoordinates.split(" ")[0]);
            int y = Integer.parseInt(pointCoordinates.split(" ")[1]);
            if (x != 0 && x != width) {
                points.add(new Point(x, y));
            }
        }
        points.add(new Point(width, height));

        System.out.println("points = " + points);

        Plane plane = new Plane(width, height, 0, 0);
        LargestRectangleFinderStrategy strategy = new DivideAndConquerRectangleFinder();

        System.out.println(strategy.find(plane, points.toArray(new Point[0])));

    }
}

