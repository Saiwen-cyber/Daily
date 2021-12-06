package practice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fang
 */
public class ChConverse extends ConverseUtil{
    @Override
    public List<char[]> converse(Object obj) {
        char ch = (char)obj;
        int num = DBUtils.selectAscii(ch);
        List<char[]> list = new ArrayList<char[]>();
        list = new IntConverse().converse(num);
        return list;
    }
}
