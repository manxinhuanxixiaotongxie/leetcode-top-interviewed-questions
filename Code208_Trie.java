/**
 * 提示：
 * <p>
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 */
public class Code208_Trie {
    private Node root;

    public Code208_Trie() {
        root = new Node();
    }

    public void insert(String word) {
        char[] chars = word.toCharArray();
        Node curNode = root;
        for (char curChar : chars) {
            if (curNode.next[curChar-'a'] == null) {
                curNode.next[curChar-'a'] = new Node();
            }
            curNode.pass++;
            curNode = curNode.next[curChar-'a'];
        }
        curNode.pass++;
        curNode.end++;
    }

    public boolean search(String word) {
        char[] chars = word.toCharArray();
        Node curNode = root;
        for (char curChar : chars) {
            if (curNode.next[curChar-'a'] == null) {
                return false;
            }
            curNode = curNode.next[curChar-'a'];
        }
        return curNode.end > 0;
    }

    public boolean startsWith(String prefix) {
        char[] chars = prefix.toCharArray();
        Node curNode = root;
        for (char curChar : chars) {
            if (curNode.next[curChar-'a'] == null) {
                return false;
            }
            curNode = curNode.next[curChar-'a'];
        }
        return curNode.pass > 0;
    }

    class Node {
        Node[] next;
        int pass;
        int end;

        Node() {
            next = new Node[26];
            pass = 0;
            end = 0;
        }
    }
}
