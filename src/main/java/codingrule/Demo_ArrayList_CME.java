package codingrule;

import java.util.ArrayList;
import java.util.List;



/**
 * 本Demo专门用于 找到一个解释：<p> 为什么在使用forEach遍历找到List中的目标元素，并且删除，此时会出现ConcurrentModificationException
 * 分析：<p> 出现这个异常的原因是： expectedModCount ！= modCount。 那么为什么这两个变量不等呢？
 *    该程序分为7个步骤，逐个分析。
 *    <p>1. 创建一个ArrayList
 *        查看源码：
 *        *******************************************************
 *        public ArrayList() {
        	this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    	  }
    	  *******************************************************
    	    这里创建了一个空的数组，作为集合中用于存储数据的容器。
      <p>2. add("aaa"). 调用 ArrayList的 add(E e)方法
      		查看源码：
      		*************************************
      		 public boolean add(E e) {
        		ensureCapacityInternal(size + 1);  // Increments modCount!! <p> 这里重点说明要增加 modCount.那么modCount是什么东西，用来干什么的？
        		elementData[size++] = e;
        		return true;
    		 }
    		*************************************
<Note> modCount的声明是在AbstractList这个抽象类中，<p>protected transient int modCount = 0;<p> The number of times this list has been <i>structurally modified.
      	 用来表示这个list被结构性地修改的次数。
      	 查看ensureCapacityInternal(int minCapacity), 确实modCount被增加了。因此，每次add， modCount都会+1。
      	 
      	 **************************************************************
      	 private void ensureCapacityInternal(int minCapacity) {
        	if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            	minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
          	}

        	ensureExplicitCapacity(minCapacity);
    	}

    	private void ensureExplicitCapacity(int minCapacity) {
        	modCount++;

        	// overflow-conscious code
        	if (minCapacity - elementData.length > 0)
            	grow(minCapacity);
    	}
    	***************************************************************
    	
      <p>因此到第4步结束时，modCount = 3
      <5> for (String item : strs),
       forEach的本质是：
       <Step>1. 调用iterator(),创建一个Itr
       
      		************************************************
      		 public Iterator<E> iterator() {
        		return new Itr();
    		}
    		************************************************
    		
    		<Note>当执行到这里时， expectedModCount = modCount
    		
       <Step>2. 调用 Itr内部的hasNext(),这个方法就相当于在遍历。
       <Step>3. 调用 next()方法，将返回的元素赋值给item。这个方法也没对 modCount和expectedModCount做任何修改。只是调用了checkForComodification();
       
    		<p> Itr内部实现：
    		
    		***********************************************************************
    	 	private class Itr implements Iterator<E> {
        		int cursor;       // index of next element to return
        		int lastRet = -1; // index of last element returned; -1 if no such
        		int expectedModCount = modCount;

       		public boolean hasNext() {
            	return cursor != size;
        	}

        	@SuppressWarnings("unchecked")
        	public E next() {
            	checkForComodification();
            	int i = cursor;
            	if (i >= size)
                	throw new NoSuchElementException();
            	Object[] elementData = ArrayList.this.elementData;
            	if (i >= elementData.length)
                	throw new ConcurrentModificationException();
            	cursor = i + 1;
            	return (E) elementData[lastRet = i];
        	}
        	************************************************************************
        	
    	<p>6. 判断是否相等
    	<p>7. 调用ArrayList的 remove(Object o)， 方法内部判断传入的参数对象是否为null，匹配元素，找到目标下标 index，删除对应位置的元素。 如何删除？ fastRemove(index)
    			查看源码：
    			
    			******************************************************
    			public boolean remove(Object o) {
			        if (o == null) {
			            for (int index = 0; index < size; index++)
			                if (elementData[index] == null) {
			                    fastRemove(index);
			                    return true;
			                }
			        } else {
			            for (int index = 0; index < size; index++)
			                if (o.equals(elementData[index])) {
			                    fastRemove(index);
			                    return true;
			                }
			        }
			        return false;
			    }
			    *****************************************************
			    
			   	看看 fastRemove(int index)的内部实现： 
			   	<NOTE>
			   		发现 fastRemove(), 对modCount +1,此时 modCount = 4，而当程序再次执行到<5>时，调用next()方法，该方法内部会调用checkForComodification();
			   		会检查modCount和ExpectedModCount是否相等，不相等就会抛出ConcurrentModificationException。此时modCount = 4, 而 expectedModCount = 3，抛出异常
			   		
			   	******************************************************************************
			   	private void fastRemove(int index) {
        			modCount++;
        			int numMoved = size - index - 1;
        			if (numMoved > 0)
            			System.arraycopy(elementData, index+1, elementData, index, numMoved);
        			elementData[--size] = null; // clear to let GC do its work
    			}
    			******************************************************************************
    			
  <NOTE>总结：
  			在使用forEach进行删除list的元素的时候会出现ConcurrentModificationException，因此使用 iterator的方式进行remove不会出错。
  			<BUT>为什么使用 iterator的方式进行remove就不会出错？
  			**********************************************
  			<eg> 使用iterator进行迭代时,code如下(这里继续使用本例中的变量)：
  			  <p>迭代部分
  			 Iterator<String> it = strs.iterator();
  			 while (it.hasNext()){
  			 	String item = it.next();
  			 	if ("aaa".equals(it)){
  			 		it.remove(); //  <here>
  			 	}
  			 }
  				
  			*********************************************
  			
  			其实，iterator方式和forEach方式的迭代步骤都是一样的：
  				<1> 调用iterator() new Itr();
  				<2> hasNext()
  				<3> next()返回当前元素值，赋值给 item
  			只有 remove的方式不一样： 前者remove时 会将modCount的赋值给 expectedModCount,所以不会出现异常
  								   而后者并没有这样做。
  			<note>iterator的remove的方式为： 该方法是 ArrayList的内部私有类类 Itr类 的一个方法
  				************************
  				public void remove() {
            		if (lastRet < 0)
                		throw new IllegalStateException();
            		checkForComodification();

            		try {
                		ArrayList.this.remove(lastRet);
                		cursor = lastRet;
                		lastRet = -1;
                		expectedModCount = modCount; //<NOTE> 重点在这里
            		} catch (IndexOutOfBoundsException ex) {
                		throw new ConcurrentModificationException();
            		}
        		}
  				***********************
  			<note>forEach的remove的方式为：  
  			 	  代码就不贴出来了，上面有。
 * @author JiaBing
 * @date 2018-12-21
 */
public class Demo_ArrayList_CME {

	public static void main(String[] args) {
		// 创建一个字符串集合
		List<String> strs = new ArrayList<String>(); // 1
		strs.add("aaa"); // 2
		strs.add("bbb"); // 3
		strs.add("ccc"); // 4
		// 删除 "aaa"的元素
		for (String item : strs) { // 5
			if("aaa".equals(item)) { // 6
				strs.remove(item); // 7
			}
		}
		System.err.println("删除 'aaa'的元素后： "+ strs);
		
	}

}
