package pl.java.scalatech;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import static com.jayway.awaitility.Awaitility.*;
import static java.util.concurrent.TimeUnit.*;
import static org.hamcrest.Matchers.*;
public class FileUtils {

	public static void createNewFile(File sourceFolder, String fileName) throws IOException {
		new File(sourceFolder, fileName).createNewFile();
	}

	public static void assertThatFileExistsInFolder(File targetFolder) throws Exception {
		waitAtMost(5, SECONDS).until(targetFolderSize(targetFolder), is(equalTo(1)));
	}

	private static Callable<Integer> targetFolderSize(final File folder) {
		return () -> folder.list().length;
	}

}