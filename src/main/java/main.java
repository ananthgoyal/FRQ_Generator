import java.util.Scanner;
import _2018.*;

public class main {


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Type in Year of Problem ('2015', '2016', etc.)");
        String year = scanner.nextLine();  // Read user input

        System.out.println("Type in Problem Number ('1', '2', etc.)");
        String problem = scanner.nextLine();


        if (year.equals("2018")) {
            if (problem.equals("1")){
                try {
                    _2018.prob_1.main(null);
                } catch (Exception a) {
                    System.out.println("error");
                }
            }

        }


    }



    }



