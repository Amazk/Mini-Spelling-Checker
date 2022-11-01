import java.util.Map;

public class Levenshtein {

    public static int distance(String str1, String str2) {
        int[][] values = new int[str1.length()+1][str2.length()+1];
        values[0][0] = 0;
        values[0][1] = 1; values[1][0] = 1;
        for(int j=1; j<=str2.length(); j++) {
            values[0][j]=j;
            for(int i=1; i<=str1.length(); i++) {
                values[i][0]=i;
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    values[i][j] = values[i-1][j-1];
                } else {
                    values[i][j] = 1 + Math.min(Math.min(values[i-1][j],values[i][j-1]),values[i-1][j-1]);
                }
            }
        }
        return values[str1.length()][str2.length()];
    }
    public static String levenMin(String word, Map<String, Integer> map) {
        int min = 100;
        String myword = "";
        for(String key : map.keySet()) {
            if(key.length()<=1) continue;
            if(Levenshtein.distance(key,word) < min) {
                min = Levenshtein.distance(key,word);
                myword = key;
            }
        }
        return myword;
    }
}
