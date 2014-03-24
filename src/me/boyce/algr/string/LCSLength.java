package me.boyce.algr.string;

/**
 * 计算“最长公共子序列”
 * <p>
 * 			0							if i,j == 0
 * c[i,j] = c[i-1,j-1] + 1				if i,j > 0 and x[i] == y[j]
 * 			max(c[i-1,j], c[i,j-1]) 	if i,j > 0 and x[i] != y[j]
 * @author boyce
 * @date 2014-3-10 下午5:29:29
 * 
 */
public class LCSLength {

	private int[][] c = null;
	private char[][] b = null;

	public int lcsLength(String str1, String str2) {
		int len1 = str1.length();
		int len2 = str2.length();
		c = new int[len1 + 1][len2 + 1];
		b = new char[len1 + 1][len2 + 1];
		for (int i = 0; i <= len1; i++)
			c[i][0] = 0;
		for (int i = 0; i <= len2; i++)
			c[0][i] = 0;
		for (int i = 0; i < len1; i++) {
			for (int j = 0; j < len2; j++) {
				if (str1.charAt(i) == str2.charAt(j)) {
					c[i + 1][j + 1] = c[i][j] + 1;
					b[i + 1][j + 1] = '↖';
				} else {
					if (c[i][j + 1] < c[i + 1][j]) {
						c[i + 1][j + 1] = c[i + 1][j];
						b[i + 1][j + 1] = '←';
					} else {
						c[i + 1][j + 1] = c[i][j + 1];
						b[i + 1][j + 1] = '↑';
					}
				}
			}
		}
		return c[len1][len2];
	}

	void printLCS(String str1) {
		StringBuilder sb = new StringBuilder();
		int i = b.length - 1;
		int j = b[0].length - 1;
		while (i > 0 && j > 0) {
			if (b[i][j] == '↖') {
				sb.append(str1.charAt(i - 1));
				i--;
				j--;
			} else if (b[i][j] == '↑')
				i--;
			else
				j--;
		}
		System.out.println(sb.reverse().toString());
	}

	public static void main(String[] args) {
		String str1 = "ABCBDAB";
		String str2 = "BDCABA";
		LCSLength lcs = new LCSLength();
		lcs.lcsLength(str1, str2);
		lcs.printLCS(str1);
	}
}
