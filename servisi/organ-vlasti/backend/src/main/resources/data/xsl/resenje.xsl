<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0"
    xmlns:res="http://resenje"
    xmlns:tipovi="http://tipovi">
    
    <xsl:variable name="space" select="'&#xA0;'"/>
    
    <xsl:variable name="zahtev" select="res:Resenje/res:Obrazlozenje/res:Postupak_zalioca/res:Podnosenje_zahteva/@datum_zahteva"/>
    <xsl:variable name="zahtevS" select="xs:string(concat(
        substring($zahtev,9,2),'.',
        substring($zahtev,6,2),'.',
        substring($zahtev,1,4),'.'))"/>
    
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
                    table {
                        width: 95%;
                        margin-bottom: 10px;
                    }
                    .levo {
                        text-align: left;
                    }
                    .desno {
                        text-align: right
                    }
                    .centriran_tekst{
                        text-align: center;
                        margin-top: 10px;
                        margin-bottom: 10px;
                    }
                    .tekst {
                        text-indent: 1rem;
                    }
                </style>
            </head>
            <body>
                <div class="a4">
                    <div class="sadrzaj">
                        <table>
                            <tr>
                                <td class="levo">
                                    <div><xsl:value-of select="res:Resenje/@tip_odluke"/></div>
                                    <div><xsl:value-of select="res:Resenje/@broj_resenja"/></div>
                                </td>
                                <td class="desno">
                                    <xsl:variable name="datum" select="res:Resenje/@datum_resenja"/>
                                    <xsl:variable name="dt" select="xs:string(concat(
                                        substring($datum,9,2),'.',
                                        substring($datum,6,2),'.',
                                        substring($datum,1,4),'.'))"/>
                                    <div>Datum: <xsl:value-of select="$dt"/> godine</div>
                                </td>
                            </tr>
                        </table>
                        <div>
                            <xsl:variable name="zahtevD" select="res:Resenje/res:Opis_zalbe/@datum_zahteva"/>
                            <xsl:variable name="z" select="xs:string(concat(
                                substring($zahtevD,9,2),'.',
                                substring($zahtevD,6,2),'.',
                                substring($zahtevD,1,4),'.'))"/>
                            Poverenik za informacije od jvnog znacaja i zastitu podataka o licnosti, u postupku po zalbi
                            koju je izjavio <xsl:value-of select="res:Resenje/res:Opis_zalbe/res:Ime"/><xsl:value-of select="$space"/><xsl:value-of select="res:Resenje/res:Opis_zalbe/res:Prezime"/>, 
                            zbog <xsl:value-of select="res:Resenje/res:Opis_zalbe/@razlog_zalbe"/><xsl:value-of select="$space"/><xsl:value-of select="res:Resenje/res:Opis_zalbe/res:Naziv_organa_vlasti"/><xsl:value-of select="$space"/>
                            <xsl:value-of select="res:Resenje/res:Opis_zalbe/res:Mesto_organa_vlasti"/>, <xsl:value-of select="res:Resenje/res:Opis_zalbe/res:Ulica_organa_vlasti"/>,
                            <xsl:value-of select="res:Resenje/res:Opis_zalbe/res:Ulicni_broj_organa_vlasti"/>, po njegovom zahtevu od <xsl:value-of select="$z"/> godine za pristup
                            informacijama od javnog znacaja donosi:
                        </div>
                        <div class="centriran_tekst">R E S E Nj E</div>
                        <div>
                            <xsl:for-each select="res:Resenje/res:Odluka/res:Nalozi/res:Nalog">
                                    <div class="tekst">
                                        <xsl:value-of select="."/>
                                    </div>
                            </xsl:for-each>
                        </div>
                        <div class="centriran_tekst">O b r a z l o z e nj e</div>
                        <div class="tekst">
                            <xsl:variable name="zalba" select="res:Resenje/res:Obrazlozenje/res:Postupak_zalioca/res:Podnosenje_zalbe/@datum_zalbe"/>
                            <xsl:variable name="za" select="xs:string(concat(
                                substring($zalba,9,2),'.',
                                substring($zalba,6,2),'.',
                                substring($zalba,1,4),'.'))"/>
							<xsl:choose>
                                <xsl:when test="boolean(res:Resenje/res:Opis_zalbe/res:Ime_zalilac)">
                                     <xsl:value-of select="res:Resenje/res:Opis_zalbe/res:Ime_zalilac"/><xsl:value-of select="$space"/><xsl:value-of select="res:Resenje/res:Opis_zalbe/res:Prezime_zalilac"/>
                                </xsl:when>
                                <xsl:otherwise><xsl:value-of select="res:Resenje/res:Opis_zalbe/res:Naziv_zalilac"/></xsl:otherwise>
                            </xsl:choose>
                            , kao trazilac informacija, izjavio je dana <xsl:value-of select="$za"/> godine zalbu povereniku, zbog <xsl:value-of select="res:Resenje/res:Opis_zalbe/@razlog_zalbe"/><xsl:value-of select="$space"/>
                            <xsl:value-of select="res:Resenje/res:Opis_zalbe/res:Naziv_organa_vlasti"/> po njegovom zahtevu od <xsl:value-of select="$zahtevS"/> godine za pristup informacijama od javnog znacaja i u prilogu dostavio kopiju istog.   
                        </div>
                        <div class="tekst">
                            <xsl:variable name="prosl" select="res:Resenje/res:Obrazlozenje/res:Prosledjivanje_zalbe/@datum_prosledjivanja"/>
                            <xsl:variable name="p" select="xs:string(concat(
                                substring($prosl,9,2),'.',
                                substring($prosl,6,2),'.',
                                substring($prosl,1,4),'.'))"/>
                            Postupajuci po zalbi, Poverenik je dana <xsl:value-of select="$p"/> godine uputio istu na iznasnjenje 
                            <xsl:value-of select="res:Resenje/res:Opis_zalbe/res:Naziv_organa_vlasti"/>, kao organu vlasti u smislu zakona 
                            po kom je zatrazeno da se izjasni o navodima zalbe, posebno o razlozima nepostupanja u zakonskom roku po podnetom zahtevu.
                        </div>
                        <div class="tekst">
                            Po razmatranju zalbe i ostalih spisa ovog predmeta, doneta je odluka kao u dispozitivu resenja iz sledecih razloga:
                        </div>
                        <div class="tekst">
                            Uvidom u spise predmeta utvrdjeno je da je <xsl:value-of select="res:Resenje/res:Opis_zalbe/res:Ime_zalilac"/><xsl:value-of select="$space"/><xsl:value-of select="res:Resenje/res:Opis_zalbe/res:Prezime_zalilac"/>, dana 
                            <xsl:value-of select="$zahtevS"/> godine, podneo <xsl:value-of select="res:Resenje/res:Opis_zalbe/res:Naziv_organa_vlasti"/> zahtev za pristup informacijama od javnog znacaja.
                        </div>
                        <div class="tekst">
                            Takodje je uvidom u spise predmeta utvrdjeno da po zahtevu zalioca od <xsl:value-of select="$zahtevS"/> godine, organ vlasti
                            <xsl:choose>
                                <xsl:when test="res:Resenje/res:Opis_zalbe/@razlog_zalbe = 'непоступање'">
                                    nije postupio, sto je duzan da ucini bez odlaganja, najkasnije u roku od 15 dana od primanja zahteva
                                </xsl:when>
                                <xsl:when test="res:Resenje/res:Opis_zalbe/@razlog_zalbe = 'непоступање у целости'">
                                    nije postupio u celosti 
                                </xsl:when>
                                <xsl:when test="res:Resenje/res:Opis_zalbe/@razlog_zalbe = 'непоступање у законском року'">
                                    nije postupio u zakonskom roku 
                                </xsl:when>
                                <xsl:otherwise>
                                    odbacio 
                                </xsl:otherwise>
                            </xsl:choose>
                            u smislu zakona po kom je zatrazeno da se izjasni o navodima zalbe, posebno o razlozima nepostupanja u zakonskom roku po podnetom zahtevu.
                        </div>
                        <div>
                            <xsl:choose>
                                <xsl:when test="res:Resenje/@tip_odluke = 'основана'">
                                    <div class="tekst">
                                        Imajuci u vidu da organ vlasti po zahtevu zalioca od <xsl:value-of select="$zahtevS"/> godine nije postupio sa navedenim odredbama <xsl:value-of select="res:Resenje/res:Obrazlozenje/res:Razlozi_odluke/res:Detaljan_opis_odgovora_na_zahtev/res:Zakonska_osnova_odgovora/res:Zakonska_osnova/res:Zakon"/>
                                        , a da nije opravdao razloge nepostupanja po podnetom zahtevu, Poverenik je u postupku po zalbi, na osnovu
                                        <xsl:for-each select="res:Resenje/res:Obrazlozenje/res:Razlozi_odluke/res:Obrazlozenje_odluke/res:Zakonske_osnove_odluke/res:Zakonska_osnova">
                                            clana <xsl:value-of select="res:Clan"/>. 
                                            <xsl:if test="boolean(res:Stav)">
                                                st. <xsl:value-of select="res:Stav"/>.
                                            </xsl:if>
                                            <xsl:value-of select="res:Zakon"/>
                                        </xsl:for-each>
                                    </div>
                                    <div>
                                        <xsl:variable name="taksa" select="format-number(res:Resenje/res:Obrazlozenje/res:Zalba_na_resenje/@taksa_tuzbe,  '.##')"/>
                                        Protiv ovog resenja nije dopustena zalba vec se, u skladu sa <xsl:value-of select="res:Resenje/res:Orazlozenje/res:Zalba_na_resenje/@zakon"/>, 
                                        moze pokrenuti upravni spor tuzbom <xsl:value-of select="res:Resenje/res:Orazlozenje/res:Zalba_na_resenje/@sud"/>, u roku od <xsl:value-of select="res:Resenje/res:Orazlozenje/res:Zalba_na_resenje/@rok_za_tuzbu"/> dana 
                                        od dana prijema resenja. Taksa na tuzbu iznosi <xsl:value-of select="$taksa"/> dinara.
                                    </div>
                                </xsl:when>
                            </xsl:choose>
                        </div>
                        <div class="desno">POVERENIK</div>
                        <div class="desno">
                            <xsl:value-of select="res:Resenje/res:Poverenik/res:Ime"/><xsl:value-of select="$space"/>
                            <xsl:value-of select="res:Resenje/res:Poverenik/res:Prezime"/>
                        </div>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
    
</xsl:stylesheet>