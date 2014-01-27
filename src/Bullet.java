import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends MovableGameObject
{

	Tank	tank;

	public Bullet(Game game)
	{
		super(game);
		speed = 5;
		if (tank != null)
		{
			this.tank = game.tanksCollection.get(0);
			direction = tank.getDirection();
			// TODO Auto-generated constructor stub
			currentCoords.setLocation(tank.getCurrentCoords());
			switch (direction)
			{
				case UP:
					currentCoords.translate(Game.GRID_SIZE / 2, 0);
				break;
				case DOWN:
					currentCoords.translate(Game.GRID_SIZE / 2, Game.GRID_SIZE);
				break;
				case LEFT:
					currentCoords.translate(0, Game.GRID_SIZE / 2);
				break;
				case RIGHT:
					currentCoords.translate(Game.GRID_SIZE, Game.GRID_SIZE / 2);
				break;
			}
		}
		this.WIDTH = Game.GRID_SIZE / 8;
		this.HEIGHT = Game.GRID_SIZE / 8;
	}

	@Override
	public void draw(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect((int) getCurrentCoords().getX(), (int) getCurrentCoords().getY(), Game.GRID_SIZE / 8, Game.GRID_SIZE / 8);
	}

	@Override
	public void updateLocation()
	{
		switch (direction)
		{
			case DEFAULT:
			case UP:
				if (checkCollision((int) getCurrentCoords().getX(), (int) (getCurrentCoords().getY() - speed)))
				{
					return;
				}
				getCurrentCoords().setLocation(getCurrentCoords().getX(), getCurrentCoords().getY() - speed);
			break;
			case LEFT:
				if (checkCollision((int) (getCurrentCoords().getX() - speed), (int) getCurrentCoords().getY()))
				{
					return;
				}
				getCurrentCoords().setLocation(getCurrentCoords().getX() - speed, getCurrentCoords().getY());
			break;
			case RIGHT:
				if (checkCollision((int) (getCurrentCoords().getX() + speed), (int) getCurrentCoords().getY()))
				{
					return;
				}
				getCurrentCoords().setLocation(getCurrentCoords().getX() + speed, getCurrentCoords().getY());
			break;
			case DOWN:
				if (checkCollision((int) getCurrentCoords().getX(), (int) (getCurrentCoords().getY() + speed)))
				{
					return;
				}
				getCurrentCoords().setLocation(getCurrentCoords().getX(), getCurrentCoords().getY() + speed);
			break;
		}
	}

	@Override
	public boolean checkCollision(int x, int y)
	{
		// TODO
		for (Tile currentTile : game.map.getMap())
		{
			if (currentTile.getBounds(x, y).intersects(getBounds(x, y)) && currentTile.getTileType() != '0')
			{
				dispose();
				return true;
			}
		}
		checkBoardBounds();
		return false;
	}

	@Override
	public boolean checkBoardBounds()
	{
		if (super.checkBoardBounds())
		{
			dispose();
			return true;
		}
		return false;
	}

	/*	@Override
		public void dispose()
			{
			// TODO Auto-generated method stub
			tank.getWeapon().remove(tank.getWeapon().size() - 1);
			}*/

	@Override
	public Rectangle getBounds(int x, int y)
	{
		// TODO Auto-generated method stub
		return new Rectangle(x, y, Game.GRID_SIZE / 8, Game.GRID_SIZE / 8);
	}
}
