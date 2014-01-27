import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel
	{
	private static final long	serialVersionUID	= 99060341211824453L;
	Game											game;

	public GamePanel(final Game game)
		{
		super(true); // double buffered
		this.game = game;
		setPreferredSize(new Dimension(Game.GRID_SIZE * Game.COLUMNS, Game.GRID_SIZE * Game.ROWS));
		}

	@Override
	public void paintComponent(Graphics g)
		{
		super.paintComponents(g);
		game.map.draw(g);

		for (Tank currentTank : game.tanksCollection)
			{
			currentTank.draw(g);
/*			for (Bullet currentBullet : currentTank.getWeapon())
				{
				currentBullet.draw(g);
				}*/
			}
		}
	}
