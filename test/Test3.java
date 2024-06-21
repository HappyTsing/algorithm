class Test3 {
    public static void main(String[] args){
      int boxCount = 5;
      int res = solution(boxCount);
      System.out.println(res);
  
    }
    public static int solution(int boxCount){
      if(boxCount <= 0){
        return 0;
      } else if(boxCount == 1){
        return 1;
      } else if(boxCount == 2){
        return 2;
      } else {
        return solution(boxCount-1) + solution(boxCount -2);
      }
    }
  }