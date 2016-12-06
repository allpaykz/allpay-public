<?php
  header('Content-Type: text/html; charset=utf-8');
  require(dirname(__FILE__) . '/class/allpay.class.php');
  //Обязательное поле для __construct
  $key_path =  dirname(__FILE__); //Путь до приватных и публичных ключей + /allpay_key/privkey.pem

  //Не обязательные поле для __construct
  $shop_name = 'TOO NAME SHOP';  // Название магазина, которое будет видеть Покупатель на странице подтверждения платежей. Обязательное для заполнения
  $wallet =  '00013'; //Идентификатор счета в ПС. На этот счет будут переводится деньги от Покупателя. Выдается в момент заключения договора. Обязательное для заполнения
  $invoice = 'number_invoice';  // Номер заказа, поле должно быть уникальным в базе данных магазина. Обязательное для заполнения
  $amount = 10; // Общая сумма к оплате. Обязательное для заполнения

  $allpay = new allpay($key_path,$shop_name,$wallet,$invoice,$amount);

  //Можно поменять или присвоить данные
  $allpay->set_shop_name('TOO NAME SHOP2');
  $allpay->set_invoice('number_invoice2');
  $allpay->set_wallet('00014');
  $allpay->set_amount('5');


  //Составное поле, описывающее товары в корзине Покупателя.  Не обязательное для заполнения
  $carts[0]['Code'] = 'string';
  $carts[0]['Name'] = 'string';
  $carts[0]['Description'] = 'string';
  $carts[0]['Quantity'] = '1000';
  $carts[0]['Cost'] = '1000';
  $carts[1]['Code'] = 'string1';
  $carts[1]['Name'] = 'string1';
  $carts[1]['Description'] = 'string1';
  $carts[1]['Quantity'] = '2000';
  $carts[1]['Cost'] = '2000';
  $allpay->set_cart($carts);
  //Составное поле, описывающее товары в корзине Покупателя.  Не обязательное для заполнения

  $allpay->create_xml(); //Создание xml документа
  echo $allpay->get_error_text();  // Вывод ошибок при создании  xml документа

  //Вывод созданного xml документа. Для тестирования.
  //header("Content-Type: text/plain; charset=utf-8");
  //echo  $allpay->get_xml();


   $merchantId = $allpay->get_xml_base64();
   echo '<form enctype="application/x-www-form-urlencoded" method="post" action="http://test.all-pay.kz/mfs/WebShopPayment.xhtml" name="all-pay_'.time().'" id="all-pay_'.time().'">
          <input type="hidden" id="webshopRequest" name="webshopRequest" value="'.$merchantId.'" />
          <input type="submit" value="SEND" />
        </form>';

?>