package pizzaProgram;
///////SPECIAL FEATURES USED ARE SWING CLASSES(import javax.swing), AND EXCEPTION HANDLING(try and catch methods)
///imports packages
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.text.*;
import java.net.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.Component;


public class PizzaOrder extends Applet implements ActionListener, KeyListener
{
	
private String[] args;//allows flappy bird to be opened in pizza program

    // variables for toppings with price

    private final double
	small = 9.99,
	medium = 11.99,
	large = 13.99,

	cheese= 1.99,
	pepperoni= 1.99,
	mushrooms= 2.29,
	olives= 2.20,
	bacon= 2.99,
	pineapple= 2.45;

    //Graphical components
    Panel topPanel,
	centerPanel,
	centerQuantityPanel,
	centerSizePanel,
	centerToppingPanel,
	centerPricePanel,
	bottomPanel;

    Label titleLabel,
	quantityLabel,
	amountLabel,
	nameLabel,
	sizeLabel,
	toppingLabel,
	priceLabel,
	priceDisplayLabel;

    Button orderButton;
     Button helpButton;
     Button playButton;

    TextField pizzaText,
	priceText;
     TextField nameText=new TextField(40);
  

    ButtonGroup orderbuttongrp;

    JRadioButton smallJRadio,
	regularJRadio,
	largeJRadio;

    JCheckBox cheeseJCheck,
	pepperoniJCheck,
	mushroomsJCheck,
	olivesJCheck,
	baconJCheck,
	pineappleJCheck;
	
    
    Image img;
    //initializes labels, textfields, radio buttons
    //sets colour of different panels

    public void init ()
    {
    
	URL codeBase= getCodeBase();
	
	img=getImage(codeBase, "pizza.jpg");
	
	resize(550,550);

	// Setting applet
	setBackground (new Color (255, 255, 255));
	setLayout (new BorderLayout (0, 0));
	this.setSize (new Dimension (700, 700));

	// Initializing Panels
	///TOP PANEL
	topPanel = new Panel ();
	topPanel.setBackground (new Color (0, 0, 0));
	topPanel.setLayout (new FlowLayout ());
	///CENTER PANEL
	centerPanel = new Panel ();


	centerQuantityPanel = new Panel ();
	centerQuantityPanel.setLayout (new FlowLayout ());

	centerSizePanel = new Panel ();
	centerSizePanel.setLayout (new FlowLayout ());

	centerToppingPanel = new Panel ();
	centerToppingPanel.setLayout (new GridLayout (2, 3, 0, 0));

	centerPricePanel = new Panel ();
	centerPricePanel.setLayout (new FlowLayout ());
	//BOTTOM PANEL
	bottomPanel = new Panel ();
	bottomPanel.setLayout (new FlowLayout ());

	// Initializing Labels
	//TITLE LABEL
	titleLabel = new Label ("Pablo's Perfect Pizza Party Place");
	titleLabel.setBackground (new Color (0, 0,0));
	titleLabel.setForeground (new Color (255, 255, 255));
	titleLabel.setFont (new Font ("Arial", Font.BOLD, 40));
	//QUANTITY LABEL
	quantityLabel = new Label ("Enter the Amount of Pizzas you would like to order. Also Enter your Name in the Textbox: ");
	quantityLabel.setForeground (new Color (0, 0, 205));
	quantityLabel.setFont (new Font ("Arial", Font.BOLD, 14));
	//AMOUNT LABEL
	amountLabel = new Label ("Amount: ");
	amountLabel.setForeground (new Color (0, 0, 0));
	amountLabel.setFont (new Font ("Arial", Font.BOLD, 14));
	//NAME LABEL
	nameLabel = new Label ("Name: ");
	nameLabel.setForeground (new Color (0, 0, 0));
	nameLabel.setFont (new Font ("Arial", Font.BOLD, 14));
	//SIZE LABEL
	sizeLabel = new Label ("Select the Pizza Size from the Following Options: Small- $9.99  Medium- $11.99  Large- $13.99");
	sizeLabel.setForeground (new Color (0, 0, 205));
	sizeLabel.setFont (new Font ("Arial", Font.BOLD, 14));
	//TOPPING LABEL
	toppingLabel = new Label ("Select the toppings you would like on your pizza.");
	toppingLabel.setForeground (new Color (0, 0, 205));
	toppingLabel.setFont (new Font ("Arial", Font.BOLD, 14));
	//PRICE LABEL
	priceLabel = new Label ("Total price of your pizza order : ");
	priceLabel.setForeground (new Color (200, 0, 0));
	priceLabel.setFont (new Font ("Arial", Font.BOLD, 14));

	priceDisplayLabel = new Label ("$0.00       ");
	priceDisplayLabel.setForeground (new Color (235, 0, 0));
	priceDisplayLabel.setFont (new Font ("Arial", Font.BOLD, 34));

	// Initializing Textfields
	pizzaText = new TextField ("0", 1);
	pizzaText.setFont (new Font ("Arial", Font.BOLD, 14));
	nameText = new TextField ("Enter Your Name Here", 25);
	nameText.setFont (new Font ("Arial", Font.BOLD, 14));

	// Initializing JRadio buttons
	smallJRadio = new JRadioButton ("Small", true);
	regularJRadio = new JRadioButton ("Regular", false);
	largeJRadio = new JRadioButton ("Large", false);

	// Grouping radio buttons
	orderbuttongrp = new ButtonGroup ();
	orderbuttongrp.add (smallJRadio);
	orderbuttongrp.add (regularJRadio);
	orderbuttongrp.add (largeJRadio);

	// Initializing JCheckboxes
	cheeseJCheck = new JCheckBox ("Extra Cheese- $1.99", false);
	pepperoniJCheck = new JCheckBox ("Pepperoni- $1.99", false);
	mushroomsJCheck = new JCheckBox ("Mushrooms- $2.29", false);
	olivesJCheck = new JCheckBox ("Green Olives- $2.20", false);
	baconJCheck = new JCheckBox ("Bacon- $2.99", false);
	pineappleJCheck = new JCheckBox ("Pineapple- $2.45", false);

	// Initializing Buttons
	orderButton = new Button ("Place your Order Now");
	orderButton.setFont (new Font ("Arial", Font.BOLD, 25));
	
	helpButton = new Button ("Help?");
	 helpButton.setBackground(Color.BLACK);
	helpButton.setForeground(Color.WHITE);
	helpButton.setFont (new Font ("Arial", Font.BOLD, 25));
	
	playButton= new Button("Play Game");
	playButton.setFont (new Font ("Arial", Font.BOLD, 25));
	

	// Registering listeners to components
	smallJRadio.addActionListener (this);
	regularJRadio.addActionListener (this);
	largeJRadio.addActionListener (this);

	cheeseJCheck.addActionListener (this);
	pepperoniJCheck.addActionListener (this);
	mushroomsJCheck.addActionListener (this);
	olivesJCheck.addActionListener (this);
	baconJCheck.addActionListener (this);
	pineappleJCheck.addActionListener (this);

	pizzaText.addKeyListener (this);
	nameText.addKeyListener (this);
	

	orderButton.addActionListener (this);
	helpButton.addActionListener (this);
	playButton.addActionListener (this);
	
    }
    ////Starts applet and adds labels, textfields etc...

