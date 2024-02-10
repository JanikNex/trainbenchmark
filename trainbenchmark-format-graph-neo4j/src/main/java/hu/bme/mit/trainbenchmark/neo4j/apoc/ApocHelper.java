package hu.bme.mit.trainbenchmark.neo4j.apoc;

import apoc.ApocConfig;
import org.neo4j.exceptions.KernelException;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.api.procedure.GlobalProcedures;
import org.neo4j.kernel.internal.GraphDatabaseAPI;

public class ApocHelper {

	// https://github.com/neo4j/apoc/issues/79#issuecomment-1367839152
	public static void registerProcedure(GraphDatabaseService db, Class<?>... procedures) throws KernelException {
		GlobalProcedures proceduresService = ((GraphDatabaseAPI) db).getDependencyResolver().resolveDependency(GlobalProcedures.class);
		for (Class<?> procedure : procedures) {
			proceduresService.registerProcedure(procedure);
			proceduresService.registerFunction(procedure);
		}
	}

	public static void updateApocConfig() {
		ApocConfig apocConfig = ApocConfig.apocConfig();
        if (apocConfig != null) {
            apocConfig.setProperty(ApocConfig.APOC_EXPORT_FILE_ENABLED,true);
            apocConfig.setProperty(ApocConfig.APOC_IMPORT_FILE_ENABLED,true);

            //System.out.println("[APOCCONFIG] importdir: "+apocConfig.getImportDir());
        } else {
            throw new RuntimeException("Could not update ApocConfig because ApocConfig.apocConfig() == null");
        }
    }

}
