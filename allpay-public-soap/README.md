# Общий модуль для SOAP API

Проект состоит из трех модулей

### 1. allpay-public-soap-client

Пример клиента для SOAP API. Простая реализация описана в классе [TransactionManagementV1_0Client](https://github.com/allpaykz/allpay-public/blob/develop/allpay-public-soap/allpay-public-soap-client/src/main/java/kz/allpay/mfs/ws/soap/v1_0/TransactionManagementV1_0Client.java)

### 2. allpay-public-soap-common

Общие классы для всего модуля. [Здесь](https://github.com/allpaykz/allpay-public/blob/develop/allpay-public-soap/allpay-public-soap-common/src/main/java/kz/allpay/mfs/ws/soap/handlers/SecuritySoapHandlerClient.java) можно найти хэндлер, который знает как правильно подписывать. [Здесь](https://github.com/allpaykz/allpay-public/blob/develop/allpay-public-soap/allpay-public-soap-common/src/main/java/kz/allpay/mfs/ws/soap/handlers/SecuritySoapHandlerServer.java) хэндлер для верификации подписи.

В [ресурсах](https://github.com/allpaykz/allpay-public/tree/develop/allpay-public-soap/allpay-public-soap-common/src/main/resources/xsds/v1_0) лежат актуальные XSD схемы 


### 3. allpay-public-soap-demo

Демо проект для демонстрации API CashIn CashOut.

Проект строится с помощью maven 3. В проекте есть несколько maven profiles.

1. local
2. alpha
3. beta

Профиль `local` нужен для локального тестирования внутри allpay.

Профиль `alpha` настраивает demo проект для работы с сервером  allpay альфа версии - https://alpha.allpay.kz/mfs

Профиль `beta` настраивает demo проект для работы с сервером  allpay бета версии - https://beta.allpay.kz/mfs

Команда для запуска билда.

`mvn clean install -P alpha,tomcat`

### Тестовые ключи

Тестовые ключи можно найти [здесь](https://github.com/allpaykz/allpay-public/tree/develop/webshop-integration/webshop-integration-keypair/src/main/resources/mockKeys). При использовании наших хэндлеров не нужно беспокоится о процессе подписания и верификации подписи.

Если вы планируете писать свою реализации подписи/верификации, тогда обратите внимание на проект [webshop-integration](https://github.com/allpaykz/allpay-public/tree/master/webshop-integration). Модуль [webshop-keypair](https://github.com/allpaykz/allpay-public/tree/master/webshop-integration/webshop-integration-keypair)

