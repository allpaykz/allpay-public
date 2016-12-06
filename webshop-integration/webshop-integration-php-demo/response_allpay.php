<?php
  header('Content-Type: text/html; charset=utf-8');
  require(dirname(__FILE__) . '/class/allpay.class.php');
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