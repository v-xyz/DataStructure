package base;

public class TrieTree {
    public static class Node{
        public int pass;
        public int end;
        public Node[] nexts;
        public Node(){
            pass = 0;
            end = 0;
            nexts = new Node[26];
        }
    }
    public static class Trie{
        private Node head;
        public Trie(){
            head = new Node();
        }

        public void insert(String word){
            char[] chars = word.toCharArray();
            Node node = head;
            node.pass++;
            //for循环表示数组的下标
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                //如果没有路需要先新建一条路
                if(node.nexts[index] == null){
                    node.nexts[index] = new Node(); //新建一条路
                }
                node.nexts[index].pass++;
                node = node.nexts[index];
            }
            node.end++;
        }
        public void delete(String word){
            if(search(word) != 0){
                char[] chars = word.toCharArray();
                Node node = head;
                node.pass--;
                for (int i = 0; i < chars.length; i++) {
                    int index = chars[i] - 'a';
                    //当pass==0时，代表后续的所有节点删除
                    if(--node.nexts[index].pass == 0){
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }
        public int search(String word){
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            Node node = head;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if(node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }
//        public int prefixNumber(String pre){
//
//        }
    }

}
