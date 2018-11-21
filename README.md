Репозиторий хранит описание и примеры использования всех публичных API allpay

- **SOAP API**.
Модуль общение по протоколу SOAP описан [здесь](https://github.com/allpaykz/allpay-public/tree/develop/allpay-public-soap)
Для авторизации запросов используются цифровые ключи.
- **Web API для интеграции с онлайн магазинами.**
Модуль описан [здесь](https://github.com/allpaykz/allpay-public/tree/develop/webshop-integration)

### Подключение библиотек этого проекта

**Вариант А.** Нужно сбилдить весь проект

1. `git clone https://github.com/allpaykz/allpay-public.git`
2. `mvn clean install -P beta`

После удачного билда, можете добавить в свой проект зависимость для использования TransactionManagementV1_0Client

        <dependency>
            <artifactId>allpay-public-soap-client</artifactId>
            <groupId>kz.allpay.mfs</groupId>
            <!-- соответствующая версия проекта - версия указанная в pom.xml в этом проекте -->
            <version>2.0.8.3</version>
        </dependency>

**Вариант Б.** Для использования библиотек через maven необходимо подключить следущие репозиориии:

pom.xml:
```xml
    <repositories>

        <repository>
            <id>oss-sonatype-snapshots</id>
            <name>oss-sonatype-snapshots</name>
            <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>

        <repository>
            <id>oss-sonatype</id>
            <name>oss-sonatype</name>
            <url>https://oss.sonatype.org/content/groups/public/</url>
        </repository>

    </repositories>
    ...
```

Для использования последней версии нужно подключить снапшот-зависимость, они собираются автоматически на пуш и всегда доступны самые свежие.
Пример зависимости:
```xml
        <!-- пример snapshot зависимости -->
        <dependency>
            <artifactId>webshop-integration-keypair</artifactId>
            <groupId>kz.allpay.mfs</groupId>
            <!-- соответствующая версия проекта - версия указанная в pom.xml в этом проекте -->
            <version>2.0.8.3-SNAPSHOT</version>
        </dependency>
```

При деплое в продакшн необходимо использования финальную версию:

```xml
        <dependency>
            <artifactId>webshop-integration-keypair</artifactId>
            <groupId>kz.allpay.mfs</groupId>
            <!-- соответствующая версия проекта - версия указанная в pom.xml в этом проекте -->
            <version>2.0.8.3</version>
        </dependency>
```

Для большей уверенности, можно проверить подпись: 

```bash
mkdir -p /tmp/check
cd /tmp/check 
wget https://oss.sonatype.org/content/groups/public/kz/allpay/mfs/webshop-integration-keypair/2.0.8.1/webshop-integration-keypair-2.0.8.1-javadoc.jar
wget https://oss.sonatype.org/content/groups/public/kz/allpay/mfs/webshop-integration-keypair/2.0.8.1/webshop-integration-keypair-2.0.8.1-javadoc.jar.asc
gpg --verify webshop-integration-keypair-2.0.8.1-javadoc.jar{.asc,}
# answer:
# gpg: Signature made Mon 26 Dec 2016 07:36:28 PM +06 using RSA key ID DEF0290B
# gpg: Good signature from "Magzhan Karassayev (work-gpg) <magzhan.karasayev@allpay.kz>"
```

Или просто собрать проект из сорсов на гитхабе(Вариант А).
