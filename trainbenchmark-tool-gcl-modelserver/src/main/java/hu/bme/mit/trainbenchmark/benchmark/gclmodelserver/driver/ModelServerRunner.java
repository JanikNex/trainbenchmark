package hu.bme.mit.trainbenchmark.benchmark.gclmodelserver.driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class ModelServerRunner {

	public static void start(String modelPath) {
		Path workspacePath = Path.of("../gcl");
		Path mmlCliPath = workspacePath.resolve("mml-cli.jar");
		Path modelServerPath = workspacePath.resolve("bin/model-server.jar");
		Path hipeNetworkPath = workspacePath.resolve("src-gen/railway/hipe/engine/hipe-network.xmi");
		Path mPath = Path.of(modelPath);
		Path workspaceModelDir = workspacePath.resolve("model");
		Path newModelPath = workspaceModelDir.resolve(mPath.getFileName());
		System.out.printf("[ModelServerRunner] Copy file from: %s%n", mPath);
		System.out.printf("[ModelServerRunner] Copy file to: %s%n", newModelPath);
		try {
			newModelPath.toFile().getParentFile().mkdirs();
			Files.copy(mPath, newModelPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		List<String> command = List.of("java", "-cp", mmlCliPath + File.pathSeparator + modelServerPath, "de.nexus.modelserver.ModelServer", workspacePath.toString(), newModelPath.toString(), hipeNetworkPath.toString());

		try {
			ProcessBuilder pb = new ProcessBuilder(command).redirectInput(ProcessBuilder.Redirect.INHERIT).redirectInput(ProcessBuilder.Redirect.INHERIT);
			Process process = pb.start();
			InputStreamReader processOutput = new InputStreamReader(process.getInputStream());
			BufferedReader processOutputReader = new BufferedReader(processOutput);
			String line;
			System.out.println("[ModelServerRunner] Waiting for completed ModelServer startup...");
			while (process.isAlive() && (line = processOutputReader.readLine()) != null) {
				System.out.println(line);
				if (line.startsWith("[gRPC Server] Server started")) {
					break;
				}
			}
			processOutputReader.close();
			processOutput.close();
			System.out.println("[ModelServerRunner] Recognized successful ModelServer start!");

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("[ModelServerRunner] Failed to start ModelServer");
			throw new RuntimeException(e);
		}
	}

	public static void cleanup() {
		Path modelDirPath = Path.of("../gcl/model");
		try {
			Files.walkFileTree(modelDirPath,
				new SimpleFileVisitor<>() {
					@Override
					public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
						Files.delete(dir);
						return FileVisitResult.CONTINUE;
					}

					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
						throws IOException {
						Files.delete(file);
						return FileVisitResult.CONTINUE;
					}
				});
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
