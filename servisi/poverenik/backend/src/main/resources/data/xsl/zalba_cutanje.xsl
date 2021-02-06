<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0"
    xmlns:zoc="http://zalbacutanje"
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
                    .naslov_opis {
                        text-align: center;
                        margin-bottom: 20px;
                    }
                    .centriran_text {
                        text-align: center;
                    }
                    .tekst {
                        margin-top: 10px;
                        text-indent: 1rem;
                    }
                    .obican_tekst {
                        margin-top: 10px;
                    }
                    .desno {
                        text-align: right;
                    }
                    .levo {
                        text-align: left;
                    }
                </style>
            </head>
            <body>
                <div class="a4">
                    <div class="sadrzaj">
                        <div class="naslov"><b>ZALBA KADA ORGAN VLASTI <u>NIJE POSTUPIO/nije postupio u celosti/PO ZAHTEVU</u></b></div>
                        <div class="naslov_opis"><b>TRAZIOCA U ZAKONSKOM ROKU (CUTANJE UPRAVE)</b></div>
                        <div><b><xsl:value-of select="zoc:Zalba_cutanje/zoc:Primalac_zalbe/tipovi:Naziv"/></b></div>
                        <div>
                            Adresa za postu: <xsl:value-of select="zoc:Zalba_cutanje/zoc:Primalac_zalbe/tipovi:Sediste/tipovi:Mesto"/>,
                            <xsl:value-of select="zoc:Zalba_cutanje/zoc:Primalac_zalbe/tipovi:Sediste/tipovi:Ulica"/>
                            br. <xsl:value-of select="zoc:Zalba_cutanje/zoc:Primalac_zalbe/tipovi:Sediste/tipovi:Ulicni_broj"/>
                        </div>
                        <div>
                            U skladu sa clanom <xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Osnova_zalbe/zoc:Clan"/>.<xsl:value-of select="$space"/>
                            <xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Osnova_zalbe/zoc:Zakon"/> podnosim:
                        </div>
                        <div class="centriran_text"><b>ZALBU</b></div>
                        <div class="centriran_text">protiv</div>
                        <div class="centriran_text"><u><xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Naziv_organa"/></u></div>
                        <div class="centriran_text">( navesti naziv organa )</div>
                        <div class="centriran_text">zbog toga sto organ vlasti</div>
                        <div  class="centriran_text">
                            <xsl:variable name="razlog" select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Razlog_zalbe"/>
                            <xsl:choose>
                                <xsl:when test="$razlog = 'nije postupio'">
                                    <b><u>nije postupio</u>/nije postupio u celosti/u zakonskom roku</b>
                                </xsl:when>
                                <xsl:when test="$razlog = 'nije postupio u celosti'">
                                    <b>nije postupio/<u>nije postupio u celosti</u>/u zakonskom roku</b>
                                </xsl:when>
                                <xsl:otherwise>
                                    <b>nije postupio/nije postupio u celosti/<u>u zakonskom roku</u></b>
                                </xsl:otherwise>
                            </xsl:choose>
                        </div>
                        <div class="centriran_text">( podvuci zbog cega se izjavljuje zalba)</div>
                        <div class="obican_tekst">
                            <xsl:variable name="datum" select="zoc:Zalba_cutanje/@datum_podnosenja_zahteva"/>
                            <xsl:variable name="d" select="xs:string(concat(
                                substring($datum,9,2),'.',
                                substring($datum,6,2),'.',
                                substring($datum,1,4),'.'))"/>
                            po mom zahtevu za slobodni pristup informacijama od javnog zanacaja koji sam podneo tom
                            organu dana <u><xsl:value-of select="$d"/></u> goodine, a kojim sam trazio/la da u skladu sa
                            <xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Osnova_zalbe/zoc:Zakon"/> omoguci uvid- kopija
                            dokumenta koji sadrzi trazene informacije o/u vezi sa:
                        </div>
                        <div>
                            <u><xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Podaci_o_zahtevu"/></u>
                        </div>
                        <div class="centriran_text">( navesti podatke o zahtevu i informaciji/ama)</div>
                        <div class="tekst">Na osnovu iznetog, predlazem da Poverenik uvazi moju zalbu i omoguci mi pristup trazenoj/im informaciji/ama.</div>
                        <div class="tekst">Kao dokaz, uz zalbu dostavljam kopiju zahteva sa dokazom o predaji organu vlasti.</div>
                        <div class="tekst"><b>Napomena:</b> Kod zalbe zbog nepostupanja po zahtevu u celosti, treba priloziti i dobijeni odgovor organa vlasti.</div>
                        <div class="desno">
                            <xsl:choose>
                                <xsl:when test="zoc:Zalba_cutanje/zoc:Zalba/zoc:Podnosilac_zalbe/tipovi:Naziv">
                                    <div>
                                        <u><xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Podnosilac_zalbe/tipovi:Naziv"/></u>
                                    </div>
                                </xsl:when>
                                <xsl:otherwise>
                                    <div>
                                        <u><xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Podnosilac_zalbe/tipovi:Ime"/>
                                            <xsl:value-of select="$space"/><xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Podnosilac_zalbe/tipovi:Prezime"/></u>
                                    </div>
                                </xsl:otherwise>
                            </xsl:choose>
                            <div>Podnosilac zalbe/Ime i prezime</div>
                            <div>
                                <u><xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Podnosilac_zalbe/tipovi:Adresa/tipovi:Ulica"/><xsl:value-of select="$space"/>
                                    <xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Podnosilac_zalbe/tipovi:Adresa/tipovi:Ulicni_broj"/>,<xsl:value-of select="$space"/>
                                    <xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Podnosilac_zalbe/tipovi:Adresa/tipovi:Mesto"/></u>
                            </div>
                            <div>adresa</div>
                            <div>
                                <u><xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Podnosilac_zalbe/tipovi:Kontakt_podaci"/></u>
                            </div>
                            <div>drugi podaci za kontakt</div>
                        </div>
                        <div class="levo">
                            <xsl:variable name="datum1" select="zoc:Zalba_cutanje/@datum_podnosenja_zalbe"/>
                            <xsl:variable name="d1" select="xs:string(concat(
                                substring($datum1,9,2),'.',
                                substring($datum1,6,2),'.',
                                substring($datum1,1,4),'.'))"/>
                            U <u><xsl:value-of select="zoc:Zalba_cutanje/@mesto"/></u>, dana <u><xsl:value-of select="$d1"/></u> godine
                        </div>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
    
</xsl:stylesheet>