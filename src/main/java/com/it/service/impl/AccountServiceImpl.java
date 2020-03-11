package com.it.service.impl;

import com.it.dao.IAccountDao;
import com.it.domain.Account;
import com.it.service.IAccountService;
import com.it.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;

/**
 * 帳戶的業務層實現類
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;


    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    public void deleteAccount(Integer accountId) {
        accountDao.deleteAccount(accountId);
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        System.out.println("transfer......");
        //1.根據名稱查詢轉出帳戶
        Account source = accountDao.findAccountByName(sourceName);
        //2.根據名稱查詢轉入帳戶
        Account target = accountDao.findAccountByName(targetName);
        //3.轉出帳戶扣錢
        source.setMoney(source.getMoney()-money);
        //4.轉入帳戶加錢
        int i=1/0;
        target.setMoney(target.getMoney()+money);
        //5.更新轉出帳戶
        accountDao.updateAccount(source);
        //int i=1/0;
        //6.更新轉入帳戶
        accountDao.updateAccount(target);
    }
}
