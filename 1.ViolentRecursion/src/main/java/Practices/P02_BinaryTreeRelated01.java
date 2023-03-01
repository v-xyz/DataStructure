package Practices;


public class P02_BinaryTreeRelated01 {
    //对于以下二叉树的结构定义
    static class Node<T>{
        T value;
        Node<T> left;
        Node<T> right;
        Node<T> parent;  //一般默认没有parent，这里parent节点是特殊情况
        public Node(T value){
            this.value = value;
        }
    }
    /**
     * 题目：
     * 对于该二叉树中的某个节点，求该节点的后继节点。
     * [后继节点：对应节点中序遍历结果的后一个节点]
     */

    /*
    * 暴力方法：利用 parent一直找到根节点，然后利用从根节点的中序遍历算出所有节点
    * 按照中序遍历的结果，最后找到指定节点的后续节点。
    *
    * 更好的方法：
    * 中序遍历的基本规则是 左中右 对于 右 又可以分解为 左中右，
    * 即 左中[左中右]，依次类推
    * 不难得出结论：
    *   一个节点[中]如果有右孩子[右]，那么其后继节点一定是右孩子节点下的最左节点
    * 如果没有右孩子呢？
    * 通过模拟一个树多次测试可以得出结论：
    *   一个节点如果没有右孩子，那么其后继节点一定在他的parent节点的链上：
    *       -如果该节点是 parent节点的右孩子，继续寻找其parent的parent,如果依然是
    *       右孩子则继续往上找，直到某个父节点是其parent节点的左孩子，则该parent节点
    *       就是后继节点
    *       -如果该节点是 parent节点的左孩子，则parent节点就直接是后继节点
    *
    * 以上结论还可以有以下事实得出: 假设一个节点 A，其左孩子的最右节点为 B,则 A 是 B的
    * 后继节点
    * */

    public static Node getSuccessorNode(Node node){
        if (node == null) {
            return node;
        }
        if (node.right != null){
            return getLeftMost(node.right);
        }else {
            Node parent = node.parent;
            while (parent != null && parent.left != node){
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    private static Node getLeftMost(Node node) {
        if (node == null) {
            return node;
        }
        while (node.left != null){
            node = node.left;
        }
        return node;
    }

}
