package main.tests;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;
import main.Ant;
import main.Swarm;

public class AntTest {

	@Test
	public void test() {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("image.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed loading image");
			System.exit(1);
		}
		
		Swarm swarm = new Swarm(image);
		
	}

}
