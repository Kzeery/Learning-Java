import java.util.ArrayList;


public class Course {
//    Courses have a unique name, a list of sections, a list of courses that they require, and a list of courses that require them.
    private String uniqueName;
    public ArrayList<Section> sections;
    public ArrayList<Course> requirements;
    public ArrayList<Course> requiredFor;

//    Constructor for courses. Takes a unique name string as an argument.
    Course(String uniqueName) {
        this.uniqueName = uniqueName;
        this.sections = new ArrayList<>();
        this.requirements = new ArrayList<>();
        this.requiredFor = new ArrayList<>();
    }

//    Takes a section argument and adds it to the list of sections for the course.
    boolean addSection(Section section) {
        if (sections.contains(section))
            return false;
        sections.add(section);
        return true;
    }

//    Modified from the original code. This now checks course requirements before returning a true or false value.
    boolean enrollStudent(Student student, String sectionName) {
//        Checking to see if there exists a section that is given to the function. If not, return false.
        int sectionIndex = getSectionIndex(sectionName);
        if (sectionIndex != -1) {
//            If a student does not have the requirements, return false.
            for (Course requirement : this.requirements) {
                if (!student.isEnrolled(requirement)) {
                    return false;
                }
            }
//            If they do have the requirements, check capacity.
            if (sections.get(sectionIndex).enrollStudent(student)) {
//                If there is capacity, proceed to add this course to the student's courses and return true.
                student.addCourse(this);
                return true;
            }
        }
        return false;
    }

//    Modified from the original code. This is very similar to enrollStudent.
    boolean unenrollStudent(Student student, String sectionName) {
//        Checking to see if there exists a section that is given to the function. If not, return false.
        int sectionIndex = getSectionIndex(sectionName);
        if (sectionIndex != -1) {
//            If this course is required for another course that the student is enrolled in, do not let them unenroll.
            for (Course requirement : this.requiredFor) {
                if (student.isEnrolled(requirement)) {
                    return false;
                }
            }
//            Check to see if the student is actually enrolled in this section.
            if (sections.get(sectionIndex).unenrollStudent(student)) {
//                If they are, remove this course from the student's courses.
                student.removeCourse(this);
                return true;
            }
        }
        return false;
    }

//    Basic function to add a course to the list of required courses.
    void addRequirement(Course course) {
        this.requirements.add(course);
    }

//    Basic function to add a course to the list of courses that require this course.
    void addRequiredFor(Course course) {
        this.requiredFor.add(course);
    }

//    Basic function to get the name of the course.
    public String getUniqueName() {
        return uniqueName;
    }

//    Returns the list index of a section in the list of sections for the course.
//    If the section does not exist, return -1.
    private int getSectionIndex(String sectionName) {
        for (int i = 0; i < sections.size(); i++) {
            if (sections.get(i).getSectionName().equals(sectionName)) {
                return i;
            }
        }
        return -1;
    }
}
