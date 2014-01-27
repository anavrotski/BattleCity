

import interfaces.Bounds;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class GameObject implements Bounds
{
	public Game							game;
	protected BufferedImage				img			= null;
	GameObjectsCollection<GameObject>	collection	= null;
	int									WIDTH		= Game.GRID_SIZE;
	int									HEIGHT		= Game.GRID_SIZE;

	public GameObject(Game game)
	{
		this.game = game;
	}

	Point	currentCoords	= new Point();

	public abstract void draw(Graphics g);

	public void dispose()
	{
		getCollection().remove(this);
	}

	public Point getCurrentCoords()
	{
		return currentCoords;
	}

	public int getCurrentX()
	{
		return (int) currentCoords.getX();
	}

	public int getCurrentY()
	{
		return (int) currentCoords.getY();
	}

	public void drawCollection(Graphics g, GameObject gameObject)
	{
		for (GameObject currentObject : collection)
		{
			currentObject.draw(g);
		}
	}

	public GameObjectsCollection<GameObject> getCollection()
	{
		return collection;
	}

}
