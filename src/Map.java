import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Map
{
	Game								game;
	private GameObjectsCollection<Tile>	mapTiles	= new GameObjectsCollection<Tile>();

	Map(Game game)
	{
		this.game = game;
	}

	public Map loadMap()
	{
		try (	BufferedReader fin = new BufferedReader(new FileReader("src\\map.txt")))
		{
			int i, k = 0;
			do
			{
				i = fin.read();
				if (i != 10 && i != 13 && i != 16) // LF, CR, EOF
				{
					int coord = k * Game.GRID_SIZE;
					getMap().add(new Tile(game, (char) i, coord % (Game.GRID_SIZE * Game.COLUMNS), coord / (Game.GRID_SIZE * Game.ROWS) * Game.GRID_SIZE));
					k++;
				}
			}
			while (i != -1);
		}
		catch (IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "Error occurred:\n" + ioe.getLocalizedMessage(), "Error occurred", JOptionPane.ERROR_MESSAGE);
		}
		return this;
	}

	public void draw(Graphics g)
	{
		for (GameObject currentTile : mapTiles)
		{
			currentTile.draw(g);
		}
	}

	public GameObjectsCollection<Tile> getMap()
	{
		// TODO Auto-generated method stub
		return mapTiles;
	}
}
