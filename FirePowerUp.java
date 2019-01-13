import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PowerUp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirePowerUp extends Bomb
{
    public FirePowerUp( String img ){
        super( img );
    }
    public void act() 
    {
        if( isTouching( Bomberman.class ) ) {
            getWorld().removeObject( this );
        }
    }    
}
