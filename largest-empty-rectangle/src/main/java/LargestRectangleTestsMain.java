import models.Dataset;
import models.Plane;
import models.Point;
import strategies.DivideAndConquerRectangleFinder;
import strategies.LargestRectangleFinderStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class LargestRectangleTestsMain {

    public static void main(String... args) throws URISyntaxException, FileNotFoundException {
        ArrayList<LargestRectangleFinderStrategy> strategies = new ArrayList<>();
        strategies.add(new DivideAndConquerRectangleFinder());

        URI datasetsFolder = Objects.requireNonNull(LargestRectangleTestsMain.class.getClassLoader().getResource("datasets")).toURI();
        File datasetsFile = new File(datasetsFolder);

        HashMap<Dataset, Integer> expectedResults = new HashMap<>();

        for (File file : Objects.requireNonNull(datasetsFile.listFiles())) {
            // Get expected result from file's name
            String fileName = file.getName();
            String expectedResultString = fileName.substring(fileName.indexOf('s') + 1, fileName.indexOf(' '));
            int expectedResult = Integer.parseInt(expectedResultString);

            // Construct dataset from files
            Scanner scanner = new Scanner(file.getAbsoluteFile());

            String planeSize = scanner.nextLine();
            int width = Integer.parseInt(planeSize.split(" ")[0]);
            int height = Integer.parseInt(planeSize.split(" ")[1]);

            int pointCount = Integer.parseInt(scanner.nextLine());

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

            Plane plane = new Plane(width, height, 0, 0);
            expectedResults.put(new Dataset(fileName, plane, points.toArray(new Point[0]), expectedResult), expectedResult);
        }

        for (LargestRectangleFinderStrategy strategy : strategies) {
            long startAll = System.nanoTime();
            System.out.println("strategy = " + strategy.getClass().getSimpleName());

            expectedResults.forEach((dataset, expectedResult) -> {
                long startDataset = System.nanoTime();
                System.out.println("dataset = " + dataset.getName());

                System.out.println("points = " + Arrays.toString(dataset.getPoints()));
                int foundResult = strategy.find(dataset.getPlane(), dataset.getPoints());
                if (foundResult != expectedResult) {
                    throw new RuntimeException("Wrong result found for : " + dataset.getName() + " : " + foundResult);
                } else {
                    System.out.println("Found result for " + dataset.getName() + " : " + expectedResult + " in " + (System.nanoTime() - startDataset) / 1e9f + "s");
                }
            });

            System.out.println("Resolved in : " + (System.nanoTime() - startAll) / 1e9f + "s");
        }
    }
}
