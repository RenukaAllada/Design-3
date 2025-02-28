class Sample{
    /****************PROBLEM-2*****************/
    //tc:0(1)
//sc:0(N)
    class LRUCache {
        private int capacity;
        private HashMap<Integer,Node> map;

        Node head;
        Node tail;

        private class Node{
            int key,value;
            Node prev,next;
            public Node(int key, int value){
                this.key=key;
                this.value=value;
            }
        }

        private void addToHead(Node node){
            node.next=head.next;
            head.next=node;
            node.prev=head;
            node.next.prev=node;
        }

        private void removeNode(Node node){
            node.prev.next=node.next;
            node.next.prev=node.prev;
        }

        public LRUCache(int capacity) {
            this.capacity=capacity;
            map=new HashMap<>();
            head=new Node(-1,-1);
            tail=new Node(-1,-1);
            head.next=tail;
            tail.prev=head;
        }

        public int get(int key) {
            if(!map.containsKey(key)){
                return -1;
            }
            Node existingNode=map.get(key);
            removeNode(existingNode);
            addToHead(existingNode);
            return existingNode.value;
        }

        public void put(int key, int value) {
            //key already present

            if(map.containsKey(key)){
                Node node=map.get(key);
                removeNode(node);
                addToHead(node);
                node.value=value;
                return;
            }

            if(map.size()==capacity){
                Node tailPrev=tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode=new Node(key,value);
            addToHead(newNode);
            map.put(key,newNode);
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

    /****************PROBLEM-1*****************/

    //TC:0(1) for next,hasNext
    //SC:0(N)
    public class NestedIterator implements Iterator<Integer> {
        Queue<Integer> queue;
        public NestedIterator(List<NestedInteger> nestedList) {
            queue =new LinkedList<>();
            flattenList(nestedList);
        }

        public void flattenList(List<NestedInteger> nestedList) {
            for(NestedInteger each:nestedList){
                if(each.isInteger()){
                    queue.add(each.getInteger());
                }else{
                    flattenList(each.getList());
                }
            }
        }

        @Override
        public Integer next() {
            return queue.poll();
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

    public class NestedIterator implements Iterator<Integer> {
        private Stack<Iterator<NestedInteger>> stack;
        NestedInteger nextEl;
        public NestedIterator(List<NestedInteger> nestedList) {
            stack=new Stack<>();
            stack.push(nestedList.iterator());
        }

        @Override
        public Integer next() {
            return nextEl.getInteger();
        }

        @Override
        public boolean hasNext() {
            while(!stack.isEmpty()){
                if(!stack.peek().hasNext()){
                    stack.pop();
                }else if((nextEl=stack.peek().next()).isInteger()){
                    return true;
                }else{
                    stack.push(nextEl.getList().iterator());
                }
            }
            return false;
        }
    }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

}