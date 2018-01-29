# Smartscity GraphQL Example
An example of GraphQL endpoint that use Springboot and JPA

## Build 
Run **./gradlew build**

## Run
Run **./gradlew bootRun**


## access 
**POST http://localhost:8080/graphql**

## Request body POST
 ```
 {
 	customer (id: "1") {
 		id
 		firstName
 		lastName
 		accounts {
 			id
 			name
 			balance

 		}
 	}
 }
```

 ## Response Body 
 ```json
 {
     "customer": {
         "id": "1",
         "firstName": "张三",
         "lastName": "Johnson",
         "accounts": [
             {
                 "id": "1",
                 "name": "张三's Checking Account",
                 "balance": 2100.5
             },
             {
                 "id": "2",
                 "name": "张三's Savings Account",
                 "balance": 325.5
             }
         ]
     }
 }
 ```




## Request  Update/Create 
**http://localhost:8080/mutate** 
 ```
 mutation M{
    first : saveCoach(firstName : "三", lastName : "张"){
        id
        firstName
        lastName
    },
    second : saveCoach(id : "22", firstName : "四", lastName : "李", accounts : [{accountId: "33", customerId: "22", accountName:"李四 Checking"},{accountId: "34", customerId: "22", accountName:"李四 Saving"}]){
        id
        firstName
        lastName
        accounts {
            id
            name
        }
    }
 }

 ```
## Response  Update/Create 
 ```json
 {
     "customer": {
         "id": "1",
         "firstName": "三",
         "lastName": "张",
         "accounts": [
             {
                 "id": "1",
                 "name": "张三's Checking Account"
             },
             {
                 "id": "2",
                 "name": "张三's Savings Account"
             }
         ]
     }
 }
 ```

 ## Request  All Customers
  ```
  {
  	allCustomers {
  		id
  		firstName
  		lastName
  		accounts {
  			id
  			name
  			balance

  		}
  	}
  }
  ```

  ## Response All Customers
  ```json
{
    "allCustomers": [
        {
            "id": "1",
            "firstName": "三",
            "lastName": "张",
            "accounts": [
                {
                    "id": "1",
                    "name": "张三's Checking Account",
                    "balance": 2100.5
                },
                {
                    "id": "2",
                    "name": "张三's Savings Account",
                    "balance": 325.5
                }
            ]
        },
        {
            "id": "2",
            "firstName": "四",
            "lastName": "李",
            "accounts": [
                {
                    "id": "3",
                    "name": "李四's Checking Account",
                    "balance": 500.5
                },
                {
                    "id": "4",
                    "name": "李四's Savings Account",
                    "balance": 5555.5
                }
            ]
        }
    ]
}
```
