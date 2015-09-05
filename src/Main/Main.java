package Main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import Game.SceneLoader;
import GameStates.Dialogue;
import GameStates.Menu;
import GameStates.Strolling;
 
public class Main extends StateBasedGame{
   
   public static final String gameName = "Marvin's Tale";
   public static final int currentState = 2;
   public static final int xSize = 800;
   public static final int ySize = 600;
   public static final int FPS_CAP = 30;
   
   public Main(String gamename){
      super(gamename);
      this.addState(new Menu());
      this.addState(new Dialogue());
      this.addState(new Strolling());
   }
   
   public void initStatesList(GameContainer gc) throws SlickException{
//      this.getState(currentState).init(gc, this);
      this.enterState(currentState);
   }
   
   public static void main(String[] args) {
      AppGameContainer appgc;
      try{
         appgc = new AppGameContainer(new Main(gameName));
         appgc.setDisplayMode(xSize, ySize, false);
         appgc.setFullscreen(false);
         appgc.setTargetFrameRate(FPS_CAP);
         appgc.setAlwaysRender(true);
         appgc.start();
      }catch(SlickException e){
         e.printStackTrace();
      }
   }
}