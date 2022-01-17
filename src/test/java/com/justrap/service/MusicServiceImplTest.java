package com.justrap.service;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;
import java.nio.file.Path;
import static java.nio.file.Paths.get;
import static java.nio.file.Files.newInputStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import com.justrap.config.WebMvcConfig;
import com.justrap.model.Beat;
import com.justrap.model.Song;
import com.justrap.model.UserInterface;
import com.justrap.service.impl.MusicServiceImpl;
import com.justrap.service.impl.UserServiceImpl;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@WebAppConfiguration // because there is @EnableWebMvc in WebMvcConfig.java
class MusicServiceImplTest {

	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Autowired
	private MusicServiceImpl service;
	
	@Autowired
	private UserServiceImpl serviceUser;
	
	// Test of createMusicByFile(MultipartFile file, boolean isSong, String musicName, UserInterface artist)
	// Test of getMusicsByName(String name)
	// Test of getFile(Music music)
	@Test
	void testCreateMusic() throws Exception{
		String pathFile = "/Users/fanny/Downloads/bekar-aleas-clip-officiel.mp3";
		String songName = "Aleas";
		int nbMusic = service.getMusicsByName(songName).size();
		
		UserInterface artist = serviceUser.getUserById("4IEwZXLE7oQzNy7dW3SE7BKvjmy2");
		Path path = get(new URL("file:///c:"+pathFile).toURI());
		
	    MockMultipartFile file = new MockMultipartFile("mySongFile", pathFile, null,  newInputStream(path));
		service.createMusicByFile(file, true, songName, artist);
		
		assertEquals(service.getMusicsByName(songName).get(0).getClass(), Song.class);
		assertNotEquals(service.getMusicsByName(songName).get(0).getClass(), Beat.class);
		assertEquals(service.getMusicsByName(songName).size(), nbMusic+1);
		
		//assertEquals(service.getFile(service.getMusicsByName("mySong").get(0)), ""+service.getMusicsByName("mySong").get(0).getId()+".mp3");

	}

}
