# Общий модуль для SOAP API

Демо версия проекта, реализующая все API находится [здесь](http://beta.allpay.kz/allpay-public-soap-demo)

Код demo проекта [здесь](allpay-public-soap-demo)

### Документация

Документация находится [здесь](https://github.com/allpaykz/allpay-public/wiki)

### 1. allpay-public-soap-client

Пример клиента для SOAP API. Простая реализация описана в классах в [этом пакете](./allpay-public-soap-client/src/main/java/kz/allpay/mfs/ws/soap)

### 2. allpay-public-soap-common

Общие классы для всего модуля. [Здесь](https://github.com/allpaykz/allpay-public/blob/develop/allpay-public-soap/allpay-public-soap-common/src/main/java/kz/allpay/mfs/ws/soap/handlers/SecuritySoapHandlerClient.java) можно найти хэндлер, который знает как правильно подписывать и верифицировать.

В [ресурсах](https://github.com/allpaykz/allpay-public/tree/develop/allpay-public-soap/allpay-public-soap-common/src/main/resources/xsds/v1_0) лежат актуальные XSD схемы


### 3. allpay-public-soap-demo

Демо проект для демонстрации SOAP API.

Проект строится с помощью maven 3. В проекте есть несколько maven profiles.

1. local
2. alpha
3. beta

Профиль `local` нужен для локального тестирования внутри allpay.

Профиль `alpha` настраивает demo проект для работы с сервером  allpay альфа версии - https://alpha.allpay.kz/mfs

Профиль `beta` настраивает demo проект для работы с сервером  allpay бета версии - https://beta.allpay.kz/mfs

Команда для запуска билда.

`mvn clean install -P alpha,tomcat`

Таким образом сгенерируется war файл `allpay-public-soap-demo.war`. Его нужно задеплоить в [томкат версии 8](https://tomcat.apache.org/download-80.cgi)

### Тестовые ключи

Тестовые ключи можно найти [здесь](https://github.com/allpaykz/allpay-public/tree/develop/webshop-integration/webshop-integration-keypair/src/main/resources/mockKeys). При использовании наших хэндлеров не нужно беспокоится о процессе подписания и верификации подписи.

Если вы планируете писать свою реализации подписи/верификации, тогда обратите внимание на проект [webshop-integration](https://github.com/allpaykz/allpay-public/tree/master/webshop-integration). Модуль [webshop-keypair](https://github.com/allpaykz/allpay-public/tree/master/webshop-integration/webshop-integration-keypair)

### Написание своей библиотеки для подписей

Примеры xml:

 - [Не подписанная xml-ка](https://github.com/allpaykz/allpay-public/blob/develop/webshop-integration/webshop-integration-keypair/src/test/resources/soap-messages/getTransactionRequest-1-not-signed.xml)
 - [Подписанная xml-ка](https://github.com/allpaykz/allpay-public/blob/develop/webshop-integration/webshop-integration-keypair/src/test/resources/soap-messages/getTransactionRequest-1.xml)
 - [Подписанная и немного измененная xml-ка](https://github.com/allpaykz/allpay-public/blob/develop/webshop-integration/webshop-integration-keypair/src/test/resources/soap-messages/getTransactionRequest-1-formatted-same-signature.xml)

Проверяется это вот этим [тестом](https://github.com/allpaykz/allpay-public/blob/develop/webshop-integration/webshop-integration-keypair/src/test/java/kz/allpay/mfs/webshop/signature/SignatureServiceSoapImplTest.java)

Для подписи этих XML использовались следущие ключи:

 - [Приватный](https://github.com/allpaykz/allpay-public/blob/develop/webshop-integration/webshop-integration-keypair/src/main/resources/mockKeys/TEST.priv.pem)
 - [Публичный](https://github.com/allpaykz/allpay-public/blob/develop/webshop-integration/webshop-integration-keypair/src/main/resources/mockKeys/TEST.pub.pem)
