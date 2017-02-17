package com.qunar.kris.share.jdk8;

import com.google.common.primitives.Ints;
import lombok.Data;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * lambda表达式
 *
 * @author kris.zhang
 */
public class LambdaStudy {

    // 作为方法的签名
    // 一个FunctionalInterface声明的接口只能有一个接口方法
    @FunctionalInterface
    interface Adder<T> {

        T add(T a, T b);

        /*
         * 我们也可以指定默认的实现
         * 有了默认实现，我们以后就不需要在创建
         * adapter了，不过这种默认方法的实现应该
         * 尽量简单
         */
        default void tip() {
            System.out.println("this is a interface tip");
        }

        // 接口静态方法，可以直接引用
        static void staticUtil() {
            System.out.println("...");
        }
    }

    private static class AdderHelper {
        static <T> T addList(List<T> list, Adder<T> adder) {
            T t = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                t = adder.add(t, list.get(i));
            }
            return t;
        }

        static int add(int a, int b) {
            return a + b;
        }
    }


    public static void whyLambda() {
        /*
         * 以前的java排序
         */
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        /*
         * 这里需要注入一个comparator的实现
         */
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        /*
         * 现在你只需要一句话
         */
        Collections.sort(names, (a,b) -> b.compareTo(a));

        /*
         * 或者，我们自定义一个加法器
         */
        System.out.println(AdderHelper.addList(names, (a, b) -> a + b));

        // lambda表达式是需要进行异常捕获的，也可以使用callable
        Runnable runnableThrowException= () -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Callable<Void> callable = () -> {Thread.sleep(10);return null;};

        /*
         * 大招来了，我们可以直接引用对象或者类的某一个方法
         * 函数签名一样即可
         */
        Adder<Integer> adder = AdderHelper::add;//也可以使用对象::add来因为非静态
        System.out.println(AdderHelper.addList(Ints.asList(1, 2, 3), adder));

        /*
         * 这个new是构造方法的意思，他的函数签名对应接口的函数签名
         * 一切都很自然。这种工厂方法，你看不到工厂的实现。。。。
         */
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person);

        /*
         * 在lambda表达式中访问外层作用域和老版本的匿名对象中的方式很相似。
         * 你可以直接访问标记了final的外层局部变量，或者实例的字段以及静态变量
         *
         * 后面有通过stream api 更加美妙的实现
         */
        int x = 10;
        System.out.println(AdderHelper.addList(names, (a, b) -> a + b + x));

    }

    /**
     * 下面一个好玩的来了，你会找到c++的感觉
     */
    @Data
    static class Person {

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        private void greet() {
            System.out.println("hello");
        }

        private String firstName;
        private String lastName;
    }

    static class Man extends Person {

        Man(String firstName, String lastName) {
            super(firstName, lastName);
        }

        void sayGreet() {
            // 可以使用super::greet 引用父类的函数
            Thread t1 = new Thread(super::greet);
            t1.start();

            // 使用this::manSayGreet访问
            Thread t2 = new Thread(this::manSayGreet);
            t2.start();
        }

        void manSayGreet() {
            System.out.println("HELLO");
        }
    }

    @FunctionalInterface
    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }

    public static void main(String[] args) {

        // 以下是常见的函数接口

        /**
            接口                 返回     参数
            --------------------------------
            Supplier<T>         T        0
            BooleanSupplier     bool     0
            IntSupplier         int      0
            DoubleSupplier      double   0
            LongSupplier        double   0
            --------------------------------
            Consumer<T>         0        T
            IntConsumer         0        int
            DoubleConsumer      0        double
            BooleanConsumer     0        bool
            ObjDoubleConsumer   0        T,double
            ObjIntConsumer<T>   0        T,int
            ObjLongConsumer<T>  0        T,long
            BiConsumer<T, U>    0        TU
            --------------------------------
            Predicate<T>        bool     T
            IntPredicate<T>     bool     int
            LongPredicate<T>    bool     long
            DoublePredicate<T>  bool     double
            BiPredicate<T, U>   bool     TU
            --------------------------------
            Function<T, R>      R        T
            BiFunction<T, U, R> R        TU
            ToIntFunction<T>    int      T
            ToLongFunction<T>   long     T
            ToDoubleFunction<T> double   T
            IntFunction<R>      R        int
            LongFunction<R>     R        long
            DoubleFunction<R>   R        double
            XTOYFunction        Y        X  (x,y:int,long,double)
            UnaryOperator<T>    T        T
            BinaryOperator<T>   T        TT
            XUnaryOperator      X        X  (x,y:int,long,double)
            XBinaryOperator     X        xX (x,y:int,long,double)
            --------------------------------
            Runnable            0        0
        */
    }
}
