package utils;

import models.Point;

public class RectangleUtils {

    public static int calculateArea(Point bottomLeft, Point topRight) {
        return RectangleUtils.calculateArea(bottomLeft.getX(), bottomLeft.getY(), topRight.getX(), topRight.getY());
    }

    public static int calculateArea(int bottomLeftX, int bottomLeftY, int topRightX, int topRightY) {
        return (topRightX - bottomLeftX) * (topRightY - bottomLeftY);
    }
}
