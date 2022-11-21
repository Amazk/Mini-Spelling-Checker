import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SpellChecker {

    private final ArrayList<String> words = new ArrayList<>();
    private final Dictionary dictionary;

    public SpellChecker(String pathToFile, String word, Dictionary dictionary) {
        try {
            Scanner scanner = new Scanner(new File(pathToFile));
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            words.add(word);
        }
        this.dictionary = dictionary;
    }
    public void spell(String word) {
        if(dictionary.contain(word)) System.out.println(word+" is in dico");
        else {
            Map<String, Integer> triCommun = new HashMap<>();
            for(String trigram : getTri(word)) {
                if (!dictionary.containTri(trigram)) continue;
                ParcoursTree.parcoursProfondeur(dictionary.getTrigrams().get(trigram), triCommun);
            }
            String key;
            Map<String, Integer> triCent = new HashMap<>();
            while(triCent.size() < 50 && triCommun.size()!=0) {
                key = compare(triCommun);
                triCent.put(key,triCommun.get(key));
                triCommun.remove(key);
            }
            Map<String, Integer> triCinq = new HashMap<>();
            while(triCinq.size() < 5) {
                key = Levenshtein.levenMin(word, triCent);
                triCinq.put(key, triCent.get(key));
                triCent.remove(key);
            }
            System.out.println(word+" : "+triCinq.keySet());
        }
    }
    private Set<String> getTri(String word) {
        if(word.length()==1) return Set.of("<"+word+">");
        Set<String> set = new HashSet<>();
        set.add("<"+word.substring(0,2));
        for(int i=0; i<word.length()-2; i++)
            set.add(word.substring(i,i+3));
        set.add(word.substring(word.length()-2)+">");
        return set;
    }
    private String compare(Map<String, Integer> triCommun) {
        String key="";
        int max=0;
        for(String k : triCommun.keySet()) {
            if(triCommun.get(k) <= max) continue;
            max = triCommun.get(k);
            key=k;
        }
        return key;
    }
    public ArrayList<String> getWords() {
        return words;
    }
}
