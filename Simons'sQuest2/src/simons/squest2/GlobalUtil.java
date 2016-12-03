/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simons.squest2;

/**
 *
 * @author Javier
 */
public class GlobalUtil {
    public static Vector2i getPos(){
        return new Vector2i(GameVariables.x/32, GameVariables.y/32);
    }
}
