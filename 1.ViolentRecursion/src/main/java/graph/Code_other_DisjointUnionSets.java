package graph;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/*
* ----------------------------并查集基础----------------------------------
* 1)有若干个样本a,b,c,d...类型假设是V
* 2)在并查集中一开始认为每个样本都在单独的集合里
* 3)用户可以在任何时候调用如下两个方法：
*   boolean isSameSet(V x, V y): 查询样本 x 和样本 y 是否属于同一个集合
*   void union(V x, V y): 把 x 和 y 各自所在的集合的所有样本合并成一个集合
* 4)isSameSet 和 union 方法的代价越低越好
*
* 路径压缩 + 按秩合并
* */
public class Code_other_DisjointUnionSets {
    public static class Code_other_DisjointUnionSets1{
        /*
        * 每个样本一开始都指向自己
        * isSameSet逻辑：通过每个样本的指向一直向父节点方向查找，找到代表这个集合的公共父节点，然后判断样本
        *               的公共父节点是否使用同一个节点，是的话表示是同一个集合
        * union逻辑： 样本找到自己的最终父节点，判断样本所在的集合总共有多少节点，然后对比两个样本所在不同集合的
        *            节点数，将少的集合的父节点合并到多的集合的父节点上，以多的集合的父节点作为最终父节点。同时
        *            合并过后注意路径压缩，直接指向最终父节点
        * */
        public static class Node<V>{
            V value;
            public Node(V v){
                value = v;
            }
        }
        public static class UnionSet<V>{

            public HashMap<V,Node<V>> nodes; //记录V与节点的对应关系，后续不会改变
            public HashMap<Node<V>,Node<V>> parents; //记录节点对应的父亲节点
            public HashMap<Node<V>,Integer> sizeMap; //只有在 一个点是一个集合最终父节点 的情况下，才能在sizeMap中有记录

            public UnionSet(List<V> values){
                for(V value : values){
                    Node<V> node = new Node<>(value);
                    nodes.put(value,node);
                    parents.put(node,node);
                    sizeMap.put(node,1);
                }
            }

            private Node<V> findFather(Node<V> cur){
                Stack<Node<V>> path = new Stack<>();
                while (cur != parents.get(cur)){ //cur不是自己的父节点，证明没来到最终父节点
                    path.push(cur);             //入栈
                    cur = parents.get(cur);     //cur来到自己的父节点位置，但还不一定是最终父节点，所以继续判断
                }
                while (!path.isEmpty()){
                    //经过以上判断cur来到最终父节点位置，path存储的是路径上的所有除最终父节点以外的节点，且它们的父节点都不是最终父节点
                    //依次将它们的父节点调整为最终父节点，路径压缩的实现
                    parents.put(path.pop(),cur);
                }
                return cur;
            }

            public boolean isSameSet(V a, V b){ //判断是不是一个集合只需要判断它们的最终父节点是不是一样即可
                //a，b如果都没有被包装过，证明a,b不在传入的list集合中，直接返回false
                if(!nodes.containsKey(a) || !nodes.containsKey(b)){
                    return false;
                }
                return findFather(nodes.get(a)) == findFather(nodes.get(b));
            }
            public void union(V a, V b){
                if(!nodes.containsKey(a) || !nodes.containsKey(b)){
                    return;
                }
                Node<V> aHead = findFather(nodes.get(a));
                Node<V> bHead = findFather(nodes.get(b));
                if(aHead != bHead){
                    int aSetSize = sizeMap.get(aHead);
                    int bSetSize = sizeMap.get(bHead);
                    Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                    Node<V> small = big == aHead ? bHead : aHead;
                    parents.put(small,big); //将small位置的最终父节点修改为 big,findFather会自动进行路径压缩调整
                    sizeMap.put(big,aSetSize + bSetSize); //最终父节点size增加
                    sizeMap.remove(small); //small不再成为最终父节点，从sizeMap中移除
                }
            }
        }

    }

    public static class Code_other_DisjointUnionSets2{
        int[] rank, parent;
        int n;

        // Constructor
        public Code_other_DisjointUnionSets2(int n)
        {
            rank = new int[n];
            parent = new int[n];
            this.n = n;
            makeSet();
        }

        // Creates n sets with single item in each
        void makeSet()
        {
            for (int i = 0; i < n; i++) {
                // Initially, all elements are in
                // their own set.
                parent[i] = i;
            }
        }

        // Returns representative of x's set
        int find(int x)
        {
            // Finds the representative of the set
            // that x is an element of
            if (parent[x] != x) {
                // if x is not the parent of itself
                // Then x is not the representative of
                // his set,
                parent[x] = find(parent[x]);

                // so we recursively call Find on its parent
                // and move i's node directly under the
                // representative of this set
            }

            return parent[x];
        }

        // Unites the set that includes x and the set
        // that includes x
        void union(int x, int y)
        {
            // Find representatives of two sets
            int xRoot = find(x), yRoot = find(y);

            // Elements are in the same set, no need
            // to unite anything.
            if (xRoot == yRoot)
                return;

            // If x's rank is less than y's rank
            if (rank[xRoot] < rank[yRoot])

                // Then move x under y  so that depth
                // of tree remains less
                parent[xRoot] = yRoot;

                // Else if y's rank is less than x's rank
            else if (rank[yRoot] < rank[xRoot])

                // Then move y under x so that depth of
                // tree remains less
                parent[yRoot] = xRoot;

            else // if ranks are the same
            {
                // Then move y under x (doesn't matter
                // which one goes where)
                parent[yRoot] = xRoot;

                // And increment the result tree's
                // rank by 1
                rank[xRoot] = rank[xRoot] + 1;
            }
        }
    }
}
