import java.util.Map;

public class ParcoursTree {

    public static void parcoursProfondeur(PrefixTree tree, Map<String, Integer> map) {
        explore(tree, "" ,map);
    }

    private static void explore(PrefixTree tree, String word, Map<String, Integer> map) {
        for(String key : tree.keys()) {
            if(tree.getValue(key).isFinal()) {
                if(map.containsKey(word+key)) map.put(word+key,map.get(word+key)+1);
                else map.put(word+key,1);
            }
            explore(tree.getValue(key), word+key,map);
        }
    }
}