    public void start ()
    {

	// Top Panel
	
	topPanel.add (titleLabel);
	centerQuantityPanel.add (nameLabel);
	centerQuantityPanel.add (nameText);

	// Nested Center Panels
	
	centerQuantityPanel.add (amountLabel);
	centerQuantityPanel.add (pizzaText);
	

	centerSizePanel.add (smallJRadio);
	centerSizePanel.add (regularJRadio);
	centerSizePanel.add (largeJRadio);

	centerToppingPanel.add (cheeseJCheck);
	centerToppingPanel.add (pepperoniJCheck);
	centerToppingPanel.add (mushroomsJCheck);
	centerToppingPanel.add (olivesJCheck);
	centerToppingPanel.add (baconJCheck);
	centerToppingPanel.add (pineappleJCheck);

	centerPricePanel.add (priceLabel);
	centerPricePanel.add (priceDisplayLabel);

	// Center Panel
	
		centerPanel.add(quantityLabel);
		centerPanel.add(centerQuantityPanel);
		centerPanel.add(sizeLabel);
		centerPanel.add(centerSizePanel);
		centerPanel.add(toppingLabel);
		centerPanel.add(centerToppingPanel);
		centerPanel.add(centerPricePanel);
		

	// Bottom Panel
	bottomPanel.add (orderButton);
	bottomPanel.add (helpButton);
	bottomPanel.add (playButton);

	// Main Applet Window
	add (topPanel, BorderLayout.NORTH);
	add (centerPanel, BorderLayout.CENTER);
	add (bottomPanel, BorderLayout.SOUTH);

       
    }


    // Unused interface methods
    public void keyTyped (KeyEvent e)
    {
    }


