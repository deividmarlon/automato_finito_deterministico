import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Declarations
        Automaton auto = new Automaton();
        Scanner scan = new Scanner(System.in);
        String word, fileName;


        //Loop: Ask file name until !@# is entered
        System.out.println("Type the file name: (Use !@# to exit)");
        fileName = scan.nextLine();
        while(!fileName.equals("!@#")) {
            try {
                File file = new File(fileName);

                //Reading alphabet
                auto.setAlphabet(file.readAlphabet());

                //Reading states
                auto.setStates(file.readStates());

                //Reading final states
                auto.setFinalStates(file.readFinalStates());

                //Reading transitions
                auto.setTransitions(file.readTransitions());

                //Closing file
                file.closeFile();

                //Loop: Ask a word until !@# is entered
                System.out.println("Enter a word: (Use !@# to exit)");
                word = scan.nextLine();
                while (!word.equals("!@#")) {
                    auto.processWord(word);
                    System.out.println("Enter a word: (Use !@# to exit)");
                    word = scan.nextLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            System.out.println("Type the file name: (Use !@# to exit)");
            fileName = scan.nextLine();
        }
        //Closing scan
        scan.close();

        System.out.println("Exiting the program");
    }

}
