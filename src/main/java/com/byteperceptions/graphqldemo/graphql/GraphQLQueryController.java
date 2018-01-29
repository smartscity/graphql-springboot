package com.byteperceptions.graphqldemo.graphql;

import com.byteperceptions.graphqldemo.customer.AllCustomersDataFetcher;
import com.byteperceptions.graphqldemo.customer.SingleCustomerDataFetcher;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;

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
@RestController
public class GraphQLQueryController {


    @Autowired
    private AllCustomersDataFetcher allCustomersDataFetcher;

    @Autowired
    private SingleCustomerDataFetcher singleCustomerDataFetcher;

    private GraphQL graphQL;

    /**
     * 初始化 graphql
     * @throws IOException
     */
    @PostConstruct
    public void loadSchema() throws IOException{
        Resource schemaResource = new ClassPathResource("/demo-schema.graphqls");
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(new InputStreamReader(schemaResource.getInputStream()));
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry,runtimeWiring);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private RuntimeWiring buildRuntimeWiring(){
        return RuntimeWiring.newRuntimeWiring().type(("Query"), typeWiring -> typeWiring
                .dataFetcher("allCustomers", allCustomersDataFetcher)
                .dataFetcher("customer", singleCustomerDataFetcher))
                .build();
    }

    @RequestMapping(value = "/graphql", method = RequestMethod.POST)
    public ResponseEntity graphQLQuery(@RequestBody String query){
        ExecutionResult result = graphQL.execute(query);
        if(!result.getErrors().isEmpty()){
            return ResponseEntity.ok(result.getErrors());
        }
        return ResponseEntity.ok(result.getData());
    }

    @RequestMapping(value = "/mutate", method = RequestMethod.POST)
    public ResponseEntity mutate(@RequestBody String mutation){
        ExecutionResult result = graphQL.execute(mutation);
        if(!result.getErrors().isEmpty()){
            return ResponseEntity.ok(result.getErrors());
        }
        return ResponseEntity.ok(result.getData());
    }
}
