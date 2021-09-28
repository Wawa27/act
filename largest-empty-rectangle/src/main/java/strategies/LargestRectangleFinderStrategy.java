package strategies;

import models.Plane;
import models.Point;

public interface LargestRectangleFinderStrategy {

    int find(Plane plane, Point[] points);
}
