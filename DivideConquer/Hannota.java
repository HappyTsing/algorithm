package DivideConquer;

import java.util.ArrayList;
import java.util.List;

public class Hannota {
    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>(){{
            add(3);
            add(2);
            add(1);
            add(0);
        }};
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();
        hanota(A,B,C);
        System.out.println(C.toString());
        
    }
    public static void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        int len = A.size();
        move(len,A,B,C);
    }

    public static void move(int len,List<Integer> A, List<Integer> B, List<Integer> C){
        System.out.println("A: "+A.toString()+"\tB: "+B.toString()+"\tC: "+C.toString());
        // 若只有一个元素，则直接移动     
        if(len == 1){
            C.add(A.remove(A.size()-1));
            return;
        }else{
            move(len-1,A,C,B);               // 将 A 上 n-1 个通过 C 移动到 B
            C.add(A.remove(A.size()-1));     // 将 A 上最后一个移动到 C，注意，此时 A 为空
            move(len-1,B,A,C);               // 将 B 上 n-1 个通过空的 A 移动到 C
        }
    }
}
