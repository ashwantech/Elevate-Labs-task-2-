import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManagementSystem {
    
    private ArrayList<Student> students;
    private Scanner scanner;
    private static int nextId = 1; 
    
  
    public StudentManagementSystem() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    // Add student
    public void addStudent() {
        try {
            System.out.println("\n--- Add Student ---");
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter marks: ");
            double marks = scanner.nextDouble();
            scanner.nextLine(); 
            
            
            Student student = new Student(nextId++, name, marks);
            students.add(student);
            
            System.out.println(" Student added successfully! ID: " + student.getId());
        } catch (InputMismatchException e) {
            System.out.println(" Invalid input! Please enter valid marks.");
            scanner.nextLine(); 
        }
    }
    
    
    public void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        
        
        sortStudentsById();
        
        for (Student student : students) {
            System.out.println(student);
        }
    }
    
   
    public void updateStudent() {
        try {
            System.out.println("\n--- Update Student ---");
            System.out.print("Enter student ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); 
            
            Student student = findStudentById(id);
            if (student == null) {
                System.out.println(" Student with ID " + id + " not found.");
                return;
            }
            
            System.out.println("Current details: " + student);
            System.out.print("Enter new name (press enter to keep current): ");
            String newName = scanner.nextLine();
            
            System.out.print("Enter new marks (enter -1 to keep current): ");
            double newMarks = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            
            if (!newName.isEmpty()) {
                student.setName(newName);
            }
            if (newMarks != -1) {
                student.setMarks(newMarks);
            }
            
            System.out.println(" Student updated successfully!");
        } catch (InputMismatchException e) {
            System.out.println(" Invalid input! Please enter valid data.");
            scanner.nextLine(); 
        }
    }
    
   
    public void deleteStudent() {
        try {
            System.out.println("\n--- Delete Student ---");
            System.out.print("Enter student ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine(); 
            
            Student student = findStudentById(id);
            if (student == null) {
                System.out.println(" Student with ID " + id + " not found.");
                return;
            }
            
            System.out.println("Are you sure you want to delete: " + student + "? (yes/no)");
            String confirmation = scanner.nextLine();
            
            if (confirmation.equalsIgnoreCase("yes")) {
                students.remove(student);
                System.out.println("Student deleted successfully!");
                
            } else {
                System.out.println("Deletion cancelled.");
            }
        } catch (InputMismatchException e) {
            System.out.println(" Invalid input! Please enter valid ID.");
            scanner.nextLine(); 
        }
    }
    
  
    public void searchStudent() {
        try {
            System.out.println("\n--- Search Student ---");
            System.out.print("Enter student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); 
            
            Student student = findStudentById(id);
            if (student != null) {
                System.out.println(" Student found: " + student);
            } else {
                System.out.println(" Student with ID " + id + " not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println(" Invalid input! Please enter valid ID.");
            scanner.nextLine(); 
        }
    }
    
    
    public void sortStudentsByMarks() {
        if (students.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }
        
        
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.getMarks(), s1.getMarks()); // Descending
            }
        });
        
        System.out.println("\n--- Students Sorted by Marks (Highest to Lowest) ---");
        for (Student student : students) {
            System.out.println(student);
        }
    }
    
    
    private Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
    
    
    private void sortStudentsById() {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Integer.compare(s1.getId(), s2.getId());
            }
        });
    }
    
    
    public void displayMenu() {
        System.out.println("\n📚 STUDENT RECORD MANAGEMENT SYSTEM");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Search Student");
        System.out.println("6. Sort Students by Marks");
        System.out.println("7. Exit");
        System.out.print("Choose an option (1-7): ");
    }
   
    public void run() {
        System.out.println(" Welcome to Student Record Management System!");
        
        while (true) {
            displayMenu();
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 
                
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        updateStudent();
                        break;
                    case 4:
                        deleteStudent();
                        break;
                    case 5:
                        searchStudent();
                        break;
                    case 6:
                        sortStudentsByMarks();
                        break;
                    case 7:
                        System.out.println(" Thank you for using Student Management System!");
                        return;
                    default:
                        System.out.println("Invalid choice! Please enter 1-7.");
                }
            } catch (InputMismatchException e) {
                System.out.println(" Invalid input! Please enter a number between 1-7.");
                scanner.nextLine(); 
            }
        }
    }
    
    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        system.run();
    }
}
