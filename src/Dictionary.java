import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Dictionary {
    private final PrefixTree dico = new PrefixTree(false);
    private final Trigram trigram = new Trigram();;
    public Dictionary(String fileName){
        String line;
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                dico.createTree(line);
                trigram.createTrigram(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean contain(String word) {
        return dico.contain(word);
    }
    public Map<String, PrefixTree> getTrigrams() {
        return trigram.getTrigrams();
    }
    public boolean containTri(String trigram) {
        return getTrigrams().containsKey(trigram);
    }
}