    public void keyPressed (KeyEvent e)
    {
    }


  //Perform validation check on user text input
    
    /////////////THIS IS WHERE WE IMPLEMENTED EXCEPTION HANDLING( STOPS THE PROGRAM FROM CRASHING BY TRAPPING ALL INVALID NUMBERS)
     
    public void keyReleased (KeyEvent e)
    {

	// Trap all non-valid numbers
	try
	{
	    Integer.parseInt (pizzaText.getText ());
	}
	catch (NumberFormatException fe)
	{
	    pizzaText.setText ("0");
	    
	}

	refreshPrice ();
    }


   
    public void actionPerformed (ActionEvent e)
    {

	// If 'order now' button is pressed
	if (e.getSource () == orderButton)
	{

	    JOptionPane.showMessageDialog (this,
		    nameText.getText ()+ ", thank you for ordering your pizza from Pablo's Perfect Pizza Party Place! The payment of "  +
		    priceDisplayLabel.getText () + " can be made at the time of delivery or pickup" +
		    "\n\nYour pizza will be ready in 20 minutes!" +
		    "\nPlease Enjoy our complimentary game while you wait for the Pizza :)",
		    "Orders Confirmed",
		    JOptionPane.INFORMATION_MESSAGE);
	}
	
	    else if (e.getSource () == helpButton)
	 {
	 
	   
			 JOptionPane.showMessageDialog (this,
		    "HELP:\n Having trouble with ordering the Pizza? Don't Worry " +  nameText.getText () +", it is quit simple.\nStart by selecting the amount of pizzas you would like to order.\nThen select the size of the Pizza you would like to order(prices are stated), followed by the toppings you enjoy on your pizza. \nThe price will change immediately after clicking an option.\n Lastly, after your order has been finalized, click on the 'Place Order Now' button to place your order. \nAlso, please enjoy our complimentary game while you wait for your pizza.  "  +
		     "" +
		    "\n\nIf you have any further questions, please don't hesitate to call us at (905)-555-8989" +
		    "\nOr visit our website at www.pablosperfectpizzapartyplace.com",
		    "HELP",
		    JOptionPane.QUESTION_MESSAGE);
	}
	
	    else if(e.getSource() == playButton)
	    {
		FlappyBird.main(args);
		
	    }

	refreshPrice ();
    }


    
     //Method to calculate and refresh the price display
     
    private void refreshPrice ()
    {

	//variables used to accumulate total price
	double price = 0;
	int pizzaAmount = Integer.parseInt (pizzaText.getText ());

	// FIXES NUMBER FORMATTING
	NumberFormat numberForm = NumberFormat.getNumberInstance ();
	DecimalFormat moneyForm = (DecimalFormat) numberForm;
	moneyForm.applyPattern ("0.00");

	// Pizza size prices
	//if user selects certain size, price will change accordingly
	if (smallJRadio.isSelected ())
	{
	    price += small * pizzaAmount; ///gets price of size from variables previosuly declared
	}

	if (regularJRadio.isSelected ())
	{
	    price += medium * pizzaAmount; ///gets price of size from variables previosuly declared
	}

	if (largeJRadio.isSelected ())
	{
	    price += large * pizzaAmount; ///gets price of size from variables previosuly declared
	}

	//if user selects certain toppings, price will change accordingly
	if (cheeseJCheck.isSelected ())
	{
	    price += cheese * pizzaAmount; ///gets price of toppings from variables previosuly declared
	}

	if (pepperoniJCheck.isSelected ())
	{
	    price += pepperoni * pizzaAmount;///gets price of toppings from variables previosuly declared
	}

	if (mushroomsJCheck.isSelected ())
	{
	    price += mushrooms * pizzaAmount;///gets price of toppings from variables previosuly declared
	}

	if (olivesJCheck.isSelected ())
	{
	    price += olives* pizzaAmount;///gets price of toppings from variables previosuly declared
	}

	if (baconJCheck.isSelected ())
	{
	    price += bacon * pizzaAmount;///gets price of toppings from variables previosuly declared
	}

	if (pineappleJCheck.isSelected ())
	{
	    price += pineapple * pizzaAmount;///gets price of toppings from variables previosuly declared
	}

	priceDisplayLabel.setText ("$" + moneyForm.format (price));///displays the price of the final pizza
    }
    
   public void paint (Graphics g)
    {
	int width = img.getWidth (this);

	int height = img.getHeight (this);

	g.drawRect (52, 52, width + 10, height + 10);

	g.drawImage (img, 57, 57, width, height, this);

    }

}





