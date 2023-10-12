package Greedy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
public class IntervalSchedule {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 3}, {5, 6},{2, 4}};
        solve(intervals);
    }

    public static void solve(int[][] intervals){
        if(intervals.length == 0) return;
        // sort by end time
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] interval1, int[] interval2){
                return interval1[1] - interval2[1];
            }
        });

        // 判断是否冲突，不冲突则写入
        List<int[]> result = new ArrayList<int[]>();
        for(int i = 0; i < intervals.length; i++){
            int[] interval = intervals[i];
            if(result.isEmpty()){
                result.add(interval);
                continue;
            }
            int[] last = (int[])result.get(result.size() - 1);
            if(last[1]  < interval[0]){
                result.add(interval);
            }
        }
        System.out.println(intervals);
    }
    
}
