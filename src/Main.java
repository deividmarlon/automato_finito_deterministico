import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Automato auto = new Automato();
        Scanner scan = new Scanner(System.in);
        String word, data;
        System.out.println("Digite o nome do arquivo.");
        data = scan.nextLine();

        while(!data.equals("fim")) {
            try {
                FileInputStream inputStream = new FileInputStream(data);
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
                System.out.println("Enter a word");
                word = scan.nextLine();
                while (!word.equals("fim")) {
                    if (auto.validateWord(word)) {
                        auto.processWord(word);
                    } else {
                        System.out.println("REJEITA");
                    }
                    System.out.println("Enter a word");
                    word = scan.nextLine();
                }
                scan.close();

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }

    }

}
