package net.kaleoweb.newappcpi.utilities;

import android.content.Context;

import net.kaleoweb.newappcpi.dao.LocalDaoModule;
import net.kaleoweb.newappcpi.dao.MaterielDaoModule;
import net.kaleoweb.newappcpi.databases.LocalDatabase;
import net.kaleoweb.newappcpi.databases.MaterielDatabase;

public class Inventory {
    
    public void storelocal(Context context){
        LocalDaoModule localDaoModule;
        LocalDatabase localDatabase = LocalDatabase.get(context);
        localDaoModule = localDatabase.localDaoModule();
        Local cabine = new Local(0,"CABINE");
        Local etag = new Local(1,"ÉTAGERE GAUCHE");
        Local tiroirrouge = new Local(2,"TIROIR ROUGE");
        Local tiroirvert = new Local(3,"TIROIR VERT");
        Local tiroirbleu = new Local(4,"TIROIR BLEU");
        Local tiroirorange = new Local(5,"TIROIR ORANGE");
        Local plandetravail = new Local(6,"PLAN DE TRAVAIL");
        Local coffreblanc = new Local(7,"COFFRE BLANC");
        Local caissonsurcbine = new Local(8,"CAISSON SUR CABINE");
        Local paroigauche = new Local(9,"PAROI GAUCHE");
        Local caissonavantgauche = new Local(10,"CAISSON AVANT GAUCHE");
        Local caissonmilieugauche = new Local(11,"CAISSON MILIEU GAUCHE");
        Local caissonarrieregauche = new Local(12,"CAISSON ARRIERE GAUCHE");
        Local paroidroite = new Local(13,"PAROI DROITE");
        Local caissonavantdroit = new Local(14,"CAISSON AVANT DROIT");
        Local caissonarrieredroit = new Local(15,"CAISSON ARRIERE DROIT");
        Local arriere = new Local(16,"ARRIERE DU VEHICULE");
        Local saco= new Local(17,"SAC O²");
        
        //INSERT
        localDaoModule.insertLocal(cabine);
        localDaoModule.insertLocal(etag);
        localDaoModule.insertLocal(tiroirrouge);
        localDaoModule.insertLocal(tiroirvert);
        localDaoModule.insertLocal(tiroirbleu);
        localDaoModule.insertLocal(tiroirorange);
        localDaoModule.insertLocal(plandetravail);
        localDaoModule.insertLocal(coffreblanc);
        localDaoModule.insertLocal(caissonsurcbine);
        localDaoModule.insertLocal(paroigauche);
        localDaoModule.insertLocal(caissonavantgauche);
        localDaoModule.insertLocal(caissonmilieugauche);
        localDaoModule.insertLocal(caissonarrieregauche);
        localDaoModule.insertLocal(paroidroite);
        localDaoModule.insertLocal(caissonavantdroit);
        localDaoModule.insertLocal(caissonarrieredroit);
        localDaoModule.insertLocal(arriere);
        localDaoModule.insertLocal(saco);
        
    }
    public void storemateriel(Context context){
        MaterielDaoModule materielDaoModule;
        MaterielDatabase materielDatabase = MaterielDatabase.get(context);
        materielDaoModule= materielDatabase.materielDaoModule();
        //Cabine
        Materiel radio = new Materiel(1,"Radio portative",0);
        materielDaoModule.insertMateriel(radio);
        Materiel gants = new Materiel(1,"Boîte gants à usage unique",0);
        materielDaoModule.insertMateriel(gants);
        Materiel lampe = new Materiel(2,"Lampe portative",0);
        materielDaoModule.insertMateriel(lampe);
        Materiel ciseaux = new Materiel(1,"Ciseaux",0);
        materielDaoModule.insertMateriel(ciseaux);
        Materiel pince = new Materiel(1,"Pince chef agrès",0);
        materielDaoModule.insertMateriel(pince);
        Materiel gilets = new Materiel(3,"Gilets réfléchissants",0);
        materielDaoModule.insertMateriel(gilets);
        Materiel blocbilan = new Materiel(1,"Bloc fiche bilan",0);
        materielDaoModule.insertMateriel(blocbilan);
        Materiel blocetranger = new Materiel(1,"Bloc langues étrangères",0);
        materielDaoModule.insertMateriel(blocetranger);
        Materiel coupe = new Materiel(1,"Coupe ceinture / Brise Vitre",0);
        materielDaoModule.insertMateriel(coupe);
        Materiel cable = new Materiel(1,"Coupe câble",0);
        materielDaoModule.insertMateriel(cable);
        Materiel refus = new Materiel(10,"Fiches refus de transport",0);
        materielDaoModule.insertMateriel(refus);
        Materiel dae = new Materiel(1,"Bloc fiche DAE",0);
        materielDaoModule.insertMateriel(dae);
        Materiel atlas = new Materiel(1,"Atlas",0);
        materielDaoModule.insertMateriel(atlas);
        Materiel manuel = new Materiel(1,"Manuel véhicule Renault",0);
        materielDaoModule.insertMateriel(manuel);
        Materiel molette = new Materiel(1,"Clé à molette",0);
        materielDaoModule.insertMateriel(molette);
        Materiel novi = new Materiel(10,"Fiche tri plan NOVI + Bracelets SINUS",0);
        materielDaoModule.insertMateriel(novi);
        Materiel cric = new Materiel(1,"Lot de bord (cric)",0);
        materielDaoModule.insertMateriel(cric);
        Materiel flacon = new Materiel(1,"Flacon clinogel",0);
        materielDaoModule.insertMateriel(flacon);
        Materiel cales = new Materiel(1,"Cales véhicule",0);
        materielDaoModule.insertMateriel(cales);
        Materiel extincteur = new Materiel(1,"Extincteur",0);
        materielDaoModule.insertMateriel(extincteur);
        Materiel chaines = new Materiel(2,"Chaines à neige",0);
        materielDaoModule.insertMateriel(chaines);
        Materiel cle = new Materiel(1,"Clé autoroute A51 (à demander)",0);
        materielDaoModule.insertMateriel(cle);
        //ETAGERE GAUCHE
        Materiel brulstop10 = new Materiel(6,"Brulstop 10/10",1);
        materielDaoModule.insertMateriel(brulstop10);
        Materiel brulstop20 = new Materiel(6,"Brulstop 20/20",1);
        materielDaoModule.insertMateriel(brulstop20);
        Materiel coussin = new Materiel(2,"Coussins bleu",1);
        materielDaoModule.insertMateriel(coussin);
        Materiel essuie = new Materiel(1,"Essuie main",1);
        materielDaoModule.insertMateriel(essuie);
        Materiel eauversable = new Materiel(2,"Eau versable 500 ml",1);
        materielDaoModule.insertMateriel(eauversable);
        Materiel hydro = new Materiel(1,"Solution hydroalcoolique",1);
        materielDaoModule.insertMateriel(hydro);
        Materiel survie = new Materiel(2,"Couverture de survie",1);
        materielDaoModule.insertMateriel(survie);
        Materiel vomix = new Materiel(3,"VOMIX",1);
        materielDaoModule.insertMateriel(vomix);
        Materiel jesco = new Materiel(1,"Ciseaux jesco",1);
        materielDaoModule.insertMateriel(jesco);
        Materiel betadine = new Materiel(5,"Bétadine jaune dosette",1);
        materielDaoModule.insertMateriel(betadine);
        Materiel betarouge = new Materiel(3,"Bétadine rouge dosette",1);
        materielDaoModule.insertMateriel(betarouge);
        Materiel serphy = new Materiel(3,"Sérum physiologique",1);
        materielDaoModule.insertMateriel(serphy);
        Materiel eaupure = new Materiel(2,"Eau purifiée stérile 45 ml",1);
        materielDaoModule.insertMateriel(eaupure);
        Materiel sparadrap = new Materiel(1,"Sparadrap",1);
        materielDaoModule.insertMateriel(sparadrap);
        
        //TIROIR ROUGE
        Materiel prfua = new Materiel(1,"Trousse perfusion adulte",2);
        materielDaoModule.insertMateriel(prfua);
        Materiel collec = new Materiel(1,"Collecteur à aiguilles",2);
        materielDaoModule.insertMateriel(collec);
        Materiel seringue = new Materiel(1,"Seringue 20 ml",2);
        materielDaoModule.insertMateriel(seringue);
        Materiel bico = new Materiel(2,"Raccords biconique",2);
        materielDaoModule.insertMateriel(bico);
        Materiel sondp = new Materiel(1,"Sonde Peters",2);
        materielDaoModule.insertMateriel(sondp);
        Materiel thermo = new Materiel(1,"Thermoscan",2);
        materielDaoModule.insertMateriel(thermo);
        
        //TIROIR VERT
        Materiel champ = new Materiel(1,"Champ stérile 150 x 90",3);
        materielDaoModule.insertMateriel(champ);
        Materiel champ1 = new Materiel(2,"Champ stérile 75 x 90",3);
        materielDaoModule.insertMateriel(champ1);
        Materiel compresses = new Materiel(5,"Compresses stériles",3);
        materielDaoModule.insertMateriel(compresses);
        Materiel pans = new Materiel(5,"Pansements absorbants stériles",3);
        materielDaoModule.insertMateriel(pans);
        Materiel bande5 = new Materiel(2,"Bandes de 5 cm",3);
        materielDaoModule.insertMateriel(bande5);
        Materiel bande10 = new Materiel(2,"Bandes de 10 cm",3);
        materielDaoModule.insertMateriel(bande10);
        Materiel bande20 = new Materiel(2,"Bandes de 20 cm",3);
        materielDaoModule.insertMateriel(bande20);
        Materiel sprdp = new Materiel(1,"Sparadrap",3);
        materielDaoModule.insertMateriel(sprdp);
        Materiel froid = new Materiel(2,"Pack froid",3);
        materielDaoModule.insertMateriel(froid);
        
        //TIROIR BLEU
        Materiel bavua = new Materiel(1,"BAVU adulte",4);
        materielDaoModule.insertMateriel(bavua);
        Materiel canul2 = new Materiel(1,"Canule de Guedel T2",4);
        materielDaoModule.insertMateriel(canul2);
        Materiel canul3 = new Materiel(1,"Canule de Guedel T3",4);
        materielDaoModule.insertMateriel(canul3);
        Materiel canul4 = new Materiel(1,"Canule de Guedel T4",4);
        materielDaoModule.insertMateriel(canul4);
        Materiel hca = new Materiel(3,"Masque haute concentration adulte",4);
        materielDaoModule.insertMateriel(hca);
        Materiel nebul = new Materiel(1,"Masque nébuliseur adulte",4);
        materielDaoModule.insertMateriel(nebul);
        
        //TIROIR ORANGE
        Materiel bavue = new Materiel(2,"BAVU enfant + nourrisson (1+1)",5);
        materielDaoModule.insertMateriel(bavue);
        Materiel hcp = new Materiel(2,"Masque haute concentration pédia.",5);
        materielDaoModule.insertMateriel(hcp);
        Materiel nebulp = new Materiel(1,"Masque nébuliseur pédiatrique",5);
        materielDaoModule.insertMateriel(nebulp);
        Materiel canul00 = new Materiel(1,"Canule de Guedel T00",5);
        materielDaoModule.insertMateriel(canul00);
        Materiel canul0 = new Materiel(1,"Canule de Guedel T0",5);
        materielDaoModule.insertMateriel(canul0);
        Materiel canul1 = new Materiel(1,"Canule de Guedel T1",5);
        materielDaoModule.insertMateriel(canul1);
        Materiel sondy = new Materiel(1,"Sonde aspiration Yankauer ch 12",5);
        materielDaoModule.insertMateriel(sondy);
        Materiel perfup = new Materiel(1,"Trousse perfusion pédiatrique",5);
        materielDaoModule.insertMateriel(perfup);
        Materiel ser20 = new Materiel(1,"Seringue 20 ml",5);
        materielDaoModule.insertMateriel(ser20);
        
        //PLAN DE TRAVAIL
        Materiel gantsuniques = new Materiel(4,"Boîtes de gants à usage unique",6);
        materielDaoModule.insertMateriel(gantsuniques);
        Materiel linge = new Materiel(1,"Lingettes",6);
        materielDaoModule.insertMateriel(linge);
        Materiel firstsec = new Materiel(1,"Sac premier secours",6);
        materielDaoModule.insertMateriel(firstsec);
        
        //COFFRE BLANC
        Materiel dsa = new Materiel(1,"DSA",7);
        materielDaoModule.insertMateriel(dsa);
        Materiel kitaccou = new Materiel(1,"Kit accouchement",7);
        materielDaoModule.insertMateriel(kitaccou);
        Materiel aev = new Materiel(1,"Kit AEV",7);
        materielDaoModule.insertMateriel(aev);
        Materiel kmi = new Materiel(1,"Kit maladie infectieuse",7);
        materielDaoModule.insertMateriel(kmi);
        
        //CAISSON SUR CABINE
        Materiel damage = new Materiel(1,"Damage controle",8);
        materielDaoModule.insertMateriel(damage);
        Materiel draps = new Materiel(4,"Draps",8);
        materielDaoModule.insertMateriel(draps);
        Materiel bassin= new Materiel(1,"Bassin",8);
        materielDaoModule.insertMateriel(bassin);
        Materiel sac_transparent = new Materiel(1,"Sac transparent",8);
        materielDaoModule.insertMateriel(sac_transparent);
        Materiel kit_membres_sectionnes = new Materiel(1,"Kit membres sectionnés",8);
        materielDaoModule.insertMateriel(kit_membres_sectionnes);
        Materiel haricot = new Materiel(1,"Haricot",8);
        materielDaoModule.insertMateriel(haricot);
        
        //PAROI GAUCHE
        Materiel bouteille15 = new Materiel(1,"Bouteille O² 15 litres",9);
        materielDaoModule.insertMateriel(bouteille15);
        Materiel lsu = new Materiel(1,"LSU + sonde Peters + racord biconique",9);
        materielDaoModule.insertMateriel(lsu);
        Materiel matelas = new Materiel(1,"Matelas à dépression+pompe+drap",9);
        materielDaoModule.insertMateriel(matelas);
        
        //CAISSON AVANT GAUCHE
        Materiel ringer_lactate = new Materiel(1,"Ringer lactate",10);
        materielDaoModule.insertMateriel(ringer_lactate);
        Materiel chlorure = new Materiel(1,"Chlorure de sodium 500 ml",10);
        materielDaoModule.insertMateriel(chlorure);
        Materiel lunettes_de_protection = new Materiel(3,"Lunettes de protection",10);
        materielDaoModule.insertMateriel(lunettes_de_protection);
        Materiel masques_chirurgicaux = new Materiel(50,"Masques chirurgicaux",10);
        materielDaoModule.insertMateriel(masques_chirurgicaux);
        
        //caisson milieu gauche
        Materiel calesTete = new Materiel(2,"Cales tête",11);
        materielDaoModule.insertMateriel(calesTete);
        Materiel sangle_araignee = new Materiel(1,"Sangle araignée",11);
        materielDaoModule.insertMateriel(sangle_araignee);
        Materiel sangle_noire = new Materiel(2,"Sangle noire",11);
        materielDaoModule.insertMateriel(sangle_noire);
        Materiel sac_lsu = new Materiel(1,"Sac LSU",11);
        materielDaoModule.insertMateriel(sac_lsu);
        
        //CAISSON ARRIERE GAUCHE
        Materiel brancard_souple= new Materiel(1,"Brancard souple",12);
        materielDaoModule.insertMateriel(brancard_souple);
        Materiel gilet_reflechissant= new Materiel(1,"Gilet réfléchissant",12);
        materielDaoModule.insertMateriel(gilet_reflechissant);
        Materiel housse_mortuaire= new Materiel(1,"Housse mortuaire",12);
        materielDaoModule.insertMateriel(housse_mortuaire);
        
        //PAROI DROITE
        Materiel act = new Materiel(1,"ACT (KED)",13);
        materielDaoModule.insertMateriel(act);
        Materiel bouteille = new Materiel(1,"Bouteille O² 5 litres",13);
        materielDaoModule.insertMateriel(bouteille);
        Materiel sacateles = new Materiel(1,"Sac atèles + pompe",13);
        materielDaoModule.insertMateriel(sacateles);
        Materiel sac_oxygene= new Materiel(1,"Sac oxygéne",13);
        materielDaoModule.insertMateriel(sac_oxygene);
        
        //CAISSON AVANT DROIT
        Materiel colliers_cervicaux = new Materiel(2,"Colliers cervicaux",14);
        materielDaoModule.insertMateriel(colliers_cervicaux);
        
        //CAISSON ARRIERE DROIT
        Materiel draps2 = new Materiel(3,"Draps",15);
        materielDaoModule.insertMateriel(draps2);
        
        //ARRIERE DU VEHICULE
        Materiel chaise = new Materiel(1,"Chaise",16);
        materielDaoModule.insertMateriel(chaise);
        Materiel plan_dur = new Materiel(1,"Plan dur (Sous le brancard)",16);
        materielDaoModule.insertMateriel(plan_dur);
        Materiel brancard_cuillere = new Materiel(1,"Brancard cuillère",16);
        materielDaoModule.insertMateriel(brancard_cuillere);
        
        //SAC O²
        Materiel bavu_adulte = new Materiel(1,"BAVU Adulte",17);
        materielDaoModule.insertMateriel(bavu_adulte);
        Materiel bavu_enfant = new Materiel(1,"BAVU Enfant",17);
        materielDaoModule.insertMateriel(bavu_enfant);
        Materiel bavu_nourrisson = new Materiel(1,"BAVU Nourrisson",17);
        materielDaoModule.insertMateriel(bavu_nourrisson);
        Materiel masque_haute_concentration_adulte = new Materiel(1,"Masque Haute concentration Adulte",17);
        materielDaoModule.insertMateriel(masque_haute_concentration_adulte);
        Materiel masque_haute = new Materiel(1,"Masque haute concentration Enfant",17);
        materielDaoModule.insertMateriel(masque_haute);
        Materiel canulestt = new Materiel(1,"Canules de GUEDEL (Chaque taille)",17);
        materielDaoModule.insertMateriel(canulestt);
        Materiel bout5l = new Materiel(1,"Bouteille O² 5L",17);
        materielDaoModule.insertMateriel(bout5l);
        Materiel colliers_cerv = new Materiel(2,"1+1 Colliers cervicaux",17);
        materielDaoModule.insertMateriel(colliers_cerv);
        
        
    }
    
}
