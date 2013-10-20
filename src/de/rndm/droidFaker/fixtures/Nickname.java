package de.rndm.droidFaker.fixtures;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mkp
 * Date: 20.10.13
 * Time: 19:45
 * To change this template use File | Settings | File Templates.
 */
public class Nickname {

    /*
    @see http://en.wikipedia.org/wiki/List_of_nicknames_of_United_States_Presidents
    js: n=[]; $('#mw-content-text ul > li > b').each(function(i,e){ n.push('"' + e.innerText + '"') }); console.log(n.join(','))
     */
    private static String[] nicknames = {
        "The American Cincinnatus","The American Fabius","The Father of His Country","The Colossus of Independence","Old Sink or Swim","His Rotundity","The Apostle of Democracy","The Man of the People","The Sage of Monticello","Little Jemmy","His Little Majesty","Father of the Constitution","The Era of Good Feelings President","The Last Cocked Hat","Old Man Eloquent","The Abolitionist","The Hero of New Orleans","Old Hickory","The American Talleyrand","The Careful Dutchman","The Enchanter","The Great Manager","The Master Spirit","Martin Van Ruin","Matty Van","The Mistletoe Politician","Old Kinderhook","The Little Magician","General Mum","Tippecanoe","Old Tippecanoe","Washington of the West","His Accidency","Napoleon of the Stump","Young Hickory","Old Rough and Ready","The American Louis Philippe","Young Hickory of the Granite Hills","Handsome Frank","Old Public Functionary","Old Buck","Bachelor President","Ten-Cent Jimmy","The Ancient One","The Great Emancipator","The Liberator","Honest Abe","The Rail-Splitter","The Tycoon","Uncle Abe","The Tennessee Tailor","Unconditional Surrender Grant","U.S. Grant","Rutherfraud","His Fraudulency","Boatman Jim","Chet","Gentleman Boss","Prince Arthur","The Dude President","Walrus","His Obstinacy","Uncle Jumbo","The Front Porch Campaigner","The Human Iceberg","The Napoleon of Protection","The Hero of San Juan Hill","The Lion","Teddy","TR","The Trust Buster","Big Chief","Big Lub","The Phrasemaker","The Schoolmaster","Il Professore","Cautious Cal","Cool Cal","Silent Cal","The Great Engineer","The Great Humanitarian","The Chief","FDR","Give 'Em Hell Harry","Ike","Jack","JFK","Bullshit Johnson","Landslide Lyndon","Light-Bulb Lyndon","LBJ","Tricky Dick","Jerry","Mr. Nice Guy","Jimmy","The Peanut Farmer","Dutch","The Great Communicator","The Gipper","The Teflon President","41","Papa Bush","Poppy","Bubba","Slick Willie","The Comeback Kid","The First Black President","The Big Dog","43","Bush Jr.","Dubya","No Drama Obama"
    };

    public static String getName(Random random) {
        return nicknames[random.nextInt(nicknames.length)];
    }
}
