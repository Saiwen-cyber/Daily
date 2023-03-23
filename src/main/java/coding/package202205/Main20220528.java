package coding.package202205;

import java.util.*;

/**
 * @author fang
 */
public class Main20220528 {
    public boolean digitCount(String num) {
        int len = num.length();
        Map<Integer, Integer> count = new HashMap<>();

        for (int i = 0; i < len; i++) {
            char ch = num.charAt(i);
            int val = ch - '0';
            count.put(val, count.getOrDefault(val, 0) + 1);
        }
        boolean re = true;
        for (int i = 0; i < len; i++) {
            int tmp = count.getOrDefault(i, 0);
            char ch = num.charAt(i);
            int val = ch - '0';
            re = re && val == tmp;
        }
        System.out.println("Charlie".compareTo("Bob"));
        return re;
    }

    public String largestWordCount(String[] messages, String[] senders) {
        String candidate = "";
        int co = 0;
        Map<String, Integer> map = new HashMap<>();
        int len = senders.length;
        for (int i = 0; i < len; i++) {
            String person = senders[i];
            String str = messages[i];
            String[] strs = str.split(" ");
            int val = map.getOrDefault(str, 0) + strs.length;
            map.put(person, val);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String person = entry.getKey();
            Integer val = entry.getValue();
            if (val > co) {
                candidate = person;
                co = val;
            } else if (val == co) {
                if (person.compareTo(candidate) > 0) {
                    candidate = person;
                }
            }
        }
        return candidate;
    }

    public long maximumImportance(int n, int[][] roads) {
        int len = roads.length;
        long[] count = new long[n];
        for (int i = 0; i < len; i++) {
            int a = roads[i][0];
            int b = roads[i][1];
            count[a]++;
            count[b]++;
        }
        Arrays.sort(count);
        long re = 0;
        for (int i = 1; i <= n; i++) {
            re = i * count[i - 1] + re;
        }
        return re;
    }

    public int rearrangeCharacters(String s, String target) {
        int len = s.length();
        Map<Character, Integer> src = new HashMap<>();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length(); i++) {
            char ch = target.charAt(i);
            map.put(ch, 0);
            src.put(ch, src.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (map.get(ch) != null) {
                map.put(ch, map.get(ch) + 1);
            }
        }
        boolean flag = true;
        int re = 0;
        while (flag) {
            for (Map.Entry<Character, Integer> entry : src.entrySet()) {
                char ch = entry.getKey();
                int val = entry.getValue();
                if (map.get(ch) - val >= 0) {
                    map.put(ch, map.get(ch) - val);
                } else {
                    flag = false;
                }
            }

            re++;
        }
        return re;
    }

    public String discountPrices(String sentence, int discount) {
        double dis = discount / 100.0;
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            if (w.startsWith("$")) {
                String tmp = w.substring(1);
                double num;
                try {
                    num = Double.parseDouble(tmp);
                } catch (Exception e) {
                    continue;
                }
                String str = String.format("%.2f", num * dis);
                words[i] = "$" + str;
            }
        }
        return String.join(" ", words);
    }

    public int totalSteps(int[] nums) {

        int re = 0;
        boolean flag = true;
        while (flag) {
            List<Integer> tmp = new ArrayList<>();
            Stack<Integer> stack = new Stack<>();
            flag = false;
            for (int i = 0; i < nums.length; i++) {
                if (stack.isEmpty()) {
                    tmp.add(nums[i]);
                    stack.add(nums[i]);
                    continue;
                }
                int val = stack.peek();
                if (nums[i] >= val) {
                    tmp.add(nums[i]);
                } else {
                    flag = true;
                }
                stack.add(nums[i]);
            }
            if(flag == false) {
                return re;
            }
            re++;
            nums = tmp.stream().mapToInt(Integer::valueOf).toArray();
        }
        return re;
    }

    public static void main(String[] args) {
        List<String> strList = new ArrayList<String>();
        strList.add("1");
        strList.add("2");
        strList.add("3");

        String[] strArr = null;

        strArr = strList.toArray(new String[strList.size()]);
        System.out.println(Arrays.toString(strArr));//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/java/java-arraylist-to-array.html


        Main20220528 main = new Main20220528();
        int[] nums = new int[]{5, 3, 4, 4, 7, 3, 6, 11, 8, 5, 11};
        main.totalSteps(nums);
        String re = main.discountPrices("there are $1 $2 and 5$ candies in the shop", 50);
        System.out.println(re);
        String s;
        String target;
        s = "abbaccaddaeea";
        target = "aaaaa";
        main.rearrangeCharacters(s, target);
        String[] str1 = new String[]{"Hello userTwooo", "Hi userThree", "Wonderful day Alice", "Nice day userThree"};
        String[] str2 = new String[]{"Alice", "userTwo", "userThree", "Alice"};
        main.largestWordCount(str1, str2);
        main.digitCount("030");
    }
}