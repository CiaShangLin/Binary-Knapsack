import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DP {

    static int N, W;
    static ArrayList<Dp> list = new ArrayList<Dp>();
    static int[][] record;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());


        StringTokenizer st, st2;
        st = new StringTokenizer(br.readLine(), " ");
        st2 = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; st.hasMoreTokens(); i++) {
            Dp dp = new Dp(Integer.parseInt(st.nextToken()), Integer.parseInt(st2.nextToken()));
            list.add(dp);
        }

        record=new int[N+1][W+1];
        for(int i=0;i<N+1;i++){
            for(int j=0;j<W+1;j++){
                record[i][j]=-1;
            }
        }


        System.out.println(solve(0,W));

    }

    static int solve(int i,int residue){
        if(record[i][residue]>0){
            return record[i][residue];
        }
        int result;
        if(i>=list.size()){
            return 0;
        }
        if(list.get(i).wight>residue){
            result=solve(i+1,residue);
        }else{
            result=Math.max(solve(i+1,residue),solve(i+1,residue-list.get(i).wight)+list.get(i).price);
        }
        record[i][residue]=result;
        return result;

    }
}

class Dp{
    int price;
    int wight;

    public Dp(int price,int wight){
        this.price=price;
        this.wight=wight;
    }
}