import java.util.ArrayList;

public class Student {
//    Every student has a unique integer id, a name, and a list of enrolled courses.
    private int studentID;
    private String name;
    private ArrayList<Course> enrolledCourses;

//    Student constructor that takes a name and id.
    Student(String name, int studentID) {
        this.studentID = studentID;
        this.name = name;
        this.enrolledCourses = new ArrayList<>();
    }

//    Basic function that adds a course to the list of courses.
    void addCourse(Course course) {
        this.enrolledCourses.add(course);
    }

//    Basic function that removes a course from the list of courses, if it exists in the list.
    void removeCourse(Course course) {
        if(this.isEnrolled(course)) {
            this.enrolledCourses.remove(course);
        }
    }

//    Basic function that returns student id.
    public int getStudentID() {
        return studentID;
    }

//    Basic function that returns student name.
    public String getName() {
        return name;
    }

//    Function that checks the list of enrolled courses to see if a course exists in it.
//    If the student is enrolled in the course, return true.
//    If not, return false.
    public Boolean isEnrolled(Course course) {
        for (Course enrolledCourse : enrolledCourses) {
            if(course.equals(enrolledCourse)) {
                return true;
            }
        }
        return false;
    }
}
