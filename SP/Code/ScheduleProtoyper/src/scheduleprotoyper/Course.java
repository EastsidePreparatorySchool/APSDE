package scheduleprotoyper;

import javafx.scene.paint.Color;
import static scheduleprotoyper.SchedulePrototyper.MasterContext;
/**
 *
 * @author hhale
 */
public class Course {

    //These are the rectangles that represent courses you have in your schedule.
    private Color color;
    private String name;
    private String subject;
    private String teacher;
    private Character period;
    private int grade;
    private int x;
    private int y;
    private int UIx;
    private int UIy;
    //UIx and UIy refer to a future UI coordinate system to be made by Justice.
    private boolean grayedOut;

    Course(String input) {
        //String format: period, grades allowed, class name, teacher, credit type *break*
        //parse those strings here later

        //placeholders below
        this.grade = 9;
        this.name = "Biology";
        this.teacher = "Waltzer";
        this.period = 'A';
        this.UIy = (int) this.period - 1;
        this.UIx = 0;

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

        this.draw();
    }

    public void erase() {
        this.color = Color.WHITE;
    }

    public void draw() {
        //In the future the coordinates should be adjusted here
        //to draw the courses at their correct sizes instead of 200*200.
        this.x = this.UIx * 200;
        this.y = this.UIy * 150;
        if (this.grayedOut == true) {
            MasterContext.setFill(this.color.desaturate());
        } else {
            MasterContext.setFill(this.color);
        }
        MasterContext.fillRect(this.x, this.y, 200, 150);
    }

    public void move(int x, int y) {
        this.erase();
        this.UIx += x;
        this.UIy += y;
        this.grayedOut = (this.UIx != 0);
        //grays out colors that are out of the main row
        this.draw();
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

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
