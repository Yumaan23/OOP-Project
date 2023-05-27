import java.util.Date;

class FileDetails {
    private String semester;
    private String fileName;
    private Date dateUploaded;
    private String userId;
    private String uploaderName;
    private FileType fileType;

    public FileDetails(String semester, String fileName, Date dateUploaded, String userId, String uploaderName, FileType fileType) {
        this.semester = semester;
        this.fileName = fileName;
        this.dateUploaded = dateUploaded;
        this.userId = userId;
        this.uploaderName = uploaderName;
        this.fileType = fileType;
    }

    public FileDetails(String semester, String fileName, FileType valueOf, String userId, String uploaderName) {
    }

    public String getSemester() {
        return semester;
    }

    public String getFileName() {


        return fileName;
    }

    public Date getDateUploaded() {
        return dateUploaded;
    }

    public String getUserId() {
        return userId;
    }

    public String getUploaderName() {
        return uploaderName;
    }

    public FileType getFileType() {
        return fileType;
    }
}

enum FileType {
    ASSIGNMENT,
    QUIZ
}