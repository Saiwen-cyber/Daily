package coding.test.mi;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<String, Long> map = new HashMap<>();
        Map<Long, Integer> re = new HashMap<>();
        List<String> stringList = new ArrayList<>();
        while (true) {
            String line = in.nextLine();
            if (line.equals("END")) {
                break;
            }
            stringList.add(line);
            String[] strs = line.split("#");
            int mapKey = Integer.parseInt(strs[0]);
            Long mapVal = converse(mapKey, strs[1]);
            map.put(line, mapVal);
            re.put(mapVal, re.getOrDefault(mapVal, 0) + 1);
        }
        boolean flag = false;
        for (int i = 0; i < stringList.size(); i++) {
            String line = stringList.get(i);
            Long val = map.get(line);
            if (re.get(val) == 1 && stringList.size() != 1) {
                System.out.println(line);
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("NONE");
        }

    }

    private static Long converse(int key, String str) {
        return Long.parseLong(str, key);
    }
}
