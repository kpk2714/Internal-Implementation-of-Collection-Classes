import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class HashMapCode1 {
    static class HashMap<K,V>
    {
        private class Node{
            K key;
            V value;

            public Node(K key,V value){
                this.key = key;
                this.value = value;
            }
        }

        private static int n = 0;
        private static int N = 0;
        private double lf = 0.75;
        private LinkedList<Node> buckets[];

        @SuppressWarnings("unchecked")
        public HashMap(){
            N = 16;
            this.buckets = new LinkedList[N];
            for(int i=0;i<N;i++){
                buckets[i] = new LinkedList<>();
            }
        }

        public int hashFunction(K key){
            if(key==null){
                return 0;
            }
            int bi = key.hashCode();
            return Math.abs(bi)%N;
        }

        public int searchInLinkedList(int bi,K key){

            LinkedList<Node> list = buckets[bi];
            for(int i=0;i<list.size();i++){
                if(list.get(i).key==key){
                    return i;
                }
            }
            return -1;
        }

        @SuppressWarnings({  "unchecked" })
        public void reHash(){
            
            LinkedList<Node> oldBuckets[] = this.buckets;
            N = N*2; // double size

            this.buckets = new LinkedList[N];
            for(int i=0;i<N;i++){
                buckets[i] = new LinkedList<>();
            }

            for(int i=0;i<oldBuckets.length;i++){
                LinkedList<Node> ll = oldBuckets[i];
                for(int j=0;j<ll.size();j++){
                    Node node = ll.get(j);
                    buckets[i].add(node);
                }
            }

            
        }

        public V put(K key,V value){
            int bi = hashFunction(key);
            int di = searchInLinkedList(bi,key);

            if(di==-1){
                buckets[bi].add(new Node(key,value));
                n++;
                // double lambda = (double) n/N;
                double k = lf*N;
                if(n>=k){
                    reHash();
                }
                return null;
            }
            else{
                Node node = buckets[bi].get(di);
                node.value = value;
                return node.value;
            }
        }

        public V get(K key){
            int bi = hashFunction(key);
            int di = searchInLinkedList(bi,key);

            if(di==-1){
                return null;
            }
            else{
                return buckets[bi].get(di).value;
            }
        }

        public int size(){
            return n;
        }

        public Set<K> keySet(){
            Set<K> keys = new HashSet<>();

            for(int i=0;i<buckets.length;i++){
                LinkedList<Node> ll = buckets[i];
                for(int j=0;j<ll.size();j++){
                    Node node = ll.get(j);
                    keys.add(node.key);
                }
            }

            return keys;
        }

        public boolean isEmpty(){
            return n==0;
        }

        public boolean containsKey(K key){
            int bi = hashFunction(key);
            int di = searchInLinkedList(bi,key);

            if(di==-1){
                return false;
            }
            return true;
        }

        public V remove(K key){
            int bi = hashFunction(key);
            int di = searchInLinkedList(bi,key);

            if(di==-1){
                return null;
            }
            else{
                Node node = buckets[bi].remove(di);
                n--;
                return node.value;
            }
        }

        public boolean containsValue(V value){

            for(int i=0;i<buckets.length;i++){
                LinkedList<Node> ll = buckets[i];
                for(int j=0;j<ll.size();j++){
                    Node node = ll.get(j);
                    if(node.value==value){
                        return true;
                    }
                }
            }

            return false;
        }

        public Collection<V> values(){
            Collection<V> c = new ArrayList<>();

            for(int i=0;i<buckets.length;i++){
                LinkedList<Node> ll = buckets[i];
                for(int j=0;j<ll.size();j++){
                    Node node = ll.get(j);
                    c.add(node.value);
                }
            }

            return c;
        }

        public V getOrDefault(K key,V defaultValue){
            int bi = hashFunction(key);
            int di = searchInLinkedList(bi,key);

            if(di==-1){
                return defaultValue;
            }
            else{
                return buckets[bi].get(di).value;
            }
        }


    }

    public static void main(String[] args) {

        HashMap<String,Integer> map = new HashMap<>();

        map.put("Krishna", 7);
        map.put("Radha", 1);

        Set<String> keys = map.keySet();
        for(String k : keys){
            System.out.println("Key : "+k+" Value : "+map.get(k));
        }

        System.out.println(map.remove("Krishn"));

        System.out.println("containsKey() : "+map.containsKey("Krishna"));
        System.out.println("containsValue() : "+map.containsValue(7));


        System.out.println("All values -> values()");

        Collection<Integer> c = map.values();
        for(Integer i : c){
            System.out.println("Values : "+i);
        }


        System.out.println("getOrDefault()  =>  "+map.getOrDefault("Krishn", 1771));
    }

}
