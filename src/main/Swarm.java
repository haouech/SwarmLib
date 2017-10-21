package main;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.DoubleFunction;

import javax.imageio.ImageIO;

public class Swarm {
	
	Factory factory;
	Pheromones pheromones;
//	BufferedImage image;
	
	public Swarm(BufferedImage image) {
		Variables.image = image;
		Variables.HEIGHT = image.getHeight();
		Variables.WIDTH = image.getWidth();
		Variables.NUM_ANTS = (int)Math.sqrt(1.0*Variables.HEIGHT*Variables.WIDTH);
		System.out.println("Num Ants: " + Variables.NUM_ANTS);
//		Variables.ANT_DISTANCE = Variables.HEIGHT + Variables.WIDTH;
		Variables.antList = new ArrayList<Ant>();
		Variables.pixels = convert2DImage();
		Variables.trails = new double[Variables.HEIGHT][Variables.WIDTH];
		Variables.visited = new int[Variables.HEIGHT][Variables.WIDTH];
		pheromones = new Pheromones();
		factory = new Factory(pheromones);
	}
	
	public void search() {
		if(Variables.image == null) {
			System.out.println("Please check your image before searching");
			return;
		}
		init();
//		printArray(Variables.heuristic);
//		System.out.println("************");
//		printArray(Variables.pixels);
		printArray(Variables.trails);
		for(int i=0; i<Variables.ITERATIONS; i++) {
			System.out.println("******  " + (i+1) + "  ******");
			init_visited();
			factory.generate();
			System.out.println("******  " + (i+1) + "  ******");
			pheromones.update();
		}
		

		printArray(Variables.trails);
		saveFinalImage();
		
	}
	
	public void init() {
		pheromones.init_trails();
		Variables.heuristic = init_heuristic_matrix();
	}
	
	public void init_visited() {
		for(int i=0; i<Variables.WIDTH; i++) {
			for(int j=0; j<Variables.HEIGHT; j++) {
				Variables.visited[i][j] = 0;
			}
		}		
	}
	
	private double[][] init_heuristic_matrix() {
		DoubleFunction<Double> f = x->{return Variables.LAMBDA*x;};
		double Z = normalize_image(f);
		System.out.println("Z: " + Z);
		int width = Variables.WIDTH;
		int height = Variables.HEIGHT;
		double[][] heuristic = new double[height][width];
		
		for(int row=0;row<height; row++) {
			for(int col=0; col<width; col++) {
				heuristic[row][col] = VC(new Pixel(row, col), f) / Z; // * Variables.pixels[row][col];
			}
		}
		return heuristic;
	}
	
	private double normalize_image(DoubleFunction<Double> f) {
		// TODO Auto-generated method stub
		int width = Variables.WIDTH;
		int height = Variables.HEIGHT;
		double Z = 0.0;
		for(int row=0;row<height; row++) {
			for(int col=0; col<width; col++) {
				Z += VC(new Pixel(row, col), f);
			}
		}
		return Z * 1e-6;
	}
	
