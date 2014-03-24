package me.boyce.algr.string;

/**
 * 字符串相似度算法（编辑距离）
 * <p>
 * 动态规划算法：
 * 			C[i-1,j-1] 
 * C[i,j] = 
 * 			MIN(C[i,j-1],C[i-1,j],C[i-1,j-1])
 * @author boyce
 * @date 2014-3-10 下午5:29:13
 *
 */
public class Levenshtein {

	public static int calcStringDistance(String str1, String str2) {
		int len1 = str1.length();
		int len2 = str2.length();
		int[][] c = new int[len1 + 1][len2 + 1];
		for (int i = 0; i <= len1; i++) {
			c[i][0] = i;
		}
		for (int i = 0; i <= len2; i++) {
			c[0][i] = i;
		}
		for (int i = 0; i < len1; i++) {
			for (int j = 0; j < len2; j++) {
				if (str2.charAt(j) == str1.charAt(i))
					c[i + 1][j + 1] = c[i][j];
				else {
					int t = c[i + 1][j];
					if (t > c[i][j + 1])
						t = c[i][j + 1];
					if (t > c[i][j])
						t = c[i][j];
					c[i + 1][j + 1] = t + 1;
				}
			}
		}
		return c[len1][len2];
	}

	public static void main(String[] args) {
		System.out.println(calcStringDistance("abcdefg", "abcdef"));
	}
}
