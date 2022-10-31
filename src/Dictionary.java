import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Dictionary {

    private final List<String> dico = new ArrayList<>();
    private final Trigram trigram = new Trigram();
    public Dictionary(String filename){
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                dico.add(line);
                trigram.createTrigram(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String testLeven(String word) {
        return Levenshtein.levenMin(word,dico);
    }

    public boolean contain(String word) {
        return dico.contains(word);
    }

    public Map<String, List<String>> getTrigram() {
        return trigram.getTrigrams();
    }
}
