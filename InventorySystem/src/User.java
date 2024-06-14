public class User {
    private final static int MAX_USERNAME_LENGTH = 8;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    public User() {
        firstName = "Super";
        lastName = "User";
        userName = "SuperUser";
        password = "1";
    }

    public User(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = createUserName();
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public boolean correctLogin(String uName, String pWord){
        return uName.toUpperCase().equals(userName) && pWord.equals(password);
    }

    private String createUserName(){
        return firstName.toUpperCase().charAt(0) + lastName.toUpperCase().substring(0,Math.min(lastName.length(),MAX_USERNAME_LENGTH));
    }
}
