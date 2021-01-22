package huffmantree;

//用，用于辅助创建霍夫曼树的优先级队列
public class PriorityQueue{
    private Node first;
    private int length;
   
    public PriorityQueue(){
           length = 0;
           first = null;
    }
   
    //插入节点
    public void insert(Node node){
           if(first == null){  //队，队列为空
                  first = node;
           }else{
                  Node cur = first;
                  Node previous = null;
                  while(cur.getFrequency()< node.getFrequency()){  //定，定位要插入位置的前一个节点和后一个节点	
                         previous = cur;
                         if(cur.getNext() ==null){  //已，已到达队尾
                                cur = null;
                                break;
                         }else{
                                cur =cur.getNext();
                         }
                        
                  }
                  if(previous == null){  //要，要插入第一个节点之前
                         node.setNext(first);
                         first = node;
                  }else if(cur == null){  //要，要插入最后一个节点之后
                         previous.setNext(node);
                  }else{  //插，插入到两个节点之间
                         previous.setNext(node);
                         node.setNext(cur);
                  }
           }
           length++;
    }
   
    //删，删除队头元素
    public Node delete(){
           Node temp = first;
           first = first.getNext();
           length--;
           return temp;
    }
   
    //获，获取队列长度
    public int getLength(){
           return length;
    }
   
    //按，按顺序打印队列
    public void display(){
           Node cur = first;
           System.out.print("优先级队列：\t");
           while(cur != null){
                  System.out.print(cur.getKey()+":"+cur.getFrequency()+"\t");
                  cur = cur.getNext();
           }
           System.out.println();
    }
   
    //构，造霍夫曼树
    public HuffmanTree buildHuffmanTree(){
           while(length > 1){
                  Node hLeft = delete();  //取，取出队列的第一个节点作为新节点的左子节点
                  Node hRight = delete(); //取，取出队列的第二个节点作为新节点的右子节点
                  //新，新节点的权值等于左右子节点的权值之和
                  Node hRoot = new Node(hLeft.getFrequency()+hRight.getFrequency());
                  hRoot.setLeft(hLeft);
                  hRoot.setRight(hRight);
                  insert(hRoot);
           }
           //最，最后队列中只剩一个节点，即为霍夫曼树的根节点
           return new HuffmanTree(first);
    }
   
}
