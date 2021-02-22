package com.khalilpan;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.khalilpan.beans.User;
import com.khalilpan.beans.UserLog;
import com.khalilpan.beans.phone;
import com.khalilpan.interfaces.PhoneInterface;
import com.khalilpan.interfaces.UserInterface;
import com.khalilpan.interfaces.UserLogInterface;


@Component
public class CommandLineRunner_User implements CommandLineRunner {

	@Autowired
	private UserInterface userInterface;
	@Autowired
	private PhoneInterface phoneInterface;
	@Autowired
	private UserLogInterface userLogInterface;
		
	@Override
	public void run(String... args) throws Exception {
		
		User newUser1 = new User("khalil", "khalil.pan2@gmail.com", "123");
		User addedUser1 = userInterface.save(newUser1);
		System.out.println("Added User ------------> "+addedUser1.toString());
		phone phone=new phone("81","322");
		phone.setUser(newUser1);
		phoneInterface.save(phone);
		UUID uuid1 = UUID.randomUUID();
		UserLog userLog1=new UserLog(newUser1.getId(),LocalDateTime.now(),uuid1.toString());
		userLog1.setUser2(newUser1);
		userLogInterface.save(userLog1);
		
		User newUser2 = new User("antonio", "antonio_alvares@hotmail.com", "123333");
		User addedUser2 = userInterface.save(newUser2);
		System.out.println("Added User ------------> "+addedUser2.toString());
		phone phone2=new phone("81","54334");
		phone2.setUser(newUser2);
		phoneInterface.save(phone2);
		UUID uuid2 = UUID.randomUUID();
		UserLog userLog2=new UserLog(newUser2.getId(),LocalDateTime.now(),uuid2.toString());
		userLog2.setUser2(newUser2);
		userLogInterface.save(userLog2);
		
		User newUser3 = new User("natalia", "nataliagonzales@gmail.com", "6623");
		User addedUser3 = userInterface.save(newUser3);
		System.out.println("Added User ------------> "+addedUser3.toString());
		phone phone3=new phone("81","4333");
		phone3.setUser(newUser3);
		phoneInterface.save(phone3);
		UUID uuid3 = UUID.randomUUID();
		UserLog userLog3=new UserLog(newUser3.getId(),LocalDateTime.now(),uuid3.toString());
		userLog3.setUser2(newUser3);
		userLogInterface.save(userLog3);
		
		//-----------------------test------------------
	
		
        
        
		//---------------------------------------------

		
	}
	
}
