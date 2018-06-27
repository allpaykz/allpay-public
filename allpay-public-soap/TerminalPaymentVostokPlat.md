### Описание взаимодействия для оплаты через терминалы

Адрес на [бете](http://beta.allpay.kz/allpay-public-soap/transaction-management/v1.1?wsdl) и [боевая](http://mfs.allpay.kz/allpay-public-soap/transaction-management/v1.1?wsdl)

#### 1.1.Пример запроса на проверку состояния абонента(vostokPlatCheck)

Для проверки состояния абонента, Терминал отправляет запрос проверки состояния абонента, содержащий следующую информацию:

- номер лицевого счета в ТОО «ЖЭУ ВКО» (техническое имя поля: utilityAccountNumber);
- номер мобильного телефона (техническое имя поля: userName).

Пример запроса:

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns="http://www.allpay.kz/mfs/soap/TransactionManagement/1.1">
   <soapenv:Header/>
   <soapenv:Body>
      <ns:vostokPlatCheck>
         <!--Optional:-->
         <VostokPlatCheckRequest>
            <header>
               <!--Optional:-->
               <lang>ru</lang>
               <timestamp>?</timestamp>
               <requester>?</requester>
               <!--Optional:-->
               <token>?</token>
            </header>
            <userName>?</userName>
            <utilityAccountNumber>?</utilityAccountNumber>
         </VostokPlatCheckRequest>
      </ns:vostokPlatCheck>
   </soapenv:Body>
</soapenv:Envelope>
```

#### 1.2. Пример  ответа системы на запрос  на проверку состояния абонента(vostokPlatCheck)

Положительный ответ содержит следующие данные:

- ФИО владельца квартиры (техническое имя поля: flatOwnerFullName);
- адрес квартиры (техническое имя поля: flatAddress);
- сумма долга или переплаты по состоянию на первый календарный день месяца (техническое имя поля: atMonthStartAmount);
- дата проверки состояния абонента (техническое имя поля: debtOverpaymentDate);
- сумма начисления в текущем месяце (техническое имя поля: monthlyAmount);
- сумма оплаты в текущем месяце (техническое имя поля: currentMonthPaymentAmount);
- сумма к оплате (техническое имя поля: currentAmount);
- максимально допустимая сумма оплаты (техническое имя поля: availableAgentBalance).

<br/>
На основе данных, полученный в ответе Агент должен показать Плательщику в терминале следующую информацию:

№ лицевого счета в ТОО «ЖЭУ ВКО»: [utilityAccountNumber]

Владелец квартиры: [flatOwnerFullName]

Адрес квартиры: [flatAddress]

[Долг/Переплата] на [01.ММ.ГГГГ] г. составляет [atMonthStartAmount] тг. // В поле [Долг/Переплата] отображается либо слово «Долг» если сумма atMonthStartAmount больше нуля, либо слово «Переплата» если сумма atMonthStartAmount меньше или равна нулю. В поле [01.ММ.ГГГГ] отображается дата первого дня месяца debtOverpaymentDate, например 01.02.2018.

Начисление в [ММ.ГГГГ] г. составляет [monthlyAmount] тг. // В поле [ММ.ГГГГ] в текстовом виде отображается текущий месяц согласно дате debtOverpaymentDate, например «февраль 2018».

Оплата в [ММ.ГГГГ] г. составляет [currentMonthPaymentAmount] тг. // В поле [ММ.ГГГГ] в текстовом виде отображается текущий месяц согласно дате debtOverpaymentDate, например «февраль 2018».

Сумма к оплате составляет [currentAmount] тг.

<br/>
Агент должен проверить, чтобы сумма к зачислению на лицевой счет не превышала availableAgentBalance.

<br/>
<br/>
Отрицательный ответ содержит представляет собой стандартный soapFault с текстом, который нужно показать плательщику в поле detail -&gt; userMessage, см. пример далее:

```xml
<soap:Envelope xmlns:soap=&quot;http://schemas.xmlsoap.org/soap/envelope/&quot;>
    <SOAP-ENV:Header xmlns:SOAP-ENV=&quot;http://schemas.xmlsoap.org/soap/envelope/&quot;/>
    <soap:Body>
        <soap:Fault>
            <faultcode>soap:Client</faultcode>
            <faultstring>cy.com.netinfo.netteller.exception.account.AccountNotFoundException</faultstring>
            <detail>
                <developerMessage xmlns=&quot;http://www.allpay.kz/mfs/soap/TransactionManagement/1.1&quot;>cy.com.netinfo.netteller.exception.account.AccountNotFoundException</developerMessage>
                <userMessage xmlns=&quot;http://www.allpay.kz/mfs/soap/TransactionManagement/1.1&quot;>ЛицевойсчетвТОО «ЖЭУВКО» ненайден</userMessage>
            </detail>
        </soap:Fault>
    </soap:Body>
</soap:Envelope>
```

```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
   <SOAP-ENV:Header xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"/>
   <soap:Body>
      <soap:Fault>
         <faultcode>soap:Client</faultcode>
         <faultstring>Header https://allpay.kz certificateNumber not provided</faultstring>
         <detail>
            <developerMessage xmlns="http://www.allpay.kz/mfs/soap/TransactionManagement/1.0">javax.xml.soap.SOAPException</developerMessage>
            <userMessage xmlns="http://www.allpay.kz/mfs/soap/TransactionManagement/1.0">Header https://allpay.kz certificateNumber not provided</userMessage>
            <stackHash xmlns="http://www.allpay.kz/mfs/soap/TransactionManagement/1.0"/>
         </detail>
      </soap:Fault>
   </soap:Body>
</soap:Envelope>
```

Положительный ответ:

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns="http://www.allpay.kz/mfs/soap/TransactionManagement/1.1">
   <soapenv:Header/>
   <soapenv:Body>
      <ns:vostokPlatCheckResponse>
         <!--Optional:-->
         <return>
            <header>
               <userMessage>?</userMessage>
               <developerMessage>?</developerMessage>
               <status>?</status>
               <timestamp>?</timestamp>
            </header>
            <vostokPlatAccountNumberInfo>
               <!--Optional:-->
               <flatOwnerFullName>?</flatOwnerFullName>
               <!--Optional:-->
               <flatAddress>?</flatAddress>
               <!--Optional:-->
               <atMonthStartAmount>?</atMonthStartAmount>
               <!--Optional:-->
               <debtOverpaymentDate>?</debtOverpaymentDate>
               <!--Optional:-->
               <monthlyAmount>?</monthlyAmount>
               <!--Optional:-->
               <currentMonthPaymentAmount>?</currentMonthPaymentAmount>
               <!--Optional:-->
               <currentAmount>?</currentAmount>
            </vostokPlatAccountNumberInfo>
            <availableAgentBalance>?</availableAgentBalance>
         </return>
      </ns:vostokPlatCheckResponse>
   </soapenv:Body>
</soapenv:Envelope>
```

#### 2.1. Пример запроса передачу оплаты(vostokPlatPay)

Для передачи оплаты, выполненной абонентом, система отправляет запрос, содержащий следующую информацию:

 - номер лицевого счета в ТОО «ЖЭУ ВКО» (техническое имя поля: utilityAccountNumber);
 - номер мобильного телефона (техническое имя поля: userName);
 - сумма к зачислению на лицевой счет (техническое имя поля: amount);
 - сумма денежных средств, принятая от плательщика с учетом комиссии (техническое имя поля: fullAmount);
 - номер транзакции терминала (техническое имя поля: rrn, должен быть уникален в разрезе мерчанта. Иными словами rrn + requester - у нас уникальны);
 - дата оплаты (техническое имя поля: valueDate);

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns="http://www.allpay.kz/mfs/soap/TransactionManagement/1.1">
   <soapenv:Header/>
   <soapenv:Body>
      <ns:vostokPlatPay>
         <!--Optional:-->
         <VostokPlatPayRequest>
            <header>
               <!--Optional:-->
               <lang>ru</lang>
               <timestamp>?</timestamp>
               <requester>?</requester>
               <!--Optional:-->
               <token>?</token>
            </header>
            <userName>?</userName>
            <utilityAccountNumber>?</utilityAccountNumber>
            <amount>?</amount>
            <fullAmount>?</fullAmount>
            <rrn>?</rrn>
            <valueDate>?</valueDate>
         </VostokPlatPayRequest>
      </ns:vostokPlatPay>
   </soapenv:Body>
</soapenv:Envelope>
```

Если платеж с парой RRN + requester уже существует в нашей системе - мы возвращаем статус платежа, который уже существует у нас. Если нет - то создает транзакцию в ожидании. Поэтому необходимо повторно запрашивать статус до победного конца.

#### 2.2. Пример  ответ системы на передачу оплаты(vostokPlatPay)

В ответе содержится статус ответа и опциональная причина.

Статус может быть один из:

 - fail(+ reason. Причина здесь только для логов. Тут может быть техническое описание и/или хэш стэктрейса и тд. Это не для пользователя) - платеж завершился с ошибкой, деньги не были переведены и статус никогда не изменится по этому платежу по этому GUID’у. Финальный статус
 - pending - нужно повторить платеж еще раз, пока что находится в обработке. Не финальный статус
 - succeeded - платеж завершился, деньги переведены(или скоро будут переведены и доступны), статус тоже не изменится по этому GUID’у. Финальный статус.

В любом другом случае(например не смогли достучаться до нас, или наш балансер вам ответил статус 500, потому что приложение деплоится) считать что ответ = pending.

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns="http://www.allpay.kz/mfs/soap/TransactionManagement/1.1">
   <soapenv:Header/>
   <soapenv:Body>
      <ns:vostokPlatPayResponse>
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
      </ns:vostokPlatPayResponse>
   </soapenv:Body>
</soapenv:Envelope>
```

xsd можно найти [здесь](https://github.com/allpaykz/allpay-public/tree/develop/allpay-public-soap/allpay-public-soap-common/src/main/resources/xsds/v1_0)
