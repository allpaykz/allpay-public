### Описание взаимодействия для оплаты через терминалы

Адрес на [бете](http://beta.allpay.kz/allpay-public-soap/transaction-management/v1.1?wsdl) и [боевая](http://mfs.allpay.kz/allpay-public-soap/transaction-management/v1.1?wsdl)

#### 1.1.Пример запроса на проверку возможности оплаты(payForGoodsCheck)

Для проверки возможности оплаты, Терминал отправляет запрос проверки состояния абонента, содержащий следующую информацию:

- номер ID терминала на который должна быть произведена оплата (техническое имя поля: terminalId);
- номер мобильного телефона (техническое имя поля: toUserName).
- сумма. Опциональное поле (техническое имя поля: amount)

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns="http://www.allpay.kz/mfs/soap/TransactionManagement/1.1">
   <soapenv:Header/>
   <soapenv:Body>
      <ns:payForGoodsCheck>
         <!--Optional:-->
         <PayForGoodsCheckRequest>
            <header>
               <!--Optional:-->
               <lang>ru</lang>
               <timestamp>?</timestamp>
               <requester>?</requester>
               <!--Optional:-->
               <token>?</token>
            </header>
            <toUserName>?</toUserName>
            <terminalId>?</terminalId>
            <!--Optional:-->
            <amount>?</amount>
         </PayForGoodsCheckRequest>
      </ns:payForGoodsCheck>
   </soapenv:Body>
</soapenv:Envelope>
```
#### 1.2 Пример ответа системы на запрос чек

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns="http://www.allpay.kz/mfs/soap/TransactionManagement/1.1">
   <soapenv:Header/>
   <soapenv:Body>
      <ns:payForGoodsCheckResponse>
         <!--Optional:-->
         <return>
            <header>
               <userMessage>?</userMessage>
               <developerMessage>?</developerMessage>
               <status>?</status>
               <timestamp>?</timestamp>
            </header>
         </return>
      </ns:payForGoodsCheckResponse>
   </soapenv:Body>
</soapenv:Envelope>
```

Либо ответом будет soapFault с описание ошибки.

#### 2.1. Пример запроса оплаты(payForGoodsPay)

- номер ID терминала на который должна быть произведена оплата (техническое имя поля: terminalId);
- номер мобильного телефона (техническое имя поля: toUserName).
- сумма. Опциональное поле (техническое имя поля: amount)
- дата платежа(техническое имя поля: valueDate)
- номер транзакции в системе агента(техническое имя поля: rrn)

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns="http://www.allpay.kz/mfs/soap/TransactionManagement/1.1">
   <soapenv:Header/>
   <soapenv:Body>
      <ns:payForGoodsPay>
         <!--Optional:-->
         <PayForGoodsPayRequest>
            <header>
               <!--Optional:-->
               <lang>ru</lang>
               <timestamp>?</timestamp>
               <requester>?</requester>
               <!--Optional:-->
               <token>?</token>
            </header>
            <toUserName>?</toUserName>
            <amount>?</amount>
            <terminalId>?</terminalId>
            <!--Optional:-->
            <valueDate>?</valueDate>
            <rrn>?</rrn>
         </PayForGoodsPayRequest>
      </ns:payForGoodsPay>
   </soapenv:Body>
</soapenv:Envelope>
```


#### 2.2. Пример  ответ системы на передачу оплаты(payForGoodsPay)

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns="http://www.allpay.kz/mfs/soap/TransactionManagement/1.1">
   <soapenv:Header/>
   <soapenv:Body>
      <ns:payForGoodsPayResponse>
         <!--Optional:-->
         <return>
            <header>
               <userMessage>?</userMessage>
               <developerMessage>?</developerMessage>
               <status>?</status>
               <timestamp>?</timestamp>
            </header>
            <terminalPaymentTransactionStatus>?</terminalPaymentTransactionStatus>
            <!--Optional:-->
            <reason>?</reason>
         </return>
      </ns:payForGoodsPayResponse>
   </soapenv:Body>
</soapenv:Envelope>
```

Либо ответом будет soapFault с описание ошибки.
