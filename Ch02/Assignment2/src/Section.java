import java.util.ArrayList;

public class Section {
//    Every section has a section name, a maximum enrolment, and a list of students enrolled.
    private String sectionName;
    private int maxEnrolment;
    private ArrayList<Student> enrolledStudents;

//    Constructor for section that requires a name and maximum enrolment.
    Section(String sectionName, int maxEnrolment) {
        enrolledStudents = new ArrayList<>();
        this.maxEnrolment = maxEnrolment;
        this.sectionName = sectionName;
    }

//    Checks if the amount of enrolled students is within the maximum enrollment.
//    Adds the student to the enrolledStudents array and returns true if capacity is okay.
//    Returns false if not.
    boolean enrollStudent(Student student) {
        if (enrolledStudents.size() >= maxEnrolment)
            return false;
        enrolledStudents.add(student);
        return true;
    }

//    Checks if the student who is trying to unenroll is currently enrolled. If they are, unenroll and return true.
//    If not, return false.
    public boolean unenrollStudent(Student student) {
        for (int i = 0; i < enrolledStudents.size(); i++) {
            if (enrolledStudents.get(i).getStudentID() == student.getStudentID()) {
                enrolledStudents.remove(i);
                return true;
            }
        }
        return false;
    }

//    Basic function to return the name of the section.
    public String getSectionName() {
        return sectionName;
    }
}
