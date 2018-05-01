
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
//David Morey
public class Engine extends BasicGame {
	static ReadExcel map = new ReadExcel();
	public float pausetimer = 120;
	public static String xls = "data/level 1.xls";
	public static String gamestate = "title";
	public static int width = 850;
	public static int height = 850;
	public static Player p;
	public static int level = 1;
	public static Rooms rooms[][] = new Rooms[11][11];
	public static String levels[][];
	public static Image wizard, title, skel, heart, 
	door_t, door_r, door_b, door_l, 
	heartd, bigG, rock,
	wall_t,wall_r,wall_l,wall_b,
	door_t_u,door_r_u,door_l_u,door_b_u,upgrade_ex,
	door_t_l,door_r_l,door_l_l,door_b_l,
	fireball, bossB;
	public Engine(String gamename) {
		super(gamename);
	}

	public void init(GameContainer gc) throws SlickException {
		try {
			bossB = new Image("data/BossB.png");
			fireball = new Image("data/FIREBALL.png");
			door_t_l = new Image("data/DOOR_T_L.png");
			door_r_l = new Image("data/DOOR_R_L.png");
			door_l_l = new Image("data/DOOR_L_L.png");
			door_b_l = new Image("data/DOOR_B_L.png");
			upgrade_ex = new Image("data/UPGRADE_Ex.png");
			wall_t = new Image("data/WALL_T.png");
			wall_r = new Image("data/WALL_R.png");
			wall_l = new Image("data/WALL_L.png");
			wall_b = new Image("data/WALL_B.png");
			rock = new Image("data/ROCK.png");
			bigG = new Image("data/big_guy.png");
			title = new Image("data/TITLE.png");
			wizard = new Image("data/WIZARD.png");
			heart = new Image("data/HEART.png");
			skel = new Image("data/skel.png");
			door_t = new Image("data/DOOR_T.png");
			door_r = new Image("data/DOOR_R.png");
			door_b = new Image("data/DOOR_B.png");
			door_l = new Image("data/DOOR_L.png");
			door_t_u = new Image("data/DOOR_T_U.png");
			door_r_u = new Image("data/DOOR_R_U.png");
			door_b_u = new Image("data/DOOR_B_U.png");
			door_l_u = new Image("data/DOOR_L_U.png");
			heartd = new Image("data/HEARTD.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		bossB = bossB.getScaledCopy(100,100);
		fireball = fireball.getScaledCopy(20,20);
		door_l_l = door_l_l.getScaledCopy(75, 75);
		door_r_l = door_r_l.getScaledCopy(75, 75);
		door_t_l = door_t_l.getScaledCopy(75, 75);
		door_b_l = door_b_l.getScaledCopy(75, 75);
		upgrade_ex = upgrade_ex.getScaledCopy(50,50);
		rock = rock.getScaledCopy(150,150);
		bigG = bigG.getScaledCopy(100, 100);
		door_l = door_l.getScaledCopy(75, 75);
		door_r = door_r.getScaledCopy(75, 75);
		door_t = door_t.getScaledCopy(75, 75);
		door_b = door_b.getScaledCopy(75, 75);
		heart = heart.getScaledCopy(40, 40);
		skel = skel.getScaledCopy(30, 30);
		wizard = wizard.getScaledCopy(50, 50);
		heartd = heartd.getScaledCopy(40, 40);
		door_t_u = door_t_u.getScaledCopy(75,75);
		door_r_u = door_r_u.getScaledCopy(75,75);
		door_b_u = door_b_u.getScaledCopy(75,75);
		door_l_u = door_l_u.getScaledCopy(75,75);
		p = new Player(width / 2, height / 2, wizard);
		rooms();
	}
	public static void rooms() {
		map.setInputFile(xls);
		try {
			map.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		levels = map.getLevel();
		if (!(gamestate.equals("title"))) {
			for (int i = 0; i < levels.length; i++) {
				for (int j = 0; j < levels.length; j++) {
					if (levels[i][j].equals("1")) {
						rooms[i][j] = new blankRoom(i, j, width, height);
					}else
					if (levels[i][j].equals("2")) {
						rooms[i][j] = new basicRoom(i, j, width, height);
					}else
					if (levels[i][j].equals("3")) {
						rooms[i][j] = new bossRoom(i, j, width, height);
					}else
					if(levels[i][j].equals("4")) {
						rooms[i][j] = new upgradeRoom(i,j,width,height);
					}
				}
			}
		}
		p.setOriginX(670);
		p.setOriginY(-40);
		p.setRoomX(0);
		p.setRoomY(0);
		p.moveRoom(6, 6);
	}
	public void update(GameContainer gc, int i) throws SlickException {
		if (pausetimer > 0) {
			pausetimer--;
		}
		Input input = gc.getInput();
		if (!(gamestate.equals("title")) && !(gamestate.equals("pause"))) {
			rooms[p.getRoomX()][p.getRoomY()].update(gc);
			p.update(gc);
			if (input.isKeyDown(Input.KEY_P) && pausetimer <= 0) {
				gamestate = "pause";
				pausetimer = 120;
			}
		} else {
			if (input.isKeyDown(Input.KEY_SPACE)) {
				gamestate = "game";
				init(gc);
			}
		}
		if (gamestate == "pause") {
			if (input.isKeyDown(Input.KEY_P) && pausetimer <= 0) {
				gamestate = "game";
				pausetimer = 120;
			}
		}
	}
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if (!(gamestate.equals("title"))) {
			rooms[p.getRoomX()][p.getRoomY()].draw(g);
			p.draw(g);
		} else {
			g.drawImage(title, 0, 0);
		}
	}
	public static void main(String[] args) {
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Engine("Detainment of David"));
			appgc.setShowFPS(false);
			appgc.setDisplayMode(width, height, false);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public static String getXls() {
		return xls;
	}
	public static void setXls(String xls) {
		Engine.xls = xls;
	}


	
}
