import java.util.ArrayList;

public class GemList
{
	private Node head;

	private int size = 0;
	private class Node
	{
		private Gem gem;
		private Node next;

		public Node(Gem gem){
			this.gem = gem;
		}

	}

	int size(){
		return size;
	}

	void draw(double y){
		Node temp = head;
		int i = 0;
		while (temp != null){
			temp.gem.draw(GemGame.indexToX(i), y);
			i++;
			temp = temp.next;
		}
	}

	public String toString(){
		Node temp = head;
		String c = "";
		if (head == null)
			return "<none>";
		while (temp != null){
			c += temp.gem + " --> ";
			temp = temp.next;
		}
		return c.substring(0, c.length() - 4);
	}

	void insertBefore(Gem gem, int index) {
		Node temp = new Node(gem);
		Node h = head;
		if (index == 0 || h == null) {
			head = temp;
			temp.next = h;
		}
		else if (index > size()) {
			for (int i = 0; i < size() - 1; i++) {
				if (h == null) //out of bounds
					throw new IndexOutOfBoundsException();
				h = h.next;
			}
			h.next = temp;
		}
		else {
			for (int i = 0; i < index - 1; i++) {
				if (h == null) //out of bounds
					throw new IndexOutOfBoundsException();
				h = h.next;
			}
			temp.next = h.next;
			h.next = temp;
		}
		size++;
	}

	int score(){
		int total = 0;
		if(size == 0)
			return total;
		else if(size == 1)
			return head.gem.getPoints();
		Node temp = head.next;
		Node prev = head;
		int count = 1, t = prev.gem.getPoints();
		while(temp != null){
			GemType color = prev.gem.getType();
			if(color == temp.gem.getType()){
				count++;
				t += temp.gem.getPoints();
			}
			else{
				total += count * t;
				count = 1;
				t = temp.gem.getPoints();
			}
			prev = temp;
			temp = temp.next;
		}
		total += t * count;
		return total;
	}


	public static void main(String [] args)
	{
		GemList list = new GemList();
		System.out.println(list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.9);

		list.insertBefore(new Gem(GemType.BLUE, 10), 0);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.8);

		list.insertBefore(new Gem(GemType.BLUE, 20), 99);  //not a mistake, should still work
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.7);

		list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.6);

		list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.5);

		list.insertBefore(new Gem(GemType.ORANGE, 50), 3);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.4);

		list.insertBefore(new Gem(GemType.GREEN, 50), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.3);
	}	
}
