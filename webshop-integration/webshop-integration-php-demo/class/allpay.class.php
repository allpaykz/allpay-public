<?
/**
 * allpay - класс для работы с платежами от AllPay beta
 * @author - Баландин Дмитрий
 */

require(dirname(__FILE__) . '/XMLSecurityKey.php');
require(dirname(__FILE__) . '/XMLSecurityDSig.php');
require(dirname(__FILE__) . '/XMLSecEnc.php');
use RobRichards\XMLSecLibs\XMLSecurityDSig;
use RobRichards\XMLSecLibs\XMLSecurityKey;
use RobRichards\XMLSecLibs\XMLSecEnc;


class allpay{

   /**
   *  URL для возврата если все хорошо прошло
   */
    const SUCCESS_URL = 'http://www.shop_name.kz/success.html';

    /**
   *  URL для возврата если оплата не прошла.
   */
    const FAIL_URL = 'http://www.shop_name.kz/fail.html';

    /**
    * Идентификатор магазина в ПС. Выдается в момент заключения договора.
    */
    const MERCHANT_ID = '75551234569';

    /**
    * Название файла приватного ключа
    */
    const PRIVATE_KEY_NAME = '/allpay_key/privkey.pem';

    /**
    * Название файла публичного ключа
    */
    const PUBLIC_KEY_NAME = '/allpay_key/pubkey.pem';

    /**
    * URL на проверку валидности xml по xsd схеме. xml запрос.
    */
    const WEB_SHOP_REQUEST_XSD = 'http://allpay.kz/xsd/1.0.0/WebShopRequest.xsd';

    /**
    * URL на проверку валидности xml по xsd схеме. xml ответ.
    */
    const WEB_SHOP_RESPONSE_XSD = 'http://allpay.kz/xsd/1.0.0/WebShopResponse.xsd';

    /**
    * Положительный статус при ответе.
    */
    const STATUS_COMPLETED   = 0;

    /**
    * Полный путь до папки с приватным и публичным ключом. Обязательное для заполнения
    * @var string
    */
    public $_key_path = '';

    /**
    * Название атрибутов для главного каталога. Для создания xml документа
    * @var string
    */
    public $_root_attributes = array();

    /**
    * Название магазина, которое будет видеть Покупатель на странице подтверждения платежей. Обязательное для заполнения
    * @var string
    */
    public $_shop_name = '';

   /**
    * Номер заказа, поле должно быть уникальным в базе данных магазина. Обязательное для заполнения
    * @var string
    */
    public $_invoice = '';

    /**
    * Составное поле, описывающее аккаунт магазина в ПС Allpay. Обязательное для заполнения
    * @var array
    */
    public $_merchant = array();

    /**
    * Составное поле, описывающее товары в корзине Покупателя. Не обязательное для заполнения
    * @var array
    */
    public $_cart = array();

    /**
    * Общая сумма к оплате. Обязательное для заполнения
    * @var string
    */
    public $_amount = 0;

    /**
    * Опции для запроса. Обязательное для заполнения
    * @var array
    */
    public $_additionals = array();

    /**
    * Таймаут транзакции. Минимальное значение 600, максимальное 86400. Обязательное для заполнения
    * @var string
    */
    public $_time_out = '600';

    /**
    * Автономное проведение транзакции. По умолчанию значение false. Если значение true, то ПС сама завершает транзакцию. Если значение false, то ПС замораживает деньги на счету Покупателя. Для завершение или отмена транзакции ПС предоставляет сервисы манупулирования транзакциями.
    * @var array
    */
    public $_auto_transaction = 'false';

    /**
    * Номер транзакции в ПС Allpay. По этому номеру можно проверить статус транзакции в веб интерфейсе и проводить манипуляции через веб сервис. Обязательное для заполнения.
    * @var array
    */
    public $_transaction_id = '';

    /**
    * Статус транзакции. Обязательное для заполнения
    * @var string
    */
    public $_status = '';

    /**
    * Описание статуса транзакции. Обязательное для заполнения
    * @var string
    */
    public $_status_description = '';

     /**
    * Адрес на сервис куда будет высылаться ответ. Обязательное для заполнения
    * @var string
    */
    public $_response_url = '';

    /**
    * Создание xml document.
    * @var DOMElement
    */
    private $_xmlDoc = '';

    /**
    * Создание xls document.
    * @var DOMElement
    */
    private $_xlsDoc = '';


    /**
    * Прифекс для создание xml document (namespace).
    * @var string
    */
    public $_prefix = 'WSReq';

     /**
    * Сообщение об ошибках.
    * @var string
    */
    public $_error_text = '';


