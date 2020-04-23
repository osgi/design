<?xml version="1.0" encoding="UTF-8"?>
<xs:stylesheet xmlns:xs="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	
	<xs:output method="html"/>

	<xs:template match="/">
		<html>
			<head>
				<title>DMT For <xs:value-of select="MgmtTree/Man"/> - <xs:value-of 
					select="MgmtTree/Mod"/></title>
			</head>
			<style>
				A,OL,LI,BODY		{ background-color:#FFFFFF; font-family: Helvetica, Arial, sans-serif; font-size: 10pt; font-weight: plain; }
				H1  		{ font-size: 14pt; font-weight: bold; font-family: Helvetica, Arial, sans-serif;}
				H2  		{ font-size: 12pt; font-weight: bold; font-family: Helvetica, Arial, sans-serif;}
				H3  		{ font-size: 10pt; font-weight: bold; font-family: Helvetica, Arial, sans-serif;}
				H4  		{ font-size: 10pt; font-weight: bold; font-family: Helvetica, Arial, sans-serif;}
				TD  		{ vertical-align:top; text-align:left; font-family: Helvetica, Arial, sans-serif; font-size: 10pt; font-weight: plain;}
				TH  		{ background-color: #FFFFDD; color:blue; vertical-align:top; text-align:left; font-family: Helvetica, Arial, sans-serif; font-size: 10pt; font-weight: plain; }
				EM  		{ font-weight: bold }
				DIV.body	{ width:600;margin-left:50; }
				P.footer	{ font-size:9pt; font-family: Helvetica, Arial, sans-serif;}
				A.navigate  { font-weight: bold }
				P   		{ font-size:10pt; font-weight:plain; font-size:10pt;  font-family: Helvetica, Arial, sans-serif;}
				PRE 		{ font-family: Courier }
				.comment    { color: red; }
				.treenode   { font-size: 12pt; font-weight: bold; color: blue; }
			</style>
				<body>
					<div style="width:780px;">
				<h1>
					<xs:value-of select="MgmtTree/Man"/>
				</h1>
				<h2>
					<xs:value-of select="MgmtTree/Mod"/>
				</h2>
					<center>
						<xs:apply-templates select="MgmtTree/Node" mode="tree"/>
					</center>
				<xs:apply-templates select="MgmtTree/Node"/>
				</div>
			</body>
		</html>
	</xs:template>


	<xs:template match="Node" mode="tree">
		<table>
			<tr>
				<td colspan="{count(Node)}">
						
					<center class="treenode">|<br/><a href="#{NodeName}"><xs:value-of select="NodeName"/></a> 
					<sup><xs:apply-templates select="DFProperties/Occurrence"/></sup></center>
				</td>
			</tr>
			<tr>
				<xs:for-each select="Node">
					<td>
						<xs:apply-templates select="." mode="tree"/>
					</td>
				</xs:for-each>
			</tr>
		</table>
	</xs:template>
		
		
	<xs:template match="One">1</xs:template>
	<xs:template match="ZeroOrOne">0,1</xs:template>
	<xs:template match="ZeroOrMore">0..*</xs:template>
	<xs:template match="OneOrMore">1..*</xs:template>
	<xs:template match="ZeroOrN">0..<xs:value-of select="."/></xs:template>
	<xs:template match="OneOrN">1..<xs:value-of select="."/></xs:template>
		

	<xs:template match="Node">
		<hr/>
		<a name="{NodeName}"/>
		<table>
			<tr>
				<th width="200px">
					<xs:value-of select="NodeName"/>
				</th>
				<td>
					<h3><xs:value-of select="DFProperties/DFTitle"/></h3>					
					<p><xs:copy-of select="DFProperties/Description"/></p>
					<xs:if test="comment">
					<p class="comment">Comments: <em><xs:value-of select="comment"/></em></p>
					</xs:if>
					<xs:apply-templates select="NodeName/spec"/>
					<table>
						<tr>
							<td width="300px"><em>AccessType</em></td>
							<td><b><xs:apply-templates select="DFProperties/AccessType/*"/></b></td>
						</tr>
						<tr>
							<td><em>Path</em></td>
							<td>
								<xs:for-each select="ancestor::Node">
									/<xs:value-of select="NodeName"/>									
								</xs:for-each>
								/<xs:value-of select="NodeName"/>									
							</td>
						</tr>
						<tr>
							<td><em>Parent</em></td>
							<td><xs:value-of select="parent::Node/NodeName"/></td>
						</tr>
						<tr>
							<td><em>Default Value</em></td>
							<td><xs:value-of select="DFProperties/DefaultValue"/></td>
						</tr>
						<tr>
							<td><em>Type</em></td>
							<td><xs:apply-templates select="DFProperties/DFType/*"/></td>
						</tr>
						<tr>
							<td><em>Format</em></td>
							<td><xs:apply-templates select="DFProperties/DFFormat"/></td>
						</tr>
						<tr>
							<td><em>Occurrence</em></td>
							<td><xs:apply-templates select="DFProperties/Occurrence/*"/></td>
						</tr>
						<tr>
							<td><em>Scope</em></td>
							<td><xs:apply-templates select="DFProperties/Scope"/></td>
						</tr>
						<xs:if test="DFProperties/ExternalSpec">
							<tr>
								<td colspan="2">
									<em>External Spec format</em><br/>
									<pre><xs:value-of select="DFProperties/ExternalSpec"/></pre>
								</td>	
							</tr>
						</xs:if>
					</table>
				</td>
			</tr>
		</table>
		<xs:apply-templates select="Node"/>
	</xs:template>

	<xs:template match="Permanent|Dynamic|Get|Replace|Add|Copy|Delete|b64|bool|chr|int|node|null|xml|Permanent|Dynamic">
		<xs:value-of select="name()"/><xs:text> </xs:text>
	</xs:template>


	<xs:template match="spec">
		<xs:if test=".">
			<p>[<xs:value-of select="."/>]</p>
		</xs:if>
	</xs:template>

	<xs:template match="MIME">
		<xs:value-of select="."/><xs:text> </xs:text>
	</xs:template>
	
</xs:stylesheet>