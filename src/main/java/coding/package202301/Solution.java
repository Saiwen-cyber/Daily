package coding.package202301;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

class Solution11 {
    public boolean digitCount(String num) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < num.length(); i++) {
            int key = num.charAt(i) - '0';
            int val = map.getOrDefault(key,0) + 1;
            map.put(key, val);
        }

        for(int i = 0; i < num.length(); i++) {
            int val = map.getOrDefault(i, 0);
            int needyVal = num.charAt(i) - '0';
            if(val != needyVal) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution11 solution = new Solution11();
        solution.digitCount("1210");
    }
}