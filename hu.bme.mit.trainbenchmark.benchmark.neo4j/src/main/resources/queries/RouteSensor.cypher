MATCH (r:Route)-[:ROUTE_SWITCHPOSITION]->(swP:SwitchPosition)-[:SWITCHPOSITION_SWITCH]->(sw:Switch)-[:TRACKELEMENT_SENSOR]->(sen:Sensor)
WHERE NOT (r)-[:ROUTE_ROUTEDEFINITION]->(sen)
RETURN DISTINCT sen