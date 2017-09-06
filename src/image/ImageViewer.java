package image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import graphs.Graph;

public class ImageViewer {
	private BufferedImage image;;JFrame frame;
	JLabel label;
	public ImageViewer() throws IOException{
		frame=new JFrame("image viewer"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		image=ImageIO.read(new File("natalie.bmp"));
		
		//this little sections is to force a colorSpace,
		BufferedImage temp=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
		temp.getGraphics().drawImage(image, 0, 0, null);
		image=temp;
		
		
		ImageIcon icon=new ImageIcon();
		icon.setImage(image);
		label=new JLabel(icon);
		
		frame.add(label);
		frame.pack();
		frame.setVisible(true);
	}
	
	
	
	int counter=0;
	public void refresh(){
	
		counter++;
		if(counter%10==0){
		frame.repaint();

		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	
	public Raster getImageRaster(){
		return image.getRaster();
	}
	
	
	public static void main(String[] args) throws IOException{
		ImageViewer view =new ImageViewer();
		Graph g=new rasterGraph(view.getImageRaster());
		graphs.GraphAlgorithms.addObserver(()->view.refresh());
		graphs.GraphAlgorithms.breadthFirstSearch(g);
		view.frame.repaint();
	}

}
