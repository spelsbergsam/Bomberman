import greenfoot.*;

/**
 * Write a description of class BombermanMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BombermanMap extends World
{
    private static int numOfBomb1; 
    private static int numOfBomb2;
    
    public BombermanMap()
    {    
        super(853, 600, 1); 
        
        numOfBomb1 = 0;
        numOfBomb2 = 0;
        
        createPlayers();
        placePermanentBricks();
        placeDestructibleBricks();
      

    }

    //make getters and setter for these static counts and include them in player classes
    public static int getNumOfBombs( int type ) {
        return type == 1 ? numOfBomb1 : numOfBomb2;
    }
    public static void moreBombs( int type) {
        if( type == 1 ) numOfBomb1++;
        if( type == 2 ) numOfBomb2++;
    }
    public static void lessBombs( int type ) {
        if( type == 1 ) numOfBomb1--;
        if( type == 2 ) numOfBomb2--;
    }
    
    public void placePermanentBricks() {
        //Create all of the permanent bricks on the sides
        for( int y = 20; y <= 636; y = y + 40) {    //left
            addObject( new PermanentBrick(), 22, y);
        }
        for( int y = 20; y <= 636; y = y + 40) {    //right
            addObject( new PermanentBrick(), getWidth() - 23, y);
        }
        for ( int x = 22; x <= 900; x = x + 45) {   //bottom
            addObject( new PermanentBrick(), x, getHeight() - 20); 
        }
        for ( int x = 22; x <= 900; x = x + 45) {   //top
            addObject( new PermanentBrick(), x, 20); 
        }

        //Place the bricks in the middle of the map
        for ( int y = 100; y <= 570; y = y + 80) {
            for ( int x = 109; x <= 750; x = x + 90 ) { 
                addObject( new PermanentBrick(), x, y);
            } 
        }
    }

    public void placeDestructibleBricks() {
        //fill the map with the destructible bricks
        for( int y = 60; y < getHeight() - 40; y = y + 40 ) {
            boolean isFirstBlock;
            if( Math.random() < 0.5 ) isFirstBlock = false;   //to randomize the first row
            else isFirstBlock = true;
            for( int x = 67; x < getWidth() - 66; x = x + ((int)(Math.random() * 2) * 89) + 45 )  {
                if( getObjectsAt( x, y, Actor.class).size() == 0 && !isFirstBlock)
                {
                    addObject( new DestructibleBrick(), x, y ); 
                }
                isFirstBlock = false;
            }
        }
    }

    public void createPlayers() {
        Bomberman player1 = new Bomberman( 1, "player1-front.gif" );
        Bomberman player2 = new Bomberman( 2, "player2-front.gif" );
        
        player1.getImage().scale( 20, 30 );
        player2.getImage().scale( 20, 30 );
        
        addObject( player1, getWidth()/13, getHeight()/10 );
        addObject( player2, getWidth() - 70, getHeight() - 60 );
        
        
    }
}

