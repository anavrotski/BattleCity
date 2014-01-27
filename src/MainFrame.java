import interfaces.Input;
import interfaces.Output;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class MainFrame extends JFrame implements Input, Output, KeyListener
{
	private static final long	serialVersionUID	= -3223478780197511771L;

	GamePanel					panel;
	Game						game;
	GridBagConstraints			c					= new GridBagConstraints();
	int							keyPressed;

	MainFrame(Game game)
	{
		super("Battle City");
		this.game = game;
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setFocusable(true);
		createAndShowGUI();
	}

	public void createAndShowGUI()
	{
		this.panel = new GamePanel(game);
		add(panel, c);
		setResizable(false);
		Insets insets = getInsets();
		int insetWide = insets.left + insets.right;
		int insetTall = insets.top + insets.bottom;
		setSize(getWidth() + insetWide, getHeight() + insetTall);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		//requestFocus();
		addKeyListener(this);

	}

	@Override
	public int getUserInput()
	{
		return keyPressed;
	}

	@Override
	public void drawGame()
	{
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub
		keyPressed = e.getKeyCode();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}
}
