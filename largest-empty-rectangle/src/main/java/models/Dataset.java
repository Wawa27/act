package models;

public class Dataset {
    private String name;
    private Plane plane;
    private Point[] points;
    private int result;

    public Dataset() {
    }

    public Dataset(String name, Plane plane, Point[] points, int result) {
        this.name = name;
        this.plane = plane;
        this.points = points;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
