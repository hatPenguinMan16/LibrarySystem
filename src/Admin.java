public class Admin extends User implements manageBooks{
    private boolean isAdmin;
    public Admin(String name, String password) {
        super(name, password);
        if (!isAdmin) System.out.println("User must be admin.");
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    void deleteUser(String name, String password){
        //If user in list remove
    }

    public void addUser(String name, String password){
        //Create new user
        //User name = new User("name","password");
        //Add to list
    }

    @Override
    public void addBook() {

    }

    @Override
    public void deleteBook() {

    }

    @Override
    public void changeBookInfo() {

    }
}
