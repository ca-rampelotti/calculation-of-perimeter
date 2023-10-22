import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) { 
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int numpoints = 0;
        for (Point p : s.getPoints()){
            numpoints = numpoints + 1;
        }    
        return numpoints;
    }

    public double getAverageLength(Shape s) {
        int num = getNumPoints(s);
        double peri = getPerimeter(s);
        double avg = peri/num;
        return avg;
    }

    public double getLargestSide(Shape s) {
        Point prev = s.getLastPoint();
        double dist = 0.0;
        for (Point next : s.getPoints()){
            double newdist = prev.distance(next);
            if (newdist > dist){
                dist = newdist;
            }
            prev = next;
        }
        return dist;
    }

    public double getLargestX(Shape s) {
        double bigX = 0.0;
        for (Point p : s.getPoints()){
            double x = p.getX();
            if (x > bigX){
                bigX = x;
            }
        }
        return bigX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double maxperi = 0;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double peri = getPerimeter(s);
            if (peri>maxperi){
                maxperi = peri;
            }
            
        }
        return maxperi;
    }
    

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double maxperi = 0;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double peri = getPerimeter(s);
            if (peri>maxperi){
                maxperi = peri;
            } 
        }
        File temp = null;
        for (File f1 : dr.selectedFiles()){
            FileResource fr1 = new FileResource(f1);
            Shape s1 = new Shape(fr1);
            double peri1 = getPerimeter(s1);
            if (peri1 == maxperi){
                temp = f1;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);      
        
    }
    
    public void testPerimeterMultipleFiles() {
        double per = getLargestPerimeterMultipleFiles();
        System.out.println(per);
    }

    public void testFileWithLargestPerimeter() {
        String name = getFileWithLargestPerimeter();
        System.out.println(name);
    }

    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter() ;
    }
}