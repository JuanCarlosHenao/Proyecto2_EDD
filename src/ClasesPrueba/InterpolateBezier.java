package ClasesPrueba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.geometry.Point2D;

/** Given N 2D points (knots), N-1 Bezier cubic curves will be generated, 
 *  passing through these knots, by adding 2(N-1) control points
 *  Conditions if spline is open: 
 *  - C2 in all the inner knots (2 ecuations x N-2 knots)
 *  - On external knots r''=0 (2 ecuations)
 
*  Tridiagonal linear system -> Thomas algorithm
 *  See: http://www.particleincell.com/blog/2012/bezier-splines/
 * 
 * @author Jose Pereda
 */
public class InterpolateBezier {

    private final List<Point2D> knots;
    private final int numSplines;
    private final List<Bezier> splines;
    
    public InterpolateBezier(List<Point2D> knots) {
        this.knots = knots;
        numSplines = knots.size()-1;
        
        splines = new ArrayList<>(numSplines);
        calculateControlPoints();
    }
    
    public List<Bezier> getSplines() { return splines; }
    
    private void calculateControlPoints() {

        for(int j = 0; j < numSplines; j++) {
            splines.add(new Bezier(knots.get(j), new Point2D(0, 0), new Point2D(0, 0), knots.get(j + 1)));
        }

        for(int i = 0; i < 2; i++) {
            double[] p1 = new double[numSplines];
            double[] p2 = new double[numSplines];
            double[] ca = new double[numSplines];
            double[] cb = new double[numSplines];
            double[] cc = new double[numSplines];
            double[] cr = new double[numSplines];
            cb[0] = 2d; 
            cc[0] = 1d; 
            cr[0] = i == 0 ? knots.get(0).getX() + 2d * knots.get(1).getX() : 
                             knots.get(0).getY() + 2d * knots.get(1).getY();
            for(int j = 1; j < numSplines - 1; j++) {
                ca[j] = 1d;
                cb[j] = 4d;
                cc[j] = 1d;
                cr[j] = i == 0 ? 4d * knots.get(j).getX() + 2d * knots.get(j + 1).getX() : 
                                 4d * knots.get(j).getY() + 2d * knots.get(j + 1).getY();
            }
            ca[numSplines-1] = 2d;
            cb[numSplines-1] = 7d;
            cr[numSplines-1] = i == 0 ? 8d * knots.get(numSplines - 1).getX() + knots.get(numSplines).getX() : 
                                        8d * knots.get(numSplines - 1).getY() + knots.get(numSplines).getY();

            for(int j = 1; j < numSplines; j++) {
                double m = ca[j] / cb[j - 1];
                cb[j] -= m * cc[j - 1];
                cr[j] -= m * cr[j - 1];
            }

            p1[numSplines - 1] = cr[numSplines - 1] / cb[numSplines - 1];
            for(int j = numSplines - 2; j >= 0; j--) {
                p1[j] = (cr[j] - cc[j] * p1[j + 1]) / cb[j];
            }

            for(int j = 0; j < numSplines - 1; j++) {
                p2[j] = (i == 0 ? 2d * knots.get(j + 1).getX() : 
                                 2d * knots.get(j + 1).getY()) - p1[j + 1];
            }
            p2[numSplines - 1] = ((i == 0 ? knots.get(numSplines).getX() : 
                                            knots.get(numSplines).getY()) + p1[numSplines - 1]) / 2d;

            for(int j = 0; j < numSplines; j++) {
                Bezier bez = splines.get(j);
                if(i == 0){
                    bez.getPoints().set(1, new Point2D(p1[j], 0));
                    bez.getPoints().set(2, new Point2D(p2[j], 0));
                } else {
                    bez.getPoints().set(1, new Point2D(bez.getPoints().get(1).getX(), p1[j]));
                    bez.getPoints().set(2, new Point2D(bez.getPoints().get(2).getX(), p2[j]));
                } 
            }
        }
        
    }
    
    class Bezier {
    
        private final List<Point2D> points;

        public Bezier(Point2D a, Point2D b, Point2D c, Point2D d) {
            points=Arrays.asList(a, b, c, d);
        }

        public List<Point2D> getPoints() { return points; }

        @Override
        public String toString() {
            return points.toString();
        }

    }
}
