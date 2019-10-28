package lotteryodds;

import java.util.*;

public class LotteryOdds {
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		//你要选几个数字
		System.out.print("How many numbers do you need to draw? ");
		int k = in.nextInt();
		
		//总共有几个数字
		System.out.print("What is teh highest number you can draw? ");
		int n = in.nextInt();
		
		/**
		 * compute binomial coefficient n*(n-1)*(n-2)*...*(n-k+1)/(1*2*3*...*k)
		 * 意思就是：计算二项式系数kCn
		 */
		
		int lotteryOdds = 1;
		for(int i = 1; i <= k; i++)
			lotteryOdds = lotteryOdds * (n - i + 1) / i;
		
		System.out.println("Your odds are 1 in " + lotteryOdds + ". Good luck!");
	}
}
