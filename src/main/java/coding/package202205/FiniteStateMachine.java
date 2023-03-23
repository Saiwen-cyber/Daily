package coding.package202205;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author fang
 */
public class FiniteStateMachine {
    /**
     * 状态信息 当前处理到字符串的哪个部分
     */
    enum StateType {
        SIGN,
        INT_NUM,
        DOT_NUM,
        NUM_DOT,
        FLOAT_NUM,
        LETTER_E,
        INDEX_SIGN,
        INDEX_NUM,
        NOTHING
    }

    enum CharType {
        NUMBER,
        DOT,
        E,
        SIGN,
        NOTHING
    }

    Map<StateType, Map<CharType, StateType>> transfer = new HashMap<>();
    Set<StateType> endState = new HashSet<>();

    public void init() {
        Map<CharType, StateType> signMap = new HashMap<>();
        //key 为加了xx， value为加了xx之后到达的状态
        signMap.put(CharType.NUMBER, StateType.INT_NUM);
        signMap.put(CharType.DOT, StateType.DOT_NUM);

        Map<CharType, StateType> intNumMap = new HashMap<>();
        intNumMap.put(CharType.NUMBER, StateType.INT_NUM);
        intNumMap.put(CharType.DOT, StateType.NUM_DOT);
        intNumMap.put(CharType.E, StateType.LETTER_E);

        Map<CharType, StateType> dotNumMap = new HashMap<>();
        dotNumMap.put(CharType.NUMBER, StateType.FLOAT_NUM);

        Map<CharType, StateType> numDotMap = new HashMap<>();
        numDotMap.put(CharType.NUMBER, StateType.FLOAT_NUM);
        numDotMap.put(CharType.E, StateType.LETTER_E);

        Map<CharType, StateType> floatNumMap = new HashMap<>();
        floatNumMap.put(CharType.NUMBER, StateType.FLOAT_NUM);
        floatNumMap.put(CharType.E, StateType.LETTER_E);


        Map<CharType, StateType> eMap = new HashMap<>();
        eMap.put(CharType.NUMBER, StateType.INDEX_NUM);
        eMap.put(CharType.SIGN, StateType.INDEX_SIGN);

        Map<CharType, StateType> indexSignMap = new HashMap<>();
        indexSignMap.put(CharType.NUMBER, StateType.INDEX_NUM);


        Map<CharType, StateType> indexNumMap = new HashMap<>();
        indexNumMap.put(CharType.NUMBER, StateType.INDEX_NUM);

        transfer.put(StateType.SIGN, signMap);
        transfer.put(StateType.INT_NUM, intNumMap);
        transfer.put(StateType.DOT_NUM, dotNumMap);
        transfer.put(StateType.NUM_DOT, numDotMap);
        transfer.put(StateType.FLOAT_NUM, floatNumMap);
        transfer.put(StateType.LETTER_E, eMap);
        transfer.put(StateType.INDEX_SIGN, indexSignMap);
        transfer.put(StateType.INDEX_NUM, indexNumMap);

        endState.add(StateType.INT_NUM);
        endState.add(StateType.NUM_DOT);
        endState.add(StateType.FLOAT_NUM);
        endState.add(StateType.INDEX_NUM);
    }

    public CharType getCharType(char ch) {
        if (ch == '+' || ch == '-') {
            return CharType.SIGN;
        } else if (ch == 'e' || ch == 'E') {
            return CharType.E;
        } else if (ch == '.') {
            return CharType.DOT;
        } else if (ch - '0' >= 0 && ch - '0' < 10) {
            return CharType.NUMBER;
        }
        return CharType.NOTHING;
    }

    public boolean isNumber(String s) {
        s = s.trim();
        if("".equals(s)) {
            return false;
        }
        init();
        StateType state = getInitialState(s.charAt(0));
        if (state.equals(StateType.NOTHING)) {
            return false;
        }
        for (int i = 1; i < s.length(); i++) {
            CharType charType = getCharType(s.charAt(i));
            state = transfer.get(state).get(charType);
            if(state.equals(StateType.NOTHING)) {
                return false;
            }
        }
        return state.equals(StateType.INT_NUM)
                ||  state.equals(StateType.FLOAT_NUM)
                || state.equals(StateType.INDEX_NUM)
                || state.equals(StateType.NUM_DOT);    }

    StateType getInitialState(char ch) {
        if (getCharType(ch).equals(CharType.SIGN)) {
            return StateType.SIGN;
        } else if (getCharType(ch).equals(CharType.DOT)) {
            return StateType.DOT_NUM;
        } else if (getCharType(ch).equals(CharType.NUMBER)) {
            return StateType.INT_NUM;
        } else {
            return StateType.NOTHING;
        }
    }

    public static void main(String[] args) {
        boolean number = new FiniteStateMachine().isNumber(".e1");
        System.out.println(number);
    }
}
