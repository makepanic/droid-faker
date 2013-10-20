package de.rndm.droidFaker.fixtures;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mkp
 * Date: 20.10.13
 * Time: 16:07
 * To change this template use File | Settings | File Templates.
 */
public class Name {

    private static String[] names = {
            "Dick","Rashad","Harold","Chong","Joey","Russell","Julio","Ahmad","Archie","Ferdinand","Erick","Sam","Milton","Edmund","Branden","Emory","Rocco","Elton","Sandy","Jeromy","Avery","Jordan","Aubrey","Darell","Joaquin","Connie","Jarod","Francisco","Collin","Hoyt","Tyler","Wilson","Milford","Nick","Brad","Jay","Richie","Sid","Josh","Wilfredo","Loren","Benton","Willard","Berry","Esteban","Chadwick","Hershel","Vito","Broderick","Lamar","Shakira","Marylynn","Alesia","Dann","Ria","Marylyn","Anjelica","Miesha","Georgiana","Olinda","Ava","Gaynell","Barb","Michell","Meggan","Lavada","Christal","Hue","Tasia","Randa","Lauri","Serina","Sigrid","Delsie","Kalyn","Leonarda","Williemae","Donella","Louise","Evelia","Cherri","Charlott","Nu","Fransisca","Lang","June","Adriene","Zella","Loreta","Jinny","Kendal","Emilee","Jane","Laurena","Desirae","Fredricka","Myrtie","Onie","Emma","Malissa"
    };

    public static String getName(Random random) {
        return names[random.nextInt(names.length)];
    }
}
