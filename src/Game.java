import interfaces.Input;
import interfaces.Output;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

public class Game
{
	public static final int		COLUMNS			= 11;
	public static final int		ROWS			= 11;
	public static final int		GRID_SIZE		= 64;
	int							tanksTotal		= 1;
	Timer						timer;
	Map							map				= new Map(this);
	MainFrame					frame;
	GameObject					gameObject;
	GameObjectsCollection<Tank>	tanksCollection	= new GameObjectsCollection<Tank>();
	Input						input;
	Output						output;
	public int					command			= 0;

	Game()
	{
		frame = new MainFrame(this);
		input = frame;
		output = frame;
		addTanks();
		int delay = 50;
		timer = new Timer(delay, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if (evt.getSource() == timer)
				{
					updateGame();
					try
					{
						Thread.sleep(10);
					}
					catch (InterruptedException e)
					{ // TODO Auto-generated catch block e.printStackTrace();
					}
				}
			}
		});
	}

	public void addTanks()
	{
		// for (Tank currentTank : tanksCollection)
		for (int i = 0; i < tanksTotal; i++)
		{
			tanksCollection.add(new Tank_Simple(this));
		}
	}

	public void start()
	{
		map.loadMap();
		timer.start();
	}

	public void updateGame()
	{
		// TODO
		getInput();
		// update game objects
		for (Tank currentTank : tanksCollection)
		{
			currentTank.updateTank();
		}
		output.drawGame();
	}

	public void getInput()
	{
		command = input.getUserInput();
		if (Direction.getDir(command) != Direction.DEFAULT)
		{
			tanksCollection.get(0).setMoving(true);
		}
		if (command == KeyEvent.VK_SPACE)
		{
			tanksCollection.get(0).fire();
		}
	}

	public static void main(String[] args)
	{
		Game game = new Game();
		game.start();
	}
}
