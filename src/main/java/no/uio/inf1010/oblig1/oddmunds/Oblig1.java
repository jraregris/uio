package no.uio.inf1010.oblig1.oddmunds;

public class Oblig1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Obligatorisk oppgave 1 - Inf1010");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

		
		PersonList pl = new PersonList();
		
		System.out.println("Legger til hundrevis av folk...");
		addABunch(pl);
		System.out.print("Sjekker at rubendw finnes...");
		if(pl.getPersonByUsername("rubendw")!=null){
			System.out.println("OK!");
		} else {
			System.out.println("FEIL!");
		}
		System.out.println("Fjerner rubendw!");
		pl.remove("rubendw");
		System.out.print("Sjekker at rubendw ikke finnes...");
		if(pl.getPersonByUsername("rubendw")==null){
			System.out.println("OK!");
		} else {
			System.out.println("FEIL!");
		}
		System.out.println("Legger til rubendw igjen!");
		pl.addNewPerson("rubendw", "Ruben Wangberg");
		System.out.print("Sjekker at rubendw og haavarte ikke er venner ennå...");
		if(pl.getPersonByUsername("rubendw").isFriendsWith("haavarte")!=true){
			System.out.println("OK!");
		} else {
			System.out.println("FEIL!");
		}
		System.out.println("Legger rubendw og haavarte til som venner!");
		pl.befriend("rubendw", "haavarte");
		System.out.print("Sjekker at rubendw og haavarte har blitt venner...");
		if(pl.getPersonByUsername("rubendw").isFriendsWith("haavarte")==true){
			System.out.println("OK!");
		} else {
			System.out.println("FEIL!");
		}
		System.out.println("Gjør rubendw og haavarte til uvenner!");
		pl.defriend("rubendw", "haavarte");
		System.out.print("Sjekker at rubendw og haavarte ikke er venner lenger...");
		if(pl.getPersonByUsername("rubendw").isFriendsWith("haavarte")!=true){
			System.out.println("OK!");
		} else {
			System.out.println("FEIL!");
		}
		System.out.println("Gir rubendw mange nye venner!");
		addABunchOfFriends(pl, "rubendw");
		System.out.println("Skriver ut en liste over rubendws venner!");
		System.out.println(pl.listFriendsAsString("rubendw"));
		System.out.println("Fjerner en person blant vennene til rubendw!");
		pl.remove("prebenno");
		System.out.println("Skriver ut vennelisten til rubendw igjen, for å forsikre oss om at det har gått bra!");
		System.out.println(pl.listFriendsAsString("rubendw"));
	}
	
	private static void addABunchOfFriends(PersonList pl, String person) {
		pl.befriend(person, "mrt");
		pl.befriend(person, "avinnogg");
		pl.befriend(person, "prebenno");
		pl.befriend(person, "kseielst");
		pl.befriend(person, "tanxn");
		pl.befriend(person, "sigurdsa");
		pl.befriend(person, "huij");
		pl.befriend(person, "anderbn");
		pl.befriend(person, "omelgsto");
		pl.befriend(person, "joakibj");
		pl.befriend(person, "richar");
		pl.befriend(person, "linnaad");
		pl.befriend(person, "hennikar");
		pl.befriend(person, "dingh");
		pl.befriend(person, "nikitam");
		pl.befriend(person, "huyt");
		pl.befriend(person, "chunyuyc");
		pl.befriend(person, "kabeern");
		pl.befriend(person, "ivicam");
		pl.befriend(person, "jorgebfr");
		pl.befriend(person, "torbjsk");
		pl.befriend(person, "kenneka");
		pl.befriend(person, "geirrn");
		pl.befriend(person, "mariebf");
	}

	private static void addABunch(PersonList pl){
		pl.addNewPerson("mrt","Kasra");
		pl.addNewPerson("avinnogg","Anders");
		pl.addNewPerson("prebenno","Preben Nenseth");
		pl.addNewPerson("kseielst","Kirsten Marie");
		pl.addNewPerson("tanxn","Xuan Tan");
		pl.addNewPerson("sigurdsa","Sigurd");
		pl.addNewPerson("huij","Hui");
		pl.addNewPerson("anderbn","Anders Blom");
		pl.addNewPerson("omelgsto","Øyvind");
		pl.addNewPerson("joakibj","Joakim");
		pl.addNewPerson("richar","Richard");
		pl.addNewPerson("linnaad","Linn Kristin");
		pl.addNewPerson("hennikar","Henni");
		pl.addNewPerson("haavarte","Håvard");
		pl.addNewPerson("rubendw","Ruben");
		pl.addNewPerson("dingh","Ding");
		pl.addNewPerson("nikitam","Nikita");
		pl.addNewPerson("huyt","Huy Quang");
		pl.addNewPerson("chunyuyc","Chun-Yu");
		pl.addNewPerson("kabeern","Kabeer");
		pl.addNewPerson("ivicam","Ivica");
		pl.addNewPerson("jorgebfr","Jørgen Bentseng");
		pl.addNewPerson("torbjsk","Torbjørn Skyberg");
		pl.addNewPerson("kenneka","Kenneth");
		pl.addNewPerson("geirrn","Geir Roar");
		pl.addNewPerson("mariebf","Marie Beate");
		pl.addNewPerson("akbarf","Shahab Faghihi");
		pl.addNewPerson("rafaellm","Rafael Lukas");
		pl.addNewPerson("ummairt","Ummair");
		pl.addNewPerson("liweiw","Liwei");
		pl.addNewPerson("suhasgj","Suhas Govind");
		pl.addNewPerson("girmayzg","Girmay Zerom");
		pl.addNewPerson("mettelol","Mette");
		pl.addNewPerson("puilingt","Puiling");
		pl.addNewPerson("prebeo","Preben Nenseth");
		pl.addNewPerson("gauravch","Gaurav");
		pl.addNewPerson("wwalmnes","W. Francis Daraman");
		pl.addNewPerson("kristelf","Kristina Heyerdahl");
		pl.addNewPerson("mshaugla","Martin Severin ");
		pl.addNewPerson("svensai","Sven");
		pl.addNewPerson("matshr","Mats Holm");
		pl.addNewPerson("wardaha","Warda");
		pl.addNewPerson("hilia","Hili");
		pl.addNewPerson("jmopsahl","Jan Magnus Granberg");
		pl.addNewPerson("rebekkjm","Rebekka Johanne");
		pl.addNewPerson("stianaki","Stian Aune");
		pl.addNewPerson("espenala","Espen-Alexis");
		pl.addNewPerson("amjansbe","Anne Marte Ihler");
		pl.addNewPerson("navdees","Navdeep");
		pl.addNewPerson("fredva","Fredrik");
		pl.addNewPerson("dkkriste","David Kai Christen");
		pl.addNewPerson("ojalass","Ojala Saghir");
		pl.addNewPerson("robinmy","Robin");
		pl.addNewPerson("sohailmm","Sohail Musa");
		pl.addNewPerson("atifrm","Atif Rashid");
		pl.addNewPerson("kristwil","Kristian");
		pl.addNewPerson("illias","Illia");
		pl.addNewPerson("eivinsax","Eivind");
		pl.addNewPerson("abdulwm","Abdul Wahab");
		pl.addNewPerson("reidarab","Reidar Andre");
		pl.addNewPerson("tinajk","Tina Jeanette");
		pl.addNewPerson("kkho","Khiem-Kim Xuan");
		pl.addNewPerson("junemto","June Marie");
		pl.addNewPerson("dmitrijv","Dmitrij");
		pl.addNewPerson("baardha","Baard");
		pl.addNewPerson("toresj","Tore Sivert Johnsen");
		pl.addNewPerson("ngatl","Nga Tuyet Thi");
		pl.addNewPerson("magnel","Magne Andre");
		pl.addNewPerson("elsagt","Elsa Gabrielle");
		pl.addNewPerson("anderv","Anders");
		pl.addNewPerson("josteili","Jostein");
		pl.addNewPerson("magnusri","Magnus");
		pl.addNewPerson("olesb","Ole Stian");
		pl.addNewPerson("trondni","Trond Anders");
		pl.addNewPerson("erikwb","Erik Wilhelm");
		pl.addNewPerson("behrozm","Behroz");
		pl.addNewPerson("thomaark","Thomas");
		pl.addNewPerson("kyrreg","Kyrre Matias");
		pl.addNewPerson("torbjh","Torbjørn Bull");
		pl.addNewPerson("helgehf","Helge Heyerdahl");
		pl.addNewPerson("erikberg","Erik");
		pl.addNewPerson("sasam","Sasa");
		pl.addNewPerson("ajmolvae","Arnold John Ngugi");
		pl.addNewPerson("henrikhe","Henrik Strømme");
		pl.addNewPerson("kjetilge","Kjetil");
		pl.addNewPerson("erlendp","Erlend");
		pl.addNewPerson("ingeml","Inger Margrethe");
		pl.addNewPerson("hennihor","Henning");
		pl.addNewPerson("erlendvr","Erlend Vihovde");
		pl.addNewPerson("chrismaa","Christine");
		pl.addNewPerson("dishankr","Dishanker");
		pl.addNewPerson("lubnar","Lubna");
		pl.addNewPerson("perats","Per Anders");
		pl.addNewPerson("moayadi","Moayad");
		pl.addNewPerson("kristoth","Kristoffer");
		pl.addNewPerson("hogenhaa","Aksel");
		pl.addNewPerson("frodevw","Frode Vegard");
		pl.addNewPerson("davidjo","David Jørgensen");
		pl.addNewPerson("raouln","Raoul");
		pl.addNewPerson("arnehs","Arne Haugum");
		pl.addNewPerson("gurilu","Guri");
		pl.addNewPerson("bernob","Bernt Øivind");
		pl.addNewPerson("eivinhb","Eivind Haukås");
		pl.addNewPerson("olegf","Oleg Petrovich");
		pl.addNewPerson("ramunek","Ramune");
		pl.addNewPerson("irinaia","Irina Igorevna");
		pl.addNewPerson("chrinr","Christopher Neumann");
		pl.addNewPerson("andrss","Andres Sørensen");
		pl.addNewPerson("jonad","Jon Andre");
		pl.addNewPerson("marold","Mari Sveum");
		pl.addNewPerson("erikjsi","Erik Jonathan");
		pl.addNewPerson("morbergm","Morten");
		pl.addNewPerson("carolsku","Caroline");
		pl.addNewPerson("haakno","Håkon");
		pl.addNewPerson("ramting","Ramtin");
		pl.addNewPerson("narink","Narin");
		pl.addNewPerson("philipcs","Philip Christian");
		pl.addNewPerson("helenens","Helene Norheim");
		pl.addNewPerson("madeleii","Madeleine G");
		pl.addNewPerson("magnhaan","Magnus Helland");
		pl.addNewPerson("chbr","Christian");
		pl.addNewPerson("matshau","Mats");
		pl.addNewPerson("robertko","Robert");
		pl.addNewPerson("stianval","Stian");
		pl.addNewPerson("peterbb","Peter Brottveit");
		pl.addNewPerson("najiak","Naji Ahmad");
		pl.addNewPerson("oleckv","Ole Christian");
		pl.addNewPerson("pfferrer","Pierre Francois Jean");
		pl.addNewPerson("frodetbj","Frode Tobias");
		pl.addNewPerson("helesa","Helene");
		pl.addNewPerson("martsv","Marte");
		pl.addNewPerson("marienh","Marie Nathalie");
		pl.addNewPerson("haavarot","Håvard Mikkelsen");
		pl.addNewPerson("runarfu","Runar");
		pl.addNewPerson("michaekg","Michael K");
		pl.addNewPerson("mariuten","Marius");
		pl.addNewPerson("simenheg","Simen");
		pl.addNewPerson("thomahg","Thomas Hoel");
		pl.addNewPerson("eirikds","Eirik Djupvik");
		pl.addNewPerson("einarnot","Einar");
		pl.addNewPerson("bendikwa","Bendik Wang");
		pl.addNewPerson("erim","Erik");
		pl.addNewPerson("torjusha","Torjus");
		pl.addNewPerson("fredrll","Fredrik Lyder");
		pl.addNewPerson("borissi","Boris");
		pl.addNewPerson("kennetsa","Kenneth Solbø");
		pl.addNewPerson("simenbu","Simen");
		pl.addNewPerson("maximh","Maxim");
		pl.addNewPerson("djamela","Djamel");
		pl.addNewPerson("konstaaz","Konstantin Aleksandrovich");
		pl.addNewPerson("nawrozk","Nawroz");
		pl.addNewPerson("jingt","Jing");
		pl.addNewPerson("fredrvaa","Fredrik");
		pl.addNewPerson("mariasfo","Victoria");
		pl.addNewPerson("gunnaaur","Gunnar");
		pl.addNewPerson("eivinded","Eivind Evenstad");
		pl.addNewPerson("mikkeln","Mikkel");
		pl.addNewPerson("andrsha","Andreas Stusvik");
		pl.addNewPerson("alexpe","Alex");
		pl.addNewPerson("mohammkk","Mohammad Khidash");
		pl.addNewPerson("espenoh","Espen O");
		pl.addNewPerson("brynjagr","Brynjar Grønhaug");
		pl.addNewPerson("markussd","Markus Sortland");
		pl.addNewPerson("arvee","Arve");
		pl.addNewPerson("arildre","Arild");
		pl.addNewPerson("ronvu","Ron Vidar");
		pl.addNewPerson("erlendax","Erlend");
		pl.addNewPerson("patricd","Patrick");
		pl.addNewPerson("zinair","Zina Ida");
		pl.addNewPerson("jonasbl","Jonas Bøhn");
		pl.addNewPerson("sofieseb","Sofie");
		pl.addNewPerson("magnudev","Magnus");
		pl.addNewPerson("eivindpr","Eivind");
		pl.addNewPerson("ingrisw","Ingrid Semb");
		pl.addNewPerson("anderkry","Anders");
		pl.addNewPerson("steinbs","Stein By");
		pl.addNewPerson("olavaand","Olav Andre");
		pl.addNewPerson("kyrrehe","Kyrre Havik");
		pl.addNewPerson("samik","Sami");
		pl.addNewPerson("rafunke","Richard Alexander");
		pl.addNewPerson("torgebo","Torgeir");
		pl.addNewPerson("farahkh","Farah");
		pl.addNewPerson("pmbraath","Paal Mikkel S");
		pl.addNewPerson("eivinbar","Eivind");
		pl.addNewPerson("ljhetlan","Lars Johannes Fjeldså");
		pl.addNewPerson("sigvef","Sigve");
		pl.addNewPerson("makirip","Hans-Olav Maki");
		pl.addNewPerson("fredreng","Fredrik");
		pl.addNewPerson("nghiepv","Nghiep Van");
		pl.addNewPerson("williarm","William R");
		pl.addNewPerson("tomasjoh","Tomas");
		pl.addNewPerson("tatyanac","Tatyana");
		pl.addNewPerson("beritlar","Berit");
		pl.addNewPerson("bwkriste","Benjamin Werner Bych");
		pl.addNewPerson("oystsand","Øystein");
		pl.addNewPerson("krth","Kristian");
		pl.addNewPerson("fridtjha","Fridtjof");
		pl.addNewPerson("mortenkw","Morten Krokan");
		pl.addNewPerson("jorgenks","Jørgen Krog");
		pl.addNewPerson("bjornagh","Bjørnar Grindhaug");
		pl.addNewPerson("miriakh","Miriam Kirstine");
		pl.addNewPerson("magnujoh","Magnus");
		pl.addNewPerson("oddmm","Odd Magnus");
		pl.addNewPerson("rvfjellb","Rune Vegard Skullerud");
		pl.addNewPerson("mariaroi","Marianne");
		pl.addNewPerson("effata","Effat");
		pl.addNewPerson("jamesdt","James David");
		pl.addNewPerson("dagolo","Dag Øyvind");
		pl.addNewPerson("johnaby","John Alexander");
		pl.addNewPerson("marishu","Mari Sønsteby");
		pl.addNewPerson("kentol","Kent");
		pl.addNewPerson("sverrhol","Sverre");
		pl.addNewPerson("henninbw","Henning Bauer");
		pl.addNewPerson("jacquelo","Jacqueline Osei");
		pl.addNewPerson("espegri","Espen");
		pl.addNewPerson("marstorh","Marius");
		pl.addNewPerson("hennikl","Henning");
		pl.addNewPerson("andrefos","Andreas");
		pl.addNewPerson("asgeirom","Asgeir Oldernes");
		pl.addNewPerson("alinach","Alina");
		pl.addNewPerson("magnutr","Magnus");
		pl.addNewPerson("snorrewb","Snorre William");
		pl.addNewPerson("baomn","Bao Marianna");
		pl.addNewPerson("benjamba","Benjamin Brunvoll");
		pl.addNewPerson("alexso","Alex");
		pl.addNewPerson("gryseg","Gry Siri");
		pl.addNewPerson("arsenc","Arsen");
		pl.addNewPerson("bentaf","Bent Anders");
		pl.addNewPerson("sarahbs","Sarah Beate");
		pl.addNewPerson("linnkbr","Linn Kristine");
		pl.addNewPerson("andehag","Anders");
		pl.addNewPerson("patrisk","Patrick Bauge");
		pl.addNewPerson("krishols","Kristine Bøhler");
		pl.addNewPerson("knutrok","Knut");
		pl.addNewPerson("kristony","Kristoffer");
		pl.addNewPerson("jennyli","Jenny");
		pl.addNewPerson("jonfg","Jon Ferdinand");
		pl.addNewPerson("toreon","Tore Olav");
		pl.addNewPerson("sindrepu","Sindre");
		pl.addNewPerson("ragnarsm","Ragnar");
		pl.addNewPerson("fredrme","Fredrik");
		pl.addNewPerson("fredriwa","Fredrik");
		pl.addNewPerson("vegardko","Vegard");
		pl.addNewPerson("thorhw","Thor Henning");
		pl.addNewPerson("marstorh","Marius");
		pl.addNewPerson("hermang","Herman");
		pl.addNewPerson("haakobja","Håkon Botnmark");
		pl.addNewPerson("steinshe","Steinar Sørli");
		pl.addNewPerson("jondr","Jon Dahl");
		pl.addNewPerson("semird","Semir");
		pl.addNewPerson("torbjpl","Torbjørn P");
		pl.addNewPerson("kinevl","Kine Veronica");
		pl.addNewPerson("jarandkh","Jarand Kiste");
		pl.addNewPerson("odarn","Oda Rønholt");
		pl.addNewPerson("oleseg","Ole");
		pl.addNewPerson("oystebjo","Øystein");
		pl.addNewPerson("sofiacl","Sofia");
		pl.addNewPerson("ophatm","Ophat");
		pl.addNewPerson("fredrhaa","Fredrik");
		pl.addNewPerson("martigla","Martin Gullien");
		pl.addNewPerson("mattiahj","Mattias Håheim");
		pl.addNewPerson("haavanes","Håvard");
		pl.addNewPerson("scfure","Silje Christine R");
		pl.addNewPerson("mohammrg","Mohammad Reza");
		pl.addNewPerson("mortensi","Morten Wåsjø");
		pl.addNewPerson("camilve","Camilla");
		pl.addNewPerson("sindrenm","Sindre Nordberg");
		pl.addNewPerson("petteaar","Petter");
		pl.addNewPerson("marifor","Maria");
		pl.addNewPerson("marstorh","Marius");
		pl.addNewPerson("karenwf","Karen Wanvik");
		pl.addNewPerson("larsjeng","Lars Jansøn");
		pl.addNewPerson("fredrins","Fredrik Notevarp");
		pl.addNewPerson("vegarang","Vegard");
		pl.addNewPerson("jorundal","Jørund");
		pl.addNewPerson("endrebs","Endre Bunes");
		pl.addNewPerson("torees","Tore Mikal");
		pl.addNewPerson("olavpo","Olav");
		pl.addNewPerson("marinan","Marina");
		pl.addNewPerson("sirins","Siri Nærland");
		pl.addNewPerson("ahbfinst","Ann-Charlotte Birgitta");
		pl.addNewPerson("anishr","Anish");
		pl.addNewPerson("axelbh","Axel Butler");
		pl.addNewPerson("jenserin","Jens Erik");
		pl.addNewPerson("zhiyansa","Zhiyan");
		pl.addNewPerson("emanuel","Emanuele");
		pl.addNewPerson("jorgeren","Jørgen Vold");
		pl.addNewPerson("maartena","Arthur");
		pl.addNewPerson("hatt","Thanh Ha");
		pl.addNewPerson("vivihn","Vivi Hoang");
		pl.addNewPerson("jorgevi","Jørgen");
		pl.addNewPerson("daniero","Daniel");
		pl.addNewPerson("eirikrl","Eirik Røbech");
		pl.addNewPerson("dinhut","Dinh Uy");
		pl.addNewPerson("tommhald","Tommy");
		pl.addNewPerson("ilirn","Ilir");
		pl.addNewPerson("marstorh","Marius");
		pl.addNewPerson("rizwanaa","Rizwan Ali");
		pl.addNewPerson("fredriti","Fredrik");
		pl.addNewPerson("olearos","Ole Andreas");
		pl.addNewPerson("thomamha","Thomas Misund");
		pl.addNewPerson("pederbe","Peder");
		pl.addNewPerson("sigbjogs","Sigbjørn Gulløv");
		pl.addNewPerson("olekto","Ole Kristian");
		pl.addNewPerson("lenacar","Lena Christine Lund");
		pl.addNewPerson("oddmunds","Oddmund");
		pl.addNewPerson("javalla","Joakim");
		pl.addNewPerson("andrebog","Andreas");
		pl.addNewPerson("jannro","Jan Nyquvist");
		pl.addNewPerson("erlenkr","Erlend");
		pl.addNewPerson("shabnapi","Shabnam");
		pl.addNewPerson("aleksase","Aleksander");
		pl.addNewPerson("mudasirs","Mudasir Esmael");
		pl.addNewPerson("igorkol","Igor");
		pl.addNewPerson("bahonurn","Bahonur");
		pl.addNewPerson("princej","Prince");
		pl.addNewPerson("evaghi","Eva Gamlesæter");
		pl.addNewPerson("paihler","Per Anton Fagerholt");
		pl.addNewPerson("dansk","Dan Scheslien");
		pl.addNewPerson("admicha","Abel Gebre Michael");
		pl.addNewPerson("tommysae","Tommy");
		pl.addNewPerson("atvaerne","Anh Tuyet Thi");
		pl.addNewPerson("migrana","Menua");
		pl.addNewPerson("sasoria","Sergio Alberto Arevalo");
		pl.addNewPerson("simenren","Simen");
		pl.addNewPerson("runeyt","Rune Y.");
		pl.addNewPerson("bartoszp","Bartosz");
		pl.addNewPerson("jmjohann","Jørgen Mundgjel Fjeld");
		pl.addNewPerson("fabiantf","Fabian Tytingvåg");
		pl.addNewPerson("oyvstenl","Øyvind");
		pl.addNewPerson("kritr","Kristian");
		pl.addNewPerson("torgeirl","Torgeir Lyche");
		pl.addNewPerson("tmtaklo","Tore Magnus Arnesen");
		pl.addNewPerson("espenmei","Espen Moen");
		pl.addNewPerson("bsalhell","Basil Seifaddin Hussain Al");
		pl.addNewPerson("henrihel","Henrik");
		pl.addNewPerson("lukaszjm","Lukasz Jan");
		pl.addNewPerson("eirigro","Eirik");
		pl.addNewPerson("khalidab","Khalid Mussanur");
		pl.addNewPerson("oleksank","Oleksandra");
		pl.addNewPerson("karimac","Karima");
		pl.addNewPerson("bahonurn","Bahonur");
		pl.addNewPerson("mikalm","Mikal");
		pl.addNewPerson("jmring","Janusz Mieczyslaw");
		pl.addNewPerson("haakonoo","Håkon Olav");
		pl.addNewPerson("mariubac","Marius");
		pl.addNewPerson("masjurse","Magnus");
		pl.addNewPerson("andrberg","Andreas");
		pl.addNewPerson("anderrb","Anders Ramsvik");
		pl.addNewPerson("menalta","Menal Talal");
		pl.addNewPerson("cecilyyz","Cecily Yan");
		pl.addNewPerson("benct","Ben Christopher");
		pl.addNewPerson("oyvinin","Øyvind");
		pl.addNewPerson("moniksk","Monika Sperstad");
		pl.addNewPerson("ejjohans","Erlend Johan Strømhaug");
		pl.addNewPerson("thomapor","Thomas");
		pl.addNewPerson("tahiray","Tahira");
		pl.addNewPerson("josek","Jose Louis");
		pl.addNewPerson("stianf","Stian Kjetil");
		pl.addNewPerson("bendiko","Bendik Rønning");
		pl.addNewPerson("bmmender","Bedeho");
		pl.addNewPerson("espeak","Espen Angell");
		pl.addNewPerson("richar","Richard");
		pl.addNewPerson("jpaasen","Jon");
		pl.addNewPerson("daghf","Dag Haavi");
		pl.addNewPerson("larsstor","Lars");
		pl.addNewPerson("fredva","Fredrik");
		pl.addNewPerson("steing","Stein");
		pl.addNewPerson("michael","Stein Michael");
		pl.addNewPerson("stm","Stein Michael");
	}
}
