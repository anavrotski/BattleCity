import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class Tank extends MovableGameObject
{
	public Tank(Game game)
	{
		super(game);
		setMoving(false);
		// TODO Auto-generated constructor stub
	}

	Bullet									bullet	= new Bullet(game);
	private GameObjectsCollection<Bullet>	bullets	= new GameObjectsCollection<Bullet>();

	@Override
	public boolean checkCollision(int x, int y)
	{
		// TODO
		for (Tile currentTile : game.map.getMap())
		{
			if (currentTile.getBounds(x, y).intersects(getBounds(x, y)) && currentTile.getTileType() != '0')
			{
				setMoving(false);
				return true;
			}
		}
		checkBoardBounds();
		return false;
	}

	public boolean checkMoveX()
	{
		if (getCurrentX() % (WIDTH / 2) == 0)
		{
			setMoving(false);
			return true;
		}
		return false;
	}

	public boolean checkMoveY()
	{
		if (getCurrentY() % (HEIGHT / 2) == 0)
		{
			setMoving(false);
			return true;
		}
		return false;
	}

	public void rotateTank(int dirKey)
	{
		Direction newDirection = Direction.getDir(dirKey);
		Direction oldDirection = getDirection();
		if (newDirection == oldDirection || oldDirection == null || newDirection == Direction.DEFAULT)
		{
			return;
		}
		switch (newDirection)
		{
			case UP:
				if (oldDirection == Direction.LEFT)
				{
					rotateTankImage(90);
				}
				else if (oldDirection == Direction.RIGHT)
				{
					rotateTankImage(-90);
				}
				else
				{
					rotateTankImage(180);
				}
			break;
			case DOWN:
				if (oldDirection == Direction.LEFT)
				{
					rotateTankImage(-90);
				}
				else if (oldDirection == Direction.RIGHT)
				{
					rotateTankImage(90);
				}
				else
				{
					rotateTankImage(180);
				}
			break;
			case LEFT:
				if (oldDirection == Direction.UP)
				{
					rotateTankImage(-90);
				}
				else if (oldDirection == Direction.RIGHT)
				{
					rotateTankImage(180);
				}
				else
				{
					rotateTankImage(90);
				}
			break;
			case RIGHT:
				if (oldDirection == Direction.LEFT)
				{
					rotateTankImage(180);
				}
				else if (oldDirection == Direction.UP)
				{
					rotateTankImage(90);
				}
				else
				{
					rotateTankImage(-90);
				}
			break;
			case DEFAULT:
				return;
			default:
				return;
		}
		setDirection(newDirection);
	}

	public void rotateTankImage(int rotation)
	{
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(rotation), img.getWidth() / 2, img.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		img = op.filter(img, null);
	}

	public void updateTank()
	{
		// TODO Auto-generated method stub
		rotateTank(game.command);
		updateLocation();
	}

	@Override
	public Rectangle getBounds(int x, int y)
	{
		return new Rectangle(x, y, Game.GRID_SIZE, Game.GRID_SIZE);
	}

	public void fire()
	{
		if (this.getWeapon().size() == 0)
		{
			this.getWeapon().add(new Bullet(game));
		}
	}

	public GameObjectsCollection<Bullet> getWeapon()
	{
		// TODO Auto-generated method stub
		return bullets;
	}

	@Override
	public void draw(Graphics g)
	{
		// TODO
		g.drawImage(img, getCurrentX(), getCurrentY(), null);
	}

	@Override
	public void updateLocation()
	{
		//TODO
		super.updateLocation();
		checkMoveX();
		checkMoveY();
	}
}
