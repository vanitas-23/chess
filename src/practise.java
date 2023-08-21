import java.util.*;

public class practise {
//    static Vector<Long> generator(int n){
//        Vector<Long> v=new Vector<Long>(n);
//        v.add(1L);
//        for(int i=1;i<n;i++){
//            v.add(v.elementAt(i-1)+i+1);
//
//        }
//        for (int i = (int) (n-1); i>0; i--)
//            v.add(v.lastElement()+i);
//
//        return v;
//    }
//    static int find_x(Vector<Long>v,int n,long q){
//        if(q==1)
//            return 1;
//        if(q==n*n)
//            return 2*n-1;
//        for (int i=0;i<2*n-1;i++){
//            if(q<=v.elementAt(i))
//                return i+1;
//        }
//        return -1;
//    }

    static long contributions(int n, int a[]) {
        // Write your code here.
        long sum = 0l;
        ArrayList<Integer> arr0 = new ArrayList<Integer>();
        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        ArrayList<Integer> arr2 = new ArrayList<Integer>();
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 3 == 0) {
                arr0.add(a[i]);
            } else if (a[i] % 3 == 1) {
                arr1.add(a[i]);
            } else arr2.add(a[i]);
        }
        for (int i = 0; i < arr0.size(); i++) {
            for (int j = i + 1; j < arr0.size(); j++) {
                sum += arr0.get(i) ^ arr0.get(j);
            }
        }

        for (int i = 0; i < arr1.size(); i++) {
            for (int j = i + 1; j < arr1.size(); j++) {
                sum += arr1.get(i) ^ arr1.get(j);
            }
        }

        for (int i = 0; i < arr2.size(); i++) {
            for (int j = i + 1; j < arr2.size(); j++) {
                sum += arr2.get(i) ^ arr2.get(j);
            }
        }
        return sum;
    }

    static int minJumps(int[] arr) {
        // your code here
        if (arr[0] == 0)
            return -1;
        int pos = 8;
        int max = 0, lil = -1, res = 0, ind = -1;
        while (pos < arr.length) {
            max = arr[pos];
            lil = -1;
            for (int i = 1; i <= max; i++) {
                if (pos + i < arr.length)
                    if (lil < arr[pos + i]) {
                        ind = pos + i;
                        lil = arr[ind];
                    }
            }
            if (lil == 0)
                return -1;
            System.out.println(ind + " " + pos);
            pos = ind;
            res++;
        }
        // System.out.println(pos+" "+lil);
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int arr[] = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = sc.nextInt();
            if (n == 1)
                System.out.println(1);
            else {

                int ans = 1, bx = 0;
                Arrays.sort(arr);
                bx=arr[n-1];
                for (int i = n - 2; i >= 0; i--) {
                   // System.out.println(arr[i]^bx);
                           if(bx>=arr[i]) {
                               bx = bx ^ arr[i];
                             //  System.out.println(bx);
                           }
                           else{
                               bx=arr[i];
                               ans++;
                           }
                   // System.out.println(bx);
                }
                System.out.println(ans);
            }
        }
    }
}