import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Automato auto = new Automato();
        String word;

        try {
            FileInputStream inputStream = new FileInputStream("C:\\Users\\danie\\Documents\\MEGAsync\\2 Ano\\LFA\\Trabalho 1\\dados.txt");
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(reader);
            Arquivo arq = new Arquivo();

            //lê o alfabeto
            auto.setAlfabeto(arq.lerAlfabeto(br));
            System.out.println("ALFABETO:");
            System.out.println(Arrays.toString(auto.getAlfabeto()));

            //lê os estados
            auto.setEstados(arq.lerEstados(br));
            System.out.println("ESTADOS:");
            System.out.println(Arrays.toString(auto.getEstados()));

            //lê os estados finais
            auto.setFinais(arq.lerFinais(br));
            System.out.println("FINAIS:");
            System.out.println(Arrays.toString(auto.getFinais()));

            //lê as transições
            auto.setTransicoes(arq.lerTransicoes(br));
            System.out.println("TRANSICOES:");
            System.out.println(auto.getTransicoes());

            //fecha arquivo
            br.close();
            reader.close();
            inputStream.close();

            //pede pela palavra até que seja digitado "fim"
            Scanner scan = new Scanner(System.in);
            do{
                System.out.println("Enter a word");
                word = scan.nextLine();
                if(auto.validateWord(word)){
                    auto.processWord(word);
                }else{
                    System.out.println("REJEITA");
                }
            }while(!word.equals("fim"));
            scan.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
