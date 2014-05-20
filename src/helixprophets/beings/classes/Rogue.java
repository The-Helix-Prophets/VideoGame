package helixprophets.beings.classes;

import org.newdawn.slick.opengl.Texture;

import helixprophets.beings.Character;

public class Rogue extends Character {


	public void fight()
	{
		//Read in position of the cursor
		//Switch to bow graphic
		//Create arrow entity at the player's position with an initial direction towards the 
		//cursor and a speed proportional to distance to cursor.
		//Cooldown of one second
		
		/* What the code would look like, not including any classes not implemented yet
		 * Point cursor= mouse.getLocation();
		 * drawTexture(fightTextures[0]); //Readying the bow
		 * delay(200); //let the texture be visible for a while
		 * double yDistance= CHARACTER_YPOSITION - cursor.getY();
		 * double xDistance= CHARACTER_XPOSITION - cursor.getX();
		 * double angle= Math.arctan(yDistance/xDistance);
		 * double speed= Math.sqrt( Math.pow(yDistance,2)+Math.pow(xDistance,2));
		 * Entity<Arrow> a= new Entity<Arrow>(angle,speed);
		 * drawTexture(fightTextures[1]); //Releasing the arrow
		 * delay(200); //let the texture be visible for a while
		 * drawTexture(moveTextures[0]); //Standby
		 */
	}
	
	public void wallJump()
	{
		//Check direction the character is facing.
		//Check if the character's graphic is adjacent to wall or platform graphic.
		//if it is, the character changes direction, is given a horizontal speed away from
		//the wall, and vertical acceleration/speed is set to be equal to what it is when jump
		//is called.
		//The character will be able to turn back around and reach the wall, allowing for
		//infinite wall jumps	
		
		/*What the code would look like, not including any classes not implemented yet
		 * if(character.getDirection=="left")
		 * {
		 *   if(CHARACTER_XPOSITION minus one is contained by a platform graphic)
		 *   {
		 *    drawTexture(moveTextures[3]); //walljump texture
		 *    character.setDirection="right";
		 *    character.setHorizontalSpeed(moveSpeed);
		 *    super.jump();
		 *   } 
		 * }
		 * if(character.getDirection=="right")
		 * {
		 *   if(CHARACTER_XPOSITION plus CHARACTER.WIDTH plus one is contained by a platform graphic along the whole length of the character)
		 *   {
		 *    drawTexture(moveTextures[3]); //walljump texture
		 *    character.setDirection="left";
		 *    character.setHorizontalSpeed(moveSpeed);
		 *    super.jump();
		 *   } 
		 * }
		 * 
		 */
	}
	
	public void ledgeClimb()
	{
		//Check if the top of the character's graphic is at the same level as the top of a
		//platform's graphic.
		//Check if vertical speed is negative (you don't want to grab a ledge if you can 
		//jump over it without any problems)
		//If both of the above are true, cycle through climbing graphics while displacing the
		//character graphic around the edge and onto the platform.
		
		/*What the code would look like, not including any classes not implemented yet
		 * if(character.getDirection=="left")
		 * {
		 *   if(CHARACTER_YPOSITION +CHARACTER_HEIGHT is the same as platform position+height along CHARACTER_XPOSITION minus one contained in that platform)
		 *   {
		 *    drawTexture(moveTextures[4]); //ledgeClimb texture
		 *    character.moveY(CHARACTER.HEIGHT/2);
		 *    drawTexture(moveTextures[5]); //ledgeClimb texture 2
		 *    character.moveY(CHARACTER.HEIGHT/2);
		 *    character.moveX(-CHARACTER.WIDTH/2);
		 *    drawTexture(moveTextures[0]); //Standby
		 *    character.moveX(-CHARACTER.WIDTH/2);
		 *   } 
		 * }
		 * if(character.getDirection=="right")
		 * {
		 *   if(CHARACTER_YPOSITION +CHARACTER_HEIGHT is the same as platform position+height along CHARACTER_XPOSITION plus CHARACTER.WIDTH plus one contained in that platform)
		 *   {
		 *    drawTexture(moveTextures[4]); //ledgeClimb texture
		 *    character.moveY(CHARACTER.HEIGHT/2);
		 *    drawTexture(moveTextures[5]); //ledgeClimb texture 2
		 *    character.moveY(CHARACTER.HEIGHT/2);
		 *    character.moveX(CHARACTER.WIDTH/2);
		 *    drawTexture(moveTextures[0]); //Standby
		 *    character.moveX(CHARACTER.WIDTH/2);
		 *   } 
		 * }
		 * 
		 */
	}
}
/*
 * The rogue shares the highest movespeed with the knight and the highest jump height with the 
 * mage. His fight() should shoot an arrow with an initial direction towards the cursor
 * and a speed porportional to the distance between the character and the cursor. 
 * His jump() should be accessible if there is a wall directly adjacent to him in the direction
 * that he is facing. Fast crawl speed.
 * He should also have a wallclimb method accessible when the top of his image lines up with the
 * top of a platform, whereas normally the bottom of the image would have to line up.
 */
