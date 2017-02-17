/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.qunar.kris.share.jdk8;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * jdk8的内置javascript引擎
 *
 * @author gongzuo.zy
 * @version $Id: JavascriptStudy.java, v0.1 2017-02-17 22:45  gongzuo.zy Exp $
 */
public class JavascriptStudy {
    public static void main(String[] args) throws ScriptException {
        // 使用js引擎能够提升动态性，但是效率是否高呢？在高并发场景下，这种脚本解析执行是否高效
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        Object result = engine.eval("'Hello, World!'.length");
        System.out.println(result);
    }
}
