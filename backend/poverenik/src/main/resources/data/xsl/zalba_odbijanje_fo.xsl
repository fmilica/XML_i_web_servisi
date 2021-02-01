<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:fo="http://www.w3.org/1999/XSL/Format"
    exclude-result-prefixes="xs"
    version="2.0"
    xmlns:zoz="http://zalbaodbijanje"
    xmlns:tipovi="http://tipovi">
    
    <xsl:variable name="space" select="'&#xA0;'"/>
    
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="zalbaodbijen-page" page-height="29.7cm" page-width="21.0cm" margin="2cm">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            
            <fo:page-sequence master-reference="zalbaodbijen-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-weight="bold" text-align="center">ZALBA PROTIV ODLUKE ORGANA VLASTI KOJOJM JE</fo:block>
                    <fo:block font-weight="bold" text-align="center">
                        <fo:inline text-decoration="underline">ODBIJEN ILI ODBACEN ZAHTEV</fo:inline> ZA PRISTUP INFORMACIJI
                    </fo:block>
                    <fo:block font-weight="bold"><xsl:value-of select="zoz:Zalba_odbijanje/zoz:podaci_o_primaocu/tipovi:Naziv"/></fo:block>
                    <fo:block>
                        Adresa za postu: <xsl:value-of select="zoz:Zalba_odbijanje/zoz:podaci_o_primaocu/tipovi:Sediste/tipovi:Mesto"/>, 
                        <xsl:value-of select="zoz:Zalba_odbijanje/zoz:podaci_o_primaocu/tipovi:Sediste/tipovi:Ulica"/> br. <xsl:value-of select="zoz:Zalba_odbijanje/zoz:podaci_o_primaocu/tipovi:Sediste/tipovi:Ulicni_broj"/>
                    </fo:block>
                    <fo:block font-weight="bold" text-align="center">Z A L B A</fo:block>
                    <fo:block text-align="center">
                        <xsl:choose>
                            <xsl:when test="boolean(zoz:Zalba_odbijanje/zoz:podaci_o_zaliocu/tipovi:Naziv)">
                                (<fo:block text-decoration="underline">
                                    <xsl:value-of select="zoz:Zalba_odbijanje/zoz:podaci_o_zaliocu/tipovi:Naziv"/>
                                </fo:block>
                            </xsl:when>
                            <xsl:otherwise>
                                (<fo:block text-decoration="underline">
                                    <xsl:value-of select="zoz:Zalba_odbijanje/zoz:podaci_o_zaliocu/tipovi:Ime"/><xsl:value-of select="$space"/><xsl:value-of select="zoz:Zalba_odbijanje/zoz:podaci_o_zaliocu/tipovi:Prezime"/>
                                </fo:block>
                            </xsl:otherwise>
                        </xsl:choose>
                    </fo:block>
                    <fo:block>
                        <fo:inline text-decoration="underline"><xsl:value-of select="zoz:Zalba_odbijanje/zoz:podaci_o_zaliocu/tipovi:Adresa/tipovi:Ulica"/>
                            <xsl:value-of select="$space"/><xsl:value-of select="zoz:Zalba_odbijanje/zoz:podaci_o_zaliocu/tipovi:Adresa/tipovi:Ulicni_broj"/>, 
                            <xsl:value-of select="zoz:Zalba_odbijanje/zoz:podaci_o_zaliocu/tipovi:Adresa/tipovi:Mesto"/></fo:inline>)
                    </fo:block>
                    <fo:block text-align="center">Ime, prezime, odnosno naziv, adresa i sediste zalioca</fo:block>
                    <fo:block text-align="center">protiv resenja-zakljucka</fo:block>
                    <fo:block text-decoration="underline" text-align="center"><xsl:value-of select="zoz:Zalba_odbijanje/zoz:podaci_o_odluci/zoz:naziv_donosioca_odluke"/></fo:block>
                    <fo:block text-align="center">(naziv organa koji je doneo odluku)</fo:block>
                    <fo:block>
                        Broj <fo:inline text-decoration="underline"><xsl:value-of select="zoz:Zalba_odbijanje/zoz:podaci_o_odluci/@broj_odluke"/></fo:inline> od <fo:inline text-decoration="underline"><xsl:value-of select="zoz:Zalba_odbijanje/zoz:podaci_o_odluci/@godina"/></fo:inline> godine.
                    </fo:block>
                    <fo:block text-indent="1rem">
                        <xsl:variable name="zahtev" select="zoz:Zalba_odbijanje/@datum_podnosenja_zahteva"/>
                        <xsl:variable name="z" select="xs:string(concat(
                            substring($zahtev,9,2),'.',
                            substring($zahtev,6,2),'.',
                            substring($zahtev,1,4),'.'))"/>
                        Navedenom odlukom organa vlasti (resenjem, zakljuckom, obavestenjem u pisanoj formi
                        sa elementima odluke), suprotno zakonu, odbijen-odbacen je moj zahtev koji sam podneo/la-
                        uputio/la dana <fo:inline text-decoration="underline"><xsl:value-of select="$z"/></fo:inline> godine i tako mi uskraceno-onemoguceno ostvarivanje ustavnog i 
                        zakonskog prava na slobodan pristup informacijama od javnog znacaja. Odluku pobijam u 
                        celosti, odnosno u delu kojim <fo:inline text-decoration="underline"><xsl:value-of select="zoz:Zalba_odbijanje/zoz:telo_zalbe/zoz:razlog_zalbe"/></fo:inline>
                        jer nije zasnovana na <xsl:value-of select="zoz:Zalba_odbijanje/zoz:telo_zalbe/zoz:osnova_zalbe/zoz:Zakon"/>.
                    </fo:block>
                    <fo:block text-indent="1rem">
                        Na osnovu iznetih razloga, predlazem da Poverenik uvazi moju zalbu, ponisti
                        odluka prvostepenog organa i omoguci mi pristup trazenoj/im informaciji/ma.
                    </fo:block>
                    <fo:block text-indent="1rem">
                        Zalbu podnosim blagovremeno, u zakonskom roku utvrdjenom u clanu <xsl:value-of select="zoz:Zalba_odbijanje/zoz:telo_zalbe/zoz:osnova_zalbe/zoz:Clan"/>. 
                        st. <xsl:value-of select="zoz:Zalba_odbijanje/zoz:telo_zalbe/zoz:osnova_zalbe/zoz:Stav"/>. 
                        <xsl:value-of select="zoz:Zalba_odbijanje/zoz:telo_zalbe/zoz:osnova_zalbe/zoz:Zakon"/>
                    </fo:block>
                    <fo:block>
                        <fo:table>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell text-align="left">
                                        <xsl:variable name="datum" select="zoz:Zalba_odbijanje/@datum_podnosenja_zalbe"/>
                                        <xsl:variable name="d" select="xs:string(concat(
                                            substring($datum,9,2),'.',
                                            substring($datum,6,2),'.',
                                            substring($datum,1,4),'.'))"/>
                                        <fo:block>U <fo:inline text-decoration="underline"><xsl:value-of select="zoz:Zalba_odbijanje/@mesto_podnosenja_zalbe"/></fo:inline></fo:block>
                                        <fo:block>dana <fo:inline text-decoration="underline"><xsl:value-of select="$d"/></fo:inline></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right">
                                        <xsl:choose>
                                            <xsl:when test="boolean(zoz:Zalba_odbijanje/zoz:podaci_o_podnosiocu_zalbe/tipovi:Naziv)">
                                                <fo:block text-decoration="underline">
                                                    <xsl:value-of select="zoz:Zalba_odbijanje/zoz:podaci_o_podnosiocu_zalbe/tipovi:Naziv"/>
                                                </fo:block>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <fo:block text-decoration="underline">
                                                    <xsl:value-of select="zoz:Zalba_odbijanje/zoz:podaci_o_podnosiocu_zalbe/tipovi:Ime"/><xsl:value-of select="$space"/><xsl:value-of select="zoz:Zalba_odbijanje/zoz:podaci_o_podnosiocu_zalbe/tipovi:Prezime"/>
                                                </fo:block>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                        <fo:block>Podnosilac zalbe/Ime i prezime</fo:block>
                                        <fo:block text-decoration="underline">
                                            <xsl:value-of select="zoz:Zalba_odbijanje/zoz:podaci_o_podnosiocu_zalbe/tipovi:Adresa/tipovi:Ulica"/>
                                            <xsl:value-of select="$space"/><xsl:value-of select="zoz:Zalba_odbijanje/zoz:podaci_o_podnosiocu_zalbe/tipovi:Adresa/tipovi:Ulicni_broj"/>, 
                                            <xsl:value-of select="zoz:Zalba_odbijanje/zoz:podaci_o_podnosiocu_zalbe/tipovi:Adresa/tipovi:Mesto"/>
                                        </fo:block>
                                        <fo:block>adresa</fo:block>
                                        <fo:block text-decoration="underline">
                                            <xsl:value-of select="zoz:Zalba_odbijanje/zoz:podaci_o_podnosiocu_zalbe/tipovi:Kontakt_podaci"/>
                                        </fo:block>
                                        <fo:block>drugi podaci za kontakt</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                        <fo:list-block>
                            <fo:list-item>
                                <fo:list-item-label end-indent="label-end()">
                                    <fo:block>
                                        <fo:inline font-family="Symbol">*</fo:inline>
                                    </fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body start-indent="body-start()">
                                    <fo:block>
                                        U zalbi se mora navesti odluka koja se pobija (resenje, zakljucak, obavestenje), naziv
                                        organa koji je odluku doneo, kao i broj i datum odluke. Dovoljno je da zalilac navede u 
                                        zalbi u kom pogledu je nezadovoljan odlukom, s tim da zalbu ne mora posebno obrazloziti.
                                        Ako zalbu izjavljuje na ovom obrascu, dodatno obrazlozenje moze posebno priloziti.
                                    </fo:block>
                                </fo:list-item-body>
                            </fo:list-item>
                            <fo:list-item>
                                <fo:list-item-label end-indent="label-end()">
                                    <fo:block>
                                        <fo:inline font-family="Symbol">*</fo:inline>
                                    </fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body start-indent="body-start()">
                                    <fo:block>
                                        Uz zalbu obavesno priloziti kopiju podnetog zahteva i dokaz o njegovoj predaji-uplacivanju organu 
                                        kao i kopiju odluke organa koja se osporava zalbom.
                                    </fo:block>
                                </fo:list-item-body>
                            </fo:list-item>
                        </fo:list-block>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
    
</xsl:stylesheet>