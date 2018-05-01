import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
//David Morey
public class Game extends BasicGame {
	
	Engine myEngine = new Engine();
	public Game(String gamename) {
		super(gamename);
		
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		myEngine.create();
		
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		myEngine.update(gc);
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		myEngine.draw(g);
		
		
	}

	public static void main(String[] args) {
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Game("Snek"));
			appgc.setShowFPS(false);
			appgc.setDisplayMode(600,600, false);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}