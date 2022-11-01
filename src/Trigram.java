import java.util.*;

public class Trigram {
    private final Map<String, PrefixTree> trigrams = new HashMap<>();

    public void createTrigram(String word) {
        if(word.length()==1) return;
        addTrigram("<"+word.substring(0,2),word);
        for(int i=0; i<word.length()-2; i++) {
            addTrigram(word.substring(i,i+3),word);
        }
        addTrigram(word.substring(word.length()-2)+">",word);
    }
    private void addTrigram(String trigram, String word) {
        if(!trigrams.containsKey(trigram))
            trigrams.put(trigram, new PrefixTree(false));
        trigrams.get(trigram).createTree(word);
    }
    public Map<String, PrefixTree> getTrigrams() {
        return trigrams;
    }
}