
import java.util.HashMap;
import java.util.Map;


public class coll {
    public static void main(String[] args) {
        // List<Integer> nums = new ArrayList<Integer>();
        // nums.add(6);
        // nums.add(9);
        // nums.add(8);
        // // nums.add("7");
        // System.out.println(nums.get(2));
    //     Set<Integer> nums = new TreeSet<Integer>();
    //     nums.add(6);
    //     nums.add(9);
    //     nums.add(8);
    //     // nums.add("7");
    //     System.out.println(nums);
    //

    Map<String,Integer> students= new HashMap<>();
    students.put("moin", 20);
    students.put("nouman", 28);
    students.put("nouman", 28);
    for(String key:students.keySet()){
    System.out.println(key + ": "+students.get(key));
    }
     }
}
