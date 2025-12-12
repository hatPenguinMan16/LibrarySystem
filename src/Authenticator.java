public class Authenticator {
   //private User[] UserList; no need for list, only one user at a time (at this point in the project)



    public void isValidUser(String inputName, String inputPassword, int currentUser){
        if (UserList[currentUser].getName() == inputName && UserList[currentUser].getPassword() == inputPassword){
            System.out.println("User is valid!");
        }
        else{
            System.out.println("Bad username or password!");
        }
    }

}
