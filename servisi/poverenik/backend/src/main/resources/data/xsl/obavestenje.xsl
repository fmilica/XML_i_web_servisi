<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0"
    xmlns:obv="http://obavestenje"
    xmlns:tipovi="http://tipovi">
    
    <xsl:variable name="space" select="'&#xA0;'"/>
    
    <xsl:template match="/">
        <html>
            <head>
                <style>
                    body {
                        background-color: grey;
                    }
                    .a4 {
                        background: white;
                        width: 21cm;
                        height: 29.7cm;
                        display: block;
                        margin: 0 auto;
                        margin-top: 1cm;
                        margin-bottom: 1cm;
                    }
                    .sadrzaj {
                        padding-top: 50px;
                        padding-bottom: 50px;
                        padding-left: 80px;
                        padding-right: 80px;
                    }
                    .trazilac {
                        margin-bottom: 20px;
                    }
                    .obavestenje {
                        text-align: center;
                    }
                    .obavestenje_opis {
                        text-align: center;
                        margin-bottom: 20px;
                    }
                    .tekst {
                        text-indent:1em;
                        margin-bottom: 10px;
                    }
                    .obican_tekst {
                        margin-bottom: 10px;
                    }
                    .informacija_opis {
                        text-align: center;
                        font-size: 10pt;
                        margin-bottom: 10px;
                    }
                </style>
            </head>
            <body>
                <div class="a4">
                    <div class="sadrzaj">
                        <div>
                            <u><xsl:value-of select="obv:Obavestenje/obv:Organ_vlasti/tipovi:Naziv"/></u>
                        </div>
                        <div>
                            <u><xsl:value-of select="obv:Obavestenje/obv:Organ_vlasti/tipovi:Sediste"/></u>
                        </div>
                        <div>(naziv i sediste organa)</div>
                        <div>
                            Broj predmeta: <u><xsl:value-of select="obv:Obavestenje/obv:Broj_predmeta"/></u>
                        </div>
                        <div>
                            Datum: <u><xsl:value-of select="obv:Obavestenje/@datum"/></u>
                        </div>
                        <div>
                            <xsl:choose>
                                <xsl:when test="obv:Obavestenje/obv:Podnosilac/tipovi:Naziv">
                                    <div>
                                        <u><xsl:value-of select="obv:Obavestenje/obv:Podnosilac/tipovi:Naziv"/></u>
                                    </div>
                                </xsl:when>
                                <xsl:otherwise>
                                    <div>
                                        <u>
                                            <xsl:value-of select="obv:Obavestenje/obv:Podnosilac/tipovi:Ime"/><xsl:value-of select="$space"/>
                                            <xsl:value-of select="obv:Obavestenje/obv:Podnosilac/tipovi:Prezime"/>
                                        </u>
                                    </div>
                                </xsl:otherwise>
                            </xsl:choose>
                        </div>
                        <div>
                            <u><xsl:value-of select="obv:Obavestenje/obv:Podnosilac/tipovi:Adresa/tipovi:Ulica"/><xsl:value-of select="$space"/>
                                <xsl:value-of select="obv:Obavestenje/obv:Podnosilac/tipovi:Adresa/tipovi:Ulicni_broj"/>, 
                                <xsl:value-of select="obv:Obavestenje/obv:Podnosilac/tipovi:Adresa/tipovi:Mesto"/></u>
                        </div>
                        <div class="trazilac">Ime i prezime/naziv/i adresa podnosioca zahteva</div>
                        <div class="obavestenje"><b>O B A V E S T E Nj E</b></div>
                        <div class="obavestenje"><b>o stavljanju na uvid dokument koji sadrzi</b></div>
                        <div class="obavestenje_opis"><b>trazenu informaciju i o izradi kopije</b></div>
                        <div class="tekst">
                            <xsl:variable name="dt1" select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Datum_potrazivanja"/>
                            <xsl:variable name="date1" select="xs:string(concat(
                                substring($dt1,9,2),'.',
                                substring($dt1,6,2),'.',
                                substring($dt1,1,4),'.'))"/>
                            
                            Na osnovu clana <xsl:value-of select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Zakonska_osnova/obv:Clan"/>
                            . st. <xsl:value-of select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Zakonska_osnova/obv:Stav"/>. 
                            <xsl:value-of select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Zakonska_osnova/obv:Zakon"/>, postupajuci po
                            vasem zahtevu za slobodan pristup informacijama od <u><xsl:value-of select="$date1"/></u> god.,
                            kojim ste trazili uvid u dokument/e sa informacijama o/u vezi sa:
                        </div>
                        <div><u><xsl:value-of select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Opis_trazene_informacije"/></u></div>
                        <div class="informacija_opis">(opis trazene informacije)</div>
                        <xsl:variable name="dt" select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Podaci_o_uvidu/obv:Datum"/>
                        <xsl:variable name="date" select="xs:string(concat(
                            substring($dt,9,2),'.',
                            substring($dt,6,2),'.',
                            substring($dt,1,4),'.'))"/>
                        <xsl:variable name="t" select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Podaci_o_uvidu/obv:Vreme"/>
                        <xsl:variable name="time" select="xs:string(concat(
                            substring($t,1,2),':',
                            substring($t,4,2),':',
                            substring($t,7,2)))"/>
                        <div class="obican_tekst">
                            obavestavamo Vas da dana <u><xsl:value-of select="$date"/></u>, u <u><xsl:value-of select="$time"/></u>
                            , odnosno u vremenu od <u><xsl:value-of select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Podaci_o_uvidu/obv:Pocetno_vreme"/></u> do <u><xsl:value-of select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Podaci_o_uvidu/obv:Krajnje_vreme"/></u>
                            casova, u prostorijama organa u <u><xsl:value-of select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Podaci_o_uvidu/obv:Mesto_uvida/tipovi:Mesto"/></u> ul. <u><xsl:value-of select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Podaci_o_uvidu/obv:Mesto_uvida/tipovi:Ulica"/></u>
                            br. <u><xsl:value-of select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Podaci_o_uvidu/obv:Mesto_uvida/tipovi:Ulicni_broj"/></u>, kancelarija br. <u><xsl:value-of select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Podaci_o_uvidu/obv:Mesto_uvida/obv:Kancelarijski_broj"/></u>
                            mozete <b>izvrsiti uvid</b> u dokument/e u kome je sadrzana trazena informacija
                        </div>
                        <div class="tekst">Tom prilikom, na Vas zahtev, moze Vam se izdati i kopija dokumenta sa trazenom informacijom.</div>
                        <div class="obican_tekst">
                            Troskovi su utvrdjeni Uredbom Vlade Republike Srbije ("Sl. glasnik RS", br 8/06), i to:
                            kopija strane A4 formata iznosi 3 dinara, A3 formata 6 dinara, CD 35 dinara, diskete 20 dinara,
                            DVD 40 dinara, audio-disketa - 150 dinara, video-kaseta 300 dinara, pretvaranje jedne strane
                            dokumenta iz fizickog u elektronski oblik - 30 dinara.
                        </div>
                        <div class="tekst">
                            Iznos ukupnih troskova izrade kopije dokumenta po vasem zahtevu iznosi 
                            <u><xsl:value-of select="obv:Obavestenje/obv:Ukupan_trosak_kopija"/></u> dinara i uplacuje se 
                            na ziro-racun Budzeta Republike Srbije br. <xsl:value-of select="obv:Obavestenje/obv:Ukupan_trosak_kopija/@ziro_racun"/>
                            , s pozivom na broj <xsl:value-of select="obv:Obavestenje/obv:Ukupan_trosak_kopija/@poziv_na_broj"/>
                            - oznaka sifre opstine/grada gde se nalazi organ vlasti (iz Pravilnika o uslovima i nacinu vodjenja racuna - "Sl. glasnik RS", 20/07... 40/10).
                        </div>
                        <div>Dostavljeno</div>
                        <div>1.  Imenovanom</div>
                        <div>2.  Arhivi</div>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>