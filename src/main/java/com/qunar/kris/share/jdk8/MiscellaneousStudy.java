/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.qunar.kris.share.jdk8;

import com.google.common.base.Throwables;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * 有一些杂七杂八的内容
 *
 * @author gongzuo.zy
 * @version $Id: MiscellaneousStudy.java, v0.1 2017-02-17 22:51  gongzuo.zy Exp $
 */
public class MiscellaneousStudy {

    public static void main(String[] args) {


        System.out.println(String.join(",", "a", "b"));//不用在使用guava的Joinner了

        // 排序 太好用了这个
        //Arrays.sort(people, Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName));

        // 处理文件
        try(Stream<String> lines = Files.lines(new File("").toPath())) {
            Optional<String> passwordEntry = lines.filter(s -> s.contains("password")).findFirst();
        } catch (IOException e) {
            // 用来查询异常堆栈
            Throwable[] throwables = e.getSuppressed();

        }

        // 方便的base64
        Base64.Encoder encoder = Base64.getEncoder();
        Base64.Decoder decoder = Base64.getDecoder();


        // 过滤空的字符串，太方便了实在是
        Stream.of("a", null, "b").filter(Objects::nonNull);

        //用来替代if
        Objects.requireNonNull(",", () -> {throw new RuntimeException("haha");});

        // 更加方便的反射操作
        try {
            Class.forName("").getMethod("main").invoke(null, new String[] {});
            // 方便多了吧
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }

        // 问价操作
        Path homeDirectory = Paths.get("/home/cay");
        //Files.copy(in, path);
        //List<String> lines = Files.readAllLines(path);
        //Files.write(path, content.getBytes(StandardCharsets.UTF_8));
        //Files.write(path, lines, StandardOpenOption.APPEND);
        //Files.createDirectory(path);
        //Files.createFile(path);
        //Files.createDirectories(path);
        //Files.move(fromPath, toPath);
        //Files.delete(path);
        //boolean deleted = Files.deleteIfExists(path);
    }
}
