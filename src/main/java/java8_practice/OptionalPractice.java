package java8_practice;

import java8_practice.trade.Trader;
import java8_practice.trade.Transaction;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Annie Fang
 */
public class OptionalPractice {
//    public int readProperties(Properties props, String name) {
//        String val = props.getProperty(name);
//        if (val != null) {
//            try {
//                int i = Integer.parseInt(val);
//                if (i > 0) {
//                    return i;
//                }
//            } catch (NumberFormatException nfe) {
//                return 0;
//            }
//        }
//        return 0;
//    }

    public static void main(String[] args) {
        OptionalPractice entity = new OptionalPractice();
        entity.testUnBoundaryStream();
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactionList = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2011, 300),
                new Transaction(mario, 2011, 300),
                new Transaction(mario, 2011, 300),
                new Transaction(alan, 2011, 300)
        );
        List<Trader> traderList = transactionList.stream()
                .map(Transaction::getTrader)
                .collect(Collectors.toList());

        System.out.println(test1(traderList, transactionList));
        test2(traderList, transactionList);
        test3(traderList, transactionList);
        test4(traderList, transactionList);
        test5(traderList, transactionList);
        test6(traderList, transactionList);
        test7(traderList, transactionList);
        test8(traderList, transactionList);

    }

    private static Optional<Transaction> test8(List<Trader> traderList, List<Transaction> transactionList) {
        transactionList.stream().min(Comparator.comparing(Transaction::getValue));
        return transactionList.stream()
                .reduce((transaction, transaction2) -> transaction.getValue() > transaction2.getValue() ? transaction2 : transaction);

    }

    private static Optional<Integer> test7(List<Trader> traderList, List<Transaction> transactionList) {
        return transactionList.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
    }

    private static void test6(List<Trader> traderList, List<Transaction> transactionList) {
//        transactionList.stream()
//                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
//                .forEach(val -> System.out.println(val.getValue()));

        transactionList.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    private static void test5(List<Trader> traderList, List<Transaction> transactionList) {
        //anyMatch
        traderList.stream().anyMatch(trader -> "Milan".equals(trader.getCity()));
//        return traderList.stream().filter(trader -> "Milan".equals(trader.getCity())).findAny();
    }

    private static String test4(List<Trader> traderList, List<Transaction> transactionList) {
        StringBuilder sb = new StringBuilder();
        traderList.stream()
                .map(Trader::getName)
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
        return traderList.stream()
                .map(Trader::getName)
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
    }

    private static List<Trader> test3(List<Trader> traderList, List<Transaction> transactionList) {
        return traderList.stream()
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
    }

    private static List<String> test2(List<Trader> traderList, List<Transaction> transactionList) {
        return traderList
                .stream()
                .map(Trader::getCity)
                .distinct().collect(Collectors.toList());
    }

    private static List<Transaction> test1(List<Trader> traderList, List<Transaction> transactionList) {
        return transactionList
                .stream()
                .filter(transaction -> transaction.getYear() == 2011)
                //TODO 按照交易额排序
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
    }

    public int readProperties(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(val -> {
                    int num;
                    try {
                        num = Integer.parseInt(val);
                        return Optional.of(num);
                    } catch (NumberFormatException nfe) {
                        return Optional.empty();
                    }
                })
                .filter(num -> num > 0)
                .orElse(0);
    }

    public void testUnBoundaryStream() {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(0)
                .forEach(e -> System.out.println(e[0]));

        IntStream.generate(new IntSupplier() {
            private int cur = 1;
            private int pre = 0;

            @Override
            public int getAsInt() {
                int oldPre = pre;
                int newCur = this.cur + this.pre;
                pre = cur;
                cur = newCur;
                return oldPre;
            }
        })
                .limit(10)
                .forEach(System.out::println);
    }


}