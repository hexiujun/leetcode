package j.hihoCoder.problemset;

/**
 * Created by j on 2018/1/30.
 */
import java.util.*;

public class TrieUtil {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //while (in.hasNext()) {
        int n = in.nextInt();
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = in.next();
        }
        int m = in.nextInt();
        String[] searchWords = new String[m];
        for (int i = 0; i < m; i++) {
            searchWords[i] = in.next();
        }

        Trie root = constructTrie(words);
        for (int i = 0; i < m; i++) {
            System.out.println(getAns(searchWords[i], root));
        }
        //}
        in.close();
    }

    public static Trie constructTrie(String[] words) {
        Trie root = new Trie(), p;
        for (String word : words) {
            p = root;
            char[] array = word.toCharArray();
            for (int i = 0; i < array.length; i++) {
                if (!p.map.containsKey(array[i]))
                    p.map.put(array[i], new Trie());
                p = p.map.get(array[i]);
                //if (i == arraylength - 1)
                p.addWord();
            }
        }

        return root;
    }

    public static int getAns(String prefix, Trie root) {
        char[] array = prefix.toCharArray();
        Trie p = root;
        for (int i = 0; i < array.length; i++) {
            if (!p.map.containsKey(array[i]))
                return 0;
            p = p.map.get(array[i]);
        }

        return p.wordCount;
    }
}

class Trie {
    int wordCount;
    Map<Character, Trie> map;

    public Trie() {
        map = new HashMap<>();
    }

    public void addWord() {
        wordCount++;
    }
}
