package com.qunar.kris.share.jdk8;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * 强大的stream api create->operation->collect
 *
 * @author kris.zhang
 */
public class StreamStudy {

    private static class Person {
        String id;
        String name;

        Person() {
        }

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        private String firstName;
        private String lastName;

        /**
         * Getter method for property id.
         *
         * @return property value of id
         */
        public String getId() {
            return id;
        }

        /**
         * Setter method for property id.
         *
         * @param id value to be assigned to property id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * Getter method for property name.
         *
         * @return property value of name
         */
        public String getName() {
            return name;
        }

        /**
         * Setter method for property name.
         *
         * @param name value to be assigned to property name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Getter method for property firstName.
         *
         * @return property value of firstName
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         * Setter method for property firstName.
         *
         * @param firstName value to be assigned to property firstName
         */
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        /**
         * Getter method for property lastName.
         *
         * @return property value of lastName
         */
        public String getLastName() {
            return lastName;
        }

        /**
         * Setter method for property lastName.
         *
         * @param lastName value to be assigned to property lastName
         */
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    // 创建stream
    public static void create() {
        // 可以通过stream构建，集合
        Stream<String> song = Stream.of("gently", "down", "the", "stream");

        // 空
        song = Stream.empty();

        // 通过generate构建
        song = Stream.generate(()-> "");

        // 针对集合类做流式处理
        List<String> list = Lists.newArrayList("abc", "def");

        // 自定义构建stream
        Stream<Person> personStream = Stream.<Person>builder()
                .add(new Person("", ""))
                .add(new Person("", ""))
                .build();

        Stream<Person> p = Stream.generate(Person::new);
    }

    // 变换，重点
    public static void trasformer() {

        // 针对集合类做流式处理
        List<String> list = Lists.newArrayList("abc", "def");

        // 总和
        System.out.println(list.stream().count());

        // filter过滤掉某些内容
        System.out.println(list.stream().filter(p -> p.contains("b")).count());

        // 排序
        list.stream().sorted().forEach(System.out::println);

        // 对应map操作 flatMap，将多stream进行合并
        list.stream().map(String::length).forEach(System.out::println);
        list.stream().map(String::toUpperCase).sorted().forEach(System.out::println);

        // 流合并
        Stream.concat(Stream.of("a"), Stream.of("b"));

        // 流去重
        Stream.of("merrily", "merrily", "merrily", "gently").distinct();

        // 跳过是个记录
        list.stream().skip(10);

        // 只要有一个匹配 就返回true
        System.out.println(list.stream().anyMatch(s -> s.startsWith("a")));

        // 必须所有都匹配
        System.out.println(list.stream().allMatch(s -> s.startsWith("a")));

        // 不能有一个匹配的
        System.out.println(list.stream().noneMatch(s -> s.startsWith("a")));

        // 只输出一个元素
        list.stream().limit(1).forEach(System.out::println);

        // 这个reduce就是我们说的加法器，将所有数相加
        list.stream().reduce((s1, s2) -> s1 + s2);

        // 最大最小，返回optional
        Stream.of(1,2,3).max(Integer::compare);
        Stream.of(1,2,3).min(Integer::compare);

        // 提供多线程并行的流
        list.parallelStream();

    }

    // 元类型
    public static void primitive() {
        IntStream zeroToNinetyNine = IntStream.range(0, 100); // Upper bound is excluded
        IntStream zeroToHundred = IntStream.rangeClosed(0, 100); // Upper bound is included
        IntStream stream = IntStream.of(1, 1, 2, 3, 5);
    }

    // 收口主要是使用collector类
    public static void collect() {

        List<Person> peoples = Lists.newArrayList();
        List<Locale> locales = Lists.newArrayList();

        // 转换 to map list set
        Stream.of(1,2,3).collect(Collectors.toList());
        Stream.of(1,2,3).collect(toSet());
        Map<String, String> idToName = peoples.stream().collect(Collectors.toMap(Person::getId, Person::getName));
        Map<String, String> concurrentMap = peoples.stream().collect(Collectors.toConcurrentMap(Person::getId, Person::getName));

        // join组合
        Stream.of("a","b").collect(Collectors.joining(","));

        // 总和
        Stream.of("a","b").collect(Collectors.counting());

        // 求最大
        Stream.of(3,2,1).collect(Collectors.maxBy(Integer::compare));

        // 求最小
        Stream.of(3,2,1).collect(Collectors.minBy(Integer::compare));

        // 统计
        Stream.of("a","b").collect(Collectors.summarizingInt(String::length));

        // 求平均
        Stream.of("a","b").collect(Collectors.averagingInt(String::length));

        // 分组
        Map<String, Set<Locale>> countryToLocaleSet = locales.stream().collect(groupingBy(Locale::getCountry, toSet()));

        // 分区
        Map<Boolean, List<Person>> partitioned = peoples.stream().collect(partitioningBy(e -> e.getId().length() > 150));

    }

    public static void main(String[] args) {
        System.out.println(        Stream.of("ab","b").collect(Collectors.averagingInt(String::length)));
    }

    public static void optional() {
        Optional<String> optional = Optional.of("");
        optional.orElse("haha");
        optional.isPresent();
        optional.orElseGet(()->"");
        optional.orElseThrow(RuntimeException::new);
        // 如果存在则消费
        optional.ifPresent(System.out::println);
    }

}
