
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
    Character period;
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
        //parse the strings here

        //placeholders
        super(0, 0, Color.WHITE);
        this.grade = 9;
        this.name = "Biology";
        this.teacher = "Waltzer";
        this.period = 'A';
        this.y = (int) this.period;
        this.x = 0;
        switch (this.period) {
            case 'A':
                this.color = Color.RED;
                break;
            case 'B':
                this.color = Color.CYAN;
                break;
            case 'C':
                this.color = Color.PURPLE;
                break;
            case 'D':
                this.color = Color.YELLOW;
                break;
            case 'E':
                this.color = Color.GREEN;
                break;
            case 'F':
                this.color = Color.BURLYWOOD;
                break;
            case 'G':
                this.color = Color.BLUE;
                break;
            case 'H':
                this.color = Color.PINK;
                break;
        }
    }
}
