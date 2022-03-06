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

    //retorna o valor de alfabeto
    //pré-condições: nenhuma
    //pós-condições: valor de alfabeto retornado
    public String[] getAlfabeto() {
        return alfabeto;
    }

    //declara o valor de alfabeto
    //pré-condições: nenhuma
    //pós-condições: valor de alfabeto declarado
    public void setAlfabeto(String[] alfabeto) {
        this.alfabeto = alfabeto;
    }

    //retorna o valor de estados
    //pré-condições: nenhuma
    //pós-condições: valor de estados retornado
    public String[] getEstados() {
        return estados;
    }

    //declara o valor de estados
    //pré-condições: nenhuma
    //pós-condições: valor de estados declarado
    public void setEstados(String[] estados) {
        this.estados = estados;
    }

    //retorna o valor de finais
    //pré-condições: nenhuma
    //pós-condições: valor de finais retornado
    public String[] getFinais() {
        return finais;
    }

    //declara o valor de finais
    //pré-condições: nenhuma
    //pós-condições: valor de finais retornado
    public void setFinais(String[] finais) {
        this.finais = finais;
    }

    //retona o valor de transicoes
    //pré-condições: nenhuma
    //pós-condições: valor de transicoes retornado
    public JSONObject getTransicoes() {
        return transicoes;
    }

    //declara o valor de transicoes
    //pré-condições: nenhuma
    //pós-condições: valor de transicoes declarado
    public void setTransicoes(JSONObject transacoes) {
        this.transicoes = transacoes;
    }

    //
    //pré-condições:
    //pós-condições:
    public boolean validateWord(String word) {
        String[] chain = word.split("");
        for (String c:chain){
            if(!Arrays.asList(this.alfabeto).contains(c)){
                return false;
            }
        }
        return true;
    }

    //
    //pré-condições:
    //pós-condições:
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
