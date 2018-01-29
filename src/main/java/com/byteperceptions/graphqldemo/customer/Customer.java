package com.byteperceptions.graphqldemo.customer;

import com.byteperceptions.graphqldemo.account.Account;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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
@Entity
@Data
@NoArgsConstructor
@ToString(of={"id"})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Column(nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts;


}
