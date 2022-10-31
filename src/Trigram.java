import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Trigram {

    private final Map<String, List<String>> trigrams = new HashMap<>();

    public Trigram() {}

    public Trigram(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                createTrigram(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTrigram(String word) {
        addTrigram("<"+word.substring(0,2),word);
        for(int i=0; i<word.length()-2; i++) {
            addTrigram(word.substring(i,i+3),word);
        }
        addTrigram(word.substring(word.length()-2)+">",word);
    }

    private void addTrigram(String trigram, String word) {
        if(!trigrams.containsKey(trigram))
            trigrams.put(trigram, new ArrayList<>());
        trigrams.get(trigram).add(word);
    }

    public Map<String, List<String>> getTrigrams() {
        return trigrams;
    }

    public Set<String> keys() {
        return trigrams.keySet();
    }
}
