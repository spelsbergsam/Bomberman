import greenfoot.*;

/**
 * Write a description of class Bomberman here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomberman extends Actor
{
    private boolean gameSetup;
    private int currentSpaces;
    private final int STARTING_SPACES = 9;
    private int playerNum;
    private int currentBombs;         ////TO-DO: Add soomething to make the max number of bombs
                                      ////         that can be placed adjustable, to the currentBombs var. 
                                      /// fix it i broke it

    public Bomberman( int player, String img ) {
        gameSetup = false; 
        playerNum = player; 
        setImage( img );
        currentSpaces = STARTING_SPACES;
        currentBombs = 1; 
    }

    public void act() 
    {
        if ( !gameSetup ) setupGame(); 
        movement();
        if( isTouching( FirePowerUp.class ) )powerUpSpaces();
        else if ( isTouching( BombPowerUp.class ) ) powerUpMaxBombs();
        if ( isTouching( BombFire.class ) ) {
            getWorld().showText( "Game Over", 425, 300 );
            getWorld().removeObject( this ); 
            Greenfoot.stop();
        }
    }    

    public void movement() {
        int xLoc = getX(); 
        int yLoc = getY();
        
        //player 1 control scheme
        if( Greenfoot.isKeyDown("d") && playerNum == 1 ) {
            setLocation( getX() + 3, getY() );
        }
        if( Greenfoot.isKeyDown("a") && playerNum == 1 ) {
            setLocation( getX() - 3, getY() );
        }
        if( Greenfoot.isKeyDown("s") && playerNum == 1) {
            setLocation( getX(), getY() + 3 );
        }
        if( Greenfoot.isKeyDown("w") && playerNum == 1) {
            setLocation( getX(), getY() - 3 );
        }
        if( Greenfoot.isKeyDown( "space" ) && playerNum == 1 ) {
            placeBomb();
        }
        
        //player 2 control scheme
        if( Greenfoot.isKeyDown("right") && playerNum == 2) {
            setLocation( getX() + 3, getY() );
        }
        if( Greenfoot.isKeyDown("left") && playerNum == 2) {
            setLocation( getX() - 3, getY() );
        }
        if( Greenfoot.isKeyDown("down") && playerNum == 2) {
            setLocation( getX(), getY() + 3 );
        }
        if( Greenfoot.isKeyDown("up") && playerNum == 2) {
            setLocation( getX(), getY() - 3 );
        }
        if( Greenfoot.isKeyDown( ";" ) && playerNum == 2) {
            placeBomb();
        }
        
        if( isTouching( Brick.class ) ) setLocation( xLoc, yLoc);
    }

    public void setupGame() {
        for ( DestructibleBrick brick : getObjectsInRange( 60, DestructibleBrick.class ) ) {
            getWorld().removeObject( brick );
        }
        gameSetup = true;
    }

    public void placeBomb() {
        if ( BombermanMap.getNumOfBombs( playerNum ) < currentBombs ){
            getWorld().addObject( new Bomb( "/BomberBombs/2.gif", playerNum, currentSpaces ),
                  getX(), getY() );
            BombermanMap.moreBombs( playerNum );
        }
    }
    
    public void powerUpSpaces() {
        if ( currentSpaces < 40 ) currentSpaces = currentSpaces + 2;
    }
    public void powerUpMaxBombs() {
        if( currentBombs < 4 ) currentBombs++; 
    }
}
