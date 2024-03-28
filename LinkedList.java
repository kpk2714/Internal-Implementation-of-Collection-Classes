public class LinkedListCode {
    static class LinkedList<V>
    {
        private class Node{
            V data;
            Node next;
            Node prev;
    
            Node(V data){
                this.data = data;
                this.next = null;
                this.prev = null;
            }
        }
    
        Node head;
        Node tail;

        public LinkedList(){
            this.head = null;
            this.tail = null;
        }

        private static int size = 0;
    
        public void traverseForward()
        {
            Node current = head;
            while(current!=null) 
            {
                System.out.print(current.data+"->");
                current = current.next;
            }
            System.out.print("null\n");
        }
    
        public void traverseBackward()
        {
            Node current = tail;
            System.out.print("null");
            while(current!=null)
            {
                System.out.print("<-"+current.data);
                current = current.prev;
            }
        }
    
        public void insertAtBegining(V data)
        {
            Node newNode = new Node(data);
            if(head==null){
                head = newNode;
                tail = newNode;
            }
            else{
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }

            size++;
        }
    
        public void insertAtPosition(V data,int position)
        {
            if(position==1){
                insertAtBegining(data);
            }
            else{
                Node newNode = new Node(data);
                Node current = head;
                int currentPosition = 1;
                while(current!=null && currentPosition<position){
                    current = current.next;
                    currentPosition++;
                }
    
                if(current==null){
                    insertAtEnd(data);
                }
                else{
                    newNode.next = current;
                    newNode.prev = current.prev;
                    current.prev.next = newNode;
                    current.prev = newNode;
                }
            }

            size++;
        }
    
        public void insertAtEnd(V data)
        {
            Node newNode = new Node(data);
            if(tail==null){
                head = newNode;
                tail = newNode;
            }
            else{
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
            size++;
        }
    
        public V deleteFromBegining()
        {
            V value;

            if(head==null){
                throw new NullPointerException("NullPointerException : As List is already empty !!!");
            }
    
            if(head==tail){
                value = head.data;
                head = null;
                tail = null;
                return value;
            }
    
            Node currentNode = head;
            value = currentNode.data;
            head = head.next;
            head.prev = null;
            currentNode.next = null;

            size--;

            return value;
        }
    
    
        public V deleteAtPosition(int position)
        {
            if(head==null){
                throw new NullPointerException("NullPointerException : As List is already empty !!!");
            }
    
            if(position==1){
                return deleteFromBegining();
            }
            else{
                Node current = head;
                int currentPosition = 1;
                while(current!=null && currentPosition<position){
                    current = current.next;
                    currentPosition++;
                }
    
                if(current==null){
                    System.out.println("Position wrong"); 
                    return null;
                }
    
                if(current==tail){
                    return deleteFromEnd();
                }

                V value = current.data;
    
                current.prev.next = current.next;
                current.next.prev = current.prev;
                current.prev = null;
                current.next = null;

                size--;

                return value;
            }
        }
    
        public V deleteFromEnd()
        {
            if(tail==null){
                throw new NullPointerException("NullPointerException : As List is already empty !!!");
            }

            V value;
    
            if(head==tail){
                value = head.data;
                head = null;
                tail = null;
                return value;
            }

            value = tail.data;
    
            Node currentNode = tail;
            tail = tail.prev;
            tail.next = null;
            currentNode.prev = null;

            size--;

            return value;
        }

        public boolean add(V value){ // only add means -> adding element at end
            insertAtEnd(value);
            return true;
        }

        public Node getNode(int index){
            if(index < (size >> 1)){
                Node current = head;
                for(int i=0;i<index;i++){
                    current = current.next;
                }

                return current;
            }
            else{
                Node current = tail;
                for(int i=size-1;i>index;i--){
                    current = current.prev;
                }
                return current;
            }
        }

        public V get(int index){
            if(index>=size){
                throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
            }
            return getNode(index).data;
        }

        public void addFirst(V value){
            insertAtBegining(value);
        }

        public void addLast(V value){
            insertAtEnd(value);
        }

        public int size(){
            return size;
        }

        public void add(V value,int position){
            insertAtPosition(value, position);
        }

        public V remove(){
            return deleteFromBegining();
        }

        public V removeFirst(){
            return deleteFromBegining();
        }

        public V remove(int index){
            return deleteAtPosition(index);
        }

        public V removeLast(){
            return deleteFromEnd();
        }

        public V set(int index , V value){
            Node node = getNode(index);
            V oldValue = node.data;
            node.data = value;
            return oldValue;
        }

        public V getFirst(){
            return head.data;
        }

        public V getLast(){
            return tail.data;
        }

        public boolean contains(V value){
            Node current = head;
            while(current!=null){
                if(current.data==value){
                    return true;
                }
                current = current.next;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.addFirst(20);
        list.addLast(30);
        list.add(40, 2);
        list.add(50);
        list.add(60);
        System.out.println("Elements are : ");
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i)+" ");
        }
        System.out.println();
        System.out.println("Head : "+list.head.data+" Tail : "+list.tail.data);
        System.out.println("List size : "+LinkedList.size);

        System.out.println("removeFirst() : "+list.removeFirst());
        System.out.println("Elements are : ");
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i)+" ");
        }
        System.out.println();


        System.out.println("remove() : "+list.remove());
        System.out.println("Elements are : ");
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i)+" ");
        }
        System.out.println();


        System.out.println("remove(index) : "+list.remove(2));
        System.out.println("Elements are : ");
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i)+" ");
        }
        System.out.println();


        System.out.println("removeLast() : "+list.removeLast());
        System.out.println("Elements are : ");
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i)+" ");
        }
        System.out.println();

        System.out.println("set(index,value) : "+list.set(0, 20));
        System.out.println("Elements are : ");
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i)+" ");
        }
        System.out.println();

        System.out.println("getFirst() : "+list.getFirst());
        System.out.println("getLast() : "+list.getLast());
        System.out.println("contains() : "+list.contains(10));
    }
}
