#coin
Create a basic invoicing service that allows users to create invoices and pay in digital currency

Requirements:
* A User must be able to type in an amount and the service would generate an Invoice
* A new digital currency address must be created for each invoice
* The User can send funds to the address and have the amount deducted from the Invoice total
* If the User pays an amount into the wallet address specified, it would need to update the total amount owed on the Invoice.
* Invoices would have three states:
    * Expired 
    * Partially paid
    * Paid

## Tech stack
* JDK 11
* Spring-boot 2
* H2 database
* maven
* BTC address generation

## Server port
* 8080

## How to run
```
mvn package && java -jar target/coin-0.0.1-SNAPSHOT.jar
```

## USAGE

### Create Invoice 
* Request
    ```
    curl -X POST http://localhost:8080/invoices/9.088
    ```
* Response
    ```
    {
        "dca": "19wBDoN7rJ9UcNrmWzoN89DqjXERa6rran", // dca = Digital currency address
        "amount": 9.09,
        "paidAmount": 0.0,
        "balance": 9.09,
        "status": null,
        "creationTime": "2019-10-25T01:29:30.437+0000"
    }
    ```
    
### Create Payment 
* Request
    ```
    curl -X POST http://localhost:8080/payments/9.088 \
    -d '{
    	"amount" : 1.114,
    	"dca" : "19wBDoN7rJ9UcNrmWzoN89DqjXERa6rran",
    	"type": "CC"
    }'
    
    ```
* Response
    ```
    {
        "id": "40289d266e0092a7016e009324270000",
        "amount": 1.11,
        "dca": "19wBDoN7rJ9UcNrmWzoN89DqjXERa6rran",
        "paymentType": "CC"
    }
    ```
   
### Get Invoice 
* Request
   ```
   curl http://localhost:8080/invoices/19wBDoN7rJ9UcNrmWzoN89DqjXERa6rran
   ```
* Response
   ```
   {
       "dca": "19wBDoN7rJ9UcNrmWzoN89DqjXERa6rran",
       "amount": 9.09,
       "paidAmount": 1.11,
       "balance": 7.98,
       "status": "PARTIALLY_PAID",
       "creationTime": "2019-10-25T01:29:30.437+0000"
   }
   ```
 
 ### Get all payments
 * Request
    ```
    curl http://localhost:8080/invoices/19wBDoN7rJ9UcNrmWzoN89DqjXERa6rran/payments
    ```
 * Response
    ```
    [
        {
            "id": "40289d266e0092a7016e009324270000",
            "amount": 1.11,
            "dca": "19wBDoN7rJ9UcNrmWzoN89DqjXERa6rran",
            "paymentType": "CC"
        },
        {
            "id": "40289d266e0092a7016e0094aef40001",
            "amount": 2.0,
            "dca": "19wBDoN7rJ9UcNrmWzoN89DqjXERa6rran",
            "paymentType": "CC"
        }
    ]
    ```
        
