
import java.util.*;
public class Main1 {
  public static void main(String[] args) {
  // please define the JAVA input here. For example: Scanner s = new Scanner(System.in);
  // please finish the function body here.
  // please define the JAVA output here. For example: System.out.println(s.nextInt());
      Scanner sc = new Scanner(System.in);
      ArrayList<Integer> list = new ArrayList();
      while(sc.hasNextInt()){
        list.add(sc.nextInt());
      }
      int target = list.get(list.size()-1);
      list.remove(list.size()-1);
      Collections.sort(list);
      int left = 0;
      int right = list.size()-1;
      int mid = (right-left)/2+left;
      System.out.print("S");
      while(left<right){
        if(list.get(mid)>target){
          System.out.print("L");
          right = mid-1;
        }else if(list.get(mid)<target){
          System.out.print("R");
          left = mid+1;
        }else{
          System.out.print("Y");
          break;
        }
        mid = (right-left)/2+left;
      }
      if(left == right){
        if(list.get(left)==target){
          System.out.print("Y");
        }else{
          System.out.print("N");
        }
      }
      if(left>right){
        System.out.print("N");
      }
  }
}
