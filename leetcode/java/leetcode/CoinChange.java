import java.util.Arrays;

/**
 * @author happyzhao
 * @data 2020/3/17 12:21
 * @type PACKAGE_NAME
 * @question
 */
public class CoinChange {
    public int coinChange1(int[] coins, int amount) {
        if(amount == 0) return 0;
        int ans = Integer.MAX_VALUE;
        for(int i = coins.length-1;i >= 0;i--){
            if(coins[i] <= amount){
                int tem = coinChange(coins,amount-coins[i]);
                if(tem != -1){
                    ans = Math.min(ans,tem+1);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    int coinChange1(int pos,int[] coins, int amount){
        if(amount == 0) return 0;
        if(pos < 0) return -1;
        int ans = Integer.MAX_VALUE;
        for(int i = coins.length-1;i >= 0;i--){
            if(coins[i] <= amount){
                int tem = coinChange(i,coins,amount-coins[i]);
                if(tem != -1){
                    ans = Math.min(ans,tem+1);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int coinChange(int[] coins, int amount) {
        return coinChange(0, coins, amount);
    }

    private int coinChange(int idxCoin, int[] coins, int amount) {
        if (amount == 0)
            return 0;
        if (idxCoin < coins.length && amount > 0) {
            int maxVal = amount / coins[idxCoin];
            int minCost = Integer.MAX_VALUE;
            for (int x = 0; x <= maxVal; x++) {
                if (amount >= x * coins[idxCoin]) {
                    int res = coinChange(idxCoin + 1, coins, amount - x * coins[idxCoin]);
                    if (res != -1)
                        minCost = Math.min(minCost, res + x);
                }
            }
            return (minCost == Integer.MAX_VALUE)? -1: minCost;
        }
        return -1;
    }

    public static void main(String[] args) {
        CoinChange co = new CoinChange();
        int[] coins = {186,419,83,408};
        Arrays.sort(coins);
        System.out.println(co.coinChange1(coins,6249));
    }
}
