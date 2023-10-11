import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//--------------------------aladhary--------------------------------------
public class SchoolApplication {
    private static List<Teacher> teachers = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    static int getNumberFromUser(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextInt();
    }
    static void createTeacher(int numTeachers, Scanner scanner) {
        for (int i = 0; i < numTeachers; i++) {
            System.out.println("\n"+"\u001B[32m<------- Teacher" + (i + 1) + " details ------->");
            System.out.print("\u001B[34mEnter name for Teacher " + (i + 1) + ": ");

            String name = scanner.next();
            double salary = getValidSalary(scanner);
            System.out.print("\u001B[0m");

            Teacher teacherDetails = new Teacher(name, salary);
            teachers.add(teacherDetails);
        }
    }

    static double getValidSalary(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter salary for the teacher: ");
                double salary = scanner.nextDouble();
                if (salary > 0) {
                    return salary;
                } else {
                    System.err.println("Salary must be greater than zero. Please try again.");
                }
            } catch (java.util.InputMismatchException e) {
                System.err.println("Invalid input for salary. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    //search by id
    static void findTeacherById(String teacherId) {
        boolean teacherFound = false;

        for (Teacher teacher : teachers) {
            if (teacher.getTeacherId().equals(teacherId)) {
                System.out.println(teacher);
                teacherFound = true;
                break; // Exit the loop once the teacher is found
            }
        }

        if (!teacherFound) {
            System.out.println("Teacher with ID " + teacherId + " not found.");
        }
    }


    static void createCourse(int numCourses, Scanner scanner) {
        for (int i = 0; i < numCourses; i++) {
            System.out.println("\n" + "\u001B[32m<------- Course" + (i + 1) + " details ------->");
            System.out.print("\u001B[34mEnter name for Course " + (i + 1) + ": ");
            String courseName = scanner.next();
            double price = getValidPrice(scanner);
            System.out.print("\u001B[0m");

            Course courseDetails = new Course(courseName, price);
            courses.add(courseDetails);
        }
    }

    static double getValidPrice(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter price for the course: ");
                double price = scanner.nextDouble();
                if (price >= 0) {
                    return price;
                } else {
                    System.err.println("Price must be greater than or equal to 0. Please try again.");
                }
            } catch (java.util.InputMismatchException e) {
                System.err.println("Invalid input for price. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }


    static void createStudent(int numStudents, Scanner scanner) {
        for (int i = 0; i < numStudents; i++) {
            System.out.println("\n" + "\u001B[32m<------- Student" + (i + 1) + " details ------->");
            System.out.print("\u001B[34mEnter name for Student " + (i + 1) + ": ");
            String studentName = scanner.next();
            System.out.print("\u001B[34mEnter Student address " + (i + 1) + ": ");
            String studentAddress = scanner.next();
//                System.out.print("\u001B[34mEnter Student email " + (i + 1) + ": ");
//                String studentEmail=scanner.next();
//                System.out.print("\u001B[0m");

            boolean validEmail = false;
            String studentEmail = null; //--------------
            while (!validEmail) {
                System.out.print("\u001B[34mEnter Student email " + (i + 1) + ": ");
                studentEmail = scanner.next();
                System.out.print("\u001B[0m");

                if (isValidEmail(studentEmail)) {
                    validEmail = true;
                } else {
                    System.out.println("Invalid email format. Please enter a valid email address.");
                }

            }
            Student studentDetails = new Student(studentName, studentAddress, studentEmail);
            students.add(studentDetails);
        }
    }
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    static void assignTeacherToCourse(Scanner scanner) {

        System.out.print("Enter TEACHER ID: ");
        String teacherId = scanner.nextLine();

        System.out.print("Enter COURSE ID: ");
        String courseId = scanner.nextLine();

        Teacher teacherToAssign = null;
        Course courseToAssign = null;

        for (Teacher teacher : teachers) {
            if (teacher.getTeacherId().equals(teacherId)) {
                teacherToAssign = teacher;
                break;
            }
        }

        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                courseToAssign = course;
                break;
            }
        }

        if (teacherToAssign != null && courseToAssign != null) {
            // Assign the teacher to the course by setting the teacher for the course
            courseToAssign.setTeacher(teacherToAssign);
            System.out.println("Teacher " + teacherToAssign.getName() + " assigned to course " + courseToAssign.getName());
        } else {
            System.out.println("Teacher or course not found. Assignment failed.");
        }
    }

    static void enrollStudentInCourse(Scanner scanner) {
        System.out.println("Enroll a Student in a Course:");

        String studentId = "";
        String courseId = "";

        // Ask the user whether to show all students' IDs or manually input
        System.out.println("Press 1 to show all students' IDs.");
        System.out.println("Press 2 for manual input.");
        int studentChoice = getNumberFromUser("\u001B[34mEnter your choice: ");
        System.out.print("\u001B[0m");


        if (studentChoice == 1) {
            // Show all students' IDs
            System.out.println("All Students' IDs:");
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                System.out.println((i + 1) + ". Student ID: " + student.getStudentId());
            }

            // Prompt for student ID
            int studentIndex = getNumberFromUser("Enter the number corresponding to the student you want to enroll: ");
            if (studentIndex >= 1 && studentIndex <= students.size()) {
                studentId = students.get(studentIndex - 1).getStudentId();
            } else {
                System.out.println("\u001B[31mInvalid student selection.");
                System.out.print("\u001B[0m");

                return;
            }
        } else if (studentChoice == 2) {
            // Manually input student ID
            System.out.print("Enter STUDENT ID: ");
            studentId = scanner.nextLine();
        } else {
            System.out.println("\u001B[31mInvalid choice.");
            System.out.print("\u001B[0m");

            return;
        }

        // Ask the user whether to show all courses' IDs or manually input
        System.out.println("Press 3 to show all courses' IDs.");
        System.out.println("Press 4 for manual input.");
        int courseChoice = getNumberFromUser("\u001B[34mEnter your choice: ");
        System.out.print("\u001B[0m");


        if (courseChoice == 3) {
            // Show all courses' IDs
            System.out.println("All Courses' IDs:");
            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
                System.out.println((i + 1) + ". Course ID: " + course.getCourseId());
            }

            // Prompt for course ID
            int courseIndex = getNumberFromUser("Enter the number corresponding to the course you want to enroll in: ");
            if (courseIndex >= 1 && courseIndex <= courses.size()) {
                courseId = courses.get(courseIndex - 1).getCourseId();
            } else {
                System.out.println("\u001B[31mInvalid course selection.");
                System.out.print("\u001B[0m");

                return;
            }
        } else if (courseChoice == 4) {
            // Manually input course ID
            System.out.print("Enter COURSE ID: ");
            courseId = scanner.nextLine();
        } else {
            System.out.println("\u001B[31mInvalid choice.");
            System.out.print("\u001B[0m");

            return;
        }

        Student studentToEnroll = null;
        Course courseToEnroll = null;

        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                studentToEnroll = student;
                break;
            }
        }

        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                courseToEnroll = course;
                break;
            }
        }

        if (studentToEnroll != null && courseToEnroll != null) {
            // Enroll the student in the course by setting the course for the student
            studentToEnroll.setCourse(courseToEnroll);
            System.out.println("Student " + studentToEnroll.getName() + " enrolled in course " + courseToEnroll.getName());
        } else {
            System.out.println("Student or course not found. Enrollment failed.");
        }
    }

    static void showStudents() {
        // Display the list of students
        System.out.println("List of Students:");

        for (Student student : students) {
            System.out.println("Student ID: " + student.getStudentId());
            System.out.println("Student Name: " + student.getName());
            System.out.println("Student Address: " + student.getAddress());
            System.out.println("Student Email: " + student.getEmail());
            System.out.println("Enrolled Course: " + (student.getCourse() != null ? student.getCourse().getName() : "Not enrolled in a course"));
            System.out.println(); // Add a blank line for better readability
        }
    }