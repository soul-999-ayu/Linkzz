import java.awt.*;
import java.awt.Dimension;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import edu.duke.ImageResource;

public class Link extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	JPanel panel = new JPanel(null);
	JButton button[] = new JButton[5];
	JLabel label[] = new JLabel[5];
	String buttonlabel[] = {"Generate", "Save QR", "", "", ""}, labels[] = {"Linkzz", "Enter URL:", "QR Code:", "", "<html><b>Fact about QR Code:</b><br>A QR code is a type of two-dimensional matrix barcode, invented in 1994, by Japanese company Denso Wave for labelling automobile parts.</html>"}, theme = "";
	JTextField urlfield = new JTextField();
	
	// Function to create the QR code
	@SuppressWarnings("deprecation")
	public static void createQR(String data, String path, String charset, Map<EncodeHintType, ErrorCorrectionLevel> hashMap, int height, int width) throws WriterException, IOException{
		BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, width, height);
		MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
	}
	
	public void setTheme(){
		File myObj = new File("C:\\Users\\"+System.getProperty("user.name")+"\\.linkzz");
		if(myObj.exists() && myObj.isFile()) {
			try {
			      Scanner myReader = new Scanner(myObj);
			      String data = "";
			      while (myReader.hasNextLine()) {
			        data = data + myReader.nextLine();
			      }
			      myReader.close();
			      if(data.contains("dark")) {
			    	  ImageIcon imageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("light.png")).getScaledInstance(button[2].getWidth(), button[2].getHeight(), Image.SCALE_SMOOTH));
			    	  button[2].setIcon(imageIcon);
			    	  this.getContentPane().setBackground(Color.black);
			    	  theme = "dark";
			    	  panel.setBackground(Color.black);
			    	  for(int i=1; i<5; i++)
				    	  label[i].setForeground(Color.white);
			    	  
			    	  File f = new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\.tmp\\qr.png");
			    	  if(f.exists() && f.isFile()) {
			    		  BatchInversions b = new BatchInversions();
				    	  ImageResource inImage = new ImageResource(f);
				          ImageResource imageInv = b.makeInversion(inImage);
				          imageInv.setFileName("inverted-" + inImage.getFileName());
				          imageInv.save();
			    	  }
			          label[3].setIcon(new ImageIcon (ImageIO.read (new File ("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\.tmp\\inverted-qr.png"))));
			      }
			    	  
			      else {
			    	  ImageIcon imageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("dark.png")).getScaledInstance(button[2].getWidth(), button[2].getHeight(), Image.SCALE_SMOOTH));
			    	  button[2].setIcon(imageIcon);
			    	  this.getContentPane().setBackground(Color.white);
			    	  theme = "light";
			    	  panel.setBackground(Color.white);
			    	  for(int i=1; i<5; i++)
				    	  label[i].setForeground(Color.black);
			    	  label[3].setIcon(new ImageIcon (ImageIO.read (new File ("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\.tmp\\qr.png"))));
			      }
			}catch (Exception e) {}
		}
		else {
			try{
			 FileWriter myWriter = new FileWriter("C:\\Users\\"+System.getProperty("user.name")+"\\.linkzz");
		      myWriter.write("light");
		      myWriter.close();
		    } catch (IOException e1) {
		      e1.printStackTrace();
		    }
			setTheme();
		}
	}	
	
	Link(){
		
		
		for(int i=0; i<5; i++) {
			button[i] = new JButton(buttonlabel[i]);
			button[i].setForeground(Color.BLUE);
			button[i].setBackground(new Color(255, 147, 147));
			button[i].setFont(new Font("MV Boli", Font.PLAIN, 13));
			button[i].setFocusable(false);
			button[i].setVisible(true);
			button[i].addActionListener(this);
			
			label[i] = new JLabel(labels[i]);
			this.add(label[i]);
			this.add(button[i]);
	}
		
		button[1].setBounds(130, 400, 90, 40);
		button[2].setBounds(320, 5, 25, 25);
		button[2].setBackground(null);
		button[2].setBorder(null);
		
		panel.setBounds(0, 0, 500, 40);
		panel.add(label[0]);
		panel.add(button[2]);
		this.add(panel);
		
		label[0].setBounds(140, 5, 80, 25);
		label[0].setForeground(Color.red);	
		label[0].setFont(new Font("MV Boli", Font.BOLD, 25));
		label[1].setFont(new Font("MV Boli", Font.BOLD, 15));
		label[2].setFont(new Font("MV Boli", Font.BOLD, 15));
		label[1].setBounds(20, 50, 320, 40);
		urlfield.setBounds(20, 80, 320, 40);
		label[2].setBounds(20, 180, 320, 40);
		label[2].setVisible(false);	
		label[3].setBounds(75, 210, 320, 180);
		label[4].setFont(new Font("MV Boli", Font.PLAIN, 15));
		label[4].setBounds(20, 430, 320, 180);
		urlfield.setFont(new Font("MV Boli", Font.PLAIN, 15));
		button[0].setBounds(130, 130, 90, 40);
		this.add(urlfield);
		
		
		this.setTitle("Linkzz");
		this.setIconImage(new ImageIcon("src/bot.jpg").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setSize(new Dimension(380, 250));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter(){
			  @Override
			  public void windowClosing(WindowEvent e){
			     label[2].setIcon(null);
			     try {
					FileUtils.deleteDirectory(new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\.tmp"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			  }
			});  
		setTheme();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button[0] && !(urlfield.getText().isEmpty())) {
			this.setSize(new Dimension(380, 630));
			
			label[2].setVisible(true);	
			
			File theDir = new File("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\.tmp"); 
			if (!theDir.exists())
			    theDir.mkdirs();
	
			Map<EncodeHintType, ErrorCorrectionLevel> hashMap
				= new HashMap<EncodeHintType,
							ErrorCorrectionLevel>();
	
			hashMap.put(EncodeHintType.ERROR_CORRECTION,
						ErrorCorrectionLevel.L);

			try {
				createQR(urlfield.getText(), "C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\.tmp\\qr.png", "UTF-8", hashMap, 200, 200);
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
			
			label[2].setText("QR Code: ("+urlfield.getText()+")");
			
			try {
				label[3].setIcon(new ImageIcon (ImageIO.read (new File ("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\.tmp\\qr.png"))));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			setTheme();
		}
		
		if(e.getSource()==button[1]) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileFilter(new FileNameExtensionFilter("PNG Files", "png"));
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				String fullpath = file.getAbsolutePath(), nstr = "";
				Character ch;
			  
				for (int i=0; i<fullpath.length(); i++){
					ch= fullpath.charAt(i);
					nstr= ch+nstr;
				}

				String rname = nstr.substring(0, nstr.indexOf("\\")), rpath = nstr.substring(nstr.indexOf("\\")+1), name = "", path = "";
			  
				ch = null;
				for (int i=0; i<rname.length(); i++){
			        ch= rname.charAt(i);
			        name= ch+name;
				}
			  
				ch = null;
				for (int i=0; i<rpath.length(); i++){
			        ch= rpath.charAt(i);
			        path= ch+path;
				}
			  
				if(!name.contains(".png"))
					name=name+".png";
			 
				Path temp = null;
				try {
					temp = Files.move(Paths.get("C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\.tmp\\qr.png"), Paths.get(path+"\\"+name));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if(temp != null){
					JOptionPane.showMessageDialog(this, "Your QR Code is saved successfuly.", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(this, "Unable to save QR Code, Please try again.", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
		if(e.getSource()==button[2]) {
			if(theme.equals("dark")) {
				try {
				      FileWriter myWriter = new FileWriter("C:\\Users\\"+System.getProperty("user.name")+"\\.linkzz");
				      myWriter.write("light");
				      myWriter.close();
			    } catch (IOException e1) {
			      e1.printStackTrace();
			    }
			}
			else {
				try {
				      FileWriter myWriter = new FileWriter("C:\\Users\\"+System.getProperty("user.name")+"\\.linkzz");
				      myWriter.write("dark");
				      myWriter.close();
			    } catch (IOException e1) {
			      System.out.println("An error occurred.");
			      e1.printStackTrace();
			    }
			}
			setTheme();
		}
	}
}