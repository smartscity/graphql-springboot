package com.byteperceptions.graphqldemo.dataloader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

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
public class DataLoadApplicationContextListener implements ApplicationListener<ContextRefreshedEvent>{

    private DataLoader dataLoader;
    private final ApplicationContext applicationContext;

    public DataLoadApplicationContextListener(ApplicationContext applicationContext, DataLoader dataLoader){
        this.applicationContext = applicationContext;
        this.dataLoader = dataLoader;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        dataLoader.loadData();
    }
}
