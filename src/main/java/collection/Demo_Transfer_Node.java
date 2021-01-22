package collection;

/**
 *     模拟JDK7 HashMap扩容时，transfer方法。多线程扩容时，会出现死循环的情况、或者数据丢失。
 * @author JiaBing
 * @date 2018-12-27
 */
public class Demo_Transfer_Node {

	public static void main(String[] args) {
		Node<String, String>[] tab = (Node<String, String>[])new Node[4];
		Node<String, String>[] newTab = (Node<String, String>[])new Node[8];
		Node<String, String> node_1 = new Node<String, String>(0, "0", "0", null);
		Node<String, String> node_4 = new Node<String, String>(4, "4", "4", null);
		Node<String, String> node_8 = new Node<String, String>(8, "8", "8", null);
		node_1.next = node_4;
		node_4.next = node_8;
		tab[0] = node_1;
		displayMap(tab);
		new Thread(()->{
			for(Node<String,String> node : tab) {
				while(null != node) {  
					Node<String,String> next = node.next;           
					int i = node.hash & 7;
					node.next = newTab[i];  
					newTab[i] = node;  
					node = next;  
				} 
			}
		}).start(); 
		new Thread(()->{
			for(Node<String,String> node : tab) {
				while(null != node) {  
					Node<String,String> next = node.next;           
					int i = node.hash & 7;
					node.next = newTab[i];  
					newTab[i] = node;  
					node = next;  
				} 
			}
		}).start(); 
		
		displayMap(newTab);
		
	}
	
	static <K, V> void displayMap(Node<K,V>[] tab) {
		for (int i = 0; i < tab.length; i++ ) {
			if (tab[i] != null) {
				System.out.print(i+" : "+ tab[i].hash);
				Node<K,V> next = tab[i].next;
				while (next!=null) {
					System.out.print(" -> "+next.hash );
					next = next.next;
				}
				System.out.println();
			}else {
				System.out.println(i+" : null");
			}
		}
	}

}



class Node<K, V>{
	int hash;
	K key;
	V val;
	Node<K, V> next;
	public Node(int hash, K key, V val, Node<K, V> next) {
		this.hash = hash;
		this.key = key;
		this.val = val;
		this.next = next;
	}
	@Override
		public int hashCode() {
			
			return hash;
		}
}