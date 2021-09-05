# Courier Tracking

## Hakkında
Courier Tracking uygulaması kuryeye ait lokasyon ve zaman bilgilerini alıp belli koşullara göre loglayan ve kuryenin gezdiği mesafeye ait bilgileri sunan bir restful web uygulamasıdır.

## Başlarken
Courier tracking uygulaması amazon web servisleri üzerinde çalışmaktadır. *Amazon EC2, S3 ve RDS* servislerini kullanmaktadır. Uygulamaya erişim *ec2-3-135-188-185.us-east-2.compute.amazonaws.com* linki üzerinden gerçekleştirilmektedir. Kullanım kısmında gösterilen 2 restful servis üzerinden iletişim gerçekleşmektedir. Servislerin çağrılması için *Postman* benzeri rest client uygulaması gerekmektedir.

### Teknolojiler
```
Java
Spring Boot
Maven
Mysql
Amazon AWS EC2, S3, RDS
```

### Gereksinimler
```
Java 8
Postman
```

### Kurulum
Courier tracking uygulaması amazon web servisleri üzerinden çalışmaktadır. Erişim için *Postman* dışında bir tool'a gerek yoktur. Fakat local ortamda çalıştırılmak isteniyorsa aşağıdaki komutlar çalıştırılarak uygulama başlatılır, *http://localhost:8080* üzerinden erişim gerçekleştirilir.
```
mvn clean install
cd target
java -jar couriertracking.jar
```

### Kullanım
- Postman uygulaması ile *ec2-3-135-188-185.us-east-2.compute.amazonaws.com:8080/courier/enter* post isteği ile kurye girişi yapılmaktadır. Post isteğinin body kısmı row/json file şeklinde ve aşağıdaki formatta yapılmalıdır.

{
	"courier": 8,
	"lat": 40.9632463,
	"lng": 29.0630908,
	"time": "2021-09-04T19:52:39.771114"
}


- Postman uygulaması ile *ec2-3-135-188-185.us-east-2.compute.amazonaws.com:8080/courier/distance/{courierId}* get isteği ile Total Travel Distance sonucu alınmaktadır.

ec2-3-135-188-185.us-east-2.compute.amazonaws.com:8080/courier/distance/8

## Yazar
* **Bora Ünal**
