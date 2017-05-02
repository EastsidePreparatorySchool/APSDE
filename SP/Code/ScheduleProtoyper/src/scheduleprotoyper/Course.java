package scheduleprotoyper;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author hhale
 */
public class Course extends Rectangle {

    private Color color;
    private String name;
    private String subject;
    private String teacher;
    private Character period;
    private int grade;
    private int UIx;
    private int UIy;
    //UIx and UIy refer to a UI coordinate system to be made by Justice.
    boolean grayedOut;

    public void erase() {
        this.setFill(Color.WHITE);
    }

    public void draw() {
        //In the future the coordinates should be adjusted here
        //to draw the courses at their correct sizes instead of 200*200.
        this.setX(this.UIx * 200);
        this.setY(this.UIy * 200);
        if (this.grayedOut == true) {
            this.setFill(this.color.desaturate());
        } else {
            this.setFill(this.color);
        }
    }

    public void move(int x, int y) {
        this.erase();
        this.UIx += x;
        this.UIy += y;
        this.grayedOut = (this.UIx != 0);
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
        this.setY((int) this.period);
        this.setX(0);

        //the colors are the same as on the current pdf schedules
        //sorted by period
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

    public Color getColor() {
        return this.color;
    }

    public String getName() {
        return this.name;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getTeacher() {
        return this.teacher;
    }

    public Character getPeriod() {
        return this.period;
    }

    public int getGrade() {
        return this.grade;
    }

    public int getUIx() {
        //the x coordinate relative to other courses
        return this.UIx;
    }

    public int getUIy() {
        //the y coordinate relative to other courses
        return this.UIy;
    }
}
