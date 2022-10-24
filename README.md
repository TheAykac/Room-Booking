<div align="center"><h1> :sparkles: Room Booking Project :sparkles: </h1> </div>
<div align="center"><h2> 🚥 ODA REZERVASYON PROJESİ 🚥 </h2> </div>

:point_right:  'Room Booking' This Project is the Final Project of ETSTUR Bootcamp. Tools: **Java Spring-Hibernate-ORM-Maven-Spring Boot-AOP-DTO-JPA-RestAPI-JSON-Dev Tools-PostgreSQL**
<br/>
 ``` 'Room Booking', Bu proje ETSTUR bootcamp'ın bitirme projesidir, Java Spring Framework ile geliştirilmiştir . Araçlar: **Java Spring-Hibernate-ORM-Maven-Spring Boot-AOP-DTO-JPA-RestAPI-JSON-Dev Tools-PostgreSQL**```
<br/>
<div align="center"><h1>  ÖZET :scroll: </h1> </div>
Bu çalışmamda, işleyiş şu şekilde gerçekleşmektedir. Sisteme oteller kaydolabilir. Sisteme kaydolan otel yöneticilerin kayıtlarını sistem yöneticileri tarafından onaylamaktadır. Onaylanan oteller sisteme otellerine ait odalar ekleyebilmektedir. Eklenen odalar herhangi bir durumdan arıza kaydı açılıp arızalı olarak bildirerek o odanın arıza süresi boyunca kiralanmaması sağlamaktadır. Yapmış olduğumuz projede Oteller gibi aynı zaman müşterilerde kaydolabilir. Kaydolan müşteriler kendilerine uygun tarih aralığında diledikleri otelden diledikleri odayı kiralayabilmektedirler. Müşterilerin kiraladıkları odalar için eğer herhangi bir kampanya koduna sahip değilseler kiraladıkları odanın asıl bedelini ödemeleri gerekir. Kampanya koduna sahip müşteriler kampanyanın uyguladığı indirim üzerinden ödeme yapabilmektedir. Ödeme yaparken dileyen kullanıcı sisteme kredi kartı bilgilerini kaydedebilir. Ödeme yapan her bir kullanıcı için ayrı birer fatura oluşturulmaktadır.

---
<div align="center"><h1>  SUMMARY :scroll:  </h1> </div>
In this work, the process takes place as follows. Hotels can register to the system. The system administrators approve the records of the hotel administrators registered in the system. Approved hotels can add rooms belonging to their hotels to the system. Added rooms can open a fault record for any situation and report them as defective, ensuring that the room is not rented during the downtime. In the project we have done, customers can also register at the same time as hotels. Registered customers can rent a room of their choice from any hotel within the date range that suits them. If the customers do not have a campaign code for the rooms they rent, they must pay the original price of the room they rented. Customers with the campaign code can pay through the discount applied by the campaign. While making a payment, any user can save credit card information in the system. A separate invoice is created for each paying user.


<b><h2> :star: Click for Backend Codes: :point_right: <a href="https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking">BACKEND CODE </a> :point_left: </h2></b> 
``` Backend Kodları İçin Tıklayınız: ```
<b><h2> :star: Click for Database Script Codes: :point_right: <a href="https://github.com/TheAykac/Room-Booking/blob/main/hotelBooking/postgreSql/databaseScript.txt">SCRIPT CODE</a> :point_left: </h2></b>
``` Veritabanı Script Kodları İçin Tıklayınız: ```
<b><h2> :star: Click for Project Video: :point_right: <a href="https://www.youtube.com/watch?v=W35yq0p_pBg">Project Video </a> :point_left: </h2></b> 
``` Proje Tanıtım Videosu İçin Tıklayınız: ```



![](/hotelBooking/project_images/booking/booking.jpg)

---
## `File Structure` 

### ` 🏗️ N-Layered Architecture`
  
<ul><li>	
  <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/api">:open_file_folder: Api</a>
	<ul><li>
			 <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/api/controllers"> :file_folder: controllers </a> </li>
		<li>
			 <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/api/models"> :file_folder: models  </a> </li>
	</ul>
	</li>
	<li>
 <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/business"> :open_file_folder: Business</a>
	<ul>
		<li> <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/business/abstracts">:file_folder: abstracts</a></li>
		<li> <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/business/adapters/posAdapters">:file_folder: adapters.posAdapters</a></li>
		<li> <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/business/concretes">:file_folder: concretes</a></li>
		<li> <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/business/outServices">:file_folder: outServices</a></li>
	</ul>
	</li>
	<li>
   <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/config"> :open_file_folder: Config</a></li>
	<li>
    <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/core/utilities"> :open_file_folder: Core</a>
	<ul>
		<li> <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/core/utilities/enums"> :file_folder: enums</a></li>
		<li> <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/core/utilities/exceptions"> :file_folder: exceptions</a></li>
		<li> <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/core/utilities/generate"> :file_folder: generate</a></li>
		<li> <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/core/utilities/mapping"> :file_folder: mapping</a></li>
		<li> <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/core/utilities/messages"> :file_folder: messages</a></li>
		<li> <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/core/utilities/result"> :file_folder: result</a></li>
	</ul>
	</li>
	<li>
    <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/dataAccess"> :open_file_folder: DataAccess</li></a>
	<li>
    <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/entity"> :open_file_folder: Entity </a>
	<ul>
		<li> <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/entity/abstracts">:file_folder: abstracts</a></li>
		<li> <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/entity/concretes">:file_folder: concretes</a></li>
		<li> <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/entity/dtos">:file_folder: dtos</a></li>
		<li> <a href = "https://github.com/TheAykac/Room-Booking/tree/main/hotelBooking/hotelBooking/src/main/java/com/example/hotelBooking/entity/requests">:file_folder: rquests</a></li>
	</ul>
	</li>
