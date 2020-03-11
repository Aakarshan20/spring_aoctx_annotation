package com.it.utils;

import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 和事務管理相關的工具類 包含了開啟事務 提交事務 回滾事務 與釋放連接
 */
@Component("txManager")
@Aspect
public class TransactionManager {
    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* com.it.service.impl.*.*(..))")
    private void pt1(){}

    /**
     * 開啟事務
     */
    @Before("pt1()")
    public void beginTransaction(){
        try{
            connectionUtils.getThreadConnection().setAutoCommit(false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 提交事務
     */
    @AfterReturning("pt1()")
    public void commit(){
        try{
            connectionUtils.getThreadConnection().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 回滾事務
     */
    @AfterThrowing("pt1()")
    public void rollback(){
        try{
            connectionUtils.getThreadConnection().rollback();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 釋放連接
     */
    @After("pt1()")
    public void release(){
        try{
            connectionUtils.getThreadConnection().close();//不是真的關了 是還回連接池中
            connectionUtils.removeConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
