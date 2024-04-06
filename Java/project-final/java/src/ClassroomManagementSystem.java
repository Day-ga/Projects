import java.sql.*;
import java.util.Scanner;

public class ClassroomManagementSystem {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/classroommanagementsystem";
        String username = "root";
        String password = "Vhur@213";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to the database");

          
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\nClassroom Management System Menu:");
                System.out.println("1. Insert a new student");
                System.out.println("2. Get students in a classroom");
                System.out.println("3. Get lecturers by department");
                System.out.println("4. Update classroom information");
                System.out.println("5. Delete a student");
                System.out.println("6. Delete a lecturer");
                System.out.println("7. Get classroom information");
                System.out.println("8. Get department information");
                System.out.println("9. Insert department information");
                System.out.println("10. insert programme information");
                System.out.println("11. Insert lecturer information");
                System.out.println("12. Place a lecturer in a classroom");
                System.out.println("13. Place a studenet in a programme");
                System.out.println("14. View all Students");
                System.out.println("15. View all Lecturers");
                System.out.println("16. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        insertStudent(connection, scanner);
                        break;
                    case 2:
                        getLecturersByDepartment(connection, scanner);
                        break;
                    case 3:
                        updateClassroomInfo(connection, scanner);
                        break;
                    case 4:
                        deleteStudent(connection, scanner);
                        break;
                    case 5:
                        deleteLecturer(connection, scanner);
                        break;
                    case 6:
                        getClassroomInfo(connection, scanner);
                        break;
                    case 7:
                        getDepartmentInfo(connection, scanner);
                        break;
                    case 8:
                        insertDepartment(connection, scanner);
                        break;
                    case 9:
                        insertClassroom(connection, scanner);
                        break;
                    case 10:
                        insertProgramme(connection, scanner);
                        break;
                    case 11:
                        insertLecturer(connection, scanner);
                        break;
                    case 12:
                        assignLecturerToClassroom(connection, scanner); 
                        break;
                    case 13:
                        assignStudentToProgramme(connection, scanner); 
                        break;
                    case 14:
                        getAllStudents(connection); 
                        break;
                    case 15:
                        getAllLecturers(connection); 
                        break;
                    case 16:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 16);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    

