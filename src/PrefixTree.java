import java.util.Hashtable;
import java.util.Set;

public class PrefixTree {
    private final Hashtable<String, PrefixTree> table = new Hashtable<>();
    private final boolean isfinal;
    public PrefixTree(boolean isfinal) {
        this.isfinal = isfinal;
    }

    public void createTree(String word) {
        PrefixTree currentTree = this;
        for(int i=0; i<word.length(); i++) {
            if(!currentTree.table.containsKey(word.substring(i,i+1)))
                currentTree.table.put(word.substring(i,i+1), i == word.length() - 1 ? new PrefixTree(true) : new PrefixTree(false));
            currentTree = currentTree.table.get(word.substring(i,i+1));
        }
    }
    public boolean contain(String word) {
        PrefixTree currentTree = this;
        for (int i = 0; i < word.length(); i++) {
            if(!currentTree.table.containsKey(word.substring(i,i+1))) return false;
            currentTree = currentTree.table.get(word.substring(i,i+1));
        }
        return currentTree.isfinal;
    }
    public String toString() {
        return table.toString();
    }
    public PrefixTree getValue(String key) {
        return table.get(key);
    }
    public Set<String> keys() {
        return table.keySet();
    }
    public boolean isFinal() {
        return isfinal;
    }
}
