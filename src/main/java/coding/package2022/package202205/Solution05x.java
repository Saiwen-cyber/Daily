package coding.package2022.package202205;

import java.util.*;

public class Solution05x {
    Map<Character, Integer> map = new HashMap<>();
    public List<String> removeAnagrams(String[] words) {
        List<String> list = new ArrayList<>();
        save(words[0]);
        for(int i = 1; i < words.length; i++) {
            if(save(words[i])) {
                list.add(words[i]);
            }
        }
        return list;
    }


    //用下二分法
    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int re = 0;
        int preIndex = 0;
        Set<Integer> set = new HashSet<>(special.length);
        for(int i = 0; i < special.length; i++) {
            int index = special[i];
            int[] left = Arrays.copyOfRange(special, preIndex, index);
            re = Math.max(re,left.length);
            preIndex = index;
        }
        return re;
    }

    public boolean isNumber(String s) {
        s = s.trim();
        //含e
        if(s.contains("e")) {
            String []strs = s.split("e");
            if(strs.length != 2) {
                return false;
            }
            return (s.contains(".") ? isFloat(strs[0]) : isInt(strs[0])) && isInt(strs[1]);
        }

        if(s.contains("E")) {
            String []strs = s.split("E");
            return (s.contains(".") ? isFloat(strs[0]) : isInt(strs[0])) && isInt(strs[1]);
        }
        return s.contains(".") ? isFloat(s) : isInt(s);
    }

    public boolean isInt(String str) {
        //没有正负
        if(str.indexOf("+") == 0 || str.indexOf("-") == 0) {
            str = str.substring(1,str.length());
            if(str.contains("+") || str.contains("-")) {
                return false;
            }
        }
        try{
            Integer.parseInt(str);
        } catch(Exception e) {
            return false;
        }
        return true;
    }

    public boolean isFloat(String str) {
        //没有正负
        if(str.indexOf("+") == 0 || str.indexOf("-") == 0) {
            str = str.substring(1,str.length());
            if(str.contains("+") || str.contains("-")) {
                return false;
            }
        }
        if(str.indexOf(".") == 0) {
            str = str.substring(1,str.length());
            return isInt(str);
        }
        if(str.indexOf(".") == str.length() - 1) {
            str = str.substring(0,str.length() - 1);
            return isInt(str);
        }
        String []strs = str.split("\\.");
        if(strs.length != 2) {
            return false;
        }
        return isInt(strs[0]) && isInt(strs[1]);
    }

    public boolean save(String str) {
        int[] m = new int[]{4,6};
        Arrays.sort(m);
        System.out.println(Arrays.binarySearch(m,7));
        System.out.println(Arrays.binarySearch(m,8));

        System.out.println(Arrays.binarySearch(m,9));
        Set<Integer> set = new HashSet<>();
        Map<Character, Integer> re = new HashMap<>();
        for(int i = 0; i < str.length(); i++) {
            char key = str.charAt(i);
            re.put(key, re.getOrDefault(key, 0) + 1);
        }
        System.out.println();
        //第一次存
        if(map.isEmpty()) {
            map = re;
            return true;
        }

        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();
            if(val != re.get(key)) {
                map = re;
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        boolean re = new Solution05x().isNumber("e9");
        System.out.println(re);
    }
}