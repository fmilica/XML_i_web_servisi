<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:fo="http://www.w3.org/1999/XSL/Format"
    exclude-result-prefixes="xs"
    version="2.0"
    xmlns:zoc="http://zalbacutanje"
    xmlns:tipovi="http://tipovi">
    
    <xsl:variable name="space" select="'&#xA0;'"/>
    
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="zalbacutanje-page" page-height="29.7cm" page-width="21.0cm" margin="2cm">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            
            <fo:page-sequence master-reference="zalbacutanje-page">
                <fo:flow flow-name="xsl-region-body" font-family="Times New Roman">
                    <fo:block font-weight="bold" text-align="center">
                        ZALBA KADA ORGAN VLASTI <fo:inline text-decoration="underline">NIJE POSTUPIO/nije postupio u celosti/PO ZAHTEVU</fo:inline>
                    </fo:block> 
                    <fo:block font-weight="bold" margin-bottom="10px"  text-align="center">TRAZIOCA U ZAKONSKOM ROKU (CUTANJE UPRAVE)</fo:block>
                    <fo:block font-weight="bold"><xsl:value-of select="zoc:Zalba_cutanje/zoc:Primalac_zalbe/tipovi:Naziv"/></fo:block>
                    <fo:block>
                        Adresa za postu: <xsl:value-of select="zoc:Zalba_cutanje/zoc:Primalac_zalbe/tipovi:Sediste/tipovi:Mesto"/>,
                        <xsl:value-of select="zoc:Zalba_cutanje/zoc:Primalac_zalbe/tipovi:Sediste/tipovi:Ulica"/>
                        br. <xsl:value-of select="zoc:Zalba_cutanje/zoc:Primalac_zalbe/tipovi:Sediste/tipovi:Ulicni_broj"/>
                    </fo:block>
                    <fo:block margin-top="10px">
                        U skladu sa clanom <xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Osnova_zalbe/zoc:Clan"/>.<xsl:value-of select="$space"/>
                        <xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Osnova_zalbe/zoc:Zakon"/> podnosim:
                    </fo:block>
                    <fo:block text-align="center" font-weight="bold">ZALBU</fo:block>
                    <fo:block text-align="center">protiv</fo:block>
                    <fo:block text-align="center" text-decoration="underline"><xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Naziv_organa"/></fo:block>
                    <fo:block text-align="center" margin-bottom="10px">( navesti naziv organa )</fo:block>
                    <fo:block text-align="center">zbog toga sto organ vlasti </fo:block>
                    <fo:block text-align="center" font-weight="bold">
                        <xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Razlog_zalbe"/>
                    </fo:block>
                    <fo:block text-align="center">( podvuci zbog cega se izjavljuje zalba)</fo:block>
                    <fo:block margin-top="10px">
                        <xsl:variable name="datum" select="zoc:Zalba_cutanje/@datum_podnosenja_zahteva"/>
                        <xsl:variable name="d" select="xs:string(concat(
                            substring($datum,9,2),'.',
                            substring($datum,6,2),'.',
                            substring($datum,1,4),'.'))"/>
                        po mom zahtevu za slobodni pristup informacijama od javnog zanacaja koji sam podneo tom
                        organu dana <fo:inline text-decoration="underline"><xsl:value-of select="$d"/></fo:inline> goodine, a kojim sam trazio/la da u skladu sa
                        <xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Osnova_zalbe/zoc:Zakon"/> omoguci uvid- kopija
                        dokumenta koji sadrzi trazene informacije o/u vezi sa:
                    </fo:block>
                    <fo:block text-decoration="underline">
                        <xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Podaci_o_zahtevu"/>
                    </fo:block>
                    <fo:block text-align="center">( navesti podatke o zahtevu i informaciji/ama)</fo:block>
                    <fo:block margin-top="10px" text-indent="20px">Na osnovu iznetog, predlazem da Poverenik uvazi moju zalbu i omoguci mi pristup trazenoj/im informaciji/ama.</fo:block>
                    <fo:block text-indent="20px">Kao dokaz, uz zalbu dostavljam kopiju zahteva sa dokazom o predaji organu vlasti.</fo:block>
                    <fo:block text-indent="20px">
                        <fo:inline font-weight="bold">Napomena:</fo:inline> Kod zalbe zbog nepostupanja po zahtevu u celosti, treba priloziti i dobijeni odgovor organa vlasti.
                    </fo:block>
                    <fo:block text-align="right">
                        <fo:block text-decoration="underline">
                            <xsl:choose>
                                <xsl:when test="zoc:Zalba_cutanje/zoc:Zalba/zoc:Podnosilac_zalbe/tipovi:Naziv">
                                    <xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Podnosilac_zalbe/tipovi:Naziv"/>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Podnosilac_zalbe/tipovi:Ime"/>
                                    <xsl:value-of select="$space"/><xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Podnosilac_zalbe/tipovi:Prezime"/>
                                </xsl:otherwise>
                            </xsl:choose>
                        </fo:block>
                        <fo:block>Podnosilac zalbe/Ime i prezime</fo:block>
                        <fo:block text-decoration="underline">
                            <xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Podnosilac_zalbe/tipovi:Adresa/tipovi:Ulica"/><xsl:value-of select="$space"/>
                            <xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Podnosilac_zalbe/tipovi:Adresa/tipovi:Ulicni_broj"/>,<xsl:value-of select="$space"/>
                            <xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Podnosilac_zalbe/tipovi:Adresa/tipovi:Mesto"/>
                        </fo:block>
                        <fo:block>adresa</fo:block>
                        <fo:block text-decoration="underline">
                            <xsl:value-of select="zoc:Zalba_cutanje/zoc:Zalba/zoc:Podnosilac_zalbe/tipovi:Kontakt_podaci"/>
                        </fo:block>
                        <fo:block>drugi podaci za kontakt</fo:block>
                    </fo:block>
                    <fo:block text-align="left">
                        <xsl:variable name="datum1" select="zoc:Zalba_cutanje/@datum_podnosenja_zalbe"/>
                        <xsl:variable name="d1" select="xs:string(concat(
                            substring($datum1,9,2),'.',
                            substring($datum1,6,2),'.',
                            substring($datum1,1,4),'.'))"/>
                        U <fo:inline text-decoration="underline"><xsl:value-of select="zoc:Zalba_cutanje/@mesto"/></fo:inline>, dana 
                        <fo:inline text-decoration="underline"><xsl:value-of select="$d1"/></fo:inline> godine
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
    
</xsl:stylesheet>