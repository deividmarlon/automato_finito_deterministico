import org.json.JSONObject;

import java.util.Arrays;

public class Automaton {
    private String [] alphabet;
    private String [] states;
    private String [] finalStates;
    private JSONObject transitions;

    //Constructor
    //pre-conditions: none
    //post-conditions: object constructed
    Automaton(){
        this.states = null;
        this.finalStates = null;
    }

    //Get value of alphabet
    //pre-conditions: none
    //post-conditions: value of alphabet returned
    public String[] getAlphabet() {
        return alphabet;
    }

    //Set value of alphabet
    //pre-conditions: none
    //post-conditions: value of alphabet set
    public void setAlphabet(String[] alphabet) {
        this.alphabet = alphabet;
    }

    //Get value of states
    //pre-conditions: none
    //post-conditions: value of stated returned
    public String[] getStates() {
        return states;
    }

    //Set value of states
    //pre-conditions: none
    //post-conditions: value of stated set
    public void setStates(String[] states) {
        this.states = states;
    }

    //Get value of finalStates
    //pre-conditions: none
    //post-conditions: value of finalStates returned
    public String[] getFinalStates() {
        return finalStates;
    }

    //Set value of finalStates
    //pre-conditions: none
    //post-conditions: value of finalStates set
    public void setFinalStates(String[] finalStates) {
        this.finalStates = finalStates;
    }

    //Get value of transitions
    //pre-conditions: none
    //post-conditions: value of transitions returned
    public JSONObject getTransitions() {
        return transitions;
    }

    //Set value of transitions
    //pre-conditions: none
    //post-conditions: value of transitions set
    public void setTransitions(JSONObject transitions) {
        this.transitions = transitions;
    }

    //Process automaton
    //pre-conditions: valid transitions
    //post-conditions: process word and print "ACEITA" if the word is valid or "REJEITA" otherwise
    public void processWord(String word) {
        String wordToProcess = word;
        String[] chain = word.split("");
        String currentState = "q0";
        for (String c:chain){
            System.out.println("["+currentState+"]"+wordToProcess);
            JSONObject transitionsProps = this.transitions.getJSONObject(currentState);
            if(transitionsProps.has(c)){
                currentState = transitionsProps.getString(c);
                wordToProcess = wordToProcess.replaceFirst(c,"");
            }else{
                System.out.println("REJEITA");
                return;
            }
        }
        System.out.println("["+currentState+"]"+wordToProcess);
        if(Arrays.asList(this.finalStates).contains(currentState)){
            System.out.println("ACEITA");
        }else{
            System.out.println("REJEITA");
        }
        return;
    }

}
