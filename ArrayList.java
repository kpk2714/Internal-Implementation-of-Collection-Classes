public class ArrayListCode 
{
    static class ArrayList<V>
    {
        private static final int DEFAULT_CAPACITY = 10;

        private Object[] elementData;
        private static int size = 0;

        public ArrayList()
        {
            this.elementData = new Object[DEFAULT_CAPACITY];
            for(int i=0;i<elementData.length;i++){
                elementData[i] = null;
            }
        }

        private int findNewCapacity(int oldCapacity)
        {
            System.out.println("Before Resizing capacity is : "+oldCapacity);
            int newCapacity = (int) ((oldCapacity*1.5)+1); // oldCapacity + (oldCapacity>>1);
            System.out.println("After Resizing capacity is : "+newCapacity);
            return newCapacity;
        }

        private void resize()
        {
            Object[] oldElementData = this.elementData;
            this.elementData = new Object[findNewCapacity(oldElementData.length)];
            for(int i=0;i<oldElementData.length;i++){
                elementData[i] = oldElementData[i];
            }
        }

        public boolean add(V value){ // insert element at end in the list
            if(size==elementData.length)
            {
                System.out.println("Now Capacity is full !!!");
                resize();
            }
            elementData[size] = value;
            size++;
            return true;
        }

        @SuppressWarnings("unchecked")
        public V get(int index) 
        {
            if(index>=size || index<0)
            {
                throw new IndexOutOfBoundsException("Index "+index+" out of bounds for length "+size);
            }
            return (V)elementData[index];
        }

        public int size()
        {
            return size;
        }

        public boolean isEmpty()
        {
            return size==0;
        }

        @SuppressWarnings("unchecked")
        public V remove(int index)
        {
            if(index>=size || index<0){
                throw new IndexOutOfBoundsException("Index "+index+" out of bounds for length "+size);
            }

            Object[] oldElementData = this.elementData;
            this.elementData = new Object[oldElementData.length];
            int k=0;
            V deletedValue = null;
            for(int i=0;i<oldElementData.length;i++){
                if(i==index)
                {
                    deletedValue = (V)oldElementData[i];
                    continue;
                }
                else
                {
                    elementData[k] = oldElementData[i];
                    k++;
                }
            }

            size--;

            return deletedValue;
        }

        public boolean contains(V value){
            for(int i=0;i<elementData.length;i++){
                if(elementData[i]==value){
                    return true;
                }
            }
            return false;
        }

        public void add(int index, V value){
            System.arraycopy(elementData, index,elementData, index + 1,size - index);
            elementData[index] = value;
            size = size + 1;
        }

        @SuppressWarnings("unchecked")
        public V set(int index,V value){
            if(index>=size || index<0){
                throw new IndexOutOfBoundsException("Index "+index+" out of bounds for length "+size);
            }
            V oldValue = (V) elementData[index];
            elementData[index] = value;
            return oldValue;
        }
    }


    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        System.out.println("Is ArrayList is Empty :"+list.isEmpty());

        list.add("Apple");list.add("Mango");list.add("Banana");
        list.add("Biscuit");list.add("Ball");list.add("Busket");
        list.add("Java");list.add("Python");list.add("Collection");
        list.add("DSA");list.add("TC");list.add("SC");
        
        System.out.println("Size of ArrayList before Deletion is : "+list.size());

        System.out.println("List Elements are ->");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

        list.remove(3);

        System.out.println("Size of ArrayList after Deletion is : "+list.size());

        System.out.println("List Elements are ->");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

        System.out.println("Is ArrayList is Empty :"+list.isEmpty());
        
        System.out.println("Is Element is present or not :"+list.contains("Mango"));

      //  System.out.println("Find the value : "+list.get(10));
       // System.out.println("Remove the element : "+list.get(17));


        list.add(1, "Krishna");
        System.out.println("List Elements are ->");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

        list.set(1, "Krishna Pada Kar");
        System.out.println("List Elements are ->");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
}
