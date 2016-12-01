package wiitty.manager.model;

public class Authenticate {

    private boolean success;
    private String message;

    public Authenticate() {
        this.success = false;
        this.message = "USERNAME_OR_PASSWORD_IS_INCORRECT";
    }

    public Authenticate(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
