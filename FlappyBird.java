package pizzaProgram;

///////SPECIAL FEATURES USED ARE SWING CLASSES(import javax.swing), AND EXCEPTION HANDLING(try and catch methods)
//import packages
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;


public class FlappyBird implements ActionListener, MouseListener, KeyListener
{
		//creates variables
	public static FlappyBird flappyBird;

	public final int WIDTH = 800, HEIGHT = 800;//dimensions of game

	public Renderer renderer;//renders game

	public Rectangle bird; //creates 'pizza box'

	public ArrayList<Rectangle> columns;//creates columns

	public int ticks, yMotion, score;//values for score, etc.

	public boolean gameOver, started;//boolean values for start and end

	public Random rand;//random function
	
    
	public FlappyBird() //constructor
	{
		JFrame jframe = new JFrame(); 
		//timer
		Timer timer = new Timer(20, this);

		renderer = new Renderer();
		rand = new Random();
		
		//j frames
		jframe.add(renderer);
		jframe.setTitle("Flappy Bird");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.addMouseListener(this);
		jframe.addKeyListener(this);
		jframe.setResizable(false);
		jframe.setVisible(true);

		//sets dimensions for bird
		bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
		columns = new ArrayList<Rectangle>();
		
		//adds columns
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);
		//starts timer
		timer.start();
	}
	
	//adds green pipes
	public void addColumn(boolean start) 
	{
		int space = 300;
		int width = 100;
		int height = 50 + rand.nextInt(300);

		if (start)
		{
			columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
		}
		else
		{
			columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
		}
	}

	//paints pipes
	public void paintColumn(Graphics g, Rectangle column)
	{
		g.setColor(Color.green.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
	}

	//code for movement of the pizza box
	public void jump()
	{
		if (gameOver)
		{
				//calls box and keeps its y value at 0 
			bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
			columns.clear();
			yMotion = 0;
			score = 0;

			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);

			gameOver = false;
		}

		if (!started)
		{
			started = true; ///if started then...
		}
		else if (!gameOver)
		{
				//ends game if y value is 0
			if (yMotion > 0)
			{
				yMotion = 0;
			}
			
			//subtracts 10 from y-motion  each time

			yMotion -= 10;
		}
	}

	///Action Performed
	public void actionPerformed(ActionEvent e)
	{
		int speed = 10; ////speed at which background moves

		ticks++;

		if (started)
			////IF STARTED...BACKGROUND STARTS TO MOVE
		{
			for (int i = 0; i < columns.size(); i++)
			{
				Rectangle column = columns.get(i);

				column.x -= speed;
			}

			if (ticks % 2 == 0 && yMotion < 15)
			{
				yMotion += 2;
			}

			for (int i = 0; i < columns.size(); i++)
			{
				Rectangle column = columns.get(i);

				if (column.x + column.width < 0)
				{
					columns.remove(column);

					if (column.y == 0)
					{
						addColumn(false);
					}
				}
			}

			bird.y += yMotion;

			for (Rectangle column : columns)
			{
				if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 10 && bird.x + bird.width / 2 < column.x + column.width / 2 + 10)
				{
					score++;
				}

				if (column.intersects(bird))
				{
					gameOver = true;

					if (bird.x <= column.x)
					{
						bird.x = column.x - bird.width;

					}
					else
					{
						if (column.y != 0)
						{
							bird.y = column.y - bird.height;
						}
						else if (bird.y < column.height)
						{
							bird.y = column.height;
						}
					}
				}
			}

			if (bird.y > HEIGHT - 120 || bird.y < 0)
			{
				gameOver = true;
			}

			if (bird.y + yMotion >= HEIGHT - 120)
			{
					/////IF THE PIZZA BOX GOES OFF THE SCREEN, GAME IS OVER
				bird.y = HEIGHT - 120 - bird.height;
				gameOver = true;
			}
		}

		renderer.repaint();
	}

	public void repaint(Graphics g)
	{
			//////SETS COLOURS AND SHAPES OF THE BACKGROUND
		g.setColor(Color.cyan);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(Color.orange);
		g.fillRect(0, HEIGHT - 120, WIDTH, 120);

		g.setColor(Color.green);
		g.fillRect(0, HEIGHT - 120, WIDTH, 20);

		g.setColor(Color.red);
		g.fillRect(bird.x, bird.y, bird.width, bird.height);

		for (Rectangle column : columns)
		{
			paintColumn(g, column);
		}

		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 45));

		if (!started)
		{
			g.drawString("Click to start!", 25, HEIGHT / 2 - 50);
		}

		if (gameOver)
		{
			g.drawString("         GAME OVER!!", 100, HEIGHT / 2 - 30);
			g.drawString("YOUR PIZZA HAS BURNED! :(", 100, HEIGHT / 1 - 300);
		}

		if (!gameOver && started)
		{
			g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
		}
	}

	public static void main(String[] args)
	{
		flappyBird = new FlappyBird();
	}

 
	public void mouseClicked(MouseEvent e)
	{
		jump();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			jump();
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	@Override
	public void keyPressed(KeyEvent e)
	{

	}
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		FlappyBird.flappyBird.repaint(g);
	}

}
