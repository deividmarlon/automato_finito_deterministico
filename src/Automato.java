import org.json.JSONObject;

import java.util.Arrays;

public class Automato {
    private String [] alfabeto;
    private String [] estados;
    private String [] finais;
    private JSONObject transicoes;

    Automato(){
        this.estados = null;
        this.finais = null;
    }

    public String[] getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(String[] alfabeto) {
        this.alfabeto = alfabeto;
    }

    public String[] getEstados() {
        return estados;
    }

    public void setEstados(String[] estados) {
        this.estados = estados;
    }

    public String[] getFinais() {
        return finais;
    }

    public void setFinais(String[] finais) {
        this.finais = finais;
    }

    public JSONObject getTransicoes() {
        return transicoes;
    }

    public void setTransicoes(JSONObject transacoes) {
        this.transicoes = transacoes;
    }

    public boolean validateWord(String word) {
        String[] chain = word.split("");
        for (String c:chain){
            if(!Arrays.asList(this.alfabeto).contains(c)){
                return false;
            }
        }
        return true;
    }

    public void processWord(String word) {
        String wordToProcess = word;
        String[] chain = word.split("");
        String currentState = "q0";
        for (String c:chain){
            System.out.println("["+currentState+"]"+wordToProcess);
            JSONObject transitionsProps = this.transicoes.getJSONObject(currentState);
            if(transitionsProps.has(c)){
                currentState = transitionsProps.getString(c);
                wordToProcess = wordToProcess.replaceFirst(c,"");
            }else{
                System.out.println("REJEITA");
                return;
            }
        }
        System.out.println("["+currentState+"]"+wordToProcess);
        if(Arrays.asList(this.finais).contains(currentState)){
            System.out.println("ACEITA");
        }else{
            System.out.println("REJEITA");
        }
        return;
    }

}
