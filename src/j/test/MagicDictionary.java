package j.test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by j on 2017/9/10.
 */
class MagicDictionary {
    Set<String> set;

    /** Initialize your data structure here. */
    public MagicDictionary() {
        set = new HashSet<>();
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String d : dict)
            set.add(d);
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        if (word.length() == 0) return false;

        char[] array = word.toCharArray();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < 26; j++) {
                char c = array[i];
                array[i] = (char)(j + 'a');
                if (array[i] != c && set.contains(String.valueOf(array)))
                    return true;
                array[i] = c;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        MagicDictionary md = new MagicDictionary();
        md.buildDict(new String[]{"hello","leetcode"});
        System.out.println(md.search("hello"));
        System.out.println(md.search("hhllo"));
        System.out.println(md.search("hell"));
        System.out.println(md.search("leetcoded"));
    }
}
