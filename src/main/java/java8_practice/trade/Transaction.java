package java8_practice.trade;

import lombok.Data;

/**
 * @author Annie Fang
 * @create 2022/11/25 7:51
 */
@Data
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;
}
