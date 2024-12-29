package hw3;

public class User {

    String name;
    String password;
    boolean isAdmin;

    boolean isAuthenticate = false;

    public User(String name, String password, boolean isAdmin) {
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;

    }

    public boolean isAuthenticate() {
        return isAuthenticate;
    }


    //3.6.
    public boolean authenticate(String name, String password) {
        if (name.equals(this.name) && password.equals(this.password)){
            isAuthenticate = true;
            return true;
        } else {
            isAuthenticate =false;
            return false;
        }
    }

}