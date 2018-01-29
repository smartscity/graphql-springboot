package com.byteperceptions.graphqldemo.customer;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

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
public class SaveCustomerDataFetcher implements DataFetcher<Customer> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer get(DataFetchingEnvironment environment) {
        Map arguments = environment.getArguments();
        Customer customer = new Customer();
        if(arguments.get("id") != null){
            customer.setId(Long.parseLong(arguments.get("id").toString()));
        }
        customer.setFirstName(arguments.get("firstName").toString());
        customer.setLastName(arguments.get("lastName").toString());

        if(arguments.get("accounts") != null){
            ArrayList array = (ArrayList) arguments.get("accounts");
            for(Object o: array){
                Map m = (Map)o;
                String accountName = m.get("accountName").toString();
            }
        }

        customer = customerRepository.save(customer);
        return customer;
    }
}
