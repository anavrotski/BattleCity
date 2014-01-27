import interfaces.Movable;

public abstract class MovableGameObject extends GameObject implements Movable
{

	public MovableGameObject(Game game)
	{
		super(game);
		// TODO Auto-generated constructor stub
	}

	private boolean	moving;
	Direction		direction	= Direction.UP;
	int				speed		= 1;

	public boolean isMoving()
	{
		return moving;
	}

	public void setMoving(boolean moving)
	{
		this.moving = moving;
	}

	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}

	public Direction getDirection()
	{
		return direction;
	}

	@Override
	public boolean checkBoardBounds()
	{
		if (getCurrentX() < 0)
		{
			getCurrentCoords().setLocation(0, getCurrentY());
			return true;
		}
		else if (getCurrentY() < 0)
		{
			getCurrentCoords().setLocation(getCurrentX(), 0);
			return true;
		}
		else if (getCurrentX() > WIDTH * (Game.COLUMNS - 1))
		{
			getCurrentCoords().setLocation(Game.GRID_SIZE * (Game.COLUMNS - 1), getCurrentY());
			return true;
		}
		else if (getCurrentY() > HEIGHT * (Game.ROWS - 1))
		{
			getCurrentCoords().setLocation(getCurrentX(), Game.GRID_SIZE * (Game.ROWS - 1));
			return true;
		}
		return false;
	}

	@Override
	public void updateLocation()
	{
		if (isMoving())
		{
			switch (getDirection())
			{
				case UP:
					if (checkCollision(getCurrentX(), (getCurrentY() - speed)))
					{
						return;
					}
					getCurrentCoords().setLocation(getCurrentX(), getCurrentY() - speed);
				break;
				case LEFT:
					if (checkCollision((getCurrentX() - speed), getCurrentY()))
					{
						return;
					}
					getCurrentCoords().setLocation(getCurrentX() - speed, getCurrentY());
				break;
				case RIGHT:
					if (checkCollision((getCurrentX() + speed), getCurrentY()))
					{
						return;
					}
					getCurrentCoords().setLocation(getCurrentX() + speed, getCurrentY());
				break;
				case DOWN:
					if (checkCollision(getCurrentX(), (getCurrentY() + speed)))
					{
						return;
					}
					getCurrentCoords().setLocation(getCurrentX(), getCurrentY() + speed);
				break;
				case DEFAULT:
				default:
				break;
			}
		}
	}
}
