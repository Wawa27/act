package strategies;

import models.Plane;
import models.Point;

/**
 * An attempt to find an O(n) algorithm
 * We add points on the plane one by one by choosing the biggest remaining rectangle
 */
public class LinearRectangleFinder implements LargestRectangleFinderStrategy {

    @Override
    public int find(Plane plane, Point[] points) {
        return 0;
    }
}
