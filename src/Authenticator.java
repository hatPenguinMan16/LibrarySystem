import java.util.ArrayList;

public class Authenticator {

    public static User login(String inputName, String inputPassword, ArrayList<User> userList) {
        for (User currentUser : userList) {
            if (currentUser.getName().equals(inputName) && currentUser.getPassword().equals(inputPassword)) {
                return currentUser;
            }
        }
        return null;
    }
}