    private static void insertStudent(Connection connection, Scanner scanner) {
        try {
            
            System.out.print("Enter student ST_Number: ");
            int stNumber = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Enter student First Name: ");
            String fName = scanner.nextLine();
            System.out.print("Enter student Last Name: ");
            String lName = scanner.nextLine();

            
            CallableStatement callableStatement = connection.prepareCall("{call InsertStudent(?, ?, ?)}");
            callableStatement.setInt(1, stNumber);
            callableStatement.setString(2, fName);
            callableStatement.setString(3, lName);
            callableStatement.execute();

            System.out.println("Student inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void getLecturersByDepartment(Connection connection, Scanner scanner) {
        try {
            
            System.out.print("Enter Department Name: ");
            String departmentName = scanner.next();

            
            CallableStatement callableStatement = connection.prepareCall("{call GetLecturersByDepartment(?)}");
            callableStatement.setString(1, departmentName);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String lecturerFName = resultSet.getString("Lecturer_fName");
                String lecturerLName = resultSet.getString("Lecturer_lName");
                System.out.println("Lecturer: " + lecturerFName + " " + lecturerLName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void updateClassroomInfo(Connection connection, Scanner scanner) {
        try {
            
            System.out.print("Enter Classroom ID: ");
            int classroomId = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Enter new Location: ");
            String location = scanner.nextLine();
            System.out.print("Enter new Date (yyyy-MM-dd): ");
            String date = scanner.nextLine();
            System.out.print("Enter new Time (HH:mm:ss): ");
            String time = scanner.nextLine();

           
            CallableStatement callableStatement = connection.prepareCall("{call UpdateClassroomInfo(?, ?, ?, ?)}");
            callableStatement.setInt(1, classroomId);
            callableStatement.setString(2, location);
            callableStatement.setDate(3, Date.valueOf(date));
            callableStatement.setTime(4, Time.valueOf(time));
            callableStatement.execute();

            System.out.println("Classroom information updated successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void deleteStudent(Connection connection, Scanner scanner) {
        try {
           
            System.out.print("Enter student ST_Number to delete: ");
            int stNumber = scanner.nextInt();

            // Call the DeleteStudent stored procedure
            CallableStatement callableStatement = connection.prepareCall("{call DeleteStudent(?)}");
            callableStatement.setInt(1, stNumber);
            callableStatement.execute();

            System.out.println("Student with ST_Number " + stNumber + " deleted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteLecturer(Connection connection, Scanner scanner) {
        try {
           
            System.out.print("Enter lecturer Lecture_ID to delete: ");
            int lectureId = scanner.nextInt();

            
            CallableStatement callableStatement = connection.prepareCall("{call DeleteLecturer(?)}");
            callableStatement.setInt(1, lectureId);
            callableStatement.execute();

            System.out.println("Lecturer with Lecture_ID " + lectureId + " deleted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void getClassroomInfo(Connection connection, Scanner scanner) {
        try {
           
            System.out.print("Enter Classroom ID to get information: ");
            int classroomId = scanner.nextInt();

           
            CallableStatement callableStatement = connection.prepareCall("{call GetClassroomInfo(?)}");
            callableStatement.setInt(1, classroomId);
            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                String location = resultSet.getString("location");
                Date date = resultSet.getDate("Date");
                Time time = resultSet.getTime("Time");

                System.out.println("Classroom ID: " + classroomId);
                System.out.println("Location: " + location);
                System.out.println("Date: " + date);
                System.out.println("Time: " + time);
            } else {
                System.out.println("Classroom not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getDepartmentInfo(Connection connection, Scanner scanner) {
        try {
            
            System.out.print("Enter Department ID to get information: ");
            int departmentId = scanner.nextInt();

            CallableStatement callableStatement = connection.prepareCall("{call GetDepartmentInfo(?)}");
            callableStatement.setInt(1, departmentId);
            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                String HOD = resultSet.getString("HOD");
                String departmentName = resultSet.getString("Department_name");

                System.out.println("Department ID: " + departmentId);
                System.out.println("HOD: " + HOD);
                System.out.println("Department Name: " + departmentName);
            } else {
                System.out.println("Department not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void insertDepartment(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter Department ID: ");
            int departmentId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter HOD: ");
            String hod = scanner.nextLine();

            System.out.print("Enter Department Name: ");
            String departmentName = scanner.nextLine();

            CallableStatement callableStatement = connection.prepareCall("{call InsertDepartment(?, ?, ?)}");
            callableStatement.setInt(1, departmentId);
            callableStatement.setString(2, hod);
            callableStatement.setString(3, departmentName);
            callableStatement.execute();

            System.out.println("Department inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void insertClassroom(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter Classroom ID: ");
            String classroomId = scanner.nextLine();

            System.out.print("Enter Location: ");
            String location = scanner.nextLine();

            System.out.print("Enter Date (yyyy-MM-dd): ");
            String date = scanner.nextLine();

            System.out.print("Enter Time (HH:mm:ss): ");
            String time = scanner.nextLine();

            CallableStatement callableStatement = connection.prepareCall("{call InsertClassroom(?, ?, ?, ?)}");
            callableStatement.setString(1, classroomId);
            callableStatement.setString(2, location);
            callableStatement.setDate(3, Date.valueOf(date));
            callableStatement.setTime(4, Time.valueOf(time));
            callableStatement.execute();

            System.out.println("Classroom inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void insertProgramme(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter Programme Code: ");
            int programmeCode = scanner.nextInt();
            scanner.nextLine(); // Consume newline
    
            System.out.print("Enter Programme Name: ");
            String programmeName = scanner.nextLine();
    
            CallableStatement callableStatement = connection.prepareCall("{call InsertProgramme(?, ?)}");
            callableStatement.setInt(1, programmeCode);
            callableStatement.setString(2, programmeName);
            callableStatement.execute();
    
            System.out.println("Programme inserted successfully.");
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void insertLecturer(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter Lecture ID: ");
            int lectureID = scanner.nextInt();
            scanner.nextLine(); // Consume newline
    
            System.out.print("Enter Lecturer First Name: ");
            String lecturerFName = scanner.nextLine();
    
            System.out.print("Enter Lecturer Last Name: ");
            String lecturerLName = scanner.nextLine();
    
            System.out.print("Enter Department: ");
            String department = scanner.nextLine();
    
            CallableStatement callableStatement = connection.prepareCall("{call InsertLecturer(?, ?, ?, ?)}");
            callableStatement.setInt(1, lectureID);
            callableStatement.setString(2, lecturerFName);
            callableStatement.setString(3, lecturerLName);
            callableStatement.setString(4, department);
            callableStatement.execute();
    
            System.out.println("Lecturer inserted successfully.");
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void assignLecturerToClassroom(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter Classroom ID: ");
            String classroomId = scanner.nextLine();
            System.out.print("Enter Lecture ID: ");
            int lectureId = scanner.nextInt();
    
            CallableStatement callableStatement = connection.prepareCall("{call AssignLecturerToClassroom(?, ?)}");
            callableStatement.setString(1, classroomId);
            callableStatement.setInt(2, lectureId);
            callableStatement.execute();
    
            System.out.println("Lecturer assigned to Classroom successfully.");
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }  
    private static void assignStudentToProgramme(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter Student ST_Number: ");
            int stNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline
    
            System.out.print("Enter Programme Code: ");
            int programmeCode = scanner.nextInt();
            scanner.nextLine(); // Consume newline
    
            CallableStatement callableStatement = connection.prepareCall("{call AssignStudentToProgramme(?, ?)}");
            callableStatement.setInt(1, stNumber);
            callableStatement.setInt(2, programmeCode);
            callableStatement.execute();
    
            System.out.println("Student assigned to Program successfully.");
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void getAllStudents(Connection connection) {
        try {
            CallableStatement callableStatement = connection.prepareCall("{call GetAllStudents()}");
            ResultSet resultSet = callableStatement.executeQuery();
    
            System.out.println("List of All Students:");
            while (resultSet.next()) {
                int stNumber = resultSet.getInt("ST_Number");
                String fName = resultSet.getString("FName");
                String lName = resultSet.getString("LName");
                System.out.println("ST_Number: " + stNumber + ", FName: " + fName + ", LName: " + lName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void getAllLecturers(Connection connection) {
        try {
            CallableStatement callableStatement = connection.prepareCall("{call GetAllLecturers()}");
            ResultSet resultSet = callableStatement.executeQuery();
    
            System.out.println("List of All Lecturers:");
            while (resultSet.next()) {
                int lectureID = resultSet.getInt("Lecture_ID");
                String lecturerFName = resultSet.getString("Lecturer_fName");
                String lecturerLName = resultSet.getString("Lecturer_lName");
                String department = resultSet.getString("Department");
                System.out.println("Lecture_ID: " + lectureID + ", Lecturer Name: " + lecturerFName + " " + lecturerLName + ", Department: " + department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
