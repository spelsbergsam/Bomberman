import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BombPowerUp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BombPowerUp extends Bomb
{
    public BombPowerUp( String img ) {
        super( img ); 
    }
    public void act() 
    {
        if( isTouching( Bomberman.class ) ) {
            getWorld().removeObject( this );
        }
    }    
}
