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

    void deleteUser(){

    }

    public void addUser(){

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
