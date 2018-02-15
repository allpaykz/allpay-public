# Репозиторий хранит библиотеки для подписи/валидации `XML` сообщений и примеры реализации веб-сервисов отправки/приемы сообщений

1. [Java библиотеки для подписи/валидации сообщений](https://github.com/allpaykz/webshop-service-examples/tree/master/webshop-integration-keypair)
2. [Демо Java сервисов](https://github.com/allpaykz/webshop-service-examples/tree/master/webshop-integration-demo)
3. [Документация по бизнес процессам здесь](https://github.com/allpaykz/documentation/tree/master/webshop-integration)

Для генерации ключей нужно
1. Авторизоваться мерчантом в системе.
2. В меню "Настройки" выбрать "Управление сертификатами"
3. Нажать "Сгенерировать пару и скачать приватный ключ"

# Версии webshop

WebShop обрабатывает ответ интегрирующейся стороны на [WebShopResponse](https://github.com/allpaykz/allpay-public/blob/develop/webshop-integration/webshop-integration-keypair/src/main/resources/xsd/1.0.0/WebShopResponse.xsd) по двум версиям протокола.

### Первая версия протокола. 

При отправке [WebShopResponse](https://github.com/allpaykz/allpay-public/blob/develop/webshop-integration/webshop-integration-keypair/src/main/resources/xsd/1.0.0/WebShopResponse.xsd) интегрирующейся стороне, мы проверяем только статус HTTP запроса. Если HTTP.statusCode = 200, то завершаем платеж, в противном случае отклоняем платеж.

### Вторая версия протокола

При отправке [WebShopResponse](https://github.com/allpaykz/allpay-public/blob/develop/webshop-integration/webshop-integration-keypair/src/main/resources/xsd/1.0.0/WebShopResponse.xsd) интегрирующейся стороне, мы ждем ответа в формате [WebShopCustomerResponse](https://github.com/allpaykz/allpay-public/blob/develop/webshop-integration/webshop-integration-keypair/src/main/resources/xsd/1.0.0/WebShopCustomerResponse.xsd). Ответ состоит из двух частей: Status и Reason. 

|Status  |Reason          |
|--------|----------------|
|DONE    |doesn't required|
|PENDING |doesn't required|
|FAIL    |required        |

Пока для reason нет особого формата.

В запросе [WebShopRequest](https://github.com/allpaykz/allpay-public/blob/develop/webshop-integration/webshop-integration-keypair/src/main/resources/xsd/1.0.0/WebShopRequest.xsd) есть элемент protocolVersion, который работает следующим образом:
1. Если значение не внесено или равно 1, то обработка пройдет по первой версии протокола.
2. Если значени равно 2, то обработка пройдет по второй версии протокола.
3. Если внесено какое-либо другое значение, то при обработке бросится ошибка. На нашей стороне платеж будет в статусе "PENDING". После 5-ой попытки провести платеж, система отправит разработчику письмо с ошибкой.
