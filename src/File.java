import org.json.JSONObject;

import java.io.*;

public class File {
    private String line;
    private String[] alphabet;
    private String[] states;
    private String[] finalStates;
    private FileInputStream inputStream;
    private InputStreamReader reader;
    private BufferedReader br;

    //Constructor
    //pre-conditions: none
    //post-conditions: object constructed
    File(String filePath){
        try {
            inputStream = new FileInputStream(filePath);
            reader = new InputStreamReader(inputStream);
            br = new BufferedReader(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Reads the alphabet line in the file, splits the string and
    //returns a string vector with alphabet
    //pre-conditions: bufferReader opened at alphabet line
    //post-conditions: alphabet string vector returned
    public String[] readAlphabet(){
        try {
            String line = br.readLine();
            line = line.substring(line.indexOf('{')+1,line.indexOf('}'));
            this.alphabet = line.split(",");
        }catch(IOException e){
            e.printStackTrace();
        }
        return alphabet;
    }

    //Reads the states line in the file, splits the string and
    //returns a string vector with states
    //pre-conditions: bufferReader opened at states line
    //post-conditions: states string vector returned
    public String[] readStates(){
        try {
            line = br.readLine();
            line = line.substring(line.indexOf('{')+1,line.indexOf('}'));
            states = line.split(",");
        }catch(IOException e){
            e.printStackTrace();
        }
        return states;
    }

    //Reads the finalStates line in the file, splits the string and
    //returns a string vector with finalStates
    //pre-conditions: bufferReader opened at finalStates line
    //post-conditions: finalStates string vector returned
    public String[] readFinalStates(){
        try {
            line = br.readLine();
            line = line.substring(line.indexOf('{')+1,line.indexOf('}'));
            finalStates = line.split(",");
        }catch(IOException e){
            e.printStackTrace();
        }
        return finalStates;
    }

    //Reads the transitions lines in the file,
    //treats entries and saves transitions as a JSONObject
    //pre-conditions: bufferReader opened at first transition line
    //post-conditions: transitions JSONObject returned
    public JSONObject readTransitions(){
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

    //Close file
    //pre-conditions: file opened
    //post-conditions: file closed
    public void closeFile(){
        try {
            br.close();
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

