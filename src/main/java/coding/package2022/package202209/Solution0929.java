package coding.package2022.package202209;

public class Solution0929 {
    public boolean isFlipedString(String s1, String s2) {
        String str2 = s2 + s2;
        int index = str2.indexOf(s1);
        if (index < 0) {
            return false;
        }
        String left = str2.substring(index + s1.length());
        String right = str2.substring(0, index);
        return (left + right).equals(s1);
    }
}