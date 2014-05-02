package helixprophets.beings.classes;

import org.newdawn.slick.opengl.Texture;

import helixprophets.beings.Character;

public class Fighter extends Character {

	public Fighter(Texture[] moveTextures, Texture[] fightTextures,
			Texture[] crawlTextures, int moveSpeed, int crawlSpeed,
			int jumpHeight) {
		super(moveTextures, fightTextures, crawlTextures, moveSpeed, crawlSpeed,
				jumpHeight);
		// TODO Auto-generated constructor stub
	}
	
	public void fight()
	{
		//Switch to the textures for swinging sword
		//Check if there is an enemy within *swordLength* pixels of the character's right side
		//If enemy is found, call any appropriate methods to reduce enemy health and knock it back
		//Return to moving/standing texture depending on character speed
		
		/*What the code would look like, not including any classes not implemented yet
		 * drawTexture(fightTextures[0]); //Sword begin swing
		 * delay(200);
		 * drawTexture(fightTextures[1]); //Sword middle swing
		 * delay(200);
		 * drawTexture(fightTextures[2])); //Sword ending swing
		 * delay(200);
		 * drawTexture(moveTextures[0]); //Standby
		 * 		 
		 *OR
		 * 
		 * Entity<Sword> sword= new Entity<Sword>(); then let the sword do its animation as an entity,
		 * or something that can sense unit collision.
		 * 
		 */
	}

}
/*Fighter should share highest movespeed with rogue but should have lowest jump height.
fight() should include swinging a sword directly in front of him in a relatively large area
with a quick attack speed. Slowest crawl speed.
*/