</ul>

---

  
  ## ` 🛠️ Language and Tools` 
<p align="left"> <a href="https://www.java.com" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a> <a href="https://spring.io/" target="_blank"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> </a>
<a href="https://www.postgresql.org/" target="_blank"> <img src="https://upload.wikimedia.org/wikipedia/commons/2/29/Postgresql_elephant.svg" width="40"height="40"/>
<a href="https://projectlombok.org/" target="_blank"> <img src="https://avatars.githubusercontent.com/u/45949248?s=200&v=4" alt="projectlombok" width="40" height="40"/> 
<a href="https://hibernate.org/" target="_blank"> <img src="https://cdn.freebiesupply.com/logos/large/2x/hibernate-logo-png-transparent.png" width="40" height="40"/> 
<a href="https://swagger.io/" target="_blank"> <img src="https://seeklogo.com/images/S/swagger-logo-A49F73BAF4-seeklogo.com.png" width="40" height="40"/> 
<a href="https://spring.io/projects/spring-data-jpa" target="_blank"> <img src="https://huongdanjava.com/wp-content/uploads/2018/01/spring-data.png" width="40"height="40"/>
<a href="https://id.heroku.com/" target="_blank"> <img src="https://cdn-icons-png.flaticon.com/512/873/873120.png" width="40" height="40"/> 
</a>
</p>
 
 
   ---
  
  ## ` 📊 ER Diagram with PostgreSQL`
   ### <a href="https://github.com/TheAykac/Room-Booking/blob/main/hotelBooking/postgreSql/databaseScript.txt">Click</a> for script codes.
<p align="center"><img src="https://github.com/TheAykac/Room-Booking/blob/main/hotelBooking/project_images/diagrams/roomBookingPostgresql.pgerd.png"></p>
  
---

  
 
  ## ` 🔭 Swagger Screenshots and Endpoints`  ###

![SwaggerEkranAlıntısı](https://github.com/TheAykac/Room-Booking/blob/main/hotelBooking/project_images/swagger-ss/campaign-controller.jpg)
![SwaggerEkranAlıntısı](https://github.com/TheAykac/Room-Booking/blob/main/hotelBooking/project_images/swagger-ss/credit-card-controller.jpg)
![SwaggerEkranAlıntısı](https://github.com/TheAykac/Room-Booking/blob/main/hotelBooking/project_images/swagger-ss/customer-controller.jpg)
![SwaggerEkranAlıntısı](https://github.com/TheAykac/Room-Booking/blob/main/hotelBooking/project_images/swagger-ss/hotel-controller.jpg)
![SwaggerEkranAlıntısı](https://github.com/TheAykac/Room-Booking/blob/main/hotelBooking/project_images/swagger-ss/invoice-controller.jpg)
![SwaggerEkranAlıntısı](https://github.com/TheAykac/Room-Booking/blob/main/hotelBooking/project_images/swagger-ss/payment-controller.jpg)
![SwaggerEkranAlıntısı](https://github.com/TheAykac/Room-Booking/blob/main/hotelBooking/project_images/swagger-ss/room-booking-controller.jpg)
![SwaggerEkranAlıntısı](https://github.com/TheAykac/Room-Booking/blob/main/hotelBooking/project_images/swagger-ss/room-controller.jpg)
![SwaggerEkranAlıntısı](https://github.com/TheAykac/Room-Booking/blob/main/hotelBooking/project_images/swagger-ss/room-renovations-controller.jpg)
![SwaggerEkranAlıntısı](https://github.com/TheAykac/Room-Booking/blob/main/hotelBooking/project_images/swagger-ss/system-management-controller.jpg)	

  
  
 
---
 

 
 




## ` 📧 Contact`

Ömer Faruk AYKAÇ

E-Mail - [farukomeraykac@gmail.com](mailto:farukomeraykac@gmail.com)

Linkedin - [linkedin.com/in/omerfarukaykac](https://www.linkedin.com/in/omerfarukaykac/)

Project Link: https://github.com/TheAykac/Room-Booking

---


