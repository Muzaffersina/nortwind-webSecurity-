package com.turkcell.northwind.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data     
@AllArgsConstructor
@NoArgsConstructor   
@Table(name = "products") // Sen bir veritabanı tablosusun demek
@Entity     // Veri tabanı nesnesı oldugunu soyler.
// data yazısını ekleyip cıkarınca sag taraftaki degisim!! -- digerleri icinde..
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // otomatik artmasi.
	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "unit_price")
	private double unitPrice;
	
	/*
	@Column(name = "units_in_stock")
	private int unitsInStock;
	*/
	@Column(name = "quantity_per_unit")
	private String quantityPerUnit;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
}

// Kodlama io javacampında spring e basladıgı kısım....

// ..Bitirme Projesi olacak.. 
//				 RentAcar      
// Bir proje ve katmanlarınızı oluşturunuz ---mvnrepository , bean ..

// --Brand - > Id, Name
//	ListAll , Create , GetById <--- Google dan araştır. içinde varmış çağırarakta yapabilirz
// 	Marka ismi tekrar edemez


// --Color - > Id, Name
//ListAll , Create , GetById <--- Google dan araştır. içinde varmış çağırarakta yapabilirz
//Renk ismi tekrar edemez



/*
 * 
 * Car -- > id,dailyPrice,moldeYear,description,brand,color
 * Car list , add , update , delete , getById
 * Araba listenirken --- >> brandName , colorName
 */


/*						18 ŞUBAT PAİR --- >>>   PAİR 4
 *  SİSTEME RESULT MİMARİSİ EKLEYİNİZ.. ESKİ KODLARI BUNA GÖRE REFACTOR EDİNİZ
 *  ARABALARI GUNLUK FİYATINA GORE AYARLAYINIZ. VERİLEN GUNLUK FİYAT VE O FİYAT ALTINDAKİ ARABALAR LİSTELENECEK
 *  ARABALARI SAYFALI LİSTELEYİNİZ.
 *  ARABALARI GÜNLÜK FİYATLARI UZERINDEN SEÇİME GÖRE ARTAN VEYA DÜŞEN SIRALAYINIZZ.   
 */

/*					4 Mart Pair --- >>> pair4
 * 	Global Exception Handler Advice -->  validation , Business Exception
 *
 *  KODLARDA TRY YOOOOK
 * 
 * 	CarMaintenance -- >> Id , description ,returnDate
 * 	CRUD , getByCarId
 * 		
 * 		--- BAKIM BILGILERI TEK TEK LİSTELENEBİLMELİ....
 * 
 * 
 *  Yeni isterler ; ; Arabalar bakıma gonderilebilir... 
 *  returnDate---- Araba bakımdan dondugunde update edilecek ( Create yaparken returnDate istemiyorm)
 *  
 *  Arabanın kiralama bilgisini tutan tablo oluşturun Rentals -- > Id , CarId,
 *  CustomerId , RentDate(Kiralama tarihi) , ReturnDate(Teslim Tarihi) . araba teslim edilmemişse ReturnDate null'dır.
 *  projenizde bu entityleri oluşturunuz.
 *  CRUD operasyonlarınızı oluşturun 
 *  
 *  ARABA BAKIMDA İSE BUNU KİRAYA VERMEK YASAK !!!!!!    
 *  
 */


/*
 * 
 * 				7 MART PAİR @@@@
 * -Kirada olan araba bakıma gönderilemez...         // circular hatası @Lazy
 * 
 * -Arabalar kiralanırken müşteriler ek hizmet(ler) satın alabilmelidir..-- Bebek koltuğu ,, scooter vs..( AdditionalService )
 *  
 * 
 * 
 */


