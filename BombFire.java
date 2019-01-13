import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BombFire here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BombFire extends Bomb
{
    private long timeStamp;

    public BombFire( String img ) {
        super( img, 0, 9 ); 
        timeStamp = System.currentTimeMillis(); 
    }

    public void act() 
    {
        if( isTouching( DestructibleBrick.class ) ) {
            removeTouching( DestructibleBrick.class );
            if ( Math.random() < 0.1 )  dropFirePowerUp(); 
            else if( Math.random() > 0.9 ) dropBombPowerUp();
        }
        if ( System.currentTimeMillis() - timeStamp >= 1000 ) getWorld().removeObject( this );
    }    

    public void dropFirePowerUp() {
        getWorld().addObject( new FirePowerUp( "/BomberBonuses/F2.gif" ), getX(), getY() ); 
    }
    public void dropBombPowerUp() {
        getWorld().addObject( new BombPowerUp( "/BomberBonuses/B2.gif" ), getX(), getY() ); 
    }
}