	private double VC(Pixel pixel, DoubleFunction<Double> f) {
		/**
		 * VC(x,y) = f(|I(x-2,y-1)-I(x+2,y+1)| + |I(x-2,y+1)-I(x+2,y-1)|
		 * 			 + |I(x-1,y-2)-I(x+1,y+2)| + |I(x-1,y-1)-I(x+1,y+1)|
		 * 			 + |I(x-1,y)  -I(x+1,y)  | + |I(x-1,y+1)-I(x-1,y-1)|
		 * 			 + |I(x-1,y+2)-I(x-1,y-2)| + |I(x  ,y-1)-I(x  ,y+1)|) 
		 */
		int x = pixel.getX();
		int y = pixel.getY();
		long value = 0;
		if(x-2 >=0 && y-1 >= 0 && x+2 < Variables.HEIGHT && y+1 < Variables.WIDTH) {
			value += Math.abs(Variables.pixels[x-2][y-1] - Variables.pixels[x+2][y+1]);
		}
		if(x-2 >=0&& x+2 < Variables.HEIGHT && y-1 >= 0 && y+1 < Variables.WIDTH) {
			value += Math.abs(Variables.pixels[x-2][y+1] - Variables.pixels[x+2][y-1]);
		}
		if(x-1 >=0 && y-2 >= 0 && x+1 < Variables.HEIGHT && y+2 < Variables.WIDTH) {
			value += Math.abs(Variables.pixels[x-1][y-2] - Variables.pixels[x+1][y+2]);
		}
		if(x-1 >=0 && y-1 >= 0 && x+1 < Variables.HEIGHT && y+1 < Variables.WIDTH) {
			value += Math.abs(Variables.pixels[x-1][y-1] - Variables.pixels[x+1][y+1]);
		}
		if(x-1 >=0 && x+1 < Variables.HEIGHT) {
			value += Math.abs(Variables.pixels[x-1][y] - Variables.pixels[x+1][y]);
		}
		if(x-1 >=0 && y-1 >= 0 && y+1 < Variables.WIDTH) {
			value += Math.abs(Variables.pixels[x-1][y+1] - Variables.pixels[x-1][y-1]);
		}
		if(x-1 >=0 && y-2 >= 0 && y+2 < Variables.WIDTH) {
			value += Math.abs(Variables.pixels[x-1][y+2] - Variables.pixels[x-1][y-2]);
		}
		if(y-1 >= 0 && y+1 < Variables.WIDTH) {
			value += Math.abs(Variables.pixels[x][y-1] - Variables.pixels[x][y+1]);
		}
		return f.apply(value);
	}
	
	private int[][] convert2DImage() {

		final byte[] pixels = ((DataBufferByte) Variables.image.getRaster().getDataBuffer()).getData();
		final int width = Variables.image.getWidth();
		final int height = Variables.image.getHeight();
		final boolean hasAlphaChannel = Variables.image.getAlphaRaster() != null;
		
		int[][] result = new int[height][width];
		int alpha = hasAlphaChannel?1:0;
		final int pixelLength = 3 + alpha;
		for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
			int argb = 0;
			argb += (int) (pixels[pixel + alpha] & 0xff); // blue
			argb += (int) (pixels[pixel + alpha + 1] & 0xff); // green
			argb += (int) (pixels[pixel + alpha + 2] & 0xff); // red
			result[row][col] = argb/3;
			col++;
			if (col == width) {
				col = 0;
				row++;
			}
		}

		return result;
	}
	
	public void loadGrayscaleImage() {
		int[][] pixels = convert2DImage();
		BufferedImage mImage = new BufferedImage(Variables.WIDTH, Variables.HEIGHT, BufferedImage.TYPE_BYTE_GRAY);
		for(int row=0; row<pixels.length; row++) {
			for(int col=0; col<pixels[0].length; col++) {
				mImage.setRGB(col, row, pixels[row][col]);
			}
		}

	    File ImageFile = new File("grayscale.png");
	    try {
	        ImageIO.write(mImage, "png", ImageFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void saveFinalImage() {
		
		BufferedImage mImage = new BufferedImage(Variables.WIDTH, Variables.HEIGHT, BufferedImage.TYPE_BYTE_GRAY);
		for(int row=0; row<Variables.HEIGHT; row++) {
			for(int col=0; col<Variables.WIDTH; col++) {
				if(Variables.trails[col][row] > Variables.THRESHHOLD) {
					mImage.setRGB(col, row, 255);
				} else {
					mImage.setRGB(col, row, (1 << 16) - 1);
				}
				
			}
		}

	    File ImageFile = new File("final.png");
	    try {
	        ImageIO.write(mImage, "png", ImageFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void printArray(double[][] Array) {
		for(int i=0; i<Variables.WIDTH; i++) {
			for(int j=0; j<Variables.HEIGHT; j++) {
				if(Array[i][j] < 1e-3) {
					System.out.print(0 + " ");
				} else {
					System.out.printf("%.3f ",Array[i][j]);
				}				
			}
			System.out.println();
		}		
	}
	
	public void printArray(int[][] Array) {
		for(int i=0; i<Variables.WIDTH; i++) {
			for(int j=0; j<Variables.HEIGHT; j++) {
				System.out.print(Array[i][j] + " ");
			}
			System.out.println();
		}		
	}
	
}
