import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

public class Arquivo {
    private String line;
    private String[] alphabet;
    private String[] estados;
    private String[] finais;

    //le a linha de alfabeto do arquivo, trata a string e
    // retorna um vetor de string com o alfabeto
    //pré-condições: buffreader aberto na linha de alfabeto
    //pós-condições: vetor de string com o alfabeto retornado
    public String[] lerAlfabeto(BufferedReader br){
        try {
            String line = br.readLine();
            line = line.substring(line.indexOf('{')+1,line.indexOf('}'));
            this.alphabet = line.split(",");
        }catch(IOException e){
            e.printStackTrace();
        }
        return alphabet;
    }

    //le a linha de estados do arquivo, trata a string e
    // retorna um vetor de string com os estados
    //pré-condições: buffreader aberto na linha de estados
    //pós-condições: vetor de string com os estados retornado
    public String[] lerEstados(BufferedReader br){
        try {
            line = br.readLine();
            line = line.substring(line.indexOf('{')+1,line.indexOf('}'));
            estados = line.split(",");
        }catch(IOException e){
            e.printStackTrace();
        }
        return estados;
    }

    //le a linha de estados finais do arquivo, trata astring e
    // retorna um vetor de string com os estados finais
    //pré-condições: buffreader aberto na linha de estados finais
    //pós-condições: vetor de string com os estados finais retornado
    public String[] lerFinais(BufferedReader br){
        try {
            line = br.readLine();
            line = line.substring(line.indexOf('{')+1,line.indexOf('}'));
            finais = line.split(",");
        }catch(IOException e){
            e.printStackTrace();
        }
        return finais;
    }

    //le as linhas com transições no arquivo, trata as
    // entradas e guarda as transições em um JSONObject
    //pré-condições: buffreader aberto em uma linha com transições
    //pós-condições: retorna o JSONObject com as transições.
    public JSONObject lerTransicoes(BufferedReader br){
        JSONObject transitions = new JSONObject();
        try {
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
        }catch(IOException e){
            e.printStackTrace();
        }
        return transitions;
    }
}

