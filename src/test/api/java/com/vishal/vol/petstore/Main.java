package com.vishal.vol.petstore;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;
import static org.junit.platform.launcher.TagFilter.includeTags;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

public class Main {

	static SummaryGeneratingListener listener = new SummaryGeneratingListener();

	public static void main(String[] args) throws IOException {

		Properties props = new Properties();
		props.load(Files.newInputStream(Path.of("src", "test", "api", "resources", "test.properties")));

		System.setProperty("test.petstore.base-url", props.getProperty("test.petstore.base-url"));
		System.setProperty("test.petstore.mode", "ALL");

		LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
				.selectors(selectPackage("com.vishal.vol.petstore"))
				.filters(includeTags(props.getProperty("test.tags").split(","))).build();

		Launcher launcher = LauncherFactory.create();

		TestPlan testPlan = launcher.discover(request);

		launcher.registerTestExecutionListeners(listener);
		launcher.execute(request);

		TestExecutionSummary summary = listener.getSummary();
		summary.printTo(new PrintWriter(System.out));
	}


}