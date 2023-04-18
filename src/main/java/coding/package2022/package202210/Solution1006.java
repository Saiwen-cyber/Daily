package coding.package2022.package202210;


import java.util.*;

/**
 * @author ASUS
 */
public class Solution1006 {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (String line : cpdomains) {
            String[] strs = line.split(" ");
            while (!"".equals(strs[1])) {
                int val = map.getOrDefault(strs[1], 0) + Integer.parseInt(strs[0]);
                map.put(strs[1], val);

                strs[1] = strs[1].contains(".") ? strs[1].substring(strs[1].indexOf(".") + 1) : "";
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            StringBuilder sb = new StringBuilder();
            int count = entry.getValue();
            String domain = entry.getKey();

            sb.append(count).append(" ").append(domain);
            list.add(sb.toString());
        }
        return list;
    }

    public static void main(String[] args) {
        Solution1006 so = new Solution1006();
        String[] s = new String[]{"9001 discuss.leetcode.com"};
        so.subdomainVisits(s);
    }
}