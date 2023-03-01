package baseStructure;

/**
 * 前缀树
 * 提供insert / delete / search / prefixNumber 四个方法
 * 可查找特定字符串数组中出现了几次
 * 可查找加入过的所有字符串，有多少字符串以特定字符作为前缀
 */
public class Code07_TrieTree {
    //封装一个作为前缀树中每一个节点的 Node
    public static class Node{
        /*
        * pass 指的是在一个一个节点加入的过程中，当前节点通过了几次
        * end 指的是加字符的过程中，这个节点成为了多少个字符串的结尾
        * */
        public int pass;
        public int end;
        public Node[] nexts; //用数组装后续节点
        public Node(){
            pass = 0;
            end = 0;
            //如果 nexts[i] == null，则表示i方向的路不存在
            nexts = new Node[26]; //对应26个英文字母
        }
    }
    public static class Trie{
        private Node root;
        public Trie(){
            root = new Node();
        }
        /*
        * 添加一个字符串，把其中重复的字符节点 pass++,最后的字符节点的 end++
        * */
        public void insert(String word){
            if(word == null){
                return;
            }
            char[] chs = word.toCharArray();
            Node node = root; //每新加一个字符串都是从头节点出发
            node.pass++;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a'; // 'a'对应0,'b'对应1,以此类推
                if(node.nexts[index] == null){
                    node.nexts[index] = new Node(); //新建一条路
                }
                node = node.nexts[index]; //node来到新建的节点上
                node.pass++;
            }
            node.end++;
        }
        //先搜索存不存在，再pass--,最后的end--,另外当pass==0时，代表后续的所有节点删除
        public void delete(String word){
            if(search(word) != 0){
                char[] chs = word.toCharArray();
                Node node = root;
                node.pass--;
                int index = 0;
                for (int i = 0; i < chs.length; i++) {
                    index = chs[i] - 'a';
                    if(--node.nexts[index].pass == 0){
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }


        //搜索单词之前出现过几次,只要找到这个字符串最后一个字符看它的end值是多少
        public int search(String word){
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            Node node = root;
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

        //所有加入的字符串中，有几个是以pre这个字符串为前缀的,只要找到这个字符串最后一个字符看它的pass值是多少
        public int prefixNumber(String pre){
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            Node node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if(node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }
}
