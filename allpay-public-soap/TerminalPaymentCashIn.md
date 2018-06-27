### Описание взаимодействия для оплаты через терминалы(v2)

Адрес на [бете](http://beta.allpay.kz/allpay-public-soap/cash-in-transaction-management/v1.1?wsdl) и [боевая](http://mfs.allpay.kz/allpay-public-soap/cash-in-transaction-management/v1.1?wsdl)

#### 1.1 Проверка пользователя запрос(checkUserAndValidateCashIn)

Пример запроса:

Входящие параметры:

userName - логин пользователя которого нужно пополнить

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns="http://www.allpay.kz/mfs/soap/CashInTransactionManagement/1.1">
   <soapenv:Header/>
   <soapenv:Body>
      <ns:checkUserAndValidateCashIn>
         <!--Optional:-->
         <CheckUserRequest>
            <header>
               <!--Optional:-->
               <lang>ru</lang>
               <timestamp>?</timestamp>
               <requester>?</requester>
               <!--Optional:-->
               <token>?</token>
            </header>
            <userName>?</userName>
         </CheckUserRequest>
      </ns:checkUserAndValidateCashIn>
   </soapenv:Body>
</soapenv:Envelope>
```

#### 1.2 Проверка пользователя ответ(checkUserAndValidateCashIn)

Блок basicUserInfo - опционален. Если он отстутсвует - значит такого пользователя нет. Запрашивать можно только клиентов. Блок basicUserInfo содержит
identified - является ли пользователь идентифицированным
IIN - ИИН - Индивидуальный идентификационный номер
firstName - имя
lastName - фамилия
patronymic - отчество


```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns="http://www.allpay.kz/mfs/soap/CashInTransactionManagement/1.1">
   <soapenv:Header/>
   <soapenv:Body>
      <ns:checkUserAndValidateCashInResponse>
         <!--Optional:-->
         <return>
            <header>
               <userMessage>?</userMessage>
               <developerMessage>?</developerMessage>
               <status>?</status>
               <timestamp>?</timestamp>
            </header>
            <!--Optional:-->
            <basicUserInfo>
               <identified>?</identified>
               <!--Optional:-->
               <IIN>?</IIN>
               <!--Optional:-->
               <firstName>?</firstName>
               <!--Optional:-->
               <lastName>?</lastName>
               <!--Optional:-->
               <patronymic>?</patronymic>
            </basicUserInfo>
         </return>
      </ns:checkUserAndValidateCashInResponse>
   </soapenv:Body>
</soapenv:Envelope>
```

#### 2.1 Проведение пополнения запрос(createCashInPayment)

Входящие параметры:

- GUID - (он же RRN) номер транзакции терминала (должен быть уникален в разрезе мерчанта. Иными словами rrn + requester - у нас уникальны);
- amount - количество на которое необходимо пополнить
- toUserName - клиент чей счет необходимо пополнить

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns="http://www.allpay.kz/mfs/soap/CashInTransactionManagement/1.1">
   <soapenv:Header/>
   <soapenv:Body>
      <ns:createCashInPayment>
         <!--Optional:-->
         <CashInRequest>
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
            <GUID>?</GUID>
         </CashInRequest>
      </ns:createCashInPayment>
   </soapenv:Body>
</soapenv:Envelope>
```

В остальном аналогично vostokPlatPay

#### 2.2 Проведение пополнения ответ(createCashInPayment)

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns="http://www.allpay.kz/mfs/soap/CashInTransactionManagement/1.1">
   <soapenv:Header/>
   <soapenv:Body>
      <ns:createCashInPaymentResponse>
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
      </ns:createCashInPaymentResponse>
   </soapenv:Body>
</soapenv:Envelope>
```

Ответ аналогичен vostokPlatPayResponse.
