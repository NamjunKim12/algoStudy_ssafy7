import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
 
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc=1;tc<T+1;tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] tree = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int max = -1;
            for(int i=0;i<N;i++) {
                tree[i] = Integer.parseInt(st.nextToken());
                if(tree[i]>max)
                    max = tree[i];
            }
            int sum=0;
            int a=0;  //짝수 필요한 수
            int b= 0;  // 홀수 필요한 수
            for(int i=0;i<N;i++) {
                if(max == tree[i])continue;
                sum+=Math.abs(max-tree[i]);
                if(Math.abs(max-tree[i])%2==0) {  //짝수
                    a+=Math.abs(max-tree[i])/2;
                }
                else {
                    b+=1;
                    a+=Math.abs(max-tree[i])/2;
                }
            }
            if(a>=b) {
                System.out.println("#"+tc+" "+((sum/3)*2+sum%3));
            }
            else {
                System.out.println("#"+tc+" "+(a*2+(b-a)*2-1));
            }
             
        }
 
    }
 
}