# Демо сервисов на PHP

Для работы с ПС Allpay нужно скопировать всю папку [Allpay PHP Demo](https://github.com/allpaykz/webshop-service-examples/tree/master/webshop-integration-php-demo). Скачать проект можно по [ссылке](https://github.com/allpaykz/webshop-service-examples/archive/master.zip) или склонировать весь репозиторий.

Если получаете сообщение `Exception [ 500 ]: Failure Signing Data: - 1`, скорее всего нужно подключить библиотеку `extension=php_openssl.dll`. Её нужно закачивать отдельно, соответствующей вашей версии `PHP`.

### Использование

#### Отредактировать файл `class/allpay.class.php`
```php
 /*
  * URL для возврата если все хорошо прошло
  */
  const SUCCESS_URL = 'http://www.shop_name.kz/success.html';

 /*
  * URL для возврата если оплата не прошла.
  */
  const FAIL_URL = 'http://www.shop_name.kz/fail.html';

 /*
  * Идентификатор магазина в платежной системе. Выдается в момент заключения договора.
  */
  const MERCHANT_ID = '75551234569';
```

#### Для вызова сервиса используйте файл `request_allpay.php`

Пример 1 вызова сервиса. Файл `request_allpay.php`:
```php
<?php
  header('Content-Type: text/html; charset=utf-8');
  require ( dirname(__FILE__) . '/class/allpay.class.php');
  //********Обязательное поле для __construct.********//
    //Путь до приватных и публичных ключей + /allpay_key/privkey.pem
    $key_path =  dirname(__FILE__);
    //********Обязательное поле для заполнения xml.********//
      //Название магазина, которое будет видеть Покупатель на странице подтверждения платежей. Обязательное для заполнения
      $shop_name = 'TOO NAME SHOP';
      //Идентификатор счета в ПС. На этот счет будут переводится деньги от Покупателя. Выдается в момент заключения договора. Обязательное для заполнения
      $wallet =  '00013';
      //Номер заказа, поле должно быть уникальным в базе данных магазина. Обязательное для заполнения
      $invoice = 'number_invoice';
      //Общая сумма к оплате. Обязательное для заполнения
      $amount = 10;
    //********Конец: Обязательное поле для заполнения xml.********//
  //********Конец: Обязательное поле для __construct.********//
  //Инициализируем класс allpay.
  $response_url = 'http://my.domain.kz/response_allpay.php';
  $allpay = new allpay($key_path,$shop_name,$wallet,$invoice,$amount,$response_url);
  
  //********Составное поле, описывающее товары в корзине Покупателя.  Не обязательное для заполнения. Но в дальнейшем при оплате будет красиво отображаться для покупателя что он покупает.********//
    //Код товара
    $carts[0]['Code'] = 'string';
    //Название товара
    $carts[0]['Name'] = 'string';
    //Описание товара
    $carts[0]['Description'] = 'string';
    //Количество товара в соответствующей мере измерения
    $carts[0]['Quantity'] = '10';
    //Стоимость товара
    $carts[0]['Cost'] = '1000';
    //Код товара №2
    $carts[1]['Code'] = 'string1';
    //Название товара №2
    $carts[1]['Name'] = 'string1';
    //Описание товара №2
    $carts[1]['Description'] = 'string1';
    //Количество товара в соответствующей мере измерения №2
    $carts[1]['Quantity'] = '8';
    //Стоимость товара №2
    $carts[1]['Cost'] = '2000';
    $allpay->set_cart($carts);
  //********Конец: Составное поле, описывающее товары в корзине Покупателя.  Не обязательное для заполнения********//
  //Создание xml документа
  $allpay->create_xml();
  //Если нужно вывести ошибку. Вывод ошибок при создании  xml документа
  //echo $allpay->get_error_text();
  //Получить xml данные в base64 для вставки в форуму.
  $webshopRequest = $allpay->get_xml_base64();
  //Главный критерий отправки формы это method="post"  и enctype="application/x-www-form-urlencoded"
  echo '<form enctype="application/x-www-form-urlencoded" method="post" action="http://test.all-pay.kz/mfs/WebShopPayment.xhtml" name="all-pay_'.time().'" id="all-pay_'.time().'">
          <input type="hidden" id="webshopRequest" name="webshopRequest" value="'.$webshopRequest.'" />
          <input type="submit" value="SEND" />
        </form>';
?>
```
Пример 2 вызова сервиса. Файл `request_allpay.php`:
```php
<?php
  header('Content-Type: text/html; charset=utf-8');
  require ( dirname(__FILE__) . '/class/allpay.class.php');
  //********Обязательное поле для __construct.********//
    //Путь до приватных и публичных ключей + /allpay_key/privkey.pem
    $key_path =  dirname(__FILE__);
    //********Конец: Обязательное поле для __construct.********//
  //Инициализируем класс allpay.
  $allpay = new allpay($key_path,$shop_name,$wallet,$invoice,$amount);
  //********Обязательное поле для заполнения xml.********//
    //Название магазина, которое будет видеть Покупатель на странице подтверждения платежей. Обязательное для заполнения
    $allpay->set_shop_name('TOO NAME SHOP2');
    //Идентификатор счета в ПС. На этот счет будут переводится деньги от Покупателя. Выдается в момент заключения договора. Обязательное для заполнения
    $allpay->set_wallet('00014');
    //Номер заказа, поле должно быть уникальным в базе данных магазина. Обязательное для заполнения
    $allpay->set_invoice('number_invoice2');
    //Общая сумма к оплате. Обязательное для заполнения
    $allpay->set_amount('5');
    //Ссылка на сервис, принимающий ответ
    $allpay->set_response_url('http://my.domain.kz/response_allpay.php');
  //********Конец: Обязательное поле для заполнения xml.********//
  //********Не обязательные поле для заполнения xml. Ставиться автоматически либо не создаются в xml файле.********//
    //Таймаут транзакции. Минимальное значение 600, максимальное 86400. Обязательное для заполнения
    $allpay->set_time_out('1024');
    //Автономное проведение транзакции. По умолчанию значение false. Если значение true, то ПС сама завершает транзакцию. Если значение false, то ПС замораживает деньги на счету Покупателя.
    $allpay->set_auto_transaction('true');
    //********Составное поле, описывающее товары в корзине Покупателя.  Не обязательное для заполнения.********//
      //Код товара
      $carts[0]['Code'] = 'string';
      //Название товара
      $carts[0]['Name'] = 'string';
      //Описание товара
      $carts[0]['Description'] = 'string';
      //Количество товара в соответствующей мере измерения
      $carts[0]['Quantity'] = '10';
      //Стоимость товара
      $carts[0]['Cost'] = '1000';
      //Код товара №2
      $carts[1]['Code'] = 'string1';
      //Название товара №2
      $carts[1]['Name'] = 'string1';
      //Описание товара №2
      $carts[1]['Description'] = 'string1';
      //Количество товара в соответствующей мере измерения №2
      $carts[1]['Quantity'] = '8';
      //Стоимость товара №2
      $carts[1]['Cost'] = '2000';
      $allpay->set_cart($carts);
    //********Конец: Составное поле, описывающее товары в корзине Покупателя.  Не обязательное для заполнения.********//
  //********Конец: Не обязательные поле для заполнения xml. Ставиться автоматически либо не создаются в xml файле.********//
  //Создание xml документа
  $allpay->create_xml();
  //Если нужно вывести ошибку. Вывод ошибок при создании  xml документа
  //echo $allpay->get_error_text();
  //Получить xml данные в base64 для вставки в форуму.
  $webshopRequest = $allpay->get_xml_base64();
  //Главный критерий отправки формы это method="post"  и enctype="application/x-www-form-urlencoded"
  echo '<form enctype="application/x-www-form-urlencoded" method="post" action="http://test.all-pay.kz/mfs/WebShopPayment.xhtml" name="all-pay_'.time().'" id="all-pay_'.time().'">
          <input type="hidden" id="webshopRequest" name="webshopRequest" value="'.$webshopRequest.'" />
          <input type="submit" value="SEND" />
        </form>';
?>
```
#### Для получения ответа WebshopResponse.xml используйте файл `response_allpay.php`.

После того как Покупатель оплатит Ваш заказ ПС Allpay высылает WebshopResponse.xml файл, принять XML-ку можно используя сервис `response_allpay.php`:
```php
<?php 
  header('Content-Type: text/html; charset=utf-8');
  require ( dirname(__FILE__) . '/class/allpay.class.php');
  //********Обязательное поле для __construct.********//
  //Путь до приватных и публичных ключей + /allpay_key/pubkey.pem
  $key_path =  dirname(__FILE__); 
  //********Конец: Обязательное поле для __construct.********//
  //Инициализируем класс allpay.
  $allpay = new allpay($key_path);
  // Делаем xml объект из POST запроса.
  $allpay->load_xml(base64_decode(file_get_contents('php://input'))); 
  // Проверка на подпись.
  if($allpay->verify_security())  
  {
     $text.=  "Signature validated!".$allpay->get_status();
     if($allpay->get_status() === $allpay::STATUS_COMPLETED)
     {
       $text .= ' InvoiceNumber = '.$allpay->get_invoice();
       $text .= ' TransactionId = '.$allpay->get_transaction_id();
       $text .= ' Status = '.$allpay->get_status();
       $text .= ' StatusDescription = '.$allpay->get_status_description();
     }
  }
  else
  {
   $text =  $allpay->get_error_text(); //вывод сообщения об ошибке
  }
 //Сохраняем в файл для тестирования
 $open = fopen (dirname(__FILE__)."/text.txt","r+");
 fwrite($open, $text);
 fclose($open);
?>
```


