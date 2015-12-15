package CoreJava;

import java.util.*;

public class Example13 extends Example {

    public Example13() {
        super("Example13");
    }

    @Override
    public void example() {
        // test linked list
        LinkedListTest();
        
        // HashSet test
        HashSetTest();
        
        TreeSetTest();
        
        PriorityQueue();
    }
    
    // test linked list
    public void LinkedListTest() {
        List<String> a = new LinkedList<String>();
        a.add("Amy");
        a.add("Doug");
        a.add("Frances");
        a.add("Gloria");
        
        List<String> b = new LinkedList<String>();
        b.add("Bob");
        b.add("Doug");
        b.add("Frances");
        b.add("Gloria");
        
        System.out.println("Original: ");
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        
        ListIterator<String> aIter = a.listIterator();
        Iterator<String> bIter = b.iterator();
        while (bIter.hasNext()) {
            if (aIter.hasNext()) {
            }
            aIter.add(bIter.next());
        }
        System.out.println("Copy b to a: ");
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        
        // remove b
        bIter = b.iterator();
        while (bIter.hasNext()) {
            bIter.next();
            if (bIter.hasNext()) {
                bIter.next();
                bIter.remove();
            }
        }
        System.out.println("Remove some from b: ");
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        
        // remove b from a
        a.removeAll(b);
        System.out.println("Remove some from b: ");
        System.out.println("a: " + a);
        System.out.println("b: " + b);     
    }
    
    // HashSet test
    public void HashSetTest() {
        Set<String> words = new HashSet<String>();
        
        words.add("luo");
        words.add("fei");
        System.out.println("HashSet words: " + words);
    }
    
    // TreeSet Test
    public void TreeSetTest() {
        SortedSet<TreeSetItem> parts = new TreeSet<TreeSetItem>();
        parts.add(new TreeSetItem("Toaster", 1234));
        parts.add(new TreeSetItem("Widget", 4562));
        parts.add(new TreeSetItem("Modem", 9912));
        System.out.println(parts);
        
        SortedSet<TreeSetItem> sortByDescr = new TreeSet<TreeSetItem>(new Comparator<TreeSetItem>() {
            public int compare(TreeSetItem a, TreeSetItem b) {
                String descrA = a.getDescription();
                String descrB = b.getDescription();
                return descrA.compareTo(descrB);
            }
        });
        
        sortByDescr.addAll(parts);
        System.out.println(sortByDescr);
    }
    
    // PriorityQueue
    public void PriorityQueue() {
        PriorityQueue<GregorianCalendar> pq = new PriorityQueue<GregorianCalendar>();
        pq.add(new GregorianCalendar(1906, Calendar.DECEMBER, 9));
        pq.add(new GregorianCalendar(1815, Calendar.DECEMBER, 10));
        pq.add(new GregorianCalendar(1903, Calendar.DECEMBER, 3));
        pq.add(new GregorianCalendar(1910, Calendar.JUNE, 22));
        
        System.out.println("Iterating over elements...");
        for (GregorianCalendar date : pq) {
            System.out.println(date.get(Calendar.YEAR));
        }
        System.out.println("Removing elements...");
        while (!pq.isEmpty())
            System.out.println(pq.remove().get(Calendar.YEAR));
    }
    
    // 
}

class TreeSetItem implements Comparable<TreeSetItem> {
    private String description;
    private int partNumber;
    
    public TreeSetItem(String aDescription, int aPartNumber) {
        description = aDescription;
        partNumber = aPartNumber;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getPartNumber() {
        return partNumber;
    }
    
    @Override
    public int compareTo(TreeSetItem o) {
        return partNumber - o.getPartNumber();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + partNumber;
        return result;
    }

    @Override
    public String toString() {
        return "TreeSetItem [description=" + description + ", partNumber="
                + partNumber + "]";
    }
}

