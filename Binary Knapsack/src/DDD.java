import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class DDD {

    static int N = 0, W = 0;
    static ArrayList<BK> list = new ArrayList<BK>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());

        StringTokenizer st, st2;
        st = new StringTokenizer(br.readLine().trim(), " ");
        st2 = new StringTokenizer(br.readLine().trim(), " ");
        while (st.hasMoreTokens() || st2.hasMoreTokens()) {
            BK bk = new BK(Double.parseDouble(st.nextToken()), Double.parseDouble(st2.nextToken()));
            list.add(bk);
        }


        list.sort(new Comparator<BK>() {
            @Override
            public int compare(BK o1, BK o2) {
                return o2.cp - o1.cp > 0 ? 1 : -1;
            }
        });


        p(0);
        System.out.println((int) max);
    }

    static double max = 0;
    static ArrayList<BK> bks = new ArrayList<>();

    static void p(int ii) {
        double wight =0;
        double price =0;
        int i=0;
        if(bks.size()!=0){
            wight = bks.get(ii).wight;
            price = bks.get(ii).price;
            i=bks.get(ii).index;
            bks.remove(ii);
        }

        double left = bound(i + 1, wight + list.get(i).wight, price + list.get(i).price);
        double right = bound(i + 1, wight, price);


        if (left != -1) {
            BK bk = new BK(i + 1, price + list.get(i).price, wight + list.get(i).wight, left);
            bks.add(bk);
        }
        if (right != -1) {
            BK bk = new BK(i + 1, price, wight, right);
            bks.add(bk);
        }

        /*bks.sort(new Comparator<BK>() {
            @Override
            public int compare(BK o1, BK o2) {
                return o2.bound - o1.bound > 0 ? 1 : -1;
            }
        });

        if (bks.get(0).bound > max) {

            //System.out.println(bks.get(0).index+" "+bks.get(0).price+" "+bks.get(0).wight+" "+bks.get(0).bound);
            p(bks.get(0).index);
        } else {
            bks.clear();
            return;
        }*/
        double m = 0;
        int index = 0;
        for (int k = 0; k < bks.size(); k++) {
            if (bks.get(k).bound > m) {
                index = k;
                m = bks.get(k).bound;
            }
        }
        //System.out.println(index+" "+m+" "+bks.get(index).index);
        if (m > max) {
            p(index);
        } else {
            bks.clear();
            return;
        }


    }

    static double bound(int index, double wight, double price) {

        if (wight > W || index > N) {
            return -1;
        }
        if (price > max) {
            max = price;
        }
        for (int i = index; i < N; i++) {
            if (wight + list.get(i).wight <= W) {
                wight += list.get(i).wight;
                price += list.get(i).price;
            } else {
                price += (W - wight) * (list.get(i).price / list.get(i).wight);
                break;
            }
        }

        return price;

    }
}

class BK {

    public BK(double price, double wight) {
        this.price = price;
        this.wight = wight;
        cp = (double) price / wight;
    }

    public BK(int index, double price, double wight, double bound) {
        this.price = price;
        this.wight = wight;
        this.index = index;
        this.bound = bound;
    }

    double price = 0;
    double wight = 0;
    double cp;
    double bound = 0;
    int index = 0;
}
