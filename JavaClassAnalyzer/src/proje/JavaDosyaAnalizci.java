////////////////////////////////////////////////////////
//													  //
//             T.C. SAKARYA ÜNİVERSİTESİ              //
//      BİLGİSAYAR VE BİLİŞİM BİLİMLERİ FAKÜLTESİ     //
//           BİLGİSAYAR MÜHENDİSLİĞİ BÖLÜMÜ           //
//    PROGRAMLAMA DİLLERİNİN PRENSİPLERİ ÖDEV RAPORU  //
//                      1.ÖDEV                        //
//                                                    //
//            Muhammed İyd - B211210569               //
//                1.Öğretim B grup                    //
//                                                    //
//         muhamad.iyd1@ogr.sakarya.edu.tr            //
//     Programın yazıldığı tarih: <05.04.2024>        //
//													  //
////////////////////////////////////////////////////////



/*
  
<p>

JavaDosyaAnalizci sınıfı: Bu sınıf, Belirtilen dizindeki Java dosyalarını bulur
ve bu dosyalardaki kodları analiz eder.
Analiz, yorum satırları, kod satırları, toplam satır sayısı,
fonksiyon sayısı ve yorumların kod üzerindeki dağılımını içerir.

</p>

*/

package proje;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaDosyaAnalizci {
	
	public static void javaDosyalariniAnalizEt(File klasor) {
        for (File dosyaGirisi : klasor.listFiles()) {
            if (dosyaGirisi.isDirectory()) {
            	javaDosyalariniAnalizEt(dosyaGirisi);
            } else if (dosyaGirisi.getName().endsWith(".java")) {
                try {
                	javaDosyasiniAnalizEt(dosyaGirisi);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
	
	
	
	 private static void javaDosyasiniAnalizEt(File klasor) throws Exception {
	    	List<String> satirlar = Files.readAllLines(Paths.get(klasor.getPath()));
	        String content = String.join("\n", satirlar); // Satır listesini tek bir dizeye dönüştür
	        
	        // Bir genel snif bildirimi bulmak icin düzenli ifade
	        Pattern publicClassPattern = Pattern.compile("\\bpublic\\s+class\\s+\\w+");
	        Matcher matcher = publicClassPattern.matcher(content);
	        
	        // Dosya bir genel sinif icermiyorsa hemen don
	        if (!matcher.find()) {
	            return;
	        }
	
	        
	        boolean javadocYorumunda = false; // in Javadoc Comment
	        boolean cokluYorumda = false; // in Multi Comment
	        int javadocYorumSatirlari = 0; // Javadoc Comment Lines
	        int digerYorumSatirlari = 0; // Other comment lines
	        int kodSatirlari = 0; // Code Lines
	        int  fonksiyonSayisi = 0; // Function Count
	        int toplamSatirlar = satirlar.size(); // Total Lines
	        
	        Pattern javadocBaslangicDuzeni = Pattern.compile("^.*/\\*\\*.*"); // javadoc Start Pattern
	        Pattern javadocBitisDuzeni = Pattern.compile(".*\\*/$"); //javadoc End Pattern
	        Pattern cokluBaslangicDuzeni = Pattern.compile("^.*/\\*.*"); // Multi Start Pattern
	        Pattern cokluBitisDuzeni = Pattern.compile(".*\\*/$"); //Multi End Pattern
	        Pattern yorumDuzeni = Pattern.compile(".*//.*"); // Comment Pattern
	        Pattern yorumVeKodDuzeni = Pattern.compile(".*;\\s?//.*"); // Comment and code pattern
	        Pattern bosSatirDuzeni = Pattern.compile("^[ \\t]*$"); // Blank line pattern
	        Pattern fonksiyonDuzeni = Pattern.compile
	        ("\\b(public|private|protected|static|final)\\s+\\w+\\s*\\w*\\s*\\(.*\\)\\s*\\{?");
	        
	        for (String satir : satirlar) {
	            if (javadocBaslangicDuzeni.matcher(satir).find()) {
	            	javadocYorumunda = true;
	            } else if (javadocBitisDuzeni.matcher(satir).find() && javadocYorumunda) {
	            	javadocYorumunda = false;
	            } else if (javadocYorumunda) {
	            	javadocYorumSatirlari++;
	            } else if (cokluBaslangicDuzeni.matcher(satir).find()) {
	            	cokluYorumda = true;
	            } else if (cokluBitisDuzeni.matcher(satir).find() && cokluYorumda) {
	            	cokluYorumda = false;
	            } else if (cokluYorumda) {
	            	digerYorumSatirlari++;
	            } else if (yorumVeKodDuzeni.matcher(satir).find()) {
	            	digerYorumSatirlari++;
	            	kodSatirlari++;
	            } else if (yorumDuzeni.matcher(satir).find()) {
	            	digerYorumSatirlari++;
	            } else if (!bosSatirDuzeni.matcher(satir).find()) {
	            	kodSatirlari++;
	            }
	            if(fonksiyonDuzeni.matcher(satir).find()) {
	            	fonksiyonSayisi++;
	            }
	        }
	        
	        double YG = ((javadocYorumSatirlari + digerYorumSatirlari) * 0.8) / fonksiyonSayisi;
	        double YH = (kodSatirlari / (double)fonksiyonSayisi) * 0.3;
	        double deviationPercentage = ((100 * YG) / YH) - 100;
	        
	        System.out.println("-------------------------------------------------");
	        System.out.println("Class: " + klasor.getName());
	        System.out.println("Javadoc Satır Sayısı: " + javadocYorumSatirlari);
	        System.out.println("Yorum Satır Sayısı: " + digerYorumSatirlari);
	        System.out.println("Kod Satır Sayısı: " + kodSatirlari);
	        System.out.println("Total Satırlar (LOC): " + toplamSatirlar);
	        System.out.println("Fonksiyon Sayısı: " + fonksiyonSayisi);
	        System.out.printf("Yorum Sapma Yüzdesi: %.2f%%\n", deviationPercentage);
	        
	    }
	   
	    }
	
	
	
	
	
	

