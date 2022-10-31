import java.util.List;
import java.util.Set;

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
                    values[i][j] = 1 + min(values[i-1][j],values[i][j-1],values[i-1][j-1]);
                }
            }
        }
        return values[str1.length()][str2.length()];
    }

    private static int min(int a, int b, int c) {
        return Math.min(Math.min(a,b),c);
    }


    public static String levenMin(String word, List<String> list) {
        int min = Levenshtein.distance(word, list.get(0));
        String myword = list.get(0);
        for(String wordd : list) {
            if(Levenshtein.distance(wordd,word) < min) {
                min = Levenshtein.distance(wordd,word);
                myword = wordd;
            }
        }
        return myword;
    }

    public static String levenMax(String word, Set<String> set) {
        int max = 0;
        String myword = "";
        for(String wordd : set) {
            if(Levenshtein.distance(wordd,word) > max) {
                max = Levenshtein.distance(wordd,word);
                myword = wordd;
            }
        }
        return myword;
    }
}
