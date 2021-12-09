package practice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fang
 */
public class ChConverse extends ConverseUtil{
    @Override
    public List<char[]> converse(Object obj) {
        char ch = ((String)obj).charAt(0);
        List<char[]> list = new ArrayList<char[]>();
        char[] binNum = IntConverse.bin((int) ch,8);
        char[] hexNum = this.hex(binNum,2);
        list.add(binNum);
        list.add(hexNum);
        return list;
    }
}
