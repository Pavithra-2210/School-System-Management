import java.util.*;

// Student class
class Student {
    private int id;
    private String name;
    private int grade;

    public Student(int id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}

// Teacher class
class Teacher {
    private int id;
    private String name;
    private String subject;

    public Teacher(int id, String name, String subject) {
        this.id = id;
        this.name = name;
        this.subject = subject;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }
}

// Course class
class Course {
    private int courseId;
    private String name;
    private Teacher teacher;
    private List<Student> studentsEnrolled;

    public Course(int courseId, String name, Teacher teacher) {
        this.courseId = courseId;
        this.name = name;
        this.teacher = teacher;
        this.studentsEnrolled = new ArrayList<>();
    }

    // Method to enroll a student
    public void enrollStudent(Student student) {
        studentsEnrolled.add(student);
    }

    // Method to remove a student
    public void removeStudent(Student student) {
        studentsEnrolled.remove(student);
    }

    // Getters and setters
    public int getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public List<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }
}

// School management application
public class SchoolManagement {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Student> students = new ArrayList<>();
    private static List<Teacher> teachers = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Welcome to School Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Add Teacher");
            System.out.println("3. Add Course");
            System.out.println("4. Enroll Student in Course");
            System.out.println("5. View All Students");
            System.out.println("6. View All Teachers");
            System.out.println("7. View All Courses");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addTeacher();
                    break;
                case 3:
                    addCourse();
                    break;
                case 4:
                    enrollStudentInCourse();
                    break;
                case 5:
                    viewAllStudents();
                    break;
                case 6:
                    viewAllTeachers();
                    break;
                case 7:
                    viewAllCourses();
                    break;
                case 8:
                    exit = true;
                    System.out.println("Exiting School Management System");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student grade: ");
        int grade = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student student = new Student(id, name, grade);
        students.add(student);
        System.out.println("Student added successfully.");
    }

    private static void addTeacher() {
        System.out.print("Enter teacher ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter teacher name: ");
        String name = scanner.nextLine();
        System.out.print("Enter subject taught: ");
        String subject = scanner.nextLine();

        Teacher teacher = new Teacher(id, name, subject);
        teachers.add(teacher);
        System.out.println("Teacher added successfully.");
    }

    private static void addCourse() {
        System.out.print("Enter course ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();

        System.out.println("Teachers:");
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getId() + ". " + teacher.getName());
        }
        System.out.print("Select teacher ID for this course: ");
        int teacherId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Teacher selectedTeacher = null;
        for (Teacher teacher : teachers) {
            if (teacher.getId() == teacherId) {
                selectedTeacher = teacher;
                break;
            }
        }

        if (selectedTeacher != null) {
            Course course = new Course(id, name, selectedTeacher);
            courses.add(course);
            System.out.println("Course added successfully.");
        } else {
            System.out.println("Teacher with ID " + teacherId + " not found.");
        }
    }

    private static void enrollStudentInCourse() {
        System.out.println("Courses:");
        for (Course course : courses) {
            System.out.println(course.getCourseId() + ". " + course.getName());
        }
        System.out.print("Select course ID to enroll student: ");
        int courseId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Course selectedCourse = null;
        for (Course course : courses) {
            if (course.getCourseId() == courseId) {
                selectedCourse = course;
                break;
            }
        }

        if (selectedCourse != null) {
            System.out.println("Students:");
            for (Student student : students) {
                System.out.println(student.getId() + ". " + student.getName());
            }
            System.out.print("Select student ID to enroll: ");
            int studentId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Student selectedStudent = null;
            for (Student student : students) {
                if (student.getId() == studentId) {
                    selectedStudent = student;
                    break;
                }
            }

            if (selectedStudent != null) {
                selectedCourse.enrollStudent(selectedStudent);
                System.out.println(selectedStudent.getName() + " enrolled in " + selectedCourse.getName() + " successfully.");
            } else {
                System.out.println("Student with ID " + studentId + " not found.");
            }
        } else {
            System.out.println("Course with ID " + courseId + " not found.");
        }
    }

    private static void viewAllStudents() {
        System.out.println("All Students:");
        for (Student student : students) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getName() + ", Grade: " + student.getGrade());
        }
    }

    private static void viewAllTeachers() {
        System.out.println("All Teachers:");
        for (Teacher teacher : teachers) {
            System.out.println("ID: " + teacher.getId() + ", Name: " + teacher.getName() + ", Subject: " + teacher.getSubject());
        }
    }

    private static void viewAllCourses() {
        System.out.println("All Courses:");
        for (Course course : courses) {
            System.out.println("ID: " + course.getCourseId() + ", Name: " + course.getName() + ", Teacher: " + course.getTeacher().getName());
            System.out.println("Students Enrolled:");
            for (Student student : course.getStudentsEnrolled()) {
                System.out.println("- " + student.getName());
            }
        }
    }
}