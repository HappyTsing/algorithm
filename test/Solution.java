import java.util.*;
class MedianFinder {
    Queue<Integer> A,B;

    public MedianFinder() {
        A = new PriorityQueue<>(); // 小顶堆，存较大的部分
        B = new PriorityQueue<>((o1,o2) -> o2 - o1 ); // 大顶堆，存较小的部分
    }
    
    public void addNum(int num) {
        // 如果A和B中的元素数量不同，则写入 B 中，因为 A 中的数据数量要么等于B，要么比 B 多一个！
        if(A.size()!=B.size()){
            A.offer(num);      // 首先需要将当前元素加入 A 后，自动排序，此时A的栈顶是最小元素
            B.offer(A.poll()); // 将该最小元素取出，写入 B 中。因为需要 A 的数据始终比 B 中的大！
        }else{  // 数据相同，则写入A
            B.offer(num);
            A.offer(B.poll());
        }
    }
    
    public double findMedian() {
        if(A.size()!=B.size()){
            return A.peek();
        }else{
            return (A.peek()+B.peek())/2.0;
        }

    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */