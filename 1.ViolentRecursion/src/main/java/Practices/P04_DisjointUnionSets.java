package Practices;

import java.util.HashMap;
import java.util.Stack;

/**
 * 一个用户具有a,b,c三个字段，认定如果这三个字段中任意一个字段相同都表示的是同一个用户
 * 给定一个用户数组 User[],要求合并用户，并返回合并之后的用户数量
 */
public class P04_DisjointUnionSets {
    public static class User{
        private String a;
        private String b;
        private String c;

        public User(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    public static int mergeUsers(User[] users){
        UnionSet<User> unionSet = new UnionSet<>(users);
        //mapA,mapB,mapC 的 key 存放user中对应的 a,b,c字段的值，value存放这个字段属于具体哪个user
        HashMap<String,User> mapA = new HashMap<>();
        HashMap<String,User> mapB = new HashMap<>();
        HashMap<String,User> mapC = new HashMap<>();
        /*
        * 如果mapA在添加过程中出现重复的 key,则证明 user是一样的，
        * */
        for(User user : users){
            if (mapA.containsKey(user.a)){
                unionSet.union(user,mapA.get(user.a));
            }else {
                mapA.put(user.a, user);
            }
            if (mapB.containsKey(user.b)){
                unionSet.union(user,mapB.get(user.b));
            }else {
                mapB.put(user.b, user);
            }
            if (mapC.containsKey(user.c)){
                unionSet.union(user,mapC.get(user.c));
            }else {
                mapC.put(user.c, user);
            }
        }
        return unionSet.getSetSum();
    }



    //--使用 Code_other_DisjointUnionSets中的并查集，并在构造函数中稍作修改和根据题目需要添加getSetSum----
    public static class Node<V>{
        V value;
        public Node(V v){
            value = v;
        }
    }
    public static class UnionSet<V>{

        public HashMap<V, Node<V>> nodes; //记录V与节点的对应关系，后续不会改变
        public HashMap<Node<V>, Node<V>> parents; //记录节点对应的父亲节点
        public HashMap<Node<V>,Integer> sizeMap; //只有一个点是一个集合最终父节点的情况下，才能在sizeMap中有记录

        public int getSetSum(){
            return sizeMap.size();
        }
        public UnionSet(V[] values){
            for(V value : values){
                Node<V> node = new Node<>(value);
                nodes.put(value,node);
                parents.put(node,node);
                sizeMap.put(node,1);
            }
        }

        public Node<V> findFather(Node<V> cur){
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)){
                path.push(cur);
                cur = parents.get(cur);
            }
            while (!path.isEmpty()){
                parents.put(path.pop(),cur);
            }
            return cur;
        }

        public boolean isSameSet(V a, V b){
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
                parents.put(small,big);
                sizeMap.put(big,aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }
    }
}
