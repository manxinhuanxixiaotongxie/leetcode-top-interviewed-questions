public class Code079_Exist {
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        int N = words.length;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean res = process(board, 0, i, j, N, words);
                if (res) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean process(char[][] board, int index, int i, int j, int n, char[] words) {
        if (index == n) {
            return true;
        }
        if (i >= board.length || j >= board[0].length || i < 0 || j < 0) {
            return false;
        }

        if (board[i][j] != words[index]) {
            return false;
        }
        char temp = board[i][j];
        board[i][j] = '0';
        boolean res = process(board, index + 1, i + 1, j, n, words)
                || process(board, index + 1, i - 1, j, n, words) ||
                process(board, index + 1, i, j + 1, n, words)
                || process(board, index + 1, i, j - 1, n, words);
        board[i][j] = temp;
        return res;
    }
}
