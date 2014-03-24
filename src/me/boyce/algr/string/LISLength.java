package me.boyce.algr.string;

/**
 * 最长递增子序列问题
 * <p>
 * 
 * L[i] = max(L[j]) + 1			if  j < i and L[j] < L[i]
 * 
 * @author boyce
 * @date 2014-3-11 上午9:42:56
 *
 */
public class LISLength {

	public int lisLength(int[] arr){
		int[] l = new int[arr.length];
		for(int i = 0; i < l.length; i++) l[i] = 1;
		for(int i = 1; i < arr.length; i++){
			int maxLen = 0;
			for(int j = 0; j < i; j++){
				if(arr[j] < arr[i] && maxLen < l[j])
					maxLen = l[j];
			}
			l[i] = maxLen + 1;
		}
		int maxLis = 0;
		for(int i = 0; i < arr.length; i++){
			if( maxLis < l[i])
				maxLis = l[i];
		}
		return maxLis;
	}
	
	public static void main(String[] args){
		LISLength lis = new LISLength();
		System.out.println(lis.lisLength(new int[]{5,2,3,4,1,2,5,3,7}));
	}
}
