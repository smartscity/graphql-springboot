package com.byteperceptions.graphqldemo.dataloader;

import com.byteperceptions.graphqldemo.account.Account;
import com.byteperceptions.graphqldemo.account.AccountRepository;
import com.byteperceptions.graphqldemo.customer.Customer;
import com.byteperceptions.graphqldemo.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * <B>文件名称：</B><BR>
 * <B>文件描述：</B><BR>
 * <BR>
 * <B>版权声明：</B>(C)2016-2018<BR>
 * <B>公司部门：</B> CBG<BR>
 * <B>创建时间：</B><BR>
 *
 * @author liyunlong liyunlong@yingu.com
 * @version 1.0
 **/
@Component
public class DataLoader {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;

    public void loadData() {
        Customer bobJohnson = createCustomer("三", "张");
        Customer sallyBrown = createCustomer("四", "李");

        Account bobsCheckingAccount = createAccount(bobJohnson, "张三's Checking Account", 2100.50);
        Account bobsSavingAccount = createAccount(bobJohnson, "张三's Savings Account", 325.50);
        Account sallysCheckingAccount = createAccount(sallyBrown, "李四's Checking Account", 500.50);
        Account sallysSavingAccount = createAccount(sallyBrown, "李四's Savings Account", 5555.50);
    }

    private Customer createCustomer(String firstName, String lastName){
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAccounts(new ArrayList<>());
        return customerRepository.save(customer);
    }

    private Account createAccount(Customer customer, String accountName, double initialBalance){
        Account account = new Account();
        account.setName(accountName);
        account.setBalance(initialBalance);
        account.setCustomer(customer);
        account = accountRepository.save(account);
        customer.getAccounts().add(account);
        customerRepository.save(customer);
        return account;
    }
}
