import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);
        String password;
        String username;

        System.out.println("What's your username?");
        username = input.nextLine();
        System.out.println("What's your password?");
        password = input.nextLine();

        User loggedUser = CapitalDAO.getUserByUNAndPassword(username, password);

        if (loggedUser != null) {
            System.out.println("You logged in as " + username);
            System.out.println("For viewing Countries and Capitals press 1");
            System.out.println("For inserting new country with capital press 2");
            int choiceOperation;
            choiceOperation = input.nextInt();
            switch (choiceOperation) {
                case 1:
                    List<Capital> capitalList = CapitalDAO.getAllCapitals();
                    for (Capital capital : capitalList) {
                        System.out.println(capital.getCountry() + ": " + capital.getCapital());
                    }
                    break;
                case 2:
                    if(loggedUser.isAdmin()) {
                        String country;
                        System.out.print("Enter name of the country: ");
                        country = input.next();
                        String capital;
                        System.out.print("Enter name of the capital: ");
                        capital = input.next();
                        if(CapitalDAO.insertCapital(country, capital, loggedUser.getId())) {
                            System.out.println("Successfully insert country and capital");
                        } else {
                            System.out.println("Error inserting new country and capital");
                        }
                        break;
                    } else {
                        System.out.println("You don't have credentials to process this operation");
                    }
                    break;
                default:
                    System.out.println("Invalid operation");
                    break;
            }

        } else {
            System.out.println("Invalid username or password");
        }
        input.close();
    }
}
