#droid-faker

##what

An android application to generate contact, message and more user related data.

##adding fixture fields

To register a new fixture to be useable in the scenario `*.json` file you have to add the key to the `FixtureType` enum

__Example:__

Add "operating-systems" to `scenarios/*.json` and add a new enum `OS("operating-system")` to `FixtureType`.
Once the config is loaded you can access the generated Fixture using `FixtureSingleton.getInstance().getFixture(FixtureType.OS)`.

##config

Copy `move-to-sdcard` to the root of your sdcard.

##Getting fixture values

Default fixtures (with possible strings to fill them (run in chromium devtools console)):

###city

```
    http://en.wikipedia.org/wiki/List_of_cities_and_towns_in_Germany
    js: t = [];$('table:not([class]) li > a:first-child').each(function(i, e){ t.push('"' + e.innerText + '"') }); console.log(t.join(','));
```

###company

```
    http://en.wikipedia.org/wiki/List_of_company_name_etymologies
    js: n = []; $('#mw-content-text > ul > li > a:first-child').each(function(i, e){ n.push('"'+e.title+'"')}); n.splice(-4);console.log(n.join(','))
```

###country

```
    http://www.internetworldstats.com/list2.htm
    js: s=[];t=$$('[summary=data2] tbody > tr > td:first-child a');Array.prototype.slice.call(t).forEach(function(element){s.push('"' + element.innerText + '"')});console.log(s.join(','))
```

###nickname

```
    http://en.wikipedia.org/wiki/List_of_nicknames_of_United_States_Presidents
    js: n=[]; $('#mw-content-text ul > li > b').each(function(i,e){ n.push('"' + e.innerText + '"') }); console.log(n.join(','))
```

###street

```
    http://www.brooklyn.com/modules.php?name=ST
    js: s=[];t=$('[name=A]').parentNode.querySelectorAll('a[rel=nofollow]');Array.prototype.slice.call(t).forEach(function(element){s.push('"' + element.innerHTML + '"')});console.log(s.join(','))
```

###title

```
    http://www.prospects.ac.uk/types_of_jobs_browse_all.htm
    js: h=[];$($('.omega ul').get(1)).find('a').each(function(i, e){ h.push('"' + e.innerHTML + '"')});console.log(h.join(','));
```

###url

```
    https://prism-break.org/
    '';$('.section ul li a').each(function(i, e){ out+='"' + e.getAttribute('href') + '",\n'; });console.log(out)
```

##Todo

- [done] contacts
- [done] sms
- [done] call log
- social network (fb, twitter, ...)
- sync settings
- [started] settings (wlan usw)
- [done] fotos
- geodaten
- [started] browsercache, verlauf, suche, fav
- [done] configfile

- email senden, entwürfe
- root, wenn verfügbar geolocation
- wifi geolocation möglich?
- [done] installation von anderen apps (ordner, url)
    - http://router-keygen.en.uptodown.com/android
    - http://forum.xda-developers.com/showthread.php?t=2036411
