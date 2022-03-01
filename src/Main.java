import jdk.swing.interop.SwingInterOpUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;

public class Main {

    public static void main(String[] args) throws FileNotFoundException{

        Automato auto = new Automato();

        try {
            FileInputStream inputStream = new FileInputStream("C:/temp/java_projects/FirstJava/assets/dados.txt");
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();

            //START:criar vetor char para o alphabet com essa primera line
            line = line.substring(line.indexOf('{')+1,line.indexOf('}'));
            String [] alphabet = line.split(",");
            auto.setAlfabeto(alphabet);
            System.out.println("ALFABETO:");
            System.out.println(Arrays.toString(auto.getAlfabeto()));
            //END:criar vetor char para o alfabeto com essa primera line

            line = br.readLine();
            //START:criar vetor string para os estados com essa segunda line
            line = line.substring(line.indexOf('{')+1,line.indexOf('}'));
            String [] estados = line.split(",");
            auto.setEstados(estados);
            System.out.println("ESTADOS:");
            System.out.println(Arrays.toString(auto.getEstados()));
            //END:criar vetor string para os estados com essa segunda line

            line = br.readLine();
            //START:criar vetor string para os finais com essa terceira line
            line = line.substring(line.indexOf('{')+1,line.indexOf('}'));
            String [] finais = line.split(",");
            auto.setFinais(finais);
            System.out.println("FINAIS:");
            System.out.println(Arrays.toString(auto.getFinais()));
            //END:criar vetor string para os finais com essa terceira line


            //START:lendo transições
            JSONObject transitions = new JSONObject();
            line = br.readLine();
            while(line != null) {
                line = line.replaceAll("[() ]", "");
                line = line.replaceAll("[)=]", ",");
                String[] transitionConfigs = line.split(",");
                JSONObject transitionProp = new JSONObject();

                transitionProp.put(transitionConfigs[1],transitionConfigs[2]);
                if(transitions.has(transitionConfigs[0])){
                    transitions.getJSONObject(transitionConfigs[0]).put(transitionConfigs[1],transitionConfigs[2]);
                }else{
                    transitions.put(transitionConfigs[0],transitionProp);
                }
                line = br.readLine();
            }
            auto.setTransicoes(transitions);
            System.out.println("TRANSICOES:");
            System.out.println(auto.getTransicoes());
            br.close();
            reader.close();
            inputStream.close();

            //START:Input palavra
            int x = 0;
            Scanner scan = new Scanner(System.in);
            while(x<10){
                System.out.println("Enter a word");
                String word = scan.nextLine();
                if(auto.validateWord(word)){
                    auto.processWord(word);
                }else{
                    System.out.println("REJEITA");
                }
                x++;
            }
            scan.close();
            String t = "TESTE";

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