     /**
    * Конструктор класса. Задает все нужные параметры
    * @param {string} key_path - Полный путь до папки с приватным и публичным ключом
    * @param {string} shop_name - Название магазина
    * @param {string} wallet - Идентификатор счета в ПС
	* @param {array} invoice - Составное поле описывающее заказ.
	* @param {string} amount - Общая сумма к оплате.
	* @param {string} prefix Значение префикса для XML
    */
    function __construct($key_path,$shop_name=null,$wallet=null,$invoice=null,$amount=null,$response_url=null,$prefix=null)
    {
       $this->set_key_path($key_path);
       $this->set_shop_name($shop_name);
       $this->set_invoice($invoice);
       $this->set_wallet($wallet);
       $this->set_amount($amount);
       $this->set_response_url($response_url);
       $this->set_additionals();
       if(!is_null($prefix)){$this->set_prefix($prefix);}
       $this->_xmlDoc = new DOMDocument("1.0", "UTF-8");
       $this->_xmlDoc->xmlStandalone  = false;
       $this->_xmlDoc->formatOutput = true;
    }

    /**
    * Задаем root атрибуты
    */
    public function set_root_attributes()
    {
     $this->_root_attributes = array(
          'xmlns:'.$this->_prefix => self::WEB_SHOP_REQUEST_XSD,
          'xmlns:xsi' => 'http://www.w3.org/2001/XMLSchema-instance',
          'xsi:schemaLocation' => self::WEB_SHOP_REQUEST_XSD.' '.self::WEB_SHOP_REQUEST_XSD
        );


    }

    /**
    * Задаем полный путь до папки с приватным и публичным ключом
    */
   public function set_key_path($key_path){ $this->_key_path = $key_path; }

    /**
    * Задаем префикс
    */
   public function set_prefix($prefix){ $this->_prefix = $prefix; }

   /**
    * Задаем название магазина
    */
   public function set_shop_name($shop_name){ $this->_shop_name = $shop_name; }

   /**
    * Задаем номер заказа магазина
    */
   public function set_invoice($invoice){ $this->_invoice = $invoice; }

    /**
    * Задаем описывающее товары в корзине покупателя.
    */
   public function set_cart($cart){ $this->_cart = $cart; }

   /**
    * Задаем сумму к оплате.
    */
   public function set_amount($amount){ $this->_amount = $amount; }

   /**
    * Задаем сообщение об ошибках.
    */
   public function set_error_text($error_text){ $this->_error_text = $error_text; }

   /**
    * Задаем таймаут транзакции.
    */
   public function set_time_out($time_out){ $this->_time_out = $time_out; $this->set_additionals(); }

   /**
    * Задаем  автономное проведение транзакции.
    */
   public function set_auto_transaction($auto_transaction){ $this->_auto_transaction = $auto_transaction; $this->set_additionals(); }

   /**
    * Задать адрес на сервис куда будет высылаться ответ.
    */
   public function set_response_url($response_url){ $this->_response_url = $response_url; $this->set_additionals(); }

   /**
    * Задаем дополнительные поля для создания xml
    */
   public function set_additionals()
   {
      $this->_additionals = array(
          'SuccessLink' =>   self::SUCCESS_URL,
          'FailureLink' =>   self::FAIL_URL,
          'ResponseURL' =>   $this->_response_url,
          'TimeoutInSeconds' => $this->_time_out,
          'AutoTransaction' => $this->_auto_transaction
      );
   }

   /**
    * Задаем уникальный номер счёта
    */
   public function set_wallet($wallet_id)
   {
       $this->_merchant = array(
            'MerchantID' =>   self::MERCHANT_ID,
            'WalletID' =>     $wallet_id
      );
   }


    /**
    * Задаем номер транзакции в ПС Allpay.
    */
   public function set_transaction_id($transaction_id){ $this->_transaction_id = $transaction_id;}

   /**
    * Задаем статус транзакции.
    */
   public function set_status($status){ $this->_status = $status;}

   /**
    * Задаем описание статуса транзакции.
    */
   public function set_status_description($status_description){ $this->_status_description = $status_description;}


   /**
    * Получить номер заказа магазина
    */
   public function get_invoice(){ return $this->_invoice; }

   /**
    * Получить номер транзакции в ПС Allpay.
    */
   public function get_transaction_id(){ return $this->_transaction_id;}

   /**
    * Получить статус транзакции.
    */
   public function get_status(){ return $this->_status;}

   /**
    * Получить описание статуса транзакции.
    */
   public function get_status_description(){ return $this->_status_description;}

   /**
    * Получить сообщение об ошибках.
    */
   public function get_error_text(){ return $this->_error_text;}

   /**
    * Получить адрес на сервис куда будет высылаться ответ.
    */
   public function get_response_url(){ return $this->_response_url;}

