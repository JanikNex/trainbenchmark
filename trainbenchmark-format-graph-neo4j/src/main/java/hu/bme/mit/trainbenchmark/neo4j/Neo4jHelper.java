package hu.bme.mit.trainbenchmark.neo4j;

import com.google.common.primitives.Ints;
import hu.bme.mit.trainbenchmark.neo4j.config.Neo4jDeployment;
import org.neo4j.configuration.GraphDatabaseSettings;
import org.neo4j.configuration.SettingImpl;
import org.neo4j.configuration.SettingValueParsers;
import org.neo4j.dbms.api.DatabaseManagementService;
import org.neo4j.dbms.api.DatabaseManagementServiceBuilder;
import org.neo4j.dbms.api.Neo4jDatabaseManagementServiceBuilder;
import org.neo4j.test.TestDatabaseManagementServiceBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Neo4jHelper {

	public static Neo4jDatabaseManagementServiceBuilder getBuilder(final Neo4jDeployment deployment, final File graphDatabaseDirectory) {
		return switch (deployment) {
			case EMBEDDED -> new DatabaseManagementServiceBuilder(graphDatabaseDirectory.toPath());
			case IN_MEMORY -> new TestDatabaseManagementServiceBuilder().impermanent();
		};
	}

	public static DatabaseManagementService startGraphDatabase(final Neo4jDeployment deployment, final File graphDatabaseDirectory) {
		File canonicalFile;
		Path exportPath;
		try {
			canonicalFile = graphDatabaseDirectory.getCanonicalFile();
			exportPath = canonicalFile.toPath().getParent().getParent().resolve("neo4j-export");
		} catch (IOException ex) {
			throw new RuntimeException("Could not convert to canonical file: " + graphDatabaseDirectory.toString());
		}

		Neo4jDatabaseManagementServiceBuilder builder = getBuilder(deployment, canonicalFile);

		builder.setConfig(GraphDatabaseSettings.procedure_unrestricted, List.of("apoc.*"));
		builder.setConfig(GraphDatabaseSettings.load_csv_file_url_root, exportPath);

		builder.setConfig(SettingImpl.newBuilder("internal.dbms.debug.trace_cursors", SettingValueParsers.BOOL, false).build(), true);

		return builder.build();
		/*try {
		 *//*Path pluginDirContainingApocJar = new File(
                ApocConfig.class.getProtectionDomain().getCodeSource().getLocation().toURI())
                .getParentFile().toPath();*//*

			Path pluginDirContainingApocJar = Path.of("/benchmark/apoc/");
			Path pluginTempDir = Files.createTempDirectory("neo4jPlugins");

			ApocConfig apocConfig = ApocConfig.apocConfig();
			System.out.println("======[APOCCONFIG]======");
			System.out.println("isNull: " + (apocConfig == null));
			System.out.println("apochJarPath: " + pluginDirContainingApocJar);
			System.out.println("pluginTempDir: " + pluginTempDir);
			System.out.println("======[APOCCONFIG]======");


			Files.find(pluginDirContainingApocJar, 1, (p, a) -> p.getFileName().toString().endsWith(".jar"))
				.forEach(p -> {
					try {
						Files.copy(p, pluginTempDir.resolve(p.getFileName()));
						System.out.println("[->] Copied jar: " + p.getFileName().toString());
					} catch (IOException e) {
						throw new UncheckedIOException(e);
					}
				});

			builder.setConfig(GraphDatabaseSettings.plugin_dir, pluginTempDir);
			builder.setConfig(GraphDatabaseSettings.procedure_unrestricted, List.of("apoc.*"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}*/

		/*assert deployment == Neo4jDeployment.EMBEDDED;

		Path canonicalPath;

		try {
			canonicalPath = graphDatabaseDirectory.getCanonicalFile().toPath();
		}catch (IOException ex){
			ex.printStackTrace();
			canonicalPath = graphDatabaseDirectory.toPath();
		}

		System.out.println("Creating Neo4J with workspace: "+canonicalPath.toString());

		Neo4jBuilder builder = Neo4jBuilders
			.newInProcessBuilder(canonicalPath)
			.withDisabledServer()
			.withConfig(GraphDatabaseSettings.load_csv_file_url_root,Path.of("/benchmark/models/"))
			.withProcedure(ExportCSV.class)
			.withProcedure(Graphs.class)
			.withProcedure(ExportGraphML.class)
			.withFunction(ExportCSV.class)
			.withFunction(Graphs.class)
			.withFunction(ExportGraphML.class);

		return builder.build().databaseManagementService();*/
	}

	public static int numberToInt(Number n) {
		if (n instanceof Integer) {
			return (Integer) n;
		} else if (n instanceof Long) {
			return Ints.checkedCast((Long) n);
		} else {
			throw new IllegalStateException("Length should be int or long");
		}
	}

}
