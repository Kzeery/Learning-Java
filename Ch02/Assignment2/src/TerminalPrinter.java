public class TerminalPrinter {
//    Basic function that prints whether or not an enrollment was successful based on a boolean value wasSuccessful.
//    Uses the student name, course name, and section name in the print statement.
    static void printEnrollmentResult(boolean wasSuccessful,
                                      String studentName,
                                      String courseName,
                                      String sectionName) {
        if (wasSuccessful) {
            System.out.println(studentName + " WAS ENROLLED IN " + courseName + " SECTION " + sectionName);
        } else {
            System.out.println(studentName + " WAS NOT ENROLLED IN " + courseName + " SECTION " + sectionName);
        }
    }

//    Basic function that prints whether or not an unenrollment was successful based on a boolean value wasSuccessful.
//    Uses the student name, course name, and section name in the print statement.
    static void printUnenrollmentResult(boolean wasSuccessful,
                                      String studentName,
                                      String courseName,
                                      String sectionName) {
        if (wasSuccessful) {
            System.out.println(studentName + " WAS UNENROLLED FROM " + courseName + " SECTION " + sectionName);
        } else {
            System.out.println(studentName + " WAS NOT UNENROLLED FROM " + courseName + " SECTION " + sectionName);
        }
    }

}
