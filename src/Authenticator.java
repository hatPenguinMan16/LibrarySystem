public class Authenticator {
    //private static User[] UserList; //no need for list, only one user at a time (at this point in the project)
    static Boolean Flip = false;

    public static boolean isValidUser(String inputName, String inputPassword, User[] UserList) {
        for (User currentUser : UserList) {
            if (currentUser.getName() == inputName && currentUser.getPassword() == inputPassword) {
                System.out.println("User is valid!");
                return true;
            }
        }
        System.out.println("Bad username or password!");
        return false;
    }
}
