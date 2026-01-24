import java.util.ArrayList;

public class Authenticator {
    //private static User[] UserList; //no need for list, only one user at a time (at this point in the project)
    static Boolean Flip = false;

    public static boolean isValidUser(String inputName, String inputPassword, ArrayList<User> userList) {
        for (User currentUser : userList) {
            if (currentUser.getName().equals(inputName) && currentUser.getPassword().equals(inputPassword)) {
                return true;
            }
        }
        return false;
    }
}
