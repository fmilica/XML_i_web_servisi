<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:fo="http://www.w3.org/1999/XSL/Format"
    exclude-result-prefixes="xs"
    version="2.0"
    xmlns:obv="http://obavestenje"
    xmlns:tipovi="http://tipovi">
    
    <xsl:variable name="space" select="'&#xA0;'"/>
    
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="obavestenje-page" page-height="29.7cm" 
                    page-width="21.0cm" margin="2cm">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="obavestenje-page">
                <fo:flow flow-name="xsl-region-body" font-family="Times New Roman">
                    <fo:block text-decoration="underline">
                        <xsl:value-of select="obv:Obavestenje/obv:Organ_vlasti/tipovi:Naziv"/>
                    </fo:block>
                    <fo:block text-decoration="underline">
                        <xsl:value-of select="obv:Obavestenje/obv:Organ_vlasti/tipovi:Sediste"/>
                    </fo:block>
                    <fo:block>
                        (naziv i sediste organa)
                    </fo:block>
                    <fo:block>
                        Broj predmeta: <fo:inline text-decoration="underline"><xsl:value-of select="obv:Obavestenje/obv:Broj_predmeta"/></fo:inline>
                    </fo:block>
                    <fo:block>
                        Datum: <fo:inline text-decoration="underline"><xsl:value-of select="obv:Obavestenje/@datum"/></fo:inline>
                    </fo:block>
                    <xsl:choose>
                        <xsl:when test="obv:Obavestenje/obv:Podnosilac/tipovi:Naziv">
                            <fo:block margin-top="10px" text-decoration="underline">
                                <xsl:value-of select="obv:Obavestenje/obv:Podnosilac/tipovi:Naziv"/>
                            </fo:block>
                        </xsl:when>
                        <xsl:otherwise>
                            <fo:block  margin-top="10px" text-decoration="underline">
                                <xsl:value-of select="obv:Obavestenje/obv:Podnosilac/tipovi:Ime"/><xsl:value-of select="$space"/>
                                <xsl:value-of select="obv:Obavestenje/obv:Podnosilac/tipovi:Prezime"/>
                            </fo:block>
                        </xsl:otherwise>
                    </xsl:choose>
                    <fo:block text-decoration="underline">
                        <xsl:value-of select="obv:Obavestenje/obv:Podnosilac/tipovi:Adresa/tipovi:Ulica"/><xsl:value-of select="$space"/>
                        <xsl:value-of select="obv:Obavestenje/obv:Podnosilac/tipovi:Adresa/tipovi:Ulicni_broj"/>, 
                        <xsl:value-of select="obv:Obavestenje/obv:Podnosilac/tipovi:Adresa/tipovi:Mesto"/>
                    </fo:block>
                    <fo:block>
                        Ime i prezime/naziv/i adresa podnosioca zahteva
                    </fo:block>
                    <fo:block font-weight="bold" margin-top="20px" text-align="center">O B A V E S T E Nj E</fo:block>
                    <fo:block font-weight="bold" text-align="center">o stavljanju na uvid dokument koji sadrzi</fo:block>
                    <fo:block font-weight="bold" text-align="center">trazenu informaciju i o izradi kopije</fo:block>
                    <xsl:variable name="dt1" select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Datum_potrazivanja"/>
                    <xsl:variable name="date1" select="xs:string(concat(
                        substring($dt1,9,2),'.',
                        substring($dt1,6,2),'.',
                        substring($dt1,1,4),'.'))"/>
                    <fo:block text-indent="20px" margin-top="10px">
                        Na osnovu clana <xsl:value-of select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Zakonska_osnova/obv:Clan"/>
                        . st. <xsl:value-of select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Zakonska_osnova/obv:Stav"/>. 
                        <xsl:value-of select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Zakonska_osnova/obv:Zakon"/>, postupajuci po
                        vasem zahtevu za slobodan pristup informacijama od <fo:inline text-decoration="underline"><xsl:value-of select="$date1"/></fo:inline> god.,
                        kojim ste trazili uvid u dokument/e sa informacijama o/u vezi sa:
                    </fo:block>
                    <fo:block text-decoration="underline">
                        <xsl:value-of select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Opis_trazene_informacije"/>
                    </fo:block>
                    <fo:block text-align="center" margin-bottom="10px">(opis trazene informacije)</fo:block>
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
                    <fo:block>
                        obavestavamo Vas da dana <fo:inline text-decoration="underline"><xsl:value-of select="$date"/></fo:inline>, u <fo:inline text-decoration="underline"><xsl:value-of select="$time"/></fo:inline>
                        , odnosno u vremenu od <fo:inline text-decoration="underline"><xsl:value-of select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Podaci_o_uvidu/obv:Pocetno_vreme"/></fo:inline> do <fo:inline text-decoration="underline"><xsl:value-of select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Podaci_o_uvidu/obv:Krajnje_vreme"/></fo:inline>
                        casova, u prostorijama organa u <fo:inline text-decoration="underline"><xsl:value-of select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Podaci_o_uvidu/obv:Mesto_uvida/tipovi:Mesto"/></fo:inline> ul. <fo:inline text-decoration="underline"><xsl:value-of select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Podaci_o_uvidu/obv:Mesto_uvida/tipovi:Ulica"/></fo:inline>
                        br. <fo:inline text-decoration="underline"><xsl:value-of select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Podaci_o_uvidu/obv:Mesto_uvida/tipovi:Ulicni_broj"/></fo:inline>, kancelarija br. <fo:inline text-decoration="underline"><xsl:value-of select="obv:Obavestenje/obv:Uvid_u_dokument/obv:Podaci_o_uvidu/obv:Mesto_uvida/obv:Kancelarijski_broj"/></fo:inline>
                            mozete <fo:inline font-weight="bold">izvrsiti uvid</fo:inline> u dokument/e u kome je sadrzana trazena informacija
                    </fo:block>
                    <fo:block>Tom prilikom, na Vas zahtev, moze Vam se izdati i kopija dokumenta sa trazenom informacijom</fo:block>
                    <fo:block text-indent="20px">
                        Troskovi su utvrdjeni Uredbom Vlade Republike Srbije ("Sl. glasnik RS", br 8/06), i to:
                        kopija strane A4 formata iznosi 3 dinara, A3 formata 6 dinara, CD 35 dinara, diskete 20 dinara,
                        DVD 40 dinara, audio-disketa - 150 dinara, video-kaseta 300 dinara, pretvaranje jedne strane
                        dokumenta iz fizickog u elektronski oblik - 30 dinara.
                    </fo:block>
                    <fo:block text-indent="20px">
                        Iznos ukupnih troskova izrade kopije dokumenta po vasem zahtevu iznosi 
                        <fo:inline text-decoration="underline"><xsl:value-of select="obv:Obavestenje/obv:Ukupan_trosak_kopija"/></fo:inline> dinara i uplacuje se 
                        na ziro-racun Budzeta Republike Srbije br. <xsl:value-of select="obv:Obavestenje/obv:Ukupan_trosak_kopija/@ziro_racun"/>
                        , s pozivom na broj <xsl:value-of select="obv:Obavestenje/obv:Ukupan_trosak_kopija/@poziv_na_broj"/>
                        - oznaka sifre opstine/grada gde se nalazi organ vlasti (iz Pravilnika o uslovima i nacinu vodjenja racuna - "Sl. glasnik RS", 20/07... 40/10).
                    </fo:block>
                    <fo:block margin-top="10px">Dostavljeno</fo:block>
                    <fo:block>1.  Imenovanom</fo:block>
                    <fo:block>2.  Arhivi</fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>