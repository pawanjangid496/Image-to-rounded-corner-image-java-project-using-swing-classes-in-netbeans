//package roundedcornerimage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class RoundedCornerImage extends JFrame implements ActionListener{

    JButton close;
    
    public RoundedCornerImage() throws IOException {
        
        BufferedImage inputImage = loadImage("");
        BufferedImage roundedImage = createRoundedImage(inputImage, 100);

        JLabel imageLabel = new JLabel(new ImageIcon(roundedImage));
        
        setUndecorated(true);
        
        getContentPane().add(imageLabel);
        
        close=new JButton("Close");
        close.setBounds(500,360,150,30);
        close.addActionListener(this);
        imageLabel.add(close);
        
        setSize(710,410);
        setVisible(true);
        setLocation(250,150);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==close){
            dispose();
        }
        
    }

    public static void main(String[] args) throws IOException {
        new RoundedCornerImage();
    }
   
    private BufferedImage loadImage(String imagePath) throws IOException {
        InputStream imageStream = getClass().getResourceAsStream("Images/iceCream.jpg");
        return ImageIO.read(imageStream);
    }
    
    private BufferedImage createRoundedImage(BufferedImage inputImage, int cornerRadius) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = outputImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setClip(new RoundRectangle2D.Float(0, 0, width, height, cornerRadius, cornerRadius));
        g2d.drawImage(inputImage, 0, 0, null);
        g2d.dispose();

        return outputImage;
    }
    
}
