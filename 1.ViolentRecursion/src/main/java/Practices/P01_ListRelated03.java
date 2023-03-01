package Practices;

/**
 * 将单向链表按某值划分成左边小，中间相等，右边大的形式
 */
public class P01_ListRelated03 {
    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            value = data;
        }
    }
    /*
    * [笔试] - 把链表放入数组里，在数组上做 partition
    * */
    public static Node method01(Node head, int pivot){
        if (head == null) {
            return head;
        }

        Node cur = head;
        int i = 0;
        while (cur != null){
            i++;
            cur = cur.next;
        }
        //以上是从头节点找到节点的总长度并创建数组
        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;

        //将每个节点都放到数组中
        for (i = 0; i != nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }

        arrPartition(nodeArr,pivot);
        for (i = 1; i != nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }
    public static void arrPartition(Node[] nodeArr,int pivot){
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index < big){
            if(nodeArr[index].value == pivot){
                index++;
            }else if(nodeArr[index].value  < pivot){
                swap(nodeArr,index++,++small);
            }else {
                swap(nodeArr,index,--big);
            }
        }
        swap(nodeArr, index, pivot);
    }
    static void swap(Node[] arr, int i, int j) {
        Node node = arr[i];
        arr[i] = arr[j];
        arr[j] = node;
    }

    /*
    * 上述方法需要链表长度的数组空间，并且不能保证稳定性。
    * [面试] - 分成小,中,大三个部分，再把各个部分串起来。[只需要6个空间，并且能保证稳定性]
    *
    * 思路：准备6个节点，分别表示小，中，大三个部分的头节点和尾节点，刚开始将节点都设为空值。
    * 开始遍历链表，每进来一个节点，判断该节点与要比较的节点值大小关系，并根据结果放入
    * 相应的小中大哪个部分。
    * 示例如下：
    * 对于 4 -> 2 -> 3 -> 5 -> 6 -> 1 -> 3 -> 0 的单向链表，假设按 3 划分
    * 准备 6 个节点分别为：
    * sH : 小于区域头节点     sT : 小于区域尾节点
    * eH : 等于区域头节点     eT : 等于区域尾节点
    * bH : 大于区域头节点     bT : 大于区域尾节点
    * 默认值都设为 null
    * 开始时 4 比 3大，由于大于区域都为空，将大于区域头和尾都指向 4 节点
    * sH :     sT :
    * eH :     eT :
    * bH : 4   bT : 4
    * 依次类推
    * sH : 2   sT : 2
    * eH : 3   eT : 3
    * bH : 4   bT : 4
    * 到 5 时，仍然发现是大于节点,由于 bH和bT已经不为空，将 bT 指向 5
    * 4 -> 5
    * |    |
    * bH   bT
    * 以此类推。
    * 4 -> 5 -> 6
    * |         |
    * bH        bT
    * 到所有节点全部安排结束：
    *  sH              eH     bH
    *  |               |      |
    *  2 -> 1 -> 0     3      4 -> 5 -> 6
    *            |     |                |
    *            sT    eT               bT
    * 最后将 sT 指向 eH, 将 eT 指向 bH :
    *   2 -> 1 -> 0 -> 3 -> 4 -> 5 -> 6
    * */
    public static Node method2(Node head, int pivot){
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node bH = null;
        Node bT = null;
        Node next = null; // save next Node
        while (head != null){
            next = head.next;
            head.next = null;
            if(head.value < pivot){
                if(sH == null){
                    sH = head;
                    sT = head;
                }else {
                    sT.next = head;
                    sT = head;
                }
            }else if(head.value == pivot){
                if(eH == null){
                    eH = head;
                    eT = head;
                }else {
                    eT.next = head;
                    eT = head;
                }
            }else {
                if(bH == null){
                    bH = head;
                    bT = head;
                }else {
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }
        if(sT != null){ //如果有小于区域
            sT.next = eH;
            //等于区域有可能不存在，如果不存在，就让小于区域的尾巴直接连接大于区域的头
            eT = eT == null ? sT : eT;
        }
        if (eT != null) {
            eT.next = bH;
        }
        return sH != null ? sH : (eH != null ? eH : bH);
    }




}
