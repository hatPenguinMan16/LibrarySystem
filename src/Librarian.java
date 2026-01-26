public class Librarian extends User implements manageBooks{
    public Librarian(String name, String password) {
        super(name, password);
    }

    @Override
    public void addBook() {
        String[] tempBookInfo = {"","","","","",""};
        for (int i = 0; i < 5; i++){
            // add input from terminal
            // maybe from file insted?
        }
    }

    @Override
    public void deleteBook() {

    }

    @Override
    public void changeBookInfo() {

    }
}
