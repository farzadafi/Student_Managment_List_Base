import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Menu menu = new Menu();
        while (true) {
            switch (menu.mainMenu()) {
                case 0:
                    System.out.println("You enter a wrong number!");
                    break;

                case 1:
                    menu.enterMenu();
                    break;

                case 2:
                    System.out.println("Have a nice day!");
                    System.exit(0);

            }
        }
    }
}
