import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("image.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed loading image");
			System.exit(1);
		}
		
		Swarm swarm = new Swarm(image);
		System.out.println("Begin edge detection");
//		swarm.search();
		System.out.println("End");
		swarm.loadGrayscaleImage();
	}

}
