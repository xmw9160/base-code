package com.xmw.java8.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mingwei.xia
 * @date 2018/6/22 11:14
 * @since V1.0
 */
public class StreamTest01 {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        List<String> threeHighCaloricDishNames = menu.stream() //从 menu 获得流（菜肴列表）
                .filter(dish -> dish.getCalories() > 300) //首先选出高热量的菜肴
                .map(Dish::getName) // 获取菜名
                .limit(3)           // 只选择头三个
                .collect(Collectors.toList());  // 将结果保存到另一个list中
//        System.out.println(threeHighCaloricDishNames);

        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
//        s.forEach(System.out::println);
//        s.forEach(System.out::println);


        // 1
//        List<String> names = new ArrayList<>();
//        for(Dish d: menu){
//            names.add(d.getName());
//        }

        // 2
//        List<String> names = new ArrayList<>();
//        Iterator<Dish> iterator = menu.iterator();
//        while(iterator.hasNext()) {
//            Dish d = iterator.next();
//            names.add(d.getName());
//        }

        // 3
//        List<String> names = menu.stream().map(Dish::getName).collect(toList());

        List<String> names =
                menu.stream()
                        .filter(d -> {
//                            System.out.println("filtering " + d.getName());
                            return d.getCalories() > 300;
                        })
                        .map(d -> {
//                            System.out.println("mapping " + d.getName());
                            return d.getName();
                        })
//                        .sorted()
                        .limit(3)
                        .collect(Collectors.toList()); // 终端操作, 触发上续代码执行
//        System.out.println(names);

//        Thread thread = new Thread(() -> System.out.println("test"));
//        thread.start();
//        thread.start();  //java.lang.IllegalThreadStateException


        // 5-使用流
        List<Dish> list = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());

        List<Integer> numbers = Arrays.asList(1, 2, 4, 5, 6, 4, 3, 1);
        // numbers.stream().filter(i -> i % 2 == 1).forEach(System.out::println);

        // 过滤 filter
        // 阶段 limit
        // 跳过元素 skip
//        menu.stream()
//                .filter(d -> d.getCalories() > 300)
//                .skip(2).forEach(dish -> System.out.println(dish.getName()));
        //.collect(toList());

        // 获取菜单名的长度
        menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());

        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());

        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);

        //一言以蔽之， flatmap 方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流
        List<String> uniqueCharacters =
                words.stream()
                        .map(w -> w.split(""))
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(Collectors.toList());

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = someNumbers.stream().reduce(0, (a, b) -> a + b);
//        System.out.println(sum);
        sum = numbers.stream().reduce(0, Integer::sum);
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        Optional<Integer> min = numbers.stream().reduce(Integer::min);

        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
//        System.out.println(shortMenu);

        numbers = Arrays.asList(2, 3, 4, 5);
        numbers.stream()
                .map(x -> x + 17)
                .filter(x -> x % 2 == 0)
                .limit(3)
                .forEach(System.out::println);
    }
}
