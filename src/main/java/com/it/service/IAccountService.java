package com.it.service;

import com.it.domain.Account;

import java.util.List;

/**
 * 帳戶的業務層接口
 */
public interface IAccountService {

    /**
     * 查詢所有
     * @return
     */

    List<Account> findAllAccount();

    /**
     * 查詢一個
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 保存操作
     */
    void saveAccount(Account account);

    /**
     * 更新操作
     */
    void updateAccount(Account account);

    /**
     * 刪除操作
     */
    void deleteAccount(Integer accountId);

    /**
     * 轉帳
     * @param sourceName 轉出帳戶
     * @param targetName 轉入帳戶
     * @param money 金額
     */
    void transfer(String sourceName, String targetName, Float money);

    //void test();
}
