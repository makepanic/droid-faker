package de.rndm.droidFaker.fixtures;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mkp
 * Date: 20.10.13
 * Time: 19:45
 * To change this template use File | Settings | File Templates.
 */
public class Title {

    /*
    @see http://www.prospects.ac.uk/types_of_jobs_browse_all.htm
    js: h=[];$($('.omega ul').get(1)).find('a').each(function(i, e){ h.push('"' + e.innerHTML + '"')});console.log(h.join(','));
     */
    private static String[] titles = {
        "Academic librarian","Accommodation manager","chartered accountant","chartered certified accountant","chartered management accountant","chartered public finance accountant","Accounting technician","Actor","Actuary","Acupuncturist","secretary/administrator","arts administrator","charity officer","Civil Service administrator","education administrator","local government officer","sports administrator","Adult guidance worker","Adult nurse","Advertising account executive","Advertising account planner","Advertising art director","Advertising copywriter","Advice worker","Aeronautical engineer","Agricultural consultant","land-based engineer","international aid/development worker","freight forwarder","Air cabin crew","Air traffic controller","Airline pilot","paramedic","Amenity horticulturist","Analytical chemist","Animal nutritionist","Animal technologist","Animator","Applications developer","Arboriculturist","Archaeologist","Architect","Architectural technologist","Archivist","Armed forces logistics/support/administrative officer","Armed forces operational officer","Armed forces technical officer","Armed forces training and education officer","commercial art gallery manager","Art therapist","fine artist","Arts administrator","community arts worker","research scientist (physical sciences)","Audiological scientist","Automotive engineer"
    };

    public static String getName(Random random) {
        return titles[random.nextInt(titles.length)];
    }
}
