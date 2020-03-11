package com.it.dao;


import com.it.domain.Account;

import java.util.List;

/**
 * 帳戶的持久層接口
 */
public interface IAccountDao {


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
     * 用名稱取得帳戶
     * @param name
     * @return 如果有為一個結果就返回 如果沒有就返回null 如果結果集超過一個 就拋異常
     */
    Account findAccountByName(String accountName);
}