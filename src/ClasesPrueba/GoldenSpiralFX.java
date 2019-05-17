package ClasesPrueba;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Jose Pereda
 */
public class GoldenSpiralFX extends Application {
    
    private final int total = 12;
    private final double minSize = 1;
    private Rectangle r0;
        
    private final List<Group> groups = new ArrayList<>();
    private final List<Point2D> knots = new ArrayList<>();
    
    @Override
    public void start(Stage primaryStage) {
        Group g = new Group();
        for (int i = total; i >= 0; i--) {
            double size = minSize * fibonacci(i + 1);
            Rectangle r = new Rectangle(40, 40, size, size);
            r.setFill(Color.TRANSPARENT);
            r.setStroke(Color.BLACK);
            
            if (i < total) {
                r.setX(r0.getX() + r0.getWidth());
                r.setY(r0.getY() + r0.getHeight());
                final Group group = new Group(r);
                ((Group) r0.getParent()).getChildren().add(group);
                groups.add(0, group);
            } else {
                g.getChildren().add(r);
                groups.add(g);
            }
            r0 = r;
            
        }
        Path path = new Path();
        path.setStroke(Color.RED);
        path.setStrokeWidth(3);
        
        AnchorPane root = new AnchorPane(g, path);
        Scene scene = new Scene(root, 750, 750);
        primaryStage.setTitle("Golden Spiral - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Circle c = new Circle(1);
        TranslateTransition tt = new TranslateTransition(Duration.seconds(5), c);
        tt.setFromX(-90);
        tt.setToX(90);
        tt.setAutoReverse(true);
        tt.setCycleCount(TranslateTransition.INDEFINITE);
        
        c.translateXProperty().addListener((obs, ov, nv) -> {
            groups.stream()
                .limit(total)
                .forEach(gi -> {
                    Rectangle ri = (Rectangle) gi.getChildren().get(0);
                    gi.getTransforms().setAll(new Rotate(nv.doubleValue(), ri.getX(), ri.getY()));
                });
            knots.clear();
            knots.add(new Point2D(40, 40));
            path.getElements().clear();
            path.getElements().setAll(new MoveTo(40, 40)); 
            groups.stream()
                .forEach(gi -> {
                    Rectangle ri = (Rectangle) gi.getChildren().get(0);
                    Point2D pi = ri.localToScene(new Point2D(ri.getX() + ri.getWidth(), ri.getY() + ri.getHeight()));
                    knots.add(1, new Point2D(pi.getX(), pi.getY()));
                });
            InterpolateBezier interpolate = new InterpolateBezier(knots);
            List<InterpolateBezier.Bezier> splines = interpolate.getSplines();
            splines.forEach(s -> {
                final List<Point2D> points = s.getPoints();
                path.getElements().add(new CubicCurveTo(
                        points.get(1).getX(), points.get(1).getY(), 
                        points.get(2).getX(), points.get(2).getY(), 
                        points.get(3).getX(), points.get(3).getY()));
            });
        });
        tt.play();

    }

    public int fibonacci(int n)  {
        return n < 2 ? n : fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}