import java.util.Scanner;

public class PrimeFactors {

    //试除法
    public String getResult1(long ulDataInput) {
        StringBuilder stringBuilder = new StringBuilder();
        long last = ulDataInput;
        for(long i = 2;i <= (long)Math.sqrt(ulDataInput);)
        {

            if(last%i == 0)//可整除
            {
                stringBuilder.append(i+" ");
                last = last/i;
            }
            else
            {
                i++;
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long ul = sc.nextLong();
        PrimeFactors primeFactors = new PrimeFactors();
        System.out.println(primeFactors.getResult1(ul));
    }
}
