<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0"
    xmlns:zoz="http://zalbaodbijanje"
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
                    .naslov {
                        text-align: center;
                    }
                    table {
                        width: 95%;
                    }
                    .naslov_opis {
                        text-align: center;
                        margin-bottom: 10px;
                    }
                    .zalba {
                        text-align: center;
                        margin-bottom: 20px;
                        margin-top: 20px;
                    }
                    .centriran_tekst {
                        text-align: center;
                    }
                    .tekst {
                        text-indent: 1rem;
                    }
                    .levo {
                        text-align: left;
                    }
                    .desno {
                        text-align: right
                    }
                </style>
            </head>
            <body>
                <div class="a4">
                    <div class="sadrzaj">
                        <div class="naslov"><b>ZALBA PROTIV ODLUKE ORGANA VLASTI KOJOJM JE</b></div>
                        <div class="naslov_opis"><b><u>ODBIJEN ILI ODBACEN ZAHTEV</u> ZA PRISTUP INFORMACIJI</b></div>
                        <div><b><xsl:value-of select="zoz:Zalba_odbijanje/zoz:Podaci_o_primaocu/tipovi:Naziv"/></b></div>
                        <div>
                            Adresa za postu: <xsl:value-of select="zoz:Zalba_odbijanje/zoz:Podaci_o_primaocu/tipovi:Sediste/tipovi:Mesto"/>, 
                            <xsl:value-of select="zoz:Zalba_odbijanje/zoz:Podaci_o_primaocu/tipovi:Sediste/tipovi:Ulica"/> br. <xsl:value-of select="zoz:Zalba_odbijanje/zoz:Podaci_o_primaocu/tipovi:Sediste/tipovi:Ulicni_broj"/>
                        </div>
                        <div class="zalba"><b>Z A L B A</b></div>
                        <div class="centriran_tekst">
                            <xsl:choose>
                                <xsl:when test="boolean(zoz:Zalba_odbijanje/zoz:Podaci_o_zaliocu/tipovi:Naziv)">
                                    <div>
                                        (<u><xsl:value-of select="zoz:Zalba_odbijanje/zoz:Podaci_o_zaliocu/tipovi:Naziv"/></u>
                                    </div>
                                </xsl:when>
                                <xsl:otherwise>
                                    <div>
                                        (<u><xsl:value-of select="zoz:Zalba_odbijanje/zoz:Podaci_o_zaliocu/tipovi:Ime"/><xsl:value-of select="$space"/><xsl:value-of select="zoz:Zalba_odbijanje/zoz:Podaci_o_zaliocu/tipovi:Prezime"/></u>
                                    </div>
                                </xsl:otherwise>
                            </xsl:choose>
                        </div>
                        <div class="centriran_tekst"><u><xsl:value-of select="zoz:Zalba_odbijanje/zoz:Podaci_o_zaliocu/tipovi:Adresa/tipovi:Ulica"/>
                            <xsl:value-of select="$space"/><xsl:value-of select="zoz:Zalba_odbijanje/zoz:Podaci_o_zaliocu/tipovi:Adresa/tipovi:Ulicni_broj"/>, 
                            <xsl:value-of select="zoz:Zalba_odbijanje/zoz:Podaci_o_zaliocu/tipovi:Adresa/tipovi:Mesto"/></u>)
                        </div>
                        <div class="centriran_tekst">Ime, prezime, odnosno naziv, adresa i sediste zalioca</div>
                        <div class="centriran_tekst">protiv resenja-zakljucka</div>
                        <div class="centriran_tekst">(<u><xsl:value-of select="zoz:Zalba_odbijanje/zoz:Podaci_o_odluci/zoz:Naziv_donosioca_odluke"/></u>)</div>
                        <div class="centriran_tekst">(naziv organa koji je doneo odluku)</div>
                        <div>Broj <u><xsl:value-of select="zoz:Zalba_odbijanje/zoz:Podaci_o_odluci/@broj_odluke"/></u> od <u><xsl:value-of select="zoz:Zalba_odbijanje/zoz:Podaci_o_odluci/@godina"/></u> godine.</div>
                        <div class="tekst">
                            <xsl:variable name="zahtev" select="zoz:Zalba_odbijanje/@datum_podnosenja_zahteva"/>
                            <xsl:variable name="z" select="xs:string(concat(
                                substring($zahtev,9,2),'.',
                                substring($zahtev,6,2),'.',
                                substring($zahtev,1,4),'.'))"/>
                            Navedenom odlukom organa vlasti (resenjem, zakljuckom, obavestenjem u pisanoj formi
                            sa elementima odluke), suprotno zakonu, odbijen-odbacen je moj zahtev koji sam podneo/la-
                            uputio/la dana <u><xsl:value-of select="$z"/></u> godine i tako mi uskraceno-onemoguceno ostvarivanje ustavnog i 
                            zakonskog prava na slobodan pristup informacijama od javnog znacaja. Odluku pobijam u 
                            celosti, odnosno u delu kojim <u><xsl:value-of select="zoz:Zalba_odbijanje/zoz:Telo_zalbe/zoz:Razlog_zalbe"/></u>
                            jer nije zasnovana na <xsl:value-of select="zoz:Zalba_odbijanje/zoz:Telo_zalbe/zoz:Osnova_zalbe/zoz:Zakon"/>.
                        </div>
                        <div class="tekst">
                            Na osnovu iznetih razloga, predlazem da Poverenik uvazi moju zalbu, ponisti
                            odluka prvostepenog organa i omoguci mi pristup trazenoj/im informaciji/ma.
                        </div>
                        <div class="tekst">
                            Zalbu podnosim blagovremeno, u zakonskom roku utvrdjenom u clanu <xsl:value-of select="zoz:Zalba_odbijanje/zoz:Telo_zalbe/zoz:Osnova_zalbe/zoz:Clan"/>. 
                            st. <xsl:value-of select="zoz:Zalba_odbijanje/zoz:Telo_zalbe/zoz:Osnova_zalbe/zoz:Stav"/>. 
                            <xsl:value-of select="zoz:Zalba_odbijanje/zoz:Telo_zalbe/zoz:Osnova_zalbe/zoz:Zakon"/>
                        </div>
                        <div>
                            <table>
                                <tr>
                                    <td>
                                        <div class="levo">
                                            <xsl:variable name="datum" select="zoz:Zalba_odbijanje/@datum_podnosenja_zalbe"/>
                                            <xsl:variable name="d" select="xs:string(concat(
                                                substring($datum,9,2),'.',
                                                substring($datum,6,2),'.',
                                                substring($datum,1,4),'.'))"/>
                                            <div>U <u><xsl:value-of select="zoz:Zalba_odbijanje/@mesto_podnosenja_zalbe"/></u>,</div>
                                            <div>dana <u><xsl:value-of select="$d"/></u> godine</div>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="desno">
                                            <div>
                                                <xsl:choose>
                                                    <xsl:when test="boolean(zoz:Zalba_odbijanje/zoz:Podaci_o_podnosiocu_zalbe/tipovi:Naziv)">
                                                        <div>
                                                            <u><xsl:value-of select="zoz:Zalba_odbijanje/zoz:Podaci_o_podnosiocu_zalbe/tipovi:Naziv"/></u>
                                                        </div>
                                                    </xsl:when>
                                                    <xsl:otherwise>
                                                        <div>
                                                            <u><xsl:value-of select="zoz:Zalba_odbijanje/zoz:Podaci_o_podnosiocu_zalbe/tipovi:Ime"/><xsl:value-of select="$space"/><xsl:value-of select="zoz:Zalba_odbijanje/zoz:Podaci_o_podnosiocu_zalbe/tipovi:Prezime"/></u>
                                                        </div>
                                                    </xsl:otherwise>
                                                </xsl:choose>   
                                            </div>
                                            <div>Podnosilac zalbe/Ime i prezime</div>
                                            <div><u>
                                                <xsl:value-of select="zoz:Zalba_odbijanje/zoz:Podaci_o_podnosiocu_zalbe/tipovi:Adresa/tipovi:Ulica"/>
                                                <xsl:value-of select="$space"/><xsl:value-of select="zoz:Zalba_odbijanje/zoz:Podaci_o_podnosiocu_zalbe/tipovi:Adresa/tipovi:Ulicni_broj"/>, 
                                                <xsl:value-of select="zoz:Zalba_odbijanje/zoz:Podaci_o_podnosiocu_zalbe/tipovi:Adresa/tipovi:Mesto"/>
                                            </u></div>
                                            <div>adresa</div>
                                            <div><u><xsl:value-of select="zoz:Zalba_odbijanje/zoz:Podaci_o_podnosiocu_zalbe/tipovi:Kontakt_podaci"/></u></div>
                                            <div>drugi podaci za kontakt</div>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                            <div class="tekst"><b>Napomena: </b></div>
                            <ul>
                                <li>
                                    <div>
                                        U zalbi se mora navesti odluka koja se pobija (resenje, zakljucak, obavestenje), naziv
                                        organa koji je odluku doneo, kao i broj i datum odluke. Dovoljno je da zalilac navede u 
                                        zalbi u kom pogledu je nezadovoljan odlukom, s tim da zalbu ne mora posebno obrazloziti.
                                        Ako zalbu izjavljuje na ovom obrascu, dodatno obrazlozenje moze posebno priloziti.
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        Uz zalbu obavesno priloziti kopiju podnetog zahteva i dokaz o njegovoj predaji-uplacivanju organu 
                                        kao i kopiju odluke organa koja se osporava zalbom.
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
    
</xsl:stylesheet>