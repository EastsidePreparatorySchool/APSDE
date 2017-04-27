import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author hhale
 */

public class Course extends Rectangle {
    Color color;
    String name;
    String subject;
    String teacher;
    int grade;
    int x;
    int y;
    boolean grayedOut;
    
    public void erase() {
        this.setFill(Color.WHITE);
    }
    
    public void draw() {
        if (this.grayedOut == true) {
            this.setFill(this.color.desaturate());
        } else {
        this.setFill(this.color);
        }
    }
    
    public void move(int x, int y) {
        this.erase();
        this.x += x;
        this.y += y;
        this.grayedOut = (this.x != 0);
        //grays out colors that are out of the main row
        this.draw();
    }
    
    Course(String input) {
        //period, grades allowed, class name, teacher, credit type *break*
        //
    }
}

