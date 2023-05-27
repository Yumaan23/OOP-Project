import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StudentHelpingPortal {
    private List<FileDetails> files;
    private List<User> users;

    public StudentHelpingPortal() {
        files = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public User login(String regId, String password) {
        for (User user : users) {
            if (user.getRegId().equals(regId) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void addFile(String semester, String fileName, String userId, String uploaderName, FileType fileType, String text) {
        for (FileDetails file : files) {
            if (file.getFileName().equalsIgnoreCase(fileName) && file.getSemester().equalsIgnoreCase(semester) && file.getFileType() == fileType) {
                System.out.println("File with the same name, semester, and type already exists. Upload rejected.");
                return;
            }
        }

        try {
            // Create a new FileWriter object
            FileWriter fileWriter = new FileWriter(fileName);

            // Write the text to the file
            fileWriter.write(text);

            // Close the FileWriter
            fileWriter.close();

            Date date = new Date();
            FileDetails fileDetails = new FileDetails(semester, fileName, date, userId, uploaderName, fileType);
            files.add(fileDetails);
            System.out.println("File uploaded successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
            e.printStackTrace();
        }
    }

    public void searchFiles(String semester, FileType fileType) {
        System.out.println("Search Results:");
        boolean found = false;
        for (FileDetails file : files) {
            String fileSemester = file.getSemester();
            if (fileSemester != null && fileSemester.equalsIgnoreCase(semester) && file.getFileType() == fileType) {
                System.out.println("Semester: " + file.getSemester() +
                        " | File: " + file.getFileName() +
                        " | Date Uploaded: " + file.getDateUploaded() +
                        " | User ID: " + file.getUserId() +
                        " | Uploader Name: " + file.getUploaderName());
                if (file.getFileType() == FileType.ASSIGNMENT || file.getFileType() == FileType.QUIZ) {
                    System.out.println("File Content:");
                    System.out.println(getFileContent(file.getFileName()));
                }
                found = true;
            }
        }
        if (!found) {
            System.out.println("No files found matching the search criteria.");
        }
    }

    public void displayAllFiles() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the semester: ");
        String semester = scanner.nextLine();

        System.out.print("Enter the file type (assignment or quiz): ");
        String fileTypeInput = scanner.nextLine();
        FileType fileType;
        if (fileTypeInput.equalsIgnoreCase("assignment")) {
            fileType = FileType.ASSIGNMENT;
        } else if (fileTypeInput.equalsIgnoreCase("quiz")) {
            fileType = FileType.QUIZ;
        } else {
            System.out.println("Invalid file type. Please try again.");
            return;
        }

        System.out.println("All Files:");
        boolean found = false;
        for (FileDetails file : files) {
            String fileSemester = file.getSemester();
            if (fileSemester != null && fileSemester.equalsIgnoreCase(semester) && file.getFileType() == fileType) {
                System.out.println("Semester: " + file.getSemester() +
                        " | File: " + file.getFileName() +
                        " | Date Uploaded: " + file.getDateUploaded() +
                        " | User ID: " + file.getUserId() +
                        " | Uploader Name: " + file.getUploaderName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No files found matching the search criteria.");
        }
    }

    public void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            if (file.delete()) {
                files.removeIf(fileDetails -> fileDetails.getFileName().equalsIgnoreCase(fileName));
                System.out.println("File deleted successfully!");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("File not found: " + fileName);
        }
    }

    private String getFileContent(String fileName) {
        // Read the content of the file
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            StringBuilder content = new StringBuilder();
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine());
                content.append(System.lineSeparator());
            }
            scanner.close();
            return content.toString();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            return "";
        }
    }
}