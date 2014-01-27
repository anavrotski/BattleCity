import interfaces.Bounds;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile extends GameObject implements Bounds
	{
	char	tileType;

	Tile(Game game, char tileType, int x, int y)
		{
		super(game);
		this.setTileType(tileType);
		getCurrentCoords().setLocation(x, y);
		try
			{
			img = ImageIO.read(new File("src\\pics\\block_" + tileType + ".png"));
			}
		catch (IOException e)
			{}
		}

	public void draw(Graphics g)
		{
		if (this.getTileType() == '0')
			{
			g.setColor(Color.BLACK);
			g.fillRect((int) getCurrentX(), (int) getCurrentY(), Game.GRID_SIZE, Game.GRID_SIZE);
			}
		g.drawImage(img, (int) getCurrentX(), (int) getCurrentY(), null);
		}

	public char getTileType()
		{
		return tileType;
		}

	public void setTileType(char tileType)
		{
		this.tileType = tileType;
		}

	@Override
	public Rectangle getBounds(int x, int y)
		{
		return new Rectangle((int) getCurrentCoords().getX(), (int) getCurrentCoords().getY(), Game.GRID_SIZE, Game.GRID_SIZE);
		}
	}
