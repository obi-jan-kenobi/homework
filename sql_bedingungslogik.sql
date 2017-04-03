SELECT name, email,
	CASE anrede
		WHEN 'Herr' THEN 'männlich'
		WHEN 'Frau' THEN 'weiblich'
		ELSE 'unbekannt'
	END AS geschlecht
FROM `raumbetreuer`;


SELECT seriennummer, bezeichnung,
	CASE COUNT(reparatur.rid)
		WHEN 0 THEN "keine Reparatur"
		WHEN 1 THEN "Normalbereich"
		WHEN 2 THEN "Normalbereich"
		ELSE "Fehleranfällig"
	END AS bemerkung
FROM `hardware`
INNER JOIN `reparatur` ON reparatur.fk_hardware = hardware.seriennummer GROUP BY seriennummer;



SELECT beschreibung, code,
	CASE COUNT(reparatur.fk_fehlercode)
		WHEN 0 THEN "Fehlercode nie aufgetreten"
		WHEN 1 THEN "Fehlercode selten"
		ELSE "Fehlercode häufig"
	END AS bemerkung
FROM `fehlerbeschreibung`
INNER JOIN reparatur ON reparatur.fk_fehlercode = fehlerbeschreibung.code GROUP BY beschreibung;


SELECT beschreibung, code,
	CASE COUNT(reparatur.fk_fehlercode)
		WHEN 0 THEN "Fehlercode nie aufgetreten"
		WHEN 1 THEN "Fehlercode selten"
		ELSE CONCAT("bei ", ROUND(COUNT(reparatur.fk_fehlercode) * 100 / (SELECT COUNT(*) FROM reparatur)), "% der Hardware")
	END AS bemerkung
FROM `fehlerbeschreibung`
INNER JOIN reparatur ON reparatur.fk_fehlercode = fehlerbeschreibung.code GROUP BY beschreibung;
