# Courier Tracking

## Getting Started

Courier tracking uygulaması amazon web servisleri üzerinden çalışmaktadır. Uygulamaya erişim ec2-3-135-188-185.us-east-2.compute.amazonaws.com linki üzerinden gerçekleştirilmektedir. Aşağıdaki 2 restful servis
üzerinden iletişim gerçekleşmektedir. Servislerin çağrılması için postman benzeri rest client uygulaması gerekmektedir.

### Prerequisites
```
Postman
```

### Installing
Courier tracking uygulaması amazon web servisleri üzerinden çalışmaktadır. Kurulama gerek kalmadan aşağıdaki örneklere benzer şekilde erişim sağlanır.

- Postman uygulaması ile *ec2-3-135-188-185.us-east-2.compute.amazonaws.com:8080/courier/enter* post isteği ile courier girişi yapılmaktadır. Post isteğinin body kısmı row/json file şeklinde ve aşağıdaki formatta yapılmalıdır.

{
	"courier": 8,
	"lat": 40.9632463,
	"lng": 29.0630908,
	"time": "2021-09-04T19:52:39.771114"
}


- Postman uygulaması ile *ec2-3-135-188-185.us-east-2.compute.amazonaws.com:8080/courier/distance/{courierId}* get isteği ile Total Travel Distance sonucu alınmaktadır.
ec2-3-135-188-185.us-east-2.compute.amazonaws.com:8080/courier/distance/8

## Authors
* **Bora Ünal**
