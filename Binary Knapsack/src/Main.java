import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;


public class Main {

    static int max = 0;
    static long min = -1;
    static int N, W;
    static ArrayList<BK> list = new ArrayList<BK>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());


        StringTokenizer st, st2;
        st = new StringTokenizer(br.readLine(), " ");
        st2 = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; st.hasMoreTokens(); i++) {
            BK bk = new BK(Integer.parseInt(st.nextToken()), Integer.parseInt(st2.nextToken()));
            list.add(bk);
        }

        list.sort(new Comparator<BK>() {
            @Override
            public int compare(BK o1, BK o2) {
                return o2.cp - o1.cp > 0 ? 1 : -1;
            }
        });

        int wight = 0, price = 0;
        for (int j = 0; j < N; j++) {
            if (wight + list.get(j).wight <= W) {
                price += list.get(j).price;
                wight += list.get(j).wight;
            }else{
                break;
            }
        }
        max = price;

        p(0, 0, 0);
        System.out.println(max);


    }

    static void p(int i, int wight, int price) {

        if (i == N) {
            if (max < price) {
                max = price;
            }
            return;
        } else {
            if (wight + list.get(i).wight > W) {
                if (price > max)
                    max = price;
                if (promise(i + 1, wight, price)) {
                    p(i + 1, wight, price);
                }

            } else {
                if (price > max)
                    max = price;

                //p(i + 1, wight + list.get(i).wight, price + list.get(i).price);


                if (price > max)
                    max = price;

                if (promise(i + 1, wight, price)) {
                    p(i + 1, wight, price);
                }


            }

        }
    }

    static boolean promise(int i, int wight, int price) {
        if (wight > W) {
            return false;
        } else {
            for (int j = i; j < N; j++) {
                if (wight + list.get(j).wight <= W) {
                    price += list.get(j).price;
                    wight += list.get(j).wight;
                } else {
                    price += (W - wight) * (list.get(j).price / list.get(j).wight);
                    break;
                }
            }
        }

        if (price >= max) {
            return true;
        }

        return false;
    }


}

/*class BK {

    public BK(int price, int wight) {
        this.price = price;
        this.wight = wight;
        cp = (double) price / wight;
    }

    int price = 0;
    int wight = 0;
    double cp;
}*/