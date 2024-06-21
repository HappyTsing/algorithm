import java.util.*;
/**
 * 求字符串的字节 
 * 输出 所有字符串和字符串的数量
 */
public class Main {
  static List<String> result = new ArrayList<>();
  public static void main(String[] args) {
    String str = "12345";
    backtrack(str, 0, new StringBuilder());
    for(String subset : result){
      System.out.println(subset);
    }
    System.out.println(result.size());
  }

  private static void backtrack(String str,int start,StringBuilder current){
    result.add(current.toString());
    for(int i = start;i<str.length();i++){
      current.append(str.charAt(i));
      backtrack(str, i+1, current);
      // 删除
      current.deleteCharAt(current.length()-1);
    }
  }
      
}