    /**
    * Создаем XML документ
    */
    public function create_xml()
    {
      $this->set_root_attributes();

      $root = $this->createElementXML($this->_prefix.":WebShopRequest");
      $this->ArrayCreateAttributeXML($this->_root_attributes,$root);
      $this->createElementXML($this->_prefix.":ShopName",$root,$this->_shop_name);
      $this->createElementXML($this->_prefix.":InvoiceNumber",$root,$this->_invoice);
      if (isset($this->_merchant["MerchantID"]))
      {
         $Merchant = $this->createElementXML($this->_prefix.":Merchant",$root);
         $this->ArraycreateElementXML($this->_merchant,$Merchant);
      }
      if (isset($this->_cart[0]["Code"]))
      {
      $Cart = $this->createElementXML($this->_prefix.":Cart",$root);
      for($i=0;$i<count($this->_cart);$i++)
      {
           $Items = $this->createElementXML($this->_prefix.":Items",$Cart);
           $this->ArraycreateElementXML($this->_cart[$i],$Items);
      }
      }
      $this->createElementXML($this->_prefix.":TotalCost",$root,$this->_amount);
      $this->ArraycreateElementXML($this->_additionals,$root);

      $this->SecurityDSig();
      $this->SchemaValidateXML(self::WEB_SHOP_REQUEST_XSD);

      $this->libxml_errors();
      $this->_unset();
    }

    /**
    * Создаем подпись к XML документу
    */
    private function SecurityDSig()
    {
      if (!file_exists($this->_key_path . self::PRIVATE_KEY_NAME)){
         throw new Exception("PRIVATE KEY not found");
      }
      $doc = new DOMDocument("1.0", "UTF-8");
      $doc->loadXML($this->_xmlDoc->saveXML());
      $options['force_uri'] = true;
      $objDSig = new XMLSecurityDSig($this->_prefix);
      $objDSig->setCanonicalMethod(XMLSecurityDSig::C14N_COMMENTS);
      $objDSig->addReference($doc, XMLSecurityDSig::SHA1, array('http://www.w3.org/2000/09/xmldsig#enveloped-signature'),$options);
      $objKey = new XMLSecurityKey(XMLSecurityKey::RSA_SHA1, array('type'=>'private'));
      $objKey->loadKey($this->_key_path . self::PRIVATE_KEY_NAME, TRUE);
      $objDSig->sign($objKey,$doc->documentElement);
      $this->_xmlDoc = $doc;
      unset($doc);
    }

    /**
    * Конструктор xml document. Создание XML элементов
    * @param {string} NameElement - Название нового DOMElement
	* @param {DOMElement} ParentElementXML - Родительский элемент которому будет присваиваться создаваемый новый DOMElement
	* @param {string} value Значение создаваемого DOMElement
    */
    private function createElementXML($NameElement,$ParentElementXML=null,$value=null)
    {
      if(!is_null($ParentElementXML))
      {
        if(!is_null($value)){return $ParentElementXML->appendChild($this->_xmlDoc->createElement($NameElement,$value));}
        else{return $ParentElementXML->appendChild($this->_xmlDoc->createElement($NameElement));}
      }
      else{return $this->_xmlDoc->appendChild($this->_xmlDoc->createElement($NameElement));}
    }

    /**
    * Конструктор xml document. Создание XML элементов из массива данных
    * @param {arrau} ArrayElementXML - Список названий нового DOMElement
	* @param {DOMElement} ParentElementXML - Родительский элемент которому будет присваиваться создаваемый новый DOMElement
    */
    private function ArraycreateElementXML($ArrayElementXML,$ParentElementXML)
    {
       foreach ($ArrayElementXML as $name => $value)
       {
           $this->createElementXML($this->_prefix.":".$name,$ParentElementXML,$value);
       }
    }

    /**
    * Конструктор xml document. Создание XML атрибутов
    * @param {DOMElement} Element - Элемент которому присваивается атрибут
	* @param {string} Attribute - Название атрибута
	* @param {string} TextNode Значение атрибута
    */
    private function createAttributeXML($Element,$Attribute,$TextNode)
    {
      $Element->appendChild(
        $this->_xmlDoc->createAttribute($Attribute))->appendChild(
          $this->_xmlDoc->createTextNode($TextNode));
    }

    /**
    * Конструктор xml document. Создание XML атрибутов из массива данных
    * @param {DOMElement} ElementXML - Элемент которому присваивается атрибут
    * @param {arrau} ArrayAttributeXML - Список свойств новых атрибутов
    */
    private function ArrayCreateAttributeXML($ArrayAttributeXML,$ElementXML)
    {
      foreach ($ArrayAttributeXML as $name => $value)
      {
         $this->createAttributeXML($ElementXML,$name,$value);
      }
    }

