/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.qunar.kris.share.jdk8;


import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author gongzuo.zy
 * @version $Id: ProgrammingWithLambdaStudy.java, v0.1 2017-02-16 15:14  gongzuo.zy Exp $
 */
public class ProgramLambdaStudy {

    public void composeFunctions() {
        Function<Integer, Integer> time2 = e -> e*2;
        Function<Integer, Integer> sqrt = e -> e*e;
        time2.compose(sqrt).apply(4);
        time2.andThen(sqrt).apply(4);
    }

    // 使用handler用来处理内部异常是个好办法
    public static <T> void doInOrderAsync(Supplier<T> first, Consumer<T> second, Consumer<Throwable> handler) {
        Thread t = new Thread() { public void run() {
            try {
                T result = first.get(); second.accept(result);
            } catch (Throwable t) { handler.accept(t);
            } }
        };
        t.start();
    }


    // 函数作为返回值
    public static Function<String, String> returnFunction() {
        return a -> a.replace("a", "b");
    }

    // 采用这种方式可以延迟执行 supplier内容，提高性能
    public static void info(Logger logger, Supplier<String> message) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(message.get());
        }
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
