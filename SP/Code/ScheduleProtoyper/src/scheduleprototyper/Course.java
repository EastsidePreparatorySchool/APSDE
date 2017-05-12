package scheduleprototyper;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

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
    private String grade;
    private int x;
    private int y;
    private int UIx;
    private int UIy;
    //UIx and UIy refer to a future UI coordinate system to be made by Justice.
    private boolean grayedOut;
    //This holds the rectangle and the text on top of it.

    Course(String input) {

        //period,grade-grade,name_of_course,teacher,typeofcredit
        //D,8-11,Spanish 2,eferguson,Spanish   
        Pattern p = Pattern.compile("([A-H]|0)(-[A-Z]S)?,([0-9]+|[0-9]+-[0-9]+),(.+),[a-z]([a-z]+),(.+)");
        Matcher m = p.matcher(input);
        //info on java regexes here
        //https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        if (!m.matches()) {
            System.out.println("Incorrect csv format.");
        } else {
        this.grade = m.group(3);
        this.name = m.group(4);
        System.out.println("Course " + this.name + " good.");
        this.teacher = m.group(5);
        this.period = m.group(1).toCharArray()[0];
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
    }

    public void erase() {
        
    }

    public void draw() {
        
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

    public String getGrade() {
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
