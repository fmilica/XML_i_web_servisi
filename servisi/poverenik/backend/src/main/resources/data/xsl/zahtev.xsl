<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0"
    xmlns:zahtev="http://zahtev"
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
					.organ {
						text-align: center;
					}
					.organ_opis {
						text-align: center;
						margin-bottom: 50px;
					}
					.zahtev {
						text-align: center;
					}
					.zahtev_opis {
						text-align: center;
						margin-bottom: 30px;
					}
					.tekst {
						text-indent:1em;
					}
					.informacije {
						text-indent:1em;
					}
					.mali_tekst {
						font-size: 10pt;
						margin-bottom: 20px;
					}
					ul.a {
						list-style-type: square;
					}
					.levo {
						text-align: left;
					}
					.desno {
						text-align: right;
					}
					table {
						width: 95%;
					}
					.opis {
						font-size: 10pt;
						margin-bottom: 10px;
					}
					.red {
						text-align: center;
					}
                </style>
            </head>
            <body>
				<div class="a4">
					<div class="sadrzaj">
						<div class="organ"><u><xsl:value-of select="zahtev:Zahtev/zahtev:Organ_vlasti/tipovi:Naziv"/>, <xsl:value-of select="zahtev:Zahtev/zahtev:Organ_vlasti/tipovi:Sediste"/></u></div>
						<div class="organ_opis">naziv i sedište organa kojem se zahtev upućuje</div>
						<div class="zahtev"><b>Z A H T E V</b></div>
						<div class="Zahtev_opis"><b>za pristup informacijama od javnog znacaja</b></div>
						<div class="tekst">
							Na osnovu clana <xsl:value-of select="zahtev:Zahtev/zahtev:Telo_zahteva/zahtev:Zakonska_osnova/zahtev:Clan"/>. 
							st. <xsl:value-of select="zahtev:Zahtev/zahtev:Telo_zahteva/zahtev:Zakonska_osnova/zahtev:Stav"/>. 
							<xsl:value-of select="zahtev:Zahtev/zahtev:Telo_zahteva/zahtev:Zakonska_osnova/zahtev:Zakon"/>. 
							("<xsl:value-of select="zahtev:Zahtev/zahtev:Telo_zahteva/zahtev:Zakonska_osnova/zahtev:Sluzbeni_glasnik/tipovi:Naziv"/>", 
							<xsl:for-each select="zahtev:Zahtev/zahtev:Telo_zahteva/zahtev:Zakonska_osnova/zahtev:Sluzbeni_glasnik/tipovi:Brojevi/tipovi:Broj">
								<span>
									<xsl:value-of select="."/>, 
								</span>
							</xsl:for-each>
							), od gore navedenog organa zahtevam:
							<ul class="a">
								<xsl:if test="boolean(zahtev:Zahtev/zahtev:Telo_zahteva/zahtev:Zahtevi/zahtev:Obavestenje)">
									<li>
										obavestenje da li poseduje trazenu infoemaciju
									</li>
								</xsl:if>
								<xsl:if test="boolean(zahtev:Zahtev/zahtev:Telo_zahteva/zahtev:Zahtevi/zahtev:Uvid)">
									<li>
										uvid u dokument koji sadrzi trazenu informaciju
									</li>
								</xsl:if>
								<xsl:if test="boolean(zahtev:Zahtev/zahtev:Telo_zahteva/zahtev:Zahtevi/zahtev:Kopija)">
									<li>
										kopiju dokumenta koji sadrzi trazenu informaciju
									</li>
								</xsl:if>
								<xsl:if test="boolean(zahtev:Zahtev/zahtev:Telo_zahteva/zahtev:Zahtevi/zahtev:Dostavljanje_kopije)">
									<li>
										dostavljanje kopije dokumenta koji sadrzi trazenu informaciju
									</li>
								</xsl:if>
								<ul class="a">
									<xsl:if test="boolean(zahtev:Zahtev/zahtev:Telo_zahteva/zahtev:Zahtevi/zahtev:Dostavljanje_kopije/zahtev:Nacin_dostave/zahtev:Dostava_postom)">
										<li>
											postom
										</li>
									</xsl:if>
									<xsl:if test="boolean(zahtev:Zahtev/zahtev:Telo_zahteva/zahtev:Zahtevi/zahtev:Dostavljanje_kopije/zahtev:Nacin_dostave/zahtev:Dostava_elektronskom_postom)">
										<li>
											elektronskom postom
										</li>
									</xsl:if>
									<xsl:if test="boolean(zahtev:Zahtev/zahtev:Telo_zahteva/zahtev:Zahtevi/zahtev:Dostavljanje_kopije/zahtev:Nacin_dostave/zahtev:Dostava_faksom)">
										<li>
											faksom
										</li>
									</xsl:if>
									<xsl:if test="boolean(zahtev:Zahtev/zahtev:Telo_zahteva/zahtev:Zahtevi/zahtev:Dostavljanje_kopije/zahtev:Nacin_dostave/zahtev:Posebna_dostava)">
										<li>
											na drugi nacin: <u><xsl:value-of select="zahtev:Zahtev/zahtev:Telo_zahteva/zahtev:Zahtevi/zahtev:Dostavljanje_kopije/zahtev:Nacin_dostave/zahtev:Posebna_dostava/zahtev:Nacin_posebne_dostave"/></u>
										</li>
									</xsl:if>
								</ul>
							</ul>
							<div>
								<div class="informacije">Ovaj zahtev se odnosi na sledece informacije:
								<u><xsl:value-of select="zahtev:Zahtev/zahtev:Telo_zahteva/zahtev:Zahtevane_informacije"/></u></div>
							</div>
							<div class="mali_tekst">(navesti sto precizniji opis informacije koja se trazi, kao i druge podatke koji olaksavaju pronalazenje trazene informacije)</div>
						</div>
						<div>
							<table>
								<tr>
									<td>
										<div class="levo">
											<div>U <u><xsl:value-of select="zahtev:Zahtev/@mesto"/></u>,</div>
											<xsl:variable name="dt" select="zahtev:Zahtev/@datum"/>
											<xsl:variable name="date" select="xs:date(concat(
												substring($dt,1,4),'-',
												substring($dt,6,2),'-',
												substring($dt,9,2)))"/>
											<div>dana <u><xsl:value-of select="format-date($date,'[D].[M].[Y].')"/>godine</u></div>
										</div>
									</td>
									<td>	
										<div class="desno">
											<div>
												<xsl:choose>
													<xsl:when test="boolean(zahtev:Zahtev/zahtev:Trazilac/tipovi:Naziv)">
														<div>
															<u><xsl:value-of select="zahtev:Zahtev/zahtev:Trazilac/tipovi:Naziv"/></u>
														</div>
													</xsl:when>
													<xsl:otherwise>
														<div>
															<u><xsl:value-of select="zahtev:Zahtev/zahtev:Trazilac/tipovi:Ime"/><xsl:value-of select="$space"/><xsl:value-of select="zahtev:Zahtev/zahtev:Trazilac/tipovi:Prezime"/></u>
														</div>
													</xsl:otherwise>
												</xsl:choose>
												<div class="opis">Trazilac informacije/Ime i prezime</div>
											</div>
											<div>
												<div>
													<u><xsl:value-of select="zahtev:Zahtev/zahtev:Trazilac/tipovi:Adresa/tipovi:Ulica"/><xsl:value-of select="$space"/><xsl:value-of select="zahtev:Zahtev/zahtev:Trazilac/tipovi:Adresa/tipovi:Ulicni_broj"/>,<xsl:value-of select="$space"/>
													<xsl:value-of select="zahtev:Zahtev/zahtev:Trazilac/tipovi:Adresa/tipovi:Mesto"/></u>
												</div>
												<div class="opis">adresa</div>
											</div>
											<div>
												<div>
													<u><xsl:value-of select="zahtev:Zahtev/zahtev:Trazilac/tipovi:Kontakt_podaci"/></u>
												</div>
												<div class="opis">drugi podaci za kontakt</div>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>