import com.google.gson.Gson;
import models.Dataset;
import models.Point;
import strategies.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Main {

    public static void main(String... args) throws IOException {
        List<LargestRectangleFinderStrategy> strategies = new ArrayList<>();
        strategies.add(new NaiveLargestRectangleFinder());
        strategies.add(new DivideAndConquerRectangleFinder());
        strategies.add(new ParallelDivideAndConquerRectangleFinder());

        File directory = new File(args[0]);
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            Gson gson = new Gson();
            String json = String.join("\r\n", Files.readAllLines(file.toPath()));
            Dataset dataset = gson.fromJson(json, Dataset.class);

            System.out.println("-----------------------------------");
            System.out.println("For dataset : " + dataset.getName());
            System.out.println("-----------------------------------");

            for (LargestRectangleFinderStrategy strategy : strategies) {
                System.out.println("Using strategy : " + strategy.getClass().getSimpleName());
                long startTime = System.nanoTime();
                ArrayList<Point> points = new ArrayList<>();
                points.add(new Point(0, 0));
                Collections.addAll(points, dataset.getPoints());
                points.add(new Point(dataset.getPlane().getWidth(), 0));
                int foundResult = strategy.find(dataset.getPlane(), points.toArray(new Point[0]));
                long deltaTime = System.nanoTime() - startTime;
                System.out.println("Found rectangle area : " + foundResult + " in " + deltaTime + "ns");
                System.out.println("Is expected result ? " + (dataset.getResult() == foundResult ? "✔" : "❌"));
                System.out.println();
            }
        }
    }
}
