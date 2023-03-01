package Practices;

/**
 * 给定一个二叉树的头节点head,返回这颗二叉树中最大[最多节点]的二叉搜索子树的size。
 * [搜索二叉树：树的节点不重复，节点的所有左树的值比节点值小，节点的所有右树的值比节点值大，
 * 每颗子树都要是搜索二叉树]
 */
public class P02_BinaryTreeRelated04 {
    static class Node{
        int value;
        Node left;
        Node right;
        public Node(int value){
            this.value = value;
        }
    }
    /*
    * 两种情况：
    * 1) 与 X 无关， 即 左树或右树上的最大二叉搜索树
    * 2) 与 X 有关， 即以 X 为头的树是最大二叉搜索树。此时需要满足以下条件：
    *    左树和右树都是二叉搜索树，左树节点最大值小于 X, 右树节点最小值大于 X
    *
    *
    * 左右树要求不一致，左树要求：
    * 1)左树最大子搜索树的size
    * 2)左树是否是搜索二叉树
    * 3)左树上节点的最大值
    * 右树要求：
    * 1)右树最大子搜索树的size
    * 2)右树是否是搜索二叉树
    * 3)右树上节点的最小值
    * 于是求两者并集.
    *
    * */
    public static class Info{
        //如果题目是求返回这颗二叉树中最大[最多节点]的二叉搜索子树的头节点
        //那么这个就是 public Node maxSubBSTHead 表示最大二叉搜索子树的头节点
        public boolean isAllBST;

        public int maxSubBSTSize;
        public int min;
        public int max;
        public Info(boolean is, int size, int mi, int ma){
            isAllBST = is;
            maxSubBSTSize = size;
            min = mi;
            max = ma;
        }
    }
        public static Info process(Node X){
            if (X == null) {
                return null;
            }
            Info leftInfo = process(X.left);
            Info rightInfo = process(X.right);
            int min = X.value;
            int max = X.value;
            int maxSubBSTSize = 0;
            if (leftInfo != null) {
                //左树最小值参与决策全局最小值
                min = Math.min(min,leftInfo.min);
                //右树最大值参与决策全局最大值
                max = Math.max(max, rightInfo.max);

                maxSubBSTSize = Math.max(maxSubBSTSize, leftInfo.maxSubBSTSize);
            }
            if (rightInfo != null) {
                //同上
                min = Math.min(min,leftInfo.min);
                max = Math.max(max, rightInfo.max);

                maxSubBSTSize = Math.max(maxSubBSTSize, rightInfo.maxSubBSTSize);
            }
            boolean isAllBST = false;

            /*
            * 如果 isAllBST 最后得到为true,则第二种与 X 有关的情况成立
            * maxSubBSTSize = 以 X 为头的所有节点树
            * */
            if(
                    //如果左树为空则为搜索二叉树，否则就拿左树的 isAllBST 判断
                    (leftInfo == null ? true : leftInfo.isAllBST)
                     &&
                    //右树同理
                    (rightInfo == null ? true : rightInfo.isAllBST)
                     &&
                    //左树最大值小于X
                    (leftInfo == null ? true : leftInfo.max < X.value)
                     &&
                    //右树最小值大于X
                    (rightInfo == null ? true : rightInfo.min > X.value)
            ){
                maxSubBSTSize =
                        (leftInfo == null ? 0 : leftInfo.maxSubBSTSize)
                        +
                        (rightInfo == null ? 0 : rightInfo.maxSubBSTSize)
                        +
                        1;
                isAllBST = true;
            }
            return new Info(isAllBST,maxSubBSTSize,min,max);
        }
    }
