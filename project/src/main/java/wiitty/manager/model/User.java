package wiitty.manager.model;

public class User {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String role;

    public User() {
        this.userName = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.role = "";
    }

    public User(String userName, String password, String firstName, String lastName, String role) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
