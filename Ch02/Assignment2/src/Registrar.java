import java.util.ArrayList;
import java.util.Scanner;

public class Registrar {
//    List of students and courses in the registrar, and the scanner to be used to receive input.
    ArrayList<Student> students;
    ArrayList<Course> courses;
    Scanner sc;

//    Class constructor. Makes the lists and scanner.
    public Registrar() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        sc = new Scanner(System.in);
    }

//    Basic function to return the next line from the user.
    private String nextLine() {
        return sc.nextLine();
    }

//    Basic function that adds a new course to the list of courses by a course name.
    private void createCourse(String courseName) {
        this.courses.add(new Course(courseName));
    }

//    Basic function that adds a new student to the list of student by a student name and id.
    private void createStudent(String studentName, int studentID) {
        this.students.add(new Student(studentName, studentID));
    }

//    Function that searches through the list of courses, and returns a course by the course name.
    private Course getCourseByName(String courseName) {
        for (Course course : this.courses) {
            if(course.getUniqueName().equals(courseName)) {
                return course;
            }
        }
        return null;
    }

//    Function that searches through the list of students, and returns a student by their student id.
    private Student getStudentByID(int studentID) {
        for (Student student : this.students) {
            if(student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }

//    Function that creates a section using a course name, the section name, and the capacity.
//    It uses the getCourseByName function to find the course, and then adds a new section to it.
    private void createSection(String courseName, String sectionName, int capacity) {
        Course course = getCourseByName(courseName);
        course.addSection(new Section(sectionName, capacity));
    }

//    This function can enrol or unenroll a student depending on the boolean enrol.
//    It finds a student and a course using the get functions, and tries to enrol or unenroll them to/from a section.
//    If enrol is true, it will try to enrol them. If enrol is false, it will try to unenroll them.
    private void changeEnrollment(int studentID, String courseName, String sectionName, boolean enrol) {
        Student student = getStudentByID(studentID);
        Course course = getCourseByName(courseName);
        if (enrol) {
            boolean enrollmentResult = course.enrollStudent(student, sectionName);
            TerminalPrinter.printEnrollmentResult(enrollmentResult, student.getName(), course.getUniqueName(), sectionName);
            return;
        }
        boolean enrollmentResult = course.unenrollStudent(student, sectionName);
        TerminalPrinter.printUnenrollmentResult(enrollmentResult, student.getName(), course.getUniqueName(), sectionName);
        return;

    }

//    This function finds course A (main course) and course B (required course) using the get functions.
//    It adds course B as a requirement for course A.
//    It then adds course A as a course that B is required for.
    private void addReq(String mainCourse, String requiredCourse) {
        Course main = getCourseByName(mainCourse);
        Course required = getCourseByName(requiredCourse);
        main.addRequirement(required);
        required.addRequiredFor(main);

    }

//    Main function that accepts user input. It uses a switch based on the first word given in the line.
    public void runCommands() {
        String line = nextLine();
        //    As long as the first word is not FINISH, the function will keep accepting input.
        while(!line.equals("FINISH")) {
            //    It splits the words into an array based on the space character.
            String[] lineSplit;
            lineSplit = line.split(" ");
            switch(lineSplit[0]) {
//                Based on the first word, it will call different functions with their arguments.
                case "COURSE":
                    createCourse(lineSplit[1]);
                    break;
                case "STUDENT":
                    createStudent(lineSplit[1], Integer.parseInt(lineSplit[2]));
                    break;
                case "SECTION":
                    createSection(lineSplit[1], lineSplit[2], Integer.parseInt(lineSplit[3]));
                    break;
                case "ENROLL":
                    changeEnrollment(Integer.parseInt(lineSplit[1]), lineSplit[2], lineSplit[3], true);
                    break;
                case "UNENROLL":
                    changeEnrollment(Integer.parseInt(lineSplit[1]), lineSplit[2], lineSplit[3], false);
                    break;
                case "REQUIREMENT":
                    addReq(lineSplit[1], lineSplit[2]);
                    break;
            }
            line = nextLine();
        }
        sc.close();
    }

    /* Hardcoded example. Instead, you need to implement new functions that read
    the input from the terminal and print the corresponding outputs. */
    public void runExampleCommands() {
        Course course1 = new Course("CS2XYZ");
        Course course2 = new Course("CS1ABC");

        Student student1 = new Student("EMMA", 23345);
        Student student2 = new Student("DAVID", 123345);

        course1.addSection(new Section("C01", 10));
        course2.addSection(new Section("C02", 1));

        boolean enrollmentResult = course1.enrollStudent(student1, "C01");
        TerminalPrinter.printEnrollmentResult(enrollmentResult, student1.getName(),
                course1.getUniqueName(), "C01");

        enrollmentResult = course1.enrollStudent(student2, "C01");
        TerminalPrinter.printEnrollmentResult(enrollmentResult, student2.getName(),
                course1.getUniqueName(), "C01");

        enrollmentResult = course2.enrollStudent(student1, "C02");
        TerminalPrinter.printEnrollmentResult(enrollmentResult, student1.getName(),
                course2.getUniqueName(), "C02");

        enrollmentResult = course2.enrollStudent(student2, "C02");
        TerminalPrinter.printEnrollmentResult(enrollmentResult, student2.getName(),
                course2.getUniqueName(), "C02");

        enrollmentResult = course2.unenrollStudent(student1, "C02");
        TerminalPrinter.printUnenrollmentResult(enrollmentResult, student1.getName(),
                course2.getUniqueName(), "C02");

        enrollmentResult = course2.unenrollStudent(student2, "C02");
        TerminalPrinter.printUnenrollmentResult(enrollmentResult, student2.getName(),
                course2.getUniqueName(), "C02");

        enrollmentResult = course2.enrollStudent(student2, "C02");
        TerminalPrinter.printEnrollmentResult(enrollmentResult, student2.getName(),
                course2.getUniqueName(), "C02");
    }

}

