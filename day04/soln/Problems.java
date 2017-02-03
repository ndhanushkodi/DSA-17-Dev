package soln;

import java.util.Map;

public class Problems {

    public static Map<Integer, Integer> getCountMap(int[] arr) {
        Map<Integer, Integer> countMap = new MyLinearMap<>();
        for (int i : arr) {
            if (countMap.containsKey(i))
                countMap.put(i, countMap.get(i) + 1);
            else
                countMap.put(i, 1);
        }
        return countMap;
    }

    public static int sumLists(Node<Integer> l1, Node<Integer> l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        int carry = 0;
        int listSum = 0;
        int power_of_ten = 1;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = ((l2 == null) ? 0 : l2.val) + ((l1 == null) ? 0 : l1.val) + carry;
            listSum = listSum + (sum % 10)*power_of_ten;
            carry = sum / 10;
            power_of_ten *= 10;

            l1 = (l1 == null) ? l1 : l1.next;
            l2 = (l2 == null) ? l2 : l2.next;
        }

        return listSum;
    }

    public static Node<Integer> reverseList(Node<Integer> head){
        Node<Integer> previous = null;
        Node<Integer> current;
        while (head != null) {
            current = head;
            head = head.next;
            current.next = previous;
            previous = current;
        }
        return previous;
    }

}
