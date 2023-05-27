public class User {
    private String regId;
    private String password;

    public User(String regId, String password) {
        this.regId = regId;
        this.password = password;
    }

    public String getRegId() {
        return regId;
    }

    public String getPassword() {
        return password;
    }
}