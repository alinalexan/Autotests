package task5.QATestTask5.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UserDataGenerator {

	enum names {Mary,Emily,Kylie,Jessica,Susan,Sarah,Erica,Kelly,Dorothy}
	enum lastnames {Dillinger,McConnahy,Dasigan,Fielding,Attias,Spark,Morales,
					Greeley,Coen,Doty,Kimmett,Lassar,Curran,Cordue,Hatfield}
	enum mailservers {yandex,mail,yahoo,gmail,aol,outlook}
	enum streets {Harbour,Isle,Green,Heather,Dale,Cinder,Tawny,Anchor,Quiet,
					Blossom,Silent,River,Amber,Honey,Robin,Sunny,Harvest}
	

	String name;
	String lastName;
	String email;
	
	public String generateLogin(){
		Date date = new Date();
		DateFormat generated = new SimpleDateFormat("MMddhhmm");
		return (generated.format(date));
	}
	
	public String generateLogin(String name){
		Date date = new Date();
		DateFormat generated = new SimpleDateFormat("MMddhhmm");
		return (name+generated.format(date));
	}
		
	public String generateName (){
		String name = names.values()[(int) new Random()
						.nextInt(names.values().length)].toString();
		this.name = name;
		return name;
	}

	public String generateLastName () {
		String lastName = lastnames.values()[(int)new Random()
						.nextInt(lastnames.values().length)].toString();
		this.lastName = lastName;
		return lastName;
	}

	public String generateEmail () {
		String mailserver = mailservers.values()[(int)new Random()
						.nextInt(mailservers.values().length)].toString();
		String email = generateLogin(name)+"@"+mailserver+".com";
		return email;
	}
	
	public String generateZip(){
		Integer raw = new Random().nextInt(62000)+10000;
		return raw.toString();
	}
	
	public String generateAddress(){
		String street = streets.values()[(int) new Random()
						.nextInt(streets.values().length)].toString();
		return street + ", " +(int) Math.random()*100 + 1; 
	}
	
	public String generateCity(){
		return "Kharkov";
	}
}