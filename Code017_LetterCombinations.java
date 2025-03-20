import java.util.ArrayList;
import java.util.List;

/**
 * 数字
 */
public class Code017_LetterCombinations {

    char[][] chars = new char[][]{{}, {}, {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'},
    };

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return ans;
        }
        process(digits.toCharArray(),0,new StringBuilder(),ans);
        return ans;
    }

    private void process(char[] digitChar,int index,StringBuilder builder,List<String> ans) {
        if (index == digitChar.length) {
            ans.add(builder.toString());
        }else {
            int digit = digitChar[index] - '0';
            for (char c : chars[digit]) {
                builder.append(c);
                process(digitChar,index+1,builder,ans);
                builder.deleteCharAt(builder.length()-1);
            }
        }
    }
}
