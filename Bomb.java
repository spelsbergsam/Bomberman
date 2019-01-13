import greenfoot.*;

/**
 * Write a description of class Bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends Actor
{
    private long timeStamp;
    private boolean isExploded;
    private int bombType; //0 is fire, 1-4 are player bombs
    private static int currentSpaces;
    public Bomb( String img ) {
        setImage( img );
    }
    public Bomb( String img, int type, int spaces ) {
        setImage( img ); 
        timeStamp = System.currentTimeMillis();
        isExploded = false; 
        bombType = type; 
        currentSpaces = spaces; 
    }

    public void act() {
        explode( currentSpaces );
    }

    public void explode( int spaces ) {
        long timePassed = System.currentTimeMillis() - timeStamp;
        if( timePassed >= 1500 && !isExploded ) { 
            setImage( "/BomberFires/C3.gif" );
            isExploded = true;
            int count = 1;
            boolean goLeft = true; boolean goRight = true; boolean goTop = true; boolean goBot = true; 
            while ( count <= spaces ) {

                if ( goRight ) {
                    Bomb explosionRight = new BombFire( "/BomberFires/H3.gif" ); 
                    getWorld().addObject( explosionRight, getX() + (15 * count), getY() );
                    if ( explosionRight.isTouching( PermanentBrick.class ) ) {
                        getWorld().removeObject( explosionRight );
                        goRight = false; 
                    }
                }

                if ( goLeft ) {
                    Bomb explosionLeft = new BombFire( "/BomberFires/H3.gif" );
                    getWorld().addObject( explosionLeft, getX() - (15 * count), getY() );
                    if ( explosionLeft.isTouching( PermanentBrick.class ) ) {
                        getWorld().removeObject( explosionLeft ); 
                        goLeft = false; 
                    }
                }

                if ( goTop ){
                    Bomb explosionTop = new BombFire( "/BomberFires/V3.gif" ); 
                    getWorld().addObject( explosionTop, getX(), getY() - (15 * count) );
                    if ( explosionTop.isTouching( PermanentBrick.class ) ) {
                        getWorld().removeObject( explosionTop ); 
                        goTop = false; 
                    }
                }

                if( goBot ) {
                    Bomb explosionBottom = new BombFire( "/BomberFires/V3.gif" );
                    getWorld().addObject( explosionBottom, getX(), getY() + (15 * count) );
                    if ( explosionBottom.isTouching( PermanentBrick.class ) ) {
                        getWorld().removeObject( explosionBottom );
                        goBot = false; 
                    }
                }

                count++; 

            }
        }
        else if ( timePassed >= 2500 && isExploded ) {
            getWorld().removeObject( this ); 
            BombermanMap.lessBombs( bombType );
            //System.out.println( "bomb removed, current count: " + BombermanMap.getNumOfBombs( 1 ) ) ;
        }
    }
    
    public int getBombType() {
        return bombType; 
    }

}