     /**
    * Парсер ошибок и запись в переменную  $this->_error_text
    * @param {libXMLError} $error - массив объектов ошибки
    */
    private function libxml_parse_error($error)
    {
      switch ($error->level)
      {
        case LIBXML_ERR_WARNING:
            $this->_error_text .= "<b>Warning $error->code</b>: ";
            break;
        case LIBXML_ERR_ERROR:
            $this->_error_text .= "<b>Error $error->code</b>: ";
            break;
        case LIBXML_ERR_FATAL:
            $this->_error_text .= "<b>Fatal Error $error->code</b>: ";
            break;
      }
      $this->_error_text .= trim($error->message);
      if($error->file){ $this->_error_text .= " in <b>$error->file</b>";}
      $this->_error_text .= " on line <b>$error->line</b>\n";
    }

    /**
    * Получение массива произошедших ошибок в xml
    */
    private function libxml_errors()
    {
      $errors = libxml_get_errors();
      foreach($errors as $error){$this->libxml_parse_error($error);}
      libxml_clear_errors();
    }

    /**
    * Проверяет действительность документа, основываясь на заданной схеме
    */
    private function SchemaValidateXML($url_xsd)
    {
      $dom = new DOMDocument("1.0", "UTF-8");
      $dom->loadXML($this->_xmlDoc->saveXML());
      if(!$dom->schemaValidate($url_xsd)) // Or schemaValidateSource if string used.
      {
        $this->libxml_errors();
      }
      unset($dom);
    }

    /**
    * Конструктор xml document. Возврат создано xml document
    */
     public function  get_xml()
     {
       if(!empty($this->_xmlDoc))
       {
         return html_entity_decode($this->_xmlDoc->saveXML(), ENT_COMPAT | ENT_HTML401, "ISO-8859-1");
       }
     }

     /**
    * Конструктор xml document. Возврат создано xml document в base64_encode
    */
     public function  get_xml_base64()
     {
       if(!empty($this->_xmlDoc))
       {
         return base64_encode(html_entity_decode($this->_xmlDoc->saveXML(), ENT_COMPAT | ENT_HTML401, "ISO-8859-1"));
       }
     }

    /**
    * Конструктор xml document. Создаем xml документ по переменой
  	* @param {DOMElement} xml - XML документ
    */
    public function load_xml($xml)
    {
      if(empty($xml)){$this->_error_text = 'No data POST request.';return;}
      try
      {
        $this->_xmlDoc->loadXML($xml);
        $this->SchemaValidateXML(self::WEB_SHOP_RESPONSE_XSD);
      }
      catch (exception $exc)
      {
        $this->_error_text = $exc->getMessage()." at line ".$exc->getLine();
      }
    }

   /**
    * Проверка подписи по публичному ключу.
    */
    public function verify_security()
    {
      $objXMLSecDSig = new XMLSecurityDSig();
      $objDSig = $objXMLSecDSig->locateSignature($this->_xmlDoc);
	  if (! $objDSig)
      {
        $this->set_error_text("Cannot locate Signature Node!");
		return false;
	  }
	  $objXMLSecDSig->canonicalizeSignedInfo();
	  $objXMLSecDSig->idKeys = array('wsu:Id');
	  $objXMLSecDSig->idNS = array('wsu'=>'http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd');
      try{$retVal = $objXMLSecDSig->validateReference();}
      catch(Exception $e){$this->set_error_text($e->getMessage());return false;}
      $objKey = $objXMLSecDSig->locateKey();
	  if (! $objKey )
      {
        $this->set_error_text("We have no idea about the key!");
		return false;
	  }
	  $key = NULL;
      $objKeyInfo = XMLSecEnc::staticLocateKeyInfo($objKey, $objDSig);
      if (! $objKeyInfo->key && empty($key))
      {
        try{$objKey->loadKey($this->_key_path . self::PUBLIC_KEY_NAME, TRUE);}
        catch(Exception $e){$this->set_error_text($e->getMessage());return false;}
	  }
     if ($objXMLSecDSig->verify($objKey)){$this->SetParametersXML();return true;}
     else{$this->set_error_text("Signature not validated!");return false;}
    }

    /**
	* Присваиваем xml значения переменным
   	*/
    private function SetParametersXML()
    {
       foreach ($this->_xmlDoc->getElementsByTagName('InvoiceNumber') as $value){$this->set_invoice($value->nodeValue);}
       foreach ($this->_xmlDoc->getElementsByTagName('TransactionId') as $value){$this->set_transaction_id($value->nodeValue);}
       foreach ($this->_xmlDoc->getElementsByTagName('Status') as $value){$this->set_status($value->nodeValue);}
       foreach ($this->_xmlDoc->getElementsByTagName('StatusDescription') as $value){$this->set_status_description($value->nodeValue);}
    }

   /**
	* Удаляем переменные
   	*/
    private function _unset()
    {
      unset(
             $this->_shop_name,
             $this->_invoice,
             $this->_merchant,
             $this->_cart,
             $this->_amount,
             $this->_prefix
       );
     }
}
?>
