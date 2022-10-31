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
        //System.out.println(dictionary.testLeven(word));
        if(dictionary.contain(word)) System.out.println(word+" is in dico");
        else {
            List<String> trigramList = new ArrayList<>();
            Trigram wordTrigram = new Trigram();
            wordTrigram.createTrigram(word);
            for(String key : wordTrigram.keys())
                if(dictionary.getTrigram().containsKey(key))
                    trigramList.addAll(dictionary.getTrigram().get(key));
            while(trigramList.stream().distinct().toList().size() > 100)
                supMinOcc(trigramList);
            Set<String> set = new HashSet<>(trigramList);
            while(set.size() > 5)
                set.remove(Levenshtein.levenMax(word, set));
            System.out.println(set);
        }
    }

    private void supMinOcc(List<String> list) {
        int min = 0;
        String minWord ="";
        for(String word : list) {
            if(Collections.frequency(list, word) < min) {
                min = Collections.frequency(list, word);
                minWord = word;
            }
        }
        while(list.contains(minWord)) list.remove(minWord);
    }

    public ArrayList<String> getWords() {
        return words;
    }
}